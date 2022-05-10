package ru.clevertec.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.clevertec.dto.ReadUserDto;

public interface UserService {

    Page<ReadUserDto> find(Pageable pageable);

    ReadUserDto findById(Long id);
}
