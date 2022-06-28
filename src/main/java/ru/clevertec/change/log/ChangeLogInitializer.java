//package ru.clevertec.change.log;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.client.RestTemplate;
//import ru.clevertec.config.NodesConfig;
//import ru.clevertec.dto.ChangeLogDto;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static ru.clevertec.constants.ApplicationConstants.CHANGE_LOG_URL;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class ChangeLogInitializer {
//
//    private final NodesConfig nodesConfig;
//    private final RestTemplate restTemplate;
//
//    @Transactional
//    public void synchronizeNodes() {
////        Map<Integer, Long> allSequences = getAllSequences();
////        Long currenSequence = allSequences.get(nodesConfig.getCurrentNode());
////        Long maxSequence = getMaxSequence(allSequences);
////        Integer portWthMaxSequenceForSynchronize = getPortWthMaxSequenceForSynchronize(allSequences, maxSequence);
////        if (currenSequence.equals(maxSequence)) {
////            log.info("Node synchronized");
////        }
////        else {
////            while (currenSequence < maxSequence) {
////                currenSequence++;
////                System.out.println(restTemplate.getForObject(buildSequenceURL(portWthMaxSequenceForSynchronize) + "/" + currenSequence,
////                        ChangeLogDto.class));
////
////            }
////        }
//
//    }
//
//    private Map<Integer, Long> getAllSequences() {
//        List<Integer> nodes = nodesConfig.getNodesFromMap();
//        HashMap<Integer, Long> result = new HashMap<>();
//        for (Integer port : nodes) {
//            result.put(port, restTemplate.getForObject(buildSequenceURL(port) + "/sequence", Long.class));
//        }
//
//        return result;
//    }
//
//    private String buildSequenceURL(Integer newLocalPort) {
//        return String.format(CHANGE_LOG_URL, newLocalPort);
//    }
//
//    private Long getMaxSequence(Map<Integer, Long> sequences) {
//        return sequences.values().stream()
//                .max(Long::compareTo)
//                .get();
//    }
//
//    private Integer getPortWthMaxSequenceForSynchronize(Map<Integer, Long> sequences, Long maxSequence) {
//        Integer portForSynchronize = 0;
//        for (Map.Entry<Integer, Long> ports : sequences.entrySet()) {
//            if (ports.getValue().equals(maxSequence)) {
//                portForSynchronize = ports.getKey();
//                break;
//            }
//        }
//        return portForSynchronize;
//    }
//}
