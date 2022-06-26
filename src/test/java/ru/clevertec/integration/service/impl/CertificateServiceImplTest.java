package ru.clevertec.integration.service.impl;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.clevertec.dto.CertificateDto;
import ru.clevertec.dto.CertificateFilter;
import ru.clevertec.dto.TagDto;
import ru.clevertec.dto.UpdateCertificatePriceDto;
import ru.clevertec.exception.EntityNotFoundException;
import ru.clevertec.integration.BaseIntegrationTest;
import ru.clevertec.service.CertificateService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RequiredArgsConstructor
class CertificateServiceImplTest extends BaseIntegrationTest {

    private final CertificateService certificateService;
    private final Long DOES_NOT_EXISTS_CERTIFICATE_ID = 420L;
    private final String DOES_NOT_EXISTS_CERTIFICATE_NAME = "NEW_CERTIFICATE_NAME";
    private final Long EXISTS_CERTIFICATE_ID = 1L;

    @Test
    void find() {
        CertificateFilter certificateFilter = CertificateFilter.builder()
                .name("LL")
                .build();
        Page<CertificateDto> actualCertificates = certificateService.find(certificateFilter, PageRequest.of(1, 5));
        assertThat(actualCertificates.getTotalElements()).isEqualTo(2);
    }

    @Test
    void findByIdAndReturn() {
        CertificateDto actualCertificate = certificateService.findById(EXISTS_CERTIFICATE_ID);
        assertThat(actualCertificate).isNotNull();
    }

    @Test
    void findByIdAndThrowEntityNotFoundException() {
        assertThrows(EntityNotFoundException.class, () -> certificateService.findById(DOES_NOT_EXISTS_CERTIFICATE_ID));
    }

    @Test
    void findByTagNameAndReturnCertificate() {
        List<CertificateDto> tags = certificateService.findByTagName("SPORT");
        assertThat(tags).hasSize(2);
    }

    @Test
    void saveCertificate() {
        List<TagDto> tags = Arrays.asList(
                TagDto.builder().name("firstTag").build(),
                TagDto.builder().name("secondTag").build(),
                TagDto.builder().name("thirdTag").build()
        );
        CertificateDto certificateDto = CertificateDto.builder()
                .name(DOES_NOT_EXISTS_CERTIFICATE_NAME)
                .description("some description")
                .createDate(LocalDateTime.of(2010, 12, 12, 12, 12))
                .duration(365L)
                .price(BigDecimal.TEN)
                .tags(tags)
                .build();
        CertificateDto actualCertificate = certificateService.save(certificateDto);
        assertAll(
                () -> assertEquals(DOES_NOT_EXISTS_CERTIFICATE_NAME, actualCertificate.getName()),
                () -> assertEquals(365L, actualCertificate.getDuration()),
                () -> assertThat(actualCertificate.getTags()).hasSize(3)
        );
    }

    @Test
    void updateCertificate() {
        List<TagDto> tags = Arrays.asList(
                TagDto.builder().name("firstTag").build(),
                TagDto.builder().name("secondTag").build(),
                TagDto.builder().name("thirdTag").build()
        );
        CertificateDto certificateDtoForUpdate = CertificateDto.builder()
                .name(DOES_NOT_EXISTS_CERTIFICATE_NAME)
                .tags(tags)
                .build();
        CertificateDto actualCertificate = certificateService.update(EXISTS_CERTIFICATE_ID, certificateDtoForUpdate);
        assertAll(
                () -> assertEquals(EXISTS_CERTIFICATE_ID, actualCertificate.getId()),
                () -> assertEquals(certificateDtoForUpdate.getName(), actualCertificate.getName()),
                () -> assertThat(actualCertificate.getTags()).hasSize(tags.size())
        );
    }

    @Test
    void updateCertificateAndThrowEntityNotFoundExceptionIfCertificateNotFound() {
        List<TagDto> tags = Arrays.asList(
                new TagDto(1L, "firstTag"),
                new TagDto(2L, "secondTag"),
                new TagDto(3L, "thirdTag")
        );
        CertificateDto certificateDtoForUpdate = CertificateDto.builder()
                .name(DOES_NOT_EXISTS_CERTIFICATE_NAME)
                .tags(tags)
                .build();
        assertThrows(EntityNotFoundException.class,
                () -> certificateService.update(DOES_NOT_EXISTS_CERTIFICATE_ID, certificateDtoForUpdate));
    }

    @Test
    void updateCertificatePrice() {
        UpdateCertificatePriceDto priceDto = UpdateCertificatePriceDto.builder()
                .price(BigDecimal.valueOf(123))
                .build();
        CertificateDto actualCertificate = certificateService.updatePrice(EXISTS_CERTIFICATE_ID, priceDto);
        assertEquals(priceDto.getPrice(), actualCertificate.getPrice());
    }

    @Test
    void removeCertificateAndThrowEntityNotFoundException() {
        assertThrows(EntityNotFoundException.class, () -> certificateService.remove(DOES_NOT_EXISTS_CERTIFICATE_ID));
    }

    @Test
    void removeCertificateAndAndDoeNotThrow() {
        assertDoesNotThrow(() -> certificateService.remove(4L));
    }
}