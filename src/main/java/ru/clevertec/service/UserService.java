package ru.clevertec.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.clevertec.dto.UserDto;

public interface UserService {

    Page<UserDto> find(Pageable pageable);

    UserDto findById(Long id);
}
