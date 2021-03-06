package ru.clevertec.mapper.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.dto.CertificateDto;
import ru.clevertec.dto.MakeOrderDto;
import ru.clevertec.dto.ReadOrderDto;
import ru.clevertec.dto.ReadUserDto;
import ru.clevertec.entity.Certificate;
import ru.clevertec.entity.Order;
import ru.clevertec.entity.User;
import ru.clevertec.mapper.CertificateMapper;
import ru.clevertec.mapper.OrderMapper;
import ru.clevertec.mapper.UserMapper;
import ru.clevertec.repository.OrderRepository;
import ru.clevertec.service.CertificateService;
import ru.clevertec.service.UserService;
import ru.clevertec.service.impl.OrderServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static ru.clevertec.utils.DataForTests.createCertificate;
import static ru.clevertec.utils.DataForTests.createOrder;
import static ru.clevertec.utils.DataForTests.createReadOderDto;
import static ru.clevertec.utils.DataForTests.createReadUserDto;
import static ru.clevertec.utils.DataForTests.createUser;

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

    private static final Long ID = 1L;

    @Test
    void findById() {
        Order order = createOrder(ID, null, null, LocalDateTime.now(), BigDecimal.valueOf(123));
        ReadOrderDto readOrderDto = createReadOderDto(order.getId(), order.getOrderDate(), order.getTotalPrice());

        doReturn(Optional.of(order))
                .when(orderRepository).findById(order.getId());
        doReturn(readOrderDto)
                .when(orderMapper).toReadOrderDto(order);

        ReadOrderDto actual = orderService.findById(order.getId());
        Assertions.assertEquals(actual, readOrderDto);

    }

}