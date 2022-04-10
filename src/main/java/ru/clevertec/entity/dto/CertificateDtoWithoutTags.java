package ru.clevertec.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class CertificateDtoWithoutTags {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long duration;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
}
