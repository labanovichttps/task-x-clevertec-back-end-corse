package ru.clevertec.service;

import ru.clevertec.dto.CertificateDto;

import java.util.List;

public interface CertificateService {

    List<CertificateDto> getAllCertificates();

    CertificateDto getCertificateById(Long id);

    CertificateDto saveCertificate(CertificateDto tagDto);

    CertificateDto updateCertificate(Long id, CertificateDto tagDto);

    void removeCertificate(Long id);
}
