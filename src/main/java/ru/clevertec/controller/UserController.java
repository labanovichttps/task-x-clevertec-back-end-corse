package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.dto.PageResponse;
import ru.clevertec.dto.ReadOrderDto;
import ru.clevertec.dto.ReadUserDto;
import ru.clevertec.dto.UserDto;
import ru.clevertec.service.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public PageResponse<ReadUserDto> find(Pageable pageable) {
        Page<ReadUserDto> users = userService.find(pageable);
        return PageResponse.of(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadUserDto> findById(@PathVariable Long id) {
        ReadUserDto user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
