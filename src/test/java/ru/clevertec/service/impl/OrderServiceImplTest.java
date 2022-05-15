package ru.clevertec.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.entity.Certificate;
import ru.clevertec.entity.Order;
import ru.clevertec.entity.User;
import ru.clevertec.mapper.CertificateMapper;
import ru.clevertec.mapper.OrderMapper;
import ru.clevertec.mapper.UserMapper;
import ru.clevertec.repository.OrderRepository;
import ru.clevertec.service.CertificateService;
import ru.clevertec.service.UserService;
import ru.clevertec.utils.DataForTests;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static ru.clevertec.utils.DataForTests.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserService userService;

    @Mock
    private CertificateService certificateService;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private CertificateMapper certificateMapper;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private OrderServiceImpl orderService;



    @Test
    void findById() {
//        certificateService.findById()
    }

    @Test
    void make() {
    }



}