package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.dto.CertificateDto;
import ru.clevertec.dto.CertificateFilter;
import ru.clevertec.dto.UpdateCertificatePriceDto;
import ru.clevertec.entity.Certificate;
import ru.clevertec.exception.EntityNotFoundException;
import ru.clevertec.mapper.CertificateMapper;
import ru.clevertec.repository.CertificateRepository;
import ru.clevertec.service.CertificateService;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CertificateServiceImpl implements CertificateService {

    private static final String CERTIFICATE_LABEL = "Certificate";
    private static final String ID_LABEL = "id";
    private final CertificateRepository certificateRepository;
    private final CertificateMapper certificateMapper;

    @Override
    public Page<CertificateDto> find(CertificateFilter filter, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withMatcher("description", match -> match.contains().ignoreCase())
                .withMatcher("name", match -> match.contains().contains().ignoreCase());
        return certificateRepository.findAll(
                        Example.of(certificateMapper.filterToCertificate(filter), matcher), pageable)
                .map(certificateMapper::toCertificateDto);
    }

    @Override
    public CertificateDto findById(Long id) {
        return certificateRepository.findById(id)
                .map(certificateMapper::toCertificateDto)
                .orElseThrow(() -> new EntityNotFoundException(CERTIFICATE_LABEL, ID_LABEL, id));
    }

    @Override
    public List<CertificateDto> findByTagName(String tagName) {
        return certificateRepository.findCertificateBy(tagName).stream()
                .map(certificateMapper::toCertificateDto)
                .collect(toList());
    }

    @Transactional
    @Override
    public CertificateDto save(CertificateDto certificateDto) {
        certificateDto.setCreateDate(LocalDateTime.now());
        Certificate certificate = certificateMapper.toCertificate(certificateDto);
        Certificate saveCertificate = certificateRepository.save(certificate);
        return certificateMapper.toCertificateDto(saveCertificate);
    }

    @Transactional
    @Override
    public CertificateDto update(Long id, CertificateDto certificateDto) {
        return certificateRepository.findById(id)
                .map(certificate -> {
                    certificateMapper.updateFromCertificateDto(certificateDto, certificate);
                    Certificate savedCertificate = certificateRepository
                            .saveAndFlush(certificate);
                    return certificateMapper.toCertificateDto(savedCertificate);
                }).orElseThrow(() -> new EntityNotFoundException(CERTIFICATE_LABEL, ID_LABEL, id));
    }

    @Transactional
    @Override
    public CertificateDto updatePrice(Long id, UpdateCertificatePriceDto certificatePriceDto) {
        return certificateRepository.findById(id)
                .map(certificate -> {
                    certificateMapper.updateFromCertificatePriceDto(certificatePriceDto, certificate);
                    Certificate saveCertificate = certificateRepository
                            .saveAndFlush(certificate);
                    return certificateMapper.toCertificateDto(saveCertificate);
                }).orElseThrow(() -> new EntityNotFoundException(CERTIFICATE_LABEL, ID_LABEL, id));
    }

    @Transactional
    @Override
    public void remove(Long id) {
        certificateRepository.findById(id)
                .map(certificate -> {
                    certificateRepository.delete(certificate);
                    certificateRepository.flush();
                    return certificate;
                })
                .orElseThrow(() -> new EntityNotFoundException(CERTIFICATE_LABEL, ID_LABEL, id));
    }

}
