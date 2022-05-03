package ru.clevertec.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class ReadOrderDto {

    private Long id;
    private BigDecimal totalPrice;
    private LocalDateTime orderDate;
}
