package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.dto.TagDto;
import ru.clevertec.dto.TagFilter;
import ru.clevertec.entity.Tag;
import ru.clevertec.exception.EntityNotFoundException;
import ru.clevertec.mapper.TagMapper;
import ru.clevertec.repository.TagRepository;
import ru.clevertec.service.TagService;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class TagServiceImpl implements TagService {

    private static final String TAG_LABEL = "Tag";
    private static final String ID_LABEL = "id";
    private static final String NAME_LABEL = "name";
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Override
    public Page<TagDto> find(TagFilter filter, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withMatcher("name", match -> match.contains().ignoreCase());
        return tagRepository.findAll(
                        Example.of(tagMapper.toTag(filter), matcher), pageable)
                .map(tagMapper::tagToDto);
    }

    @Override
    public TagDto findById(Long id) {
        return tagRepository.findById(id)
                .map(tagMapper::tagToDto)
                .orElseThrow(() -> new EntityNotFoundException(TAG_LABEL, ID_LABEL, id));
    }

    @Override
    public TagDto findByNameOrSave(TagDto tagDto) {
        return tagRepository.findByName(tagDto.getName())
                .map(tagMapper::tagToDto)
                .orElseGet(() -> save(tagDto));
    }

    @Transactional
    @Override
    public TagDto save(TagDto tagDto) {
        Tag tag = tagMapper.toTag(tagDto);
        Tag saveTag = tagRepository.save(tag);
        return tagMapper.tagToDto(saveTag);
    }

    @Transactional
    @Override
    public TagDto update(Long id, TagDto tagDto) {
        return tagRepository.findById(id)
                .map(tag -> {
                    tagMapper.updateTagFromTagDto(tagDto, tag);
                    Tag saveTag = tagRepository.saveAndFlush(tag);
                    return tagMapper.tagToDto(saveTag);
                }).orElseThrow(() -> new EntityNotFoundException(TAG_LABEL, ID_LABEL, id));
    }

    @Transactional
    @Override
    public void remove(Long id) {
        tagRepository.findById(id)
                .map(tag -> {
                    tagRepository.delete(tag);
                    tagRepository.flush();
                    return tag;
                })
                .orElseThrow(() -> new EntityNotFoundException(TAG_LABEL, ID_LABEL, id));
    }
}
