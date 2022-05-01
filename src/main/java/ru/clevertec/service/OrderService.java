package ru.clevertec.service;

import ru.clevertec.dto.CertificateDto;
import ru.clevertec.dto.OrderDto;
import ru.clevertec.dto.UserDto;

public interface OrderService {

    OrderDto createOrder(UserDto userDto, CertificateDto certificateDto);
}
