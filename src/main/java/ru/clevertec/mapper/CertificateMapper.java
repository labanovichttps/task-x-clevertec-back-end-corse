package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.dto.CertificateFilter;
import ru.clevertec.entity.Certificate;
import ru.clevertec.dto.CertificateDto;

@Mapper(componentModel = "spring")
public interface CertificateMapper {

    CertificateDto toCertificateDto(Certificate certificate);

    Certificate toCertificate(CertificateDto certificateDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "lastUpdateDate", ignore = true)
    Certificate toCertificate(CertificateFilter certificateFilter);
}
