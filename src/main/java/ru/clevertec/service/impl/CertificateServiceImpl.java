package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.dto.CertificateDto;
import ru.clevertec.entiry.dto.CertificateFilter;
import ru.clevertec.entity.Certificate;
import ru.clevertec.exception.EntityNotFoundException;
import ru.clevertec.mapper.CertificateMapper;
import ru.clevertec.repository.CertificateRepository;
import ru.clevertec.service.CertificateService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CertificateServiceImpl implements CertificateService {

    private static final String CERTIFICATE_LABEL = "Certificate";
    private static final String ID_LABEL = "id";

    private final CertificateRepository certificateRepository;
    private final CertificateMapper mapper;

    public Page<CertificateDto> getAllCertificates(CertificateFilter certificateFilter, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        return certificateRepository.findAll(
                        Example.of(mapper.toCertificate(certificateFilter), matcher), pageable)
                .map(mapper::toCertificateDto);
    }

    @Override
    public CertificateDto getCertificateById(Long id) {
        return certificateRepository.findById(id)
                .map(mapper::toCertificateDto)
                .orElseThrow(() -> new EntityNotFoundException(CERTIFICATE_LABEL, ID_LABEL, id));
    }

    @Override
    public List<CertificateDto> getCertificatesByTagName(String tagName) {
        return certificateRepository.findCertificateBy(tagName).stream()
                .map(mapper::toCertificateDto)
                .collect(toList());
    }

    @Transactional
    @Override
    public CertificateDto saveCertificate(CertificateDto certificateDto) {
        Certificate certificate = mapper.toCertificate(certificateDto);
        Certificate saveCertificate = certificateRepository.save(certificate);
        return mapper.toCertificateDto(saveCertificate);
    }

    @Transactional
    @Override
    public CertificateDto updateCertificate(Long id, CertificateDto certificateDto) {
        return certificateRepository.findById(id)
                .map(certificate -> {
                    Certificate certificateForUpdate = updateCertificate(certificateDto, certificate);
                    Certificate saveCertificate = certificateRepository
                            .saveAndFlush(certificateForUpdate);
                    return mapper.toCertificateDto(saveCertificate);
                }).orElseThrow(() -> new EntityNotFoundException(CERTIFICATE_LABEL, ID_LABEL, id));
    }

    @Transactional
    @Override
    public CertificateDto updateCertificatePrice(Long id, CertificateDto certificateDto) {
        return certificateRepository.findById(id)
                .map(certificate -> {
                    certificate.setPrice(certificateDto.getPrice());
                    Certificate saveCertificate = certificateRepository
                            .saveAndFlush(certificate);
                    return mapper.toCertificateDto(saveCertificate);
                }).orElseThrow(() -> new EntityNotFoundException(CERTIFICATE_LABEL, ID_LABEL, id));
    }

    @Transactional
    @Override
    public void removeCertificate(Long id) {
        certificateRepository.findById(id)
                .map(certificate -> {
                    certificateRepository.delete(certificate);
                    certificateRepository.flush();
                    return certificate;
                })
                .orElseThrow(() -> new EntityNotFoundException(CERTIFICATE_LABEL, ID_LABEL, id));
    }

    private Certificate updateCertificate(CertificateDto certificateDto, Certificate certificate) {
        if (Objects.nonNull(certificateDto.getName())) {
            certificate.setName(certificateDto.getName());
        } else if (Objects.nonNull(certificateDto.getDescription())) {
            certificate.setDescription(certificateDto.getDescription());
        } else if (Objects.nonNull(certificateDto.getPrice())) {
            certificate.setPrice(certificateDto.getPrice());
        } else if (Objects.nonNull(certificateDto.getDuration())) {
            certificate.setDuration(certificateDto.getDuration());
        } else if (Objects.nonNull(certificateDto.getCreateDate())) {
            certificate.setCreateDate(certificateDto.getCreateDate());
        } else if (Objects.nonNull(certificateDto.getLastUpdateDate())) {
            certificate.setLastUpdateDate(certificateDto.getLastUpdateDate());
        }
        certificate.setLastUpdateDate(LocalDateTime.now());

        return certificate;
    }

}
