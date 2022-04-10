package ru.clevertec.entiry.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class CertificateDto {

    private Long id;
    private String name;
    private LocalDateTime createDate;
    private List<TagDtoWithoutCertificates> tags;
}
