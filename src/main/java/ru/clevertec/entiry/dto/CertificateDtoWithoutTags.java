package ru.clevertec.entiry.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CertificateDtoWithoutTags {

    private Long id;
    private String name;
    private LocalDateTime createDate;
}
