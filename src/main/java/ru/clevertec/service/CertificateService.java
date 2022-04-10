package ru.clevertec.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.clevertec.entity.Certificate;
import ru.clevertec.entity.dto.CertificateDto;
import ru.clevertec.mapper.CertificateMapper;
import ru.clevertec.repository.CertificateRepository;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class CertificateService {

    private final CertificateRepository certificateRepository;

    public List<CertificateDto> getAllCertificates() {
        return certificateRepository.findAll().stream()
                .map(CertificateMapper.INSTANCE::mapToCertificateDto)
                .collect(toList());
    }

}
