package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.dto.PageResponse;
import ru.clevertec.dto.TagDto;
import ru.clevertec.dto.TagFilter;
import ru.clevertec.service.TagService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tags")
public class TagController {

    private final TagService tagService;

    @GetMapping
    public PageResponse<TagDto> find(@Valid TagFilter filter, Pageable pageable) {
        Page<TagDto> tags = tagService.find(filter, pageable);
        return PageResponse.of(tags);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDto> findById(@PathVariable @Positive Long id) {
        TagDto tagDto = tagService.findById(id);
        return new ResponseEntity<>(tagDto, HttpStatus.OK);
    }

    @GetMapping("/the-most-widely-used")
    public ResponseEntity<TagDto> findTheMostWidelyUsed() {
        return new ResponseEntity<>(tagService.findTheMostWidelyTag(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TagDto> create(@RequestBody TagDto tagDto) {
        TagDto saveTag = tagService.save(tagDto);
        return new ResponseEntity<>(saveTag, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagDto> update(@PathVariable @Positive Long id,
                                         @RequestBody @Valid TagDto tagDto) {
        TagDto updateTag = tagService.update(id, tagDto);
        return new ResponseEntity<>(updateTag, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable @Positive Long id) {
        tagService.remove(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
