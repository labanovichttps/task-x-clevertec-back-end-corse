package ru.clevertec.integration.service.impl;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import ru.clevertec.dto.PageResponse;
import ru.clevertec.dto.TagDto;
import ru.clevertec.dto.TagFilter;
import ru.clevertec.exception.EntityNotFoundException;
import ru.clevertec.integration.BaseIntegrationTest;
import ru.clevertec.service.TagService;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RequiredArgsConstructor
class TagServiceImplTest extends BaseIntegrationTest {

    private final TagService tagService;
    private final Long EXISTS_TAG_ID = 1L;
    private static final String NEW_TAG_NAME = "NEW_TAG_NAME";
    private final Long DOES_NOT_EXISTS_TAG_ID = 420L;

    @Test
    void find() {
        TagFilter tagFilter = TagFilter.builder()
                .name("T")
                .build();
        PageResponse<TagDto> tags = PageResponse.of(tagService.find(tagFilter, PageRequest.of(1, 2)));
        assertAll(
                () -> assertEquals(2, tags.getMetadata().getSize()),
                () -> assertEquals(1, tags.getMetadata().getPage()),
                () -> assertEquals(5, tags.getMetadata().getTotalElements())
        );
    }

    @Test
    void findByIdAndDontThrowAnyException() {
        assertDoesNotThrow(() -> tagService.findById(EXISTS_TAG_ID));
    }

    @Test
    void findByIdAndThrowEntityNotFoundException() {
        assertThrows(EntityNotFoundException.class, () -> tagService.findById(DOES_NOT_EXISTS_TAG_ID));
    }

    @Test
    void findByNameAndReturnTag() {
        String existsTagName = "SPORT";
        TagDto tagDto = new TagDto(EXISTS_TAG_ID, existsTagName);
        TagDto actual = tagService.findByNameOrSave(tagDto);
        assertEquals(tagDto, actual);
    }

    @Test
    void findByNameAndSave() {
        TagDto tagDto = TagDto.builder()
                .name(NEW_TAG_NAME)
                .build();
        TagDto actual = tagService.findByNameOrSave(tagDto);
        assertEquals(tagDto.getName(), actual.getName());
    }

    @Test
    void findTheMostWidelyTag() {
        TagDto theMostWidelyTag = TagDto.builder()
                .id(3L)
                .name("EDUCATION")
                .build();
        assertEquals(theMostWidelyTag, tagService.findTheMostWidelyTag());
    }

    @Test
    void saveTag() {
        TagDto tagDtoForSave = TagDto.builder()
                .name(NEW_TAG_NAME)
                .build();
        TagDto actualTag = tagService.save(tagDtoForSave, null);
        assertEquals(tagDtoForSave.getName(), actualTag.getName());
    }


    @Test
    void updateTagAndDoesntThrow() {
        TagDto tagDtoForUpdate = TagDto.builder()
                .name(NEW_TAG_NAME)
                .build();
        TagDto actualTag = tagService.update(EXISTS_TAG_ID, tagDtoForUpdate, null);
        assertEquals(tagDtoForUpdate.getName(), actualTag.getName());
    }

    @Test
    void updateTagAndThrowEntityNotFoundExceptionIfTagDoesNotExists() {
        TagDto tagDtoForUpdate = TagDto.builder()
                .name(NEW_TAG_NAME)
                .build();
        assertThrows(EntityNotFoundException.class, () -> tagService.update(DOES_NOT_EXISTS_TAG_ID, tagDtoForUpdate, null));
    }

    @Test
    void removeTagAndThrowEntityNotFoundException() {
        assertThrows(EntityNotFoundException.class, () -> tagService.remove(DOES_NOT_EXISTS_TAG_ID, null));
    }

    @Test
    void removeTagAndDoeNotThrow() {
        assertDoesNotThrow(() -> tagService.remove(EXISTS_TAG_ID, null));
    }
}