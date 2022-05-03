package ru.clevertec.mapper;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.clevertec.dto.CertificateDto;
import ru.clevertec.entity.Certificate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
@SpringBootTest
class CertificateMapperTest {

    private final CertificateMapper certificateMapper;

    @Test
    void updateFromCertificateDto() {
        Certificate certificate = Certificate.builder()
                .id(1L)
                .name("some")
                .description("aaa")
                .price(BigDecimal.TEN)
                .duration(123L)
                .createDate(LocalDateTime.now())
                .lastUpdateDate(LocalDateTime.now())
                .build();
        CertificateDto certificateDto = CertificateDto.builder()
                .name("new")
                .duration(420L)
                .build();
        certificateMapper.updateFromCertificateDto(certificateDto, certificate);
        System.out.println(certificate);
    }
}