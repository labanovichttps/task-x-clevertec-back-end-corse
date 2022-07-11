package ru.clevertec.integration.service.impl;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import ru.clevertec.dto.PageResponse;
import ru.clevertec.dto.ReadUserDto;
import ru.clevertec.exception.EntityNotFoundException;
import ru.clevertec.integration.BaseIntegrationTest;
import ru.clevertec.service.UserService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RequiredArgsConstructor
class UserServiceImplTest extends BaseIntegrationTest {

    private final UserService userService;

    @Test
    void findAndReturnExistData() {
        PageResponse<ReadUserDto> actualUsers = PageResponse.of(userService.find(PageRequest.of(1, 2)));
        assertAll(
                () -> assertEquals(4, actualUsers.getMetadata().getTotalElements()),
                () -> assertThat(actualUsers.getContent()).hasSize(2),
                () -> assertEquals(1, actualUsers.getMetadata().getPage())
        );
    }

    @Test
    void findByIdAndIfIDIsCorrectReturnUser() {
        assertThat(userService.findById(1L)).isNotNull();
    }

    @Test
    void findByIdAndIfIDIsNotCorrectThrowEntityNotFoundException() {
       assertThrows(EntityNotFoundException.class, () -> userService.findById(420L));
    }
}