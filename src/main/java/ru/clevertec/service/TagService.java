package ru.clevertec.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.clevertec.dto.TagDto;
import ru.clevertec.dto.TagFilter;

public interface TagService {

    Page<TagDto> getTags(TagFilter filter, Pageable pageable);

    TagDto getTagById(Long id);

    TagDto saveTag(TagDto tagDto);

    TagDto updateTag(Long id, TagDto tagDto);

    void removeTag(Long id);
}
