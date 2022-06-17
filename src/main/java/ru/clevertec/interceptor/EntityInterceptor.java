package ru.clevertec.interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import ru.clevertec.config.NodesConfig;
import ru.clevertec.service.HealthCheckService;
import ru.clevertec.wrapper.CustomHttpServletRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class EntityInterceptor implements HandlerInterceptor {

    private final NodesConfig nodesConfig;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final HealthCheckService healthCheckService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (request.getParameter("request_from_client") == null) {
            CustomHttpServletRequestWrapper requestWrapper = new CustomHttpServletRequestWrapper(request);
            List<Integer> ports = nodesConfig.getNodes().values()
                    .stream()
                    .flatMap(Collection::stream)
                    .collect(toList());
            if (HttpMethod.POST.toString().equals(request.getMethod())) {
                doPost(request, requestWrapper, ports);
            } else if (HttpMethod.PUT.toString().equals(request.getMethod())) {
                doPut(request, requestWrapper, ports);
            } else if (HttpMethod.DELETE.toString().equals(request.getMethod())) {
                doDelete(request, ports);
            } else if (HttpMethod.PATCH.toString().equals(request.getMethod())) {
                doPatch(request, requestWrapper, ports);
            } else {
                throw new HttpRequestMethodNotSupportedException("Method is unsupported.");
            }
        }
    }

    private void doPatch(HttpServletRequest request, CustomHttpServletRequestWrapper requestWrapper, List<Integer> ports) throws JsonProcessingException {
        String body = requestWrapper.getBody();
        Object objectForPatch = objectMapper.readValue(body, Object.class);
        ports.stream()
                .filter(port -> port != request.getLocalPort())
                .filter(healthCheckService::isAlive)
                .forEach(port -> CompletableFuture.runAsync(() ->
                        restTemplate.patchForObject(replaceURL(request, port), objectForPatch, Object.class)));
    }

    private void doDelete(HttpServletRequest request, List<Integer> ports) {
        ports.stream()
                .filter(port -> port != request.getLocalPort())
                .filter(healthCheckService::isAlive)
                .forEach(port -> CompletableFuture.runAsync(() ->
                        restTemplate.delete(replaceURL(request, port))));
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

    private void doPost(HttpServletRequest request, CustomHttpServletRequestWrapper requestWrapper, List<Integer> ports) throws JsonProcessingException {
        String body = requestWrapper.getBody();
        Object objectForPost = objectMapper.readValue(body, Object.class);
        ports.stream()
                .filter(port -> port != request.getLocalPort())
                .filter(healthCheckService::isAlive)
                .forEach(port -> CompletableFuture.runAsync(() ->
                        restTemplate.postForObject(replaceURL(request, port), objectForPost, Object.class)));
    }

    private String replaceURL(HttpServletRequest request, Integer port) {
        return request.getRequestURL().toString().replace(String.valueOf(request.getLocalPort()), String.valueOf(port))
               + "?request_from_client=false";
    }
}
