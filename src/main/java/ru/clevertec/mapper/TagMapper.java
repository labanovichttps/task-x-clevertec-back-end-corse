package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.dto.TagFilter;
import ru.clevertec.entity.Tag;
import ru.clevertec.dto.TagDto;

@Mapper(componentModel = "spring")
public interface TagMapper {

    TagDto tagToDto(Tag tag);

    @Mapping(target = "certificates", ignore = true)
    Tag toTag(TagDto tagDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "certificates", ignore = true)
    Tag toTag(TagFilter tagFilter);
}
