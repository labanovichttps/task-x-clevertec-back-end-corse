package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.entity.Tag;
import ru.clevertec.dto.TagDto;

@Mapper(componentModel = "spring")
public interface TagMapper {

    TagDto tagToDto(Tag tag);

    Tag toTag(TagDto tagDto);

}
