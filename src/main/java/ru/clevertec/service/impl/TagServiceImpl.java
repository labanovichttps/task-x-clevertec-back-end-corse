package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.dto.TagDto;
import ru.clevertec.entity.Tag;
import ru.clevertec.exception.EntityNotFoundException;
import ru.clevertec.mapper.TagMapper;
import ru.clevertec.repository.TagRepository;
import ru.clevertec.service.TagService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Override
    public List<TagDto> getAllTags() {
        return tagRepository.findAll().stream()
                .map(tagMapper::tagToDto)
                .collect(toList());
    }

    @Override
    public TagDto getTagById(Long id) {
        return tagRepository.findById(id)
                .map(tagMapper::tagToDto)
                .orElseThrow(() -> new EntityNotFoundException("Tag", "id", id, HttpStatus.NOT_FOUND));
    }

    @Transactional
    @Override
    public TagDto saveTag(TagDto tagDto) {
        Tag tag = tagMapper.toTag(tagDto);
        tagRepository.save(tag);
        return tagDto;
    }

    @Transactional
    @Override
    public TagDto updateTag(Long id, TagDto tagDto) {
        return tagRepository.findById(id)
                .map(entity -> {
                    tagDto.setId(id);
                    tagRepository.saveAndFlush(tagMapper.toTag(tagDto));
                    return tagDto;
                }).orElseThrow(() -> new EntityNotFoundException("Tag", "id", id, HttpStatus.NOT_FOUND));
    }

    @Transactional
    @Override
    public boolean removeTag(Long id) {
        return tagRepository.findById(id)
                .map(tag -> {
                    tagRepository.delete(tag);
                    tagRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
