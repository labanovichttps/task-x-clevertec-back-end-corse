package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.dto.ReadUserDto;
import ru.clevertec.dto.UserDto;
import ru.clevertec.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "orders", ignore = true)
    User readUserDtoToUser(ReadUserDto readUserDto);

    User toUser(UserDto userDto);

    ReadUserDto toReadUserDto(User user);
}
