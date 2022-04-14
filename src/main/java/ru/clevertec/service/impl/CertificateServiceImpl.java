package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.dto.CertificateDto;
import ru.clevertec.entity.Certificate;
import ru.clevertec.exception.EntityNotFoundException;
import ru.clevertec.mapper.CertificateMapper;
import ru.clevertec.repository.CertificateRepository;
import ru.clevertec.service.CertificateService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository certificateRepository;
    private final CertificateMapper certificateMapper;

    public List<CertificateDto> getAllCertificates() {
        return certificateRepository.findAll().stream()
                .map(certificateMapper::toCertificateDto)
                .collect(toList());
    }

    @Override
    public CertificateDto getCertificateById(Long id) {
        return certificateRepository.findById(id)
                .map(certificateMapper::toCertificateDto)
                .orElseThrow(() -> new EntityNotFoundException("Certificate", "id", id, HttpStatus.NOT_FOUND));
    }

    @Transactional
    @Override
    public CertificateDto saveCertificate(CertificateDto certificateDto) {
        Certificate certificate = certificateMapper.toCertificate(certificateDto);
        certificateRepository.save(certificate);
        return certificateDto;
    }

    @Transactional
    @Override
    public CertificateDto updateCertificate(Long id, CertificateDto certificateDto) {
        return certificateRepository.findById(id)
                .map(certificate -> {
                    certificateDto.setId(id);
                    certificateRepository.saveAndFlush(certificateMapper.toCertificate(certificateDto));
                    return certificateDto;
                }).orElseThrow(() -> new EntityNotFoundException("Certificate", "id", id, HttpStatus.NOT_FOUND));
    }

    @Transactional
    @Override
    public boolean removeCertificate(Long id) {
        return certificateRepository.findById(id)
                .map(certificate -> {
                    certificateRepository.delete(certificate);
                    certificateRepository.flush();
                    return true;
                })
                .orElse(false);
    }

}
