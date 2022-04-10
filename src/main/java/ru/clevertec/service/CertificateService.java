package ru.clevertec.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.entiry.Certificate;
import ru.clevertec.entiry.Tag;
import ru.clevertec.entiry.dto.CertificateDto;
import ru.clevertec.entiry.dto.TagDtoWithoutCertificates;
import ru.clevertec.repository.CertificateRepository;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
@Service
public class CertificateService {

    private final CertificateRepository certificateRepository;

    public List<CertificateDto> getAllCertificates() {
        return certificateRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(toList());
    }

    private CertificateDto convertEntityToDto(Certificate certificate) {
        return CertificateDto.builder()
                .id(certificate.getId())
                .name(certificate.getName())
                .createDate(certificate.getCreateDate())
                .tags(
                        certificate.getTags().stream()
                                .map(this::convertToSimpleDto)
                                .collect(toList())
                )
                .build();
    }

    private TagDtoWithoutCertificates convertToSimpleDto(Tag tag) {
        return TagDtoWithoutCertificates.builder()
                .id(tag.getId())
                .name(tag.getName())
                .build();
    }

}
