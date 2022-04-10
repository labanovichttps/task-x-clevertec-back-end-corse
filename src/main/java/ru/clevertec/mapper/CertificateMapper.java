package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.clevertec.entity.Certificate;
import ru.clevertec.entity.dto.CertificateDto;

@Mapper
public interface CertificateMapper {

    CertificateMapper INSTANCE = Mappers.getMapper(CertificateMapper.class);
    CertificateDto mapToCertificateDto(Certificate certificate);
}
