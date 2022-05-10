package ru.clevertec.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.clevertec.dto.CertificateDto;
import ru.clevertec.dto.CertificateFilter;
import ru.clevertec.dto.UpdateCertificatePriceDto;

import java.util.List;

public interface CertificateService {

    Page<CertificateDto> find(CertificateFilter certificateFilter, Pageable pageable);

    CertificateDto findById(Long id);

    List<CertificateDto> findByTagName(String tagName);

    CertificateDto save(CertificateDto certificateDto);

    CertificateDto update(Long id, CertificateDto certificateDto);

    CertificateDto updatePrice(Long id, UpdateCertificatePriceDto updateCertificatePriceDto);

    void remove(Long id);
}
