package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.clevertec.dto.CertificateDto;
import ru.clevertec.entity.Certificate;
import ru.clevertec.exception.EntityNotFoundException;
import ru.clevertec.mapper.CertificateMapper;
import ru.clevertec.repository.CertificateRepository;
import ru.clevertec.service.CertificateService;

import java.util.List;

import static java.util.stream.Collectors.toList;

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
                .orElseThrow(()-> new EntityNotFoundException("Certificate", "id", id, HttpStatus.NOT_FOUND));
    }

    @Override
    public CertificateDto saveCertificate(CertificateDto tagDto) {
        return null;
    }

    @Override
    public CertificateDto updateCertificate(CertificateDto tagDto) {
        Certificate certificate = certificateRepository.save(certificateMapper.toCertificate(tagDto));
        return certificateMapper.toCertificateDto(certificate);
    }

    @Override
    public void removeCertificate(Long id) {

    }

}
