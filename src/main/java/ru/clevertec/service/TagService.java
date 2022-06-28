package ru.clevertec.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.clevertec.dto.TagDto;
import ru.clevertec.dto.TagFilter;

import javax.servlet.http.HttpServletRequest;

public interface TagService {

    Page<TagDto> find(TagFilter filter, Pageable pageable);

    TagDto findById(Long id);

    TagDto findByNameOrSave(TagDto tagDto);

    TagDto findTheMostWidelyTag();

    TagDto save(TagDto tagDto, HttpServletRequest request);

    TagDto update(Long id, TagDto tagDto, HttpServletRequest request);

    void remove(Long id, HttpServletRequest request);
}
