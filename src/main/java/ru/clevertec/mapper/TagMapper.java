package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.clevertec.dto.TagDto;
import ru.clevertec.dto.TagFilter;
import ru.clevertec.entity.Tag;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TagMapper {

    TagDto toTagDto(Tag tag);

    Tag toTag(TagDto tagDto);

    @Mapping(target = "id", ignore = true)
    Tag filterToTag(TagFilter tagFilter);

    void updateTagFromTagDto(TagDto tagDto, @MappingTarget Tag tag);
}
