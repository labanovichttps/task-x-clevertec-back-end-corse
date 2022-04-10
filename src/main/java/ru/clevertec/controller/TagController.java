package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.entiry.dto.TagDto;
import ru.clevertec.service.TagService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TagController {

    private final TagService tagService;

    @GetMapping("/tags")
    public ResponseEntity<List<TagDto>> getAllTags() {
        return new ResponseEntity<>(tagService.findAllTags(), HttpStatus.OK);
    }
}
