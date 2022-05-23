package ru.clevertec.integration;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestConstructor;
import ru.clevertec.dto.TagDto;
import ru.clevertec.service.TagService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.context.TestConstructor.AutowireMode.ALL;

@TestConstructor(autowireMode = ALL)
@RequiredArgsConstructor
public class TagServiceTest implements BaseIntegrationTest{

    private final TagService tagService;

    @Test
    void findById(){
        TagDto tag = tagService.findById(1L);
        assertNotNull(tag);
    }

}
