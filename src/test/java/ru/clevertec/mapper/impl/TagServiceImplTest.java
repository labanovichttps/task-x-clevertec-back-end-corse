package ru.clevertec.mapper.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.dto.TagDto;
import ru.clevertec.entity.Tag;
import ru.clevertec.exception.EntityNotFoundException;
import ru.clevertec.mapper.TagMapper;
import ru.clevertec.repository.TagRepository;
import ru.clevertec.service.impl.TagServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static ru.clevertec.utils.DataForTests.getTag;
import static ru.clevertec.utils.DataForTests.getTagDto;

@ExtendWith({MockitoExtension.class})
class TagServiceImplTest {

    @Mock
    private TagRepository tagRepository;
    @Mock
    private TagMapper tagMapper;
    @InjectMocks
    private TagServiceImpl tagService;
    private static final Long TAG_ID = 1L;
    private static final String TAG_NAME = "SPORT";
    private static final Long TAG_FOR_SAVE_ID = 2L;
    private static final String TAG_FOR_SAVE_NAME = "SAVE TAG";

    @Test
    void findById() {
        Tag tag = getTag(TAG_ID, TAG_NAME);
        TagDto tagDto = getTagDto(TAG_ID, TAG_NAME);

        doReturn(Optional.of(tag))
                .when(tagRepository).findById(tag.getId());
        doReturn(tagDto)
                .when(tagMapper).toTagDto(tag);

        TagDto actual = tagService.findById(tag.getId());
        assertThat(actual).isNotNull();
    }

    @Test
    void findByIdAndThrowEntityNotFoundException() {
        Tag tag = getTag(TAG_ID, TAG_NAME);

        doReturn(Optional.empty())
                .when(tagRepository).findById(tag.getId());

        assertThrows(EntityNotFoundException.class, () -> tagService.findById(tag.getId()));
    }

    @Test
    void findByNameIfExists() {
        Tag tag = getTag(TAG_ID, TAG_NAME);
        TagDto tagDto = getTagDto(TAG_ID, TAG_NAME);

        doReturn(Optional.of(tag))
                .when(tagRepository).findByName(tag.getName());
        doReturn(tagDto)
                .when(tagMapper).toTagDto(tag);

        TagDto actual = tagService.findByNameOrSave(tagDto);
        assertThat(actual.getName()).isEqualTo(tagDto.getName());
    }

    @Test
    @DisplayName("Find tag by name if not exists returns null and save.")
    void findByNameIfNotExistsSave() {
        Tag tagForSave = getTag(TAG_FOR_SAVE_ID, TAG_FOR_SAVE_NAME);
        TagDto tagForSaveDto = getTagDto(TAG_FOR_SAVE_ID, TAG_FOR_SAVE_NAME);

        doReturn(Optional.empty())
                .when(tagRepository).findByName(tagForSave.getName());
        doReturn(tagForSave)
                .when(tagMapper).toTag(tagForSaveDto);
        doReturn(tagForSaveDto)
                .when(tagMapper).toTagDto(tagForSave);
        doReturn(tagForSave)
                .when(tagRepository).save(tagForSave);

        TagDto actual = tagService.findByNameOrSave(tagForSaveDto);
        assertThat(actual.getName()).isEqualTo(tagForSave.getName());
    }

    @Test
    void findTheMostWidelyTag() {
        Tag tag = getTag(TAG_ID, TAG_NAME);
        TagDto tagDto = getTagDto(TAG_ID, TAG_NAME);
        doReturn(Optional.of(tag))
                .when(tagRepository).findTheMostWidelyTag();
        doReturn(tagDto)
                .when(tagMapper).toTagDto(tag);

        TagDto actual = tagService.findTheMostWidelyTag();
        assertEquals(actual, tagDto);
    }

    @Test
    void save() {
        Tag tagForSave = getTag(TAG_FOR_SAVE_ID, TAG_FOR_SAVE_NAME);
        TagDto tagForSaveDto = getTagDto(TAG_FOR_SAVE_ID, TAG_FOR_SAVE_NAME);
        doReturn(tagForSave)
                .when(tagRepository).save(tagForSave);
        doReturn(tagForSaveDto)
                .when(tagMapper).toTagDto(tagForSave);
        doReturn(tagForSave)
                .when(tagMapper).toTag(tagForSaveDto);

        TagDto actual = tagService.save(tagForSaveDto);
        assertEquals(actual, tagForSaveDto);
    }

    @Test
    void update() {
        Tag tag = getTag(TAG_ID, TAG_NAME);
        TagDto tagForSaveDto = getTagDto(null, TAG_FOR_SAVE_NAME);
        Tag updatedTag = getTag(tag.getId(), tagForSaveDto.getName());
        TagDto updatedTagDto = getTagDto(updatedTag.getId(), updatedTag.getName());

        doReturn(Optional.of(tag))
                .when(tagRepository).findById(tag.getId());
        doNothing()
                .when(tagMapper).updateTagFromTagDto(tagForSaveDto, tag);
        doReturn(updatedTag)
                .when(tagRepository).saveAndFlush(tag);
        doReturn(updatedTagDto)
                .when(tagMapper).toTagDto(updatedTag);

        TagDto actual = tagService.update(tag.getId(), tagForSaveDto);
        assertThat(actual.getName()).isEqualTo(tagForSaveDto.getName());

    }

    @Test
    void checkRemoveAndDontThrow() {
        Tag tag = getTag(TAG_ID, TAG_NAME);
        doReturn(Optional.of(tag))
                .when(tagRepository).findById(tag.getId());
        doNothing()
                .when(tagRepository).delete(tag);

        assertDoesNotThrow(() -> tagService.remove(tag.getId()));
    }


}