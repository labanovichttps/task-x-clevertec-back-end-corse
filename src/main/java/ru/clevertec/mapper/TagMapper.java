package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.clevertec.entity.Tag;
import ru.clevertec.entity.dto.TagDto;

@Mapper
public interface TagMapper {

    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    TagDto mapTagToDto(Tag tag);

    Tag mapToTag(TagDto tagDto);
}
