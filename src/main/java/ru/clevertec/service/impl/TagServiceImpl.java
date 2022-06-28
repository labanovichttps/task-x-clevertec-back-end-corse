package ru.clevertec.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.constants.ApplicationConstants;
import ru.clevertec.dto.ChangeLogDto;
import ru.clevertec.dto.TagDto;
import ru.clevertec.dto.TagFilter;
import ru.clevertec.entity.Tag;
import ru.clevertec.event.entity.EntityEvent;
import ru.clevertec.exception.EntityNotFoundException;
import ru.clevertec.mapper.TagMapper;
import ru.clevertec.repository.TagRepository;
import ru.clevertec.service.TagService;

import javax.servlet.http.HttpServletRequest;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class TagServiceImpl implements TagService {

    private static final String TAG_LABEL = "Tag";
    private static final String ID_LABEL = "id";
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;
    private final ObjectMapper objectMapper;
    private final ApplicationEventPublisher publisher;

    @Override
    public Page<TagDto> find(TagFilter filter, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withMatcher("name", match -> match.contains().ignoreCase());
        return tagRepository.findAll(
                        Example.of(tagMapper.filterToTag(filter), matcher), pageable)
                .map(tagMapper::toTagDto);
    }

    @Override
    public TagDto findById(Long id) {
        return tagRepository.findById(id)
                .map(tagMapper::toTagDto)
                .orElseThrow(() -> new EntityNotFoundException(TAG_LABEL, ID_LABEL, id));
    }

    @Override
    public TagDto findByNameOrSave(TagDto tagDto) {
        return tagRepository.findByName(tagDto.getName())
                .map(tagMapper::toTagDto)
                .orElseGet(() -> {
                    Tag tag = tagMapper.toTag(tagDto);
                    Tag saveTag = tagRepository.save(tag);
                    return tagMapper.toTagDto(saveTag);
                });
    }

    @Override
    public TagDto findTheMostWidelyTag() {
        return tagRepository.findTheMostWidelyTag()
                .map(tagMapper::toTagDto)
                .orElseThrow(() -> new EntityNotFoundException("The most widely tag hasn't found."));
    }

    @Transactional
    @Override
    public TagDto save(TagDto tagDto, HttpServletRequest request) {
        Tag tag = tagMapper.toTag(tagDto);
        Tag saveTag = tagRepository.save(tag);
        publishEvent(request, saveTag);
        return tagMapper.toTagDto(saveTag);
    }

    @Transactional
    @Override
    public TagDto update(Long id, TagDto tagDto, HttpServletRequest request) {
        return tagRepository.findById(id)
                .map(tag -> {
                    tagMapper.updateTagFromTagDto(tagDto, tag);
                    Tag updatedTag = tagRepository.saveAndFlush(tag);
                    publishEvent(request, updatedTag);
                    return tagMapper.toTagDto(updatedTag);
                })
                .orElseThrow(() -> new EntityNotFoundException(TAG_LABEL, ID_LABEL, id));
    }

    @Transactional
    @Override
    public void remove(Long id, HttpServletRequest request) {
        tagRepository.findById(id)
                .map(tag -> {
                    tagRepository.delete(tag);
                    publishEvent(request, tag);
                    tagRepository.flush();
                    return tag;
                })
                .orElseThrow(() -> new EntityNotFoundException(TAG_LABEL, ID_LABEL, id));
    }

    @SneakyThrows
    private void publishEvent(HttpServletRequest request, Tag tag) {
        if (request != null) {
            String newURL = request.getRequestURL().toString().replace(String.valueOf(request.getLocalPort()),
                    "%s") + ApplicationConstants.LOGGED;
            ChangeLogDto logDto = ChangeLogDto.builder()
                    .url(newURL)
                    .body(objectMapper.writeValueAsString(tag))
                    .httpMethod(request.getMethod())
                    .build();
            publisher.publishEvent(new EntityEvent(tag, logDto));
        }
    }
}
