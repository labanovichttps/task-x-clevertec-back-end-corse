package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.dto.CertificateDto;
import ru.clevertec.dto.MakeOrderDto;
import ru.clevertec.dto.ReadOrderDto;
import ru.clevertec.dto.UserDto;
import ru.clevertec.entity.Order;
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

    @Override
    public Page<ReadOrderDto> find(Long userId, Pageable pageable) {
        return orderRepository.findAllByUserId(userId, pageable)
                .map(orderMapper::toReadOrderDto);
    }


    @Override
    @Transactional
    public ReadOrderDto makeOrder(MakeOrderDto orderDto) {
        UserDto userDto = userService.findById(orderDto.getUserId());
        CertificateDto certificate = certificateService.findById(orderDto.getCertificateId());

        Order unsavedOrder = Order.builder()
                .user(userMapper.toUser(userDto))
                .certificate(certificateMapper.toCertificate(certificate))
                .orderDate(LocalDateTime.now())
                .totalPrice(certificate.getPrice())
                .build();

        Order saveOrder = orderRepository.save(unsavedOrder);
        return orderMapper.toReadOrderDto(saveOrder);
    }
}
