package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.dto.PageResponse;
import ru.clevertec.dto.ReadUserDto;
import ru.clevertec.service.UserService;

import javax.validation.constraints.Positive;

@RestController
@Validated
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<ReadUserDto> findUsers(Pageable pageable) {
        Page<ReadUserDto> users = userService.find(pageable);
        return PageResponse.of(users);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReadUserDto findUserById(@PathVariable @Positive Long id) {
        return userService.findById(id);
    }
}
