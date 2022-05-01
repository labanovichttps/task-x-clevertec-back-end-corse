package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.dto.UserDto;
import ru.clevertec.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);
}
