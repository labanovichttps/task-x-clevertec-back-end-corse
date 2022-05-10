package ru.clevertec.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.dto.TagDto;
import ru.clevertec.entity.Certificate;
import ru.clevertec.entity.Tag;
import ru.clevertec.repository.TagRepository;
import ru.clevertec.service.impl.TagServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CertificateMapperTest {

    @Mock
    private TagMapper tagMapper = Mappers.getMapper(TagMapper.class);
    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private TagServiceImpl tagService;

    private final CertificateMapper certificateMapper = Mappers.getMapper(CertificateMapper.class);

    @Test
    void toCertificateDto() {
        when(tagRepository.findById(1L))
                .thenReturn(Optional.of(new Tag(1L, "qwe")));
        TagDto tag = tagService.findById(1L);
        System.out.println(tag);
        assertThat(tag).isNotNull();
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


    private Certificate createCertificate() {
        return Certificate.builder()
                .id(1L)
                .name("WRITER-2020")
                .description("About how to write books.")
                .price(BigDecimal.valueOf(777.77))
                .duration(365L)
                .createDate(LocalDateTime.of(2004, 4, 18, 10, 20))
                .tags(createTags())
                .build();
    }

    private List<Tag> createTags() {

        //todo return list of tags
        return null;
    }
}