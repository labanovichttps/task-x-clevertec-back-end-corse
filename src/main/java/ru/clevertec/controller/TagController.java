package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.entity.dto.TagDto;
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
}
