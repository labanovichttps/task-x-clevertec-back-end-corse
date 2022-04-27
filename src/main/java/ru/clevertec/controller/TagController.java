package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import ru.clevertec.dto.TagDto;
import ru.clevertec.service.TagService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tags")
public class TagController {

    private final TagService tagService;

    @GetMapping
    public ResponseEntity<List<TagDto>> getAllTags() {
        List<TagDto> tags = tagService.getAllTags();
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDto> getTagById(@PathVariable Long id){
        TagDto tagDto = tagService.getTagById(id);
        return new ResponseEntity<>(tagDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TagDto> createTag(@RequestBody TagDto tagDto){
        TagDto saveTag = tagService.saveTag(tagDto);
        return new ResponseEntity<>(saveTag, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagDto> updateTag(@PathVariable Long id,
                                            @RequestBody TagDto tagDto){
        TagDto updateTag = tagService.updateTag(id, tagDto);
        return new ResponseEntity<>(updateTag, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable Long id){
        tagService.removeTag(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
