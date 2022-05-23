package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.clevertec.dto.ReadUserDto;
import ru.clevertec.exception.EntityNotFoundException;
import ru.clevertec.mapper.UserMapper;
import ru.clevertec.repository.UserRepository;
import ru.clevertec.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private static final String USER_LABEL = "User";
    private static final String ID_LABEL = "id";

    @Override
    public Page<ReadUserDto> find(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::toReadUserDto);
    }

    @Override
    public ReadUserDto findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toReadUserDto)
                .orElseThrow(() -> new EntityNotFoundException(USER_LABEL, ID_LABEL, id));
    }
}
