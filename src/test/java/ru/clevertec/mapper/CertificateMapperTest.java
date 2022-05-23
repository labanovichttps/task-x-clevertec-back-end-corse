package ru.clevertec.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.service.impl.TagServiceImpl;

@ExtendWith(MockitoExtension.class)
class CertificateMapperTest {

    @Mock
    private TagMapper tagMapper = Mappers.getMapper(TagMapper.class);

    @Mock
    private TagServiceImpl tagService;

    private final CertificateMapper certificateMapper = Mappers.getMapper(CertificateMapper.class);

    @Test
    void toCertificateDto() {

    }

    @Test
    void toCertificate() {
    }

    @Test
    void updateFromCertificateDto() {
    }

    @Test
    void filterToCertificate() {
    }

    @Test
    void updateFromCertificatePriceDto() {
    }

    @Test
    void mapListTags() {
    }

}