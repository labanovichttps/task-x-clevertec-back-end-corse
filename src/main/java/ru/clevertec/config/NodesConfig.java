package ru.clevertec.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Data
@Component
@ConfigurationProperties(prefix = "cluster")
public class NodesConfig {

    private Map<Integer, List<Integer>> nodes;

    public List<Integer> getNodesFromMap() {
        return nodes.values().stream()
                .flatMap(Collection::stream)
                .collect(toList());
    }

}
