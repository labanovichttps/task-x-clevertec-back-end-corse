package ru.clevertec.service;

import ru.clevertec.dto.TagDto;

import java.util.List;

public interface TagService {

    List<TagDto> getAllTags();

    TagDto getTagById(Long id);

    TagDto saveTag(TagDto tagDto);

    TagDto updateTag(TagDto tagDto);

    boolean removeTag(Long id);
}
