package ru.clevertec.integration;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.clevertec.dto.TagDto;
import ru.clevertec.service.TagService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RequiredArgsConstructor
public class TagServiceTest extends BaseIntegrationTest{

    private final TagService tagService;

    @Test
    void findById(){
        TagDto tag = tagService.findById(1L);
        assertNotNull(tag);
    }

}
