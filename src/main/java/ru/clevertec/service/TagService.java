package ru.clevertec.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.entity.Tag;
import ru.clevertec.entity.dto.TagDto;
import ru.clevertec.entity.exception.EntityNotFoundException;
import ru.clevertec.mapper.TagMapper;
import ru.clevertec.repository.TagRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TagService {

    private final TagRepository tagRepository;

    public List<TagDto> findAllTags() {
        return tagRepository.findAll().stream()
                .map(TagMapper.INSTANCE::mapTagToDto)
                .collect(Collectors.toList());
    }

    public TagDto findById(Long id){
        return tagRepository.findById(id)
                .map(TagMapper.INSTANCE::mapTagToDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Requested resource not found (id = %d)", id)));
    }

    public TagDto save(TagDto tagDto){
        Tag tag = TagMapper.INSTANCE.mapToTag(tagDto);
        tagRepository.save(tag);
        return tagDto;
    }

    public TagDto update(TagDto tagDto){
        Tag tag = TagMapper.INSTANCE.mapToTag(tagDto);
        tagRepository.save(tag);
        return tagDto;
    }

    public void remove(Tag tag){
        tagRepository.delete(tag);
    }
}
