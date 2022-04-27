package ru.clevertec.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.clevertec.dto.CertificateDto;
import ru.clevertec.entiry.dto.CertificateFilter;

import java.util.List;

public interface CertificateService {

    Page<CertificateDto> getAllCertificates(CertificateFilter certificateFilter, Pageable pageable);

    CertificateDto getCertificateById(Long id);

    List<CertificateDto> getCertificatesByTagName(String tagName);

    CertificateDto saveCertificate(CertificateDto certificateDto);

    CertificateDto updateCertificate(Long id, CertificateDto certificateDto);

    CertificateDto updateCertificatePrice(Long id, CertificateDto certificateDto);

    void removeCertificate(Long id);
}
