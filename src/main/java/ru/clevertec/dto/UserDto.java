package ru.clevertec.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDto {

    private Long id;
    private String login;
    private List<OrderDto> orders;
}
