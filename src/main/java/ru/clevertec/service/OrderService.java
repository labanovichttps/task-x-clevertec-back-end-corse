package ru.clevertec.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.clevertec.dto.MakeOrderDto;
import ru.clevertec.dto.ReadOrderDto;

import java.util.Optional;

public interface OrderService {

    Page<ReadOrderDto> find(Long userId, Pageable pageable);

    ReadOrderDto findById(Long id);

    ReadOrderDto makeOrder(MakeOrderDto orderDto);
}
