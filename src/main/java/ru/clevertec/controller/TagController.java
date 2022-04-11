package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.entity.Tag;
import ru.clevertec.entity.dto.TagDto;
import ru.clevertec.mapper.TagMapper;
import ru.clevertec.service.TagService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TagController {

    private final TagService tagService;

    @GetMapping("/tags")
    public ResponseEntity<List<TagDto>> getAllTags() {
        List<TagDto> tags = tagService.findAllTags();
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("/tags/{id}")
    public ResponseEntity<TagDto> getTagById(@PathVariable Long id){
        TagDto tagDto = tagService.findById(id);
        return new ResponseEntity<>(tagDto, HttpStatus.OK);
    }

    @PostMapping("/tags")
    public ResponseEntity<TagDto> createTag(@RequestBody TagDto tagDto){
        TagDto saveTag = tagService.save(tagDto);
        return new ResponseEntity<>(saveTag, HttpStatus.CREATED);
    }

    @PutMapping("/tags")
    public ResponseEntity<TagDto> updateTag(@RequestBody TagDto tagDto){
        TagDto saveTag = tagService.update(tagDto);
        return new ResponseEntity<>(saveTag, HttpStatus.CREATED);
    }

    @DeleteMapping("/tags")
    public void deleteTag(@RequestBody TagDto tagDto){
        Tag tag = TagMapper.INSTANCE.mapToTag(tagDto);
        tagService.remove(tag);
    }
}
