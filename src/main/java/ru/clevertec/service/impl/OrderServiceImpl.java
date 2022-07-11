package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.dto.CertificateDto;
import ru.clevertec.dto.MakeOrderDto;
import ru.clevertec.dto.ReadOrderDto;
import ru.clevertec.dto.ReadUserDto;
import ru.clevertec.entity.Order;
import ru.clevertec.exception.EntityNotFoundException;
import ru.clevertec.mapper.CertificateMapper;
import ru.clevertec.mapper.OrderMapper;
import ru.clevertec.mapper.UserMapper;
import ru.clevertec.repository.OrderRepository;
import ru.clevertec.service.CertificateService;
import ru.clevertec.service.OrderService;
import ru.clevertec.service.UserService;

import java.time.LocalDateTime;


@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final CertificateService certificateService;
    private final OrderMapper orderMapper;
    private final CertificateMapper certificateMapper;
    private final UserMapper userMapper;

    private static final String ORDER_LABEL = "order";
    private static final String ID_LABEL = "id";

    @Override
    public ReadOrderDto findById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::toReadOrderDto)
                .orElseThrow(() -> new EntityNotFoundException(ORDER_LABEL, ID_LABEL, id));
    }

    @Override
    @Transactional
    public ReadOrderDto make(MakeOrderDto orderDto) {
        ReadUserDto userDto = userService.findById(orderDto.getUserId());
        CertificateDto certificate = certificateService.findById(orderDto.getCertificateId());

        Order unsavedOrder = Order.builder()
                .user(userMapper.readUserDtoToUser(userDto))
                .certificate(certificateMapper.toCertificate(certificate))
                .orderDate(LocalDateTime.now())
                .totalPrice(certificate.getPrice())
                .build();

        Order saveOrder = orderRepository.saveAndFlush(unsavedOrder);
        return orderMapper.toReadOrderDto(saveOrder);
    }
}

