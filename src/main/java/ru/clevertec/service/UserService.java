package ru.clevertec.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.clevertec.dto.UserDto;
import ru.clevertec.entity.User;

import java.util.Optional;

public interface UserService {

    Page<UserDto> find(Pageable pageable);

    UserDto findById(Long id);
}
