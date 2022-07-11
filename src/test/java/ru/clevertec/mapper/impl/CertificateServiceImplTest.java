package ru.clevertec.mapper.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.dto.CertificateDto;
import ru.clevertec.dto.TagDto;
import ru.clevertec.dto.UpdateCertificatePriceDto;
import ru.clevertec.entity.Certificate;
import ru.clevertec.exception.EntityNotFoundException;
import ru.clevertec.mapper.CertificateMapper;
import ru.clevertec.repository.CertificateRepository;
import ru.clevertec.service.impl.CertificateServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static ru.clevertec.utils.DataForTests.createCertificate;
import static ru.clevertec.utils.DataForTests.createCertificateDto;
import static ru.clevertec.utils.DataForTests.createTags;
import static ru.clevertec.utils.DataForTests.createTagsDto;

@ExtendWith(MockitoExtension.class)
class CertificateServiceImplTest {

    @Mock
    private CertificateMapper certificateMapper;

    @Mock
    private CertificateRepository certificateRepository;

    @InjectMocks
    private CertificateServiceImpl certificateService;
    private static final Long ID = 1L;
    private static final String NAME = "Certificate-14";
    private static final String DESCRIPTION = "Some description";
    private static final BigDecimal PRICE = BigDecimal.TEN;
    private static final Long DURATION = 365L;
    private static final LocalDateTime CREATE_DATE = LocalDateTime.of(2020, 4, 14, 10, 20);


    @Test
    void checkFindByIdReturnsNotNull() {
        Certificate certificate = createCertificate(ID, NAME, DESCRIPTION, PRICE, DURATION, CREATE_DATE,
                CREATE_DATE.plusDays(100), createTags());
        CertificateDto certificateDto = createCertificateDto(ID, NAME, DESCRIPTION, PRICE, DURATION, CREATE_DATE,
                CREATE_DATE.plusDays(100), createTagsDto());

        doReturn(Optional.of(certificate))
                .when(certificateRepository).findById(certificate.getId());
        doReturn(certificateDto)
                .when(certificateMapper).toCertificateDto(certificate);

        CertificateDto actual = certificateService.findById(certificate.getId());
        assertEquals(actual, certificateDto);
    }

    @Test
    void checkFindByIdThrowsEntityNotFoundException() {
        Certificate certificate = createCertificate(ID, NAME, DESCRIPTION, PRICE, DURATION, CREATE_DATE,
                CREATE_DATE.plusDays(100), createTags());

        doReturn(Optional.empty())
                .when(certificateRepository).findById(certificate.getId());

        assertThrows(EntityNotFoundException.class, () -> certificateService.findById(certificate.getId()));
    }

    @Test
    void checkFindByTagName() {
        Certificate certificate = createCertificate(ID, NAME, DESCRIPTION, PRICE, DURATION, CREATE_DATE,
                CREATE_DATE.plusDays(100), createTags());
        CertificateDto certificateDto = createCertificateDto(ID, NAME, DESCRIPTION, PRICE, DURATION, CREATE_DATE,
                CREATE_DATE.plusDays(100), createTagsDto());
        TagDto tagDto = certificateDto.getTags().get(0);
        List<Certificate> certificates = new ArrayList<>(Arrays.asList(
                certificate, certificate, certificate, certificate
        ));

        doReturn(certificates)
                .when(certificateRepository).findCertificatesBy(tagDto.getName());
        doReturn(certificateDto)
                .when(certificateMapper).toCertificateDto(certificate);

        List<CertificateDto> actual = certificateService.findByTagName(tagDto.getName());
        assertThat(actual).hasSize(4);
    }

    @Test
    void checkSave() {
        Certificate certificate = createCertificate(ID, NAME, DESCRIPTION, PRICE, DURATION, CREATE_DATE,
                CREATE_DATE.plusDays(100), createTags());
        CertificateDto certificateDto = createCertificateDto(ID, NAME, DESCRIPTION, PRICE, DURATION, CREATE_DATE,
                CREATE_DATE.plusDays(100), createTagsDto());

        doReturn(certificate)
                .when(certificateMapper).toCertificate(certificateDto);
        doReturn(certificate)
                .when(certificateRepository).save(certificate);
        doReturn(certificateDto)
                .when(certificateMapper).toCertificateDto(certificate);

        CertificateDto actual = certificateService.save(certificateDto);
        assertEquals(actual, certificateDto);
    }

    @Test
    void checkUpdate() {
        Certificate certificate = createCertificate(ID, NAME, DESCRIPTION, PRICE, DURATION, CREATE_DATE,
                CREATE_DATE.plusDays(100), createTags());
        CertificateDto certificateDto = createCertificateDto(null, "newTagName", "new tag description",
                BigDecimal.valueOf(123), null, LocalDateTime.of(2015, 5, 15, 20, 21),
                CREATE_DATE.plusDays(100), createTagsDto());
        Certificate updateCertificate = createCertificate(certificate.getId(), certificateDto.getName(), certificateDto.getDescription(),
                certificateDto.getPrice(), certificate.getDuration(), certificateDto.getCreateDate(),
                certificateDto.getLastUpdateDate(), certificate.getTags());
        CertificateDto updateCertificateDto = createCertificateDto(certificate.getId(), "newTagName", "new tag description",
                BigDecimal.valueOf(123), certificate.getDuration(), LocalDateTime.of(2015, 5, 15, 20, 21),
                CREATE_DATE.plusDays(100), createTagsDto());

        doReturn(Optional.of(certificate))
                .when(certificateRepository).findById(certificate.getId());
        doNothing()
                .when(certificateMapper).updateFromCertificateDto(certificateDto, certificate);
        doReturn(updateCertificate)
                .when(certificateRepository).saveAndFlush(certificate);
        doReturn(updateCertificateDto)
                .when(certificateMapper).toCertificateDto(updateCertificate);

        CertificateDto actual = certificateService.update(certificate.getId(), certificateDto);
        assertEquals(actual, updateCertificateDto);

    }

    @Test
    void checkUpdatePrice() {
        Certificate certificate = createCertificate(ID, NAME, DESCRIPTION, PRICE, DURATION, CREATE_DATE,
                CREATE_DATE.plusDays(100), createTags());
        UpdateCertificatePriceDto certificatePriceDto = UpdateCertificatePriceDto.builder()
                .price(BigDecimal.ONE)
                .build();
        Certificate updateCertificate = createCertificate(ID, NAME, DESCRIPTION, certificatePriceDto.getPrice(), DURATION, CREATE_DATE,
                CREATE_DATE.plusDays(100), createTags());
        CertificateDto updateCertificateDto = createCertificateDto(ID, NAME, DESCRIPTION, certificatePriceDto.getPrice(), DURATION, CREATE_DATE,
                CREATE_DATE.plusDays(100), createTagsDto());

        doReturn(Optional.of(certificate))
                .when(certificateRepository).findById(certificate.getId());
        doNothing()
                .when(certificateMapper).updateFromCertificatePriceDto(certificatePriceDto, certificate);
        doReturn(updateCertificate)
                .when(certificateRepository).saveAndFlush(certificate);
        doReturn(updateCertificateDto)
                .when(certificateMapper).toCertificateDto(updateCertificate);

        CertificateDto actual = certificateService.updatePrice(certificate.getId(), certificatePriceDto);
        assertThat(actual.getPrice()).isEqualTo(certificatePriceDto.getPrice());
    }

    @Test
    void checkRemoveAndDoesntThrowEntityNotFoundException() {
        Certificate certificate = createCertificate(ID, NAME, DESCRIPTION, PRICE, DURATION, CREATE_DATE,
                CREATE_DATE.plusDays(100), createTags());

        doReturn(Optional.of(certificate))
                .when(certificateRepository).findById(certificate.getId());
        doNothing()
                .when(certificateRepository).delete(certificate);

        assertDoesNotThrow(() -> certificateService.remove(certificate.getId()));

    }

}