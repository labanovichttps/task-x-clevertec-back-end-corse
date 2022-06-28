package ru.clevertec.event.entity;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.clevertec.change.log.ChangeLogInitializer;
import ru.clevertec.config.NodesConfig;
import ru.clevertec.dto.TagDto;
import ru.clevertec.exception.EntityNotFoundException;
import ru.clevertec.service.ChangeLogService;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static ru.clevertec.constants.ApplicationConstants.CHANGE_LOG_URL;

@Component
@RequiredArgsConstructor
public class EntityListener {

    private final ChangeLogService changeLogService;
    private final NodesConfig nodesConfig;
    private final RestTemplate restTemplate;

    @EventListener
    public void acceptEntity(EntityEvent entityEvent) {
        changeLogService.save(entityEvent.getChangeLogDto());
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
    }

    @SneakyThrows
    private Long getMaxChangeLogSequence() {
        List<Integer> nodes = nodesConfig.getNodesFromMap();
        return nodes.stream()
                .map(port ->
                        restTemplate.getForObject(buildSequenceURL(port), Long.class))
                .filter(Objects::nonNull)
                .max(Long::compareTo)
                .orElseThrow(() -> new EntityNotFoundException("sequence not found"));
    }


    private String buildSequenceURL(Integer newLocalPort) {
        return String.format(CHANGE_LOG_URL, newLocalPort);
    }
}
