package ru.clevertec.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.SpringApplicationRunner;
import ru.clevertec.entity.Tag;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@SpringBootTest
@RequiredArgsConstructor
class TagRepositoryTest {

    private final TagRepository tagRepository;
    private static final String IT_TAG_NAME = "IT";

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByNameIgnoreCaseTest() {
        Optional<Tag> maybeTag = tagRepository.findByNameIgnoreCase(IT_TAG_NAME);
        assertTrue(maybeTag.isPresent());
    }

}