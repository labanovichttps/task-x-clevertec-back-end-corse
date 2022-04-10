package ru.clevertec.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.entiry.Certificate;
import ru.clevertec.entiry.Tag;
import ru.clevertec.entiry.dto.CertificateDto;
import ru.clevertec.entiry.dto.CertificateDtoWithoutTags;
import ru.clevertec.entiry.dto.TagDto;
import ru.clevertec.entiry.dto.TagDtoWithoutCertificates;
import ru.clevertec.repository.TagRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
@Service
public class TagService {

    private final TagRepository tagRepository;

    public List<TagDto> findAllTags() {
        return tagRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(toList());
    }

    private TagDto convertEntityToDto(Tag tag) {
        return TagDto.builder()
                .id(tag.getId())
                .name(tag.getName())
                .certificates(
                        tag.getCertificates().stream()
                                .map(this::convertToSimpleDto)
                                .collect(toList())
                )
                .build();
    }

    private CertificateDtoWithoutTags convertToSimpleDto(Certificate certificate) {
        return CertificateDtoWithoutTags.builder()
                .id(certificate.getId())
                .name(certificate.getName())
                .createDate(certificate.getCreateDate())
                .build();
    }
}
