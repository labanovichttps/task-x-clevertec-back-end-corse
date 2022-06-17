package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.dto.PageResponse;
import ru.clevertec.dto.TagDto;
import ru.clevertec.dto.TagFilter;
import ru.clevertec.service.TagService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<TagDto> findTags(@Valid TagFilter filter, Pageable pageable) {
        Page<TagDto> tags = tagService.find(filter, pageable);
        return PageResponse.of(tags);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TagDto findTagById(@PathVariable @Positive Long id) {
        return tagService.findById(id);
    }

    @GetMapping("/the-most-widely-used")
    @ResponseStatus(HttpStatus.OK)
    public TagDto findTheMostWidelyUsedTag() {
        return tagService.findTheMostWidelyTag();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TagDto createTag(@RequestBody @Valid TagDto tagDto) {
        return tagService.save(tagDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TagDto updateTag(@PathVariable @Positive Long id,
                            @RequestBody @Valid TagDto tagDto) {
        return tagService.update(id, tagDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTag(@PathVariable @Positive Long id) {
        tagService.remove(id);
    }
}
