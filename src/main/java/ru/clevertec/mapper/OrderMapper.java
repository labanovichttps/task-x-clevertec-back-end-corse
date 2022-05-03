package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.dto.ReadOrderDto;
import ru.clevertec.entity.Order;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {

    public abstract ReadOrderDto toReadOrderDto(Order order);
}
