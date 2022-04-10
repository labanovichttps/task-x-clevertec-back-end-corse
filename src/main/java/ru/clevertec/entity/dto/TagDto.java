package ru.clevertec.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TagDto {

    private Long id;
    private String name;
    private List<CertificateDtoWithoutTags> certificates;
}
