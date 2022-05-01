package ru.clevertec.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class OrderDto {

    private Long id;
    private CertificateDto certificate;
    private LocalDateTime orderDate;
    private BigDecimal totalPrice;
}
