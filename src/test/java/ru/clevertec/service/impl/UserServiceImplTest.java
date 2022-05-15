package ru.clevertec.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.dto.ReadUserDto;
import ru.clevertec.entity.User;
import ru.clevertec.exception.EntityNotFoundException;
import ru.clevertec.mapper.UserMapper;
import ru.clevertec.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static ru.clevertec.utils.DataForTests.createReadUserDto;
import static ru.clevertec.utils.DataForTests.createUser;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    public static final Long USER_ID = 1L;
    public static final String USER_LOGIN = "login123";

    @Test
    void checkFindById() {
        User user = createUser(USER_ID, USER_LOGIN, null);
        ReadUserDto userDto = createReadUserDto(USER_ID, USER_LOGIN);
        doReturn(Optional.of(user))
                .when(userRepository).findById(user.getId());
        doReturn(userDto)
                .when(userMapper).toReadUserDto(user);

        ReadUserDto actual = userService.findById(user.getId());
        assertEquals(actual, userDto);
    }

    @Test
    void checkFindByIdAndThrowEntityNotFoundException() {
        doReturn(Optional.empty())
                .when(userRepository).findById(USER_ID);

        assertThrows(EntityNotFoundException.class, () -> userService.findById(USER_ID));
    }

}