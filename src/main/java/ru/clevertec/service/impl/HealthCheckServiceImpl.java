package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.clevertec.config.NodesConfig;
import ru.clevertec.service.HealthCheckService;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static ru.clevertec.constants.ApplicationConstants.HEALTH_CHECK_URL;

@Service
@Slf4j
@RequiredArgsConstructor
public class HealthCheckServiceImpl implements HealthCheckService {

    private final RestTemplate restTemplate;
    private final NodesConfig nodesConfig;

    @Override
    @Scheduled(cron = "0 * * * * ?")
    public void check() {
        List<Integer> nodes = nodesConfig.getNodes().values().stream()
                .flatMap(Collection::stream)
                .collect(toList());
        nodes.forEach(port -> {
            try {
                HttpStatus status = restTemplate.getForObject(replaceUrl(port), HttpStatus.class);
                if (HttpStatus.OK.equals(status)) {
                    log.info("Port {} has status {} and alive))))", port, status);
                } else {
                    log.error("Port {} fucked up(((( has status{}", port, status);
                }
            } catch (Exception e) {
                log.error("Port {} fucked up((((", port);
            }
        });
    }

    @Override
    public boolean isAlive(int port) {
        try {
            HttpStatus status = restTemplate.getForObject(replaceUrl(port), HttpStatus.class);
            return HttpStatus.OK.equals(status);
        } catch (Exception e) {
            return false;
        }
    }

    private String replaceUrl(int port) {
        return String.format(HEALTH_CHECK_URL, port, port);
    }
}
