package ru.clevertec.service;

import ru.clevertec.dto.TagDto;

import java.util.List;
import java.util.Optional;

public interface TagService {

    List<TagDto> getAllTags();

    TagDto getTagById(Long id);

    Optional<TagDto> findTagByName(String name);

    TagDto saveTag(TagDto tagDto);

    TagDto updateTag(Long id, TagDto tagDto);

    TagDto findTagByNameOrSave(TagDto tagDto);

    void removeTag(Long id);
}
