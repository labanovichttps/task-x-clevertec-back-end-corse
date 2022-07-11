package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.dto.SequenceDto;
import ru.clevertec.repository.OrderRepository;
import ru.clevertec.service.OrderSequenceService;

@RequiredArgsConstructor
@Service
public class OrderSequenceServiceImpl implements OrderSequenceService {

    private final OrderRepository orderRepository;

    @Override
    public void setSequence(SequenceDto sequence) {
        orderRepository.setSequence(sequence.getValue());
    }

    @Override
    public Long getSequence() {
        return orderRepository.getSequence()
                .orElse(0L);
    }
}

