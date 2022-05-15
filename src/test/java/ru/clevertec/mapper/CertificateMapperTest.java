package ru.clevertec.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.entity.Certificate;
import ru.clevertec.entity.Tag;
import ru.clevertec.repository.TagRepository;
import ru.clevertec.service.impl.TagServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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