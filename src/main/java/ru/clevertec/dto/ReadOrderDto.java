package ru.clevertec.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class ReadOrderDto {

    @Positive
    private Long id;

    @Positive
    private BigDecimal totalPrice;
    private LocalDateTime orderDate;
}
