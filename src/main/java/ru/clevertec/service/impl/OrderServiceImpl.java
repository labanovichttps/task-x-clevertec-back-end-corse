package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.dto.CertificateDto;
import ru.clevertec.dto.OrderDto;
import ru.clevertec.dto.UserDto;
import ru.clevertec.repository.OrderRepository;
import ru.clevertec.service.OrderService;


@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public OrderDto createOrder(UserDto userDto, CertificateDto certificateDto) {
        //todo: do method to create an order

        return null;
    }
}
