package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.dto.TagDto;
import ru.clevertec.entity.Tag;
import ru.clevertec.exception.EntityNotFoundException;
import ru.clevertec.mapper.TagMapper;
import ru.clevertec.repository.TagRepository;
import ru.clevertec.service.TagService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class TagServiceImpl implements TagService {

    private static final String TAG_LABEL = "Tag";
    private static final String ID_LABEL = "id";

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
                .orElseThrow(() -> new EntityNotFoundException(TAG_LABEL, ID_LABEL, id));
    }

    @Override
    public Optional<TagDto> findTagByName(String name) {
        return tagRepository.findByNameIgnoreCase(name)
                .map(tagMapper::tagToDto);
    }

    @Transactional
    @Override
    public TagDto saveTag(TagDto tagDto) {
        Tag tag = tagMapper.toTag(tagDto);
        Tag saveTag = tagRepository.save(tag);
        return tagMapper.tagToDto(saveTag);
    }

    @Transactional
    @Override
    public TagDto updateTag(Long id, TagDto tagDto) {
        return tagRepository.findById(id)
                .map(tag -> {
                    Tag updateTag = updateTag(tagDto, tag);
                    Tag saveTag = tagRepository.saveAndFlush(updateTag);
                    return tagMapper.tagToDto(saveTag);
                }).orElseThrow(() -> new EntityNotFoundException(TAG_LABEL, ID_LABEL, id));
    }

    @Override
    public TagDto findTagByNameOrSave(TagDto tagDto) {
        return findTagByName(tagDto.getName())
                .orElseGet(() -> saveTag(tagDto));
    }

    @Transactional
    @Override
    public void removeTag(Long id) {
        tagRepository.findById(id)
                .map(tag -> {
                    tagRepository.delete(tag);
                    tagRepository.flush();
                    return tag;
                })
                .orElseThrow(() -> new EntityNotFoundException(TAG_LABEL, ID_LABEL, id));
    }

    private Tag updateTag(TagDto from, Tag to) {
        if (Objects.nonNull(from.getName())) {
            to.setName(from.getName());
        }

        return to;
    }


}
