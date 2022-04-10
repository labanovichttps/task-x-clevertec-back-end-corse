package ru.clevertec.entity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TagDtoWithoutCertificates {

    private Long id;
    private String name;
}
