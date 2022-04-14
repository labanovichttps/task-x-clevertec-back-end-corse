package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.entity.Certificate;
import ru.clevertec.dto.CertificateDto;

@Mapper(componentModel = "spring")
public interface CertificateMapper {

    CertificateDto toCertificateDto(Certificate certificate);

    Certificate toCertificate(CertificateDto certificateDto);
}
