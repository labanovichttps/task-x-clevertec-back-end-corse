package ru.clevertec.integration.service.impl;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.clevertec.dto.MakeOrderDto;
import ru.clevertec.dto.ReadOrderDto;
import ru.clevertec.exception.EntityNotFoundException;
import ru.clevertec.integration.BaseIntegrationTest;
import ru.clevertec.service.OrderService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RequiredArgsConstructor
class OrderServiceImplTest extends BaseIntegrationTest {

    private final OrderService orderService;

    @Test
    void findByIdAndIfIDIsCorrectReturnOrder() {
        Long existsOrderId = 1L;
        assertNotNull(orderService.findById(existsOrderId));
    }

    @Test
    void findByIdAndIfIDIsNotCorrectThrowEntityNotFoundException() {
        Long doesNotExistsOrderId = 420L;
        assertThrows(EntityNotFoundException.class, () -> orderService.findById(doesNotExistsOrderId));
    }

    @Test
    void makeOrder() {
        MakeOrderDto orderDto = MakeOrderDto.builder()
                .userId(1L)
                .certificateId(1L)
                .build();
        ReadOrderDto actualOrder = orderService.make(orderDto);
        assertNotNull(actualOrder);
    }
}