package ru.clevertec.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import ru.clevertec.config.NodesConfig;
import ru.clevertec.dto.MakeOrderDto;
import ru.clevertec.dto.ReadOrderDto;
import ru.clevertec.dto.SequenceDto;
import ru.clevertec.exception.EntityNotFoundException;
import ru.clevertec.wrapper.CustomHttpServletRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static ru.clevertec.constants.ApplicationConstants.REQUEST_FROM_CLIENT;
import static ru.clevertec.constants.ApplicationConstants.SEQUENCE_URL;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderInterceptor implements HandlerInterceptor {

    private final NodesConfig nodesConfig;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    @Override
    @Transactional
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getParameter(REQUEST_FROM_CLIENT) == null) {
            if (HttpMethod.POST.toString().equals(request.getMethod())) {
                doPost(request, response);
                return false;
            } else if (HttpMethod.GET.toString().equals(request.getMethod())){
//                getOrderIdFromURL()
               log.warn(request.getRequestURI());
            }
        }
        return true;
    }

    private void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CustomHttpServletRequestWrapper requestWrapper = new CustomHttpServletRequestWrapper(request);
        Long maxOrderSequence = getMaxOrderSequence();
        long portIndex = getPortIndex(maxOrderSequence);
        Integer mainPort = getMainPort(portIndex);
        String orderBody = requestWrapper.getBody();
        MakeOrderDto makeOrderDto = objectMapper.readValue(orderBody, MakeOrderDto.class);
        increaseSequenceInNextNode(portIndex, maxOrderSequence);
        ReadOrderDto entity = nodesConfig.getNodes().get(mainPort).stream()
                .map(port -> CompletableFuture.supplyAsync(() ->
                        restTemplate.postForObject(replaceURL(request, port), makeOrderDto, ReadOrderDto.class)))
                .map(CompletableFuture::join)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("entity not found"));
        writeResponse(response, entity);
    }

    private void writeResponse(HttpServletResponse response, ReadOrderDto entity) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(entity));
    }

    private long getPortIndex(Long maxOrderSequence) {
        return (maxOrderSequence + 1) % nodesConfig.getNodes().size();
    }

    private void increaseSequenceInNextNode(Long currentPortIndex, Long maxOrderSequence) {
        Integer nextMainPort = getMainPort(getPortIndex(currentPortIndex));
        SequenceDto sequenceDto = new SequenceDto(maxOrderSequence + 1);
        nodesConfig.getNodes().get(nextMainPort)
                .forEach(port -> CompletableFuture.runAsync(() -> restTemplate.postForObject(buildSequenceURL(port), sequenceDto, Object.class)));
    }

    private Integer getMainPort(long portIndex) {
        return nodesConfig.getNodes().keySet().stream()
                .filter(node -> node % 10 == portIndex)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Main port not defined"));
    }

    private Long getMaxOrderSequence() {
        List<Integer> nodes = nodesConfig.getNodes().values()
                .stream().flatMap(Collection::stream)
                .collect(Collectors.toList());
        return nodes.stream()
                .map(port -> CompletableFuture.supplyAsync(() ->
                        restTemplate.getForObject(buildSequenceURL(port), Long.class)))
                .map(CompletableFuture::join)
                .max(Long::compareTo)
                .orElseThrow(() -> new EntityNotFoundException("sequence not found"));
    }

    private String buildSequenceURL(Integer newLocalPort) {
        return String.format(SEQUENCE_URL, newLocalPort);
    }

    private String replaceURL(HttpServletRequest request, Integer port) {
        return request.getRequestURL().toString().replace(String.valueOf(request.getLocalPort()), String.valueOf(port))
               + "?" + REQUEST_FROM_CLIENT + "=false";
    }
}
