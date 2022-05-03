package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.clevertec.dto.TagFilter;
import ru.clevertec.entity.Tag;
import ru.clevertec.dto.TagDto;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TagMapper {

    TagDto tagToDto(Tag tag);

    Tag toTag(TagDto tagDto);

    @Mapping(target = "id", ignore = true)
    Tag toTag(TagFilter tagFilter);

    void updateTagFromTagDto(TagDto tagDto, @MappingTarget Tag tag);
}
