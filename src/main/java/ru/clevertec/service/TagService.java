package ru.clevertec.service;

import ru.clevertec.dto.TagDto;

import java.util.List;

public interface TagService {

    List<TagDto> getAllTags();

    TagDto getTagById(Long id);

    TagDto findTagByName(String name);

    TagDto saveTag(TagDto tagDto);

    TagDto updateTag(Long id, TagDto tagDto);

    void removeTag(Long id);
}
