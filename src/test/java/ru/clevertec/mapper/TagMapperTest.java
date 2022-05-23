package ru.clevertec.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.clevertec.dto.TagDto;
import ru.clevertec.dto.TagFilter;
import ru.clevertec.entity.Tag;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TagMapperTest {

    private final TagMapper tagMapper = Mappers.getMapper(TagMapper.class);
    private final Tag tag = Tag.builder()
            .id(1L)
            .name("IT")
            .build();
    private final TagDto tagDto = TagDto.builder()
            .id(1L)
            .name("IT")
            .build();

    @Test
    void checkTagToDto() {
        TagDto actualDto = tagMapper.toTagDto(tag);
        assertEquals(actualDto, tagDto);
    }

    @Test
    void checkTagDtoToTag() {
        TagDto actualTag = tagMapper.toTagDto(tag);
        assertEquals(actualTag, tagDto);
    }

    @Test
    void checkTagFilterToTag() {
        TagFilter tagFilter = new TagFilter("IT");
        Tag actualTag = tagMapper.filterToTag(tagFilter);

        assertEquals(actualTag.getName(), tagFilter.getName());
    }

    @Test
    void checkUpdateTagFromTagDto() {
        Tag oldTag = Tag.builder()
                .id(1L)
                .name("oldTagName")
                .build();
        TagDto newTagDto = TagDto.builder()
                .name("newTagName")
                .build();
        tagMapper.updateTagFromTagDto(newTagDto, oldTag);

        assertEquals(oldTag.getName(), newTagDto.getName());
    }

    @Test
    void checkUpdateTagFromTagDtoWithNulls() {
        Tag oldTag = Tag.builder()
                .id(1L)
                .name("oldTagName")
                .build();
        TagDto tagDto = TagDto.builder()
                .id(null)
                .name(null)
                .build();
        tagMapper.updateTagFromTagDto(tagDto, oldTag);

        assertEquals(oldTag, oldTag);
    }

}