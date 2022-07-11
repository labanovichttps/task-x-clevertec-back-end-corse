package ru.clevertec.service;

import ru.clevertec.dto.MakeOrderDto;
import ru.clevertec.dto.ReadOrderDto;

public interface OrderService {

    ReadOrderDto findById(Long id);

    ReadOrderDto make(MakeOrderDto orderDto);
}
