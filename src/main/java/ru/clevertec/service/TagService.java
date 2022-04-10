package ru.clevertec.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.entity.dto.TagDto;
import ru.clevertec.entity.exception.TagNotFoundException;
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
                .orElseThrow(() -> new TagNotFoundException("Tag not found!"));
    }
}
