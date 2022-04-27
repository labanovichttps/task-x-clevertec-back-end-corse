package ru.clevertec.entiry.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class CertificateFilter {

    String name;
    String description;
    BigDecimal price;
    Long duration;
}
