package ru.clevertec.interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import ru.clevertec.config.NodesConfig;
import ru.clevertec.dto.ChangeLogDto;
import ru.clevertec.event.entity.EntityEvent;
import ru.clevertec.service.HealthCheckService;
import ru.clevertec.wrapper.CustomHttpServletRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
@RequiredArgsConstructor
@Transactional
public class EntityInterceptor implements HandlerInterceptor {

    private final NodesConfig nodesConfig;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final HealthCheckService healthCheckService;
    private final ApplicationEventPublisher publisher;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (request.getParameter("request_from_client") == null) {
            CustomHttpServletRequestWrapper requestWrapper = new CustomHttpServletRequestWrapper(request);
            List<Integer> allPorts = nodesConfig.getNodesFromMap();
            if (HttpMethod.GET.toString().equals(request.getMethod())) {
                log.info("Get for {}", request.getLocalPort());
            } else if (HttpMethod.POST.toString().equals(request.getMethod())) {
                doPost(request, requestWrapper, allPorts);
            } else if (HttpMethod.PUT.toString().equals(request.getMethod())) {
                doPut(request, requestWrapper, allPorts);
            } else if (HttpMethod.DELETE.toString().equals(request.getMethod())) {
                doDelete(request, allPorts);
            } else if (HttpMethod.PATCH.toString().equals(request.getMethod())) {
                doPut(request, requestWrapper, allPorts);
            } else {
                throw new HttpRequestMethodNotSupportedException("Method is unsupported.");
            }
        }
    }

    private void doPost(HttpServletRequest request, CustomHttpServletRequestWrapper requestWrapper, List<Integer> ports) throws JsonProcessingException {
        String body = requestWrapper.getBody();
        Object objectForPost = objectMapper.readValue(body, Object.class);
        ports.stream()
                .filter(port -> port != request.getLocalPort())
                .filter(healthCheckService::isAlive)
                .forEach(port -> CompletableFuture.runAsync(() ->
                        restTemplate.postForObject(replaceURL(request, port), objectForPost, Object.class)));

    }

    private void doPut(HttpServletRequest request, CustomHttpServletRequestWrapper requestWrapper, List<Integer> ports) throws JsonProcessingException {
        String body = requestWrapper.getBody();
        Object objectForPut = objectMapper.readValue(body, Object.class);
        ports.stream()
                .filter(port -> port != request.getLocalPort())
                .filter(healthCheckService::isAlive)
                .forEach(port -> CompletableFuture.runAsync(() ->
                        restTemplate.put(replaceURL(request, port), objectForPut, Object.class)));
    }

    private void doDelete(HttpServletRequest request, List<Integer> ports) {
        ports.stream()
                .filter(port -> port != request.getLocalPort())
                .filter(healthCheckService::isAlive)
                .forEach(port -> CompletableFuture.runAsync(() ->
                        restTemplate.delete(replaceURL(request, port))));
    }

    private String replaceURL(HttpServletRequest request, Integer port) {
        return request.getRequestURL().toString().replace(String.valueOf(request.getLocalPort()), String.valueOf(port))
               + "?request_from_client=false";
    }
}
