package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import ru.clevertec.dto.CertificateDto;
import ru.clevertec.dto.CertificateFilter;
import ru.clevertec.dto.TagDto;
import ru.clevertec.entity.Certificate;
import ru.clevertec.entity.Tag;
import ru.clevertec.service.TagService;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class CertificateMapper {

    @Autowired
    private TagService tagService;

    @Autowired
    private TagMapper tagMapper;

    public abstract CertificateDto toCertificateDto(Certificate certificate);

    public abstract Certificate toCertificate(CertificateDto certificateDto);

    @Mapping(target = "tags", qualifiedByName = "mapListTags")
    public abstract void updateFromCertificateDto(CertificateDto certificateDto, @MappingTarget Certificate certificate);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "duration", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "lastUpdateDate", ignore = true)
    public abstract Certificate filterToCertificate(CertificateFilter certificateFilter);

    @Named(value = "mapListTags")
    public List<Tag> mapListTags(List<TagDto> tagsDto) {
        if (Objects.nonNull(tagsDto)) {
            return tagsDto.stream()
                    .map(tagService::findByNameOrSave)
                    .map(tagMapper::toTag)
                    .collect(toList());
        }

        return null;
    }

}
