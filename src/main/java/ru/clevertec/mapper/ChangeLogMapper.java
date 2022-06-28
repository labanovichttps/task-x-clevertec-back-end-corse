package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.clevertec.dto.ChangeLogDto;
import ru.clevertec.entity.ChangeLog;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ChangeLogMapper {

    ChangeLog changeLogToChangeLog(ChangeLogDto changeLogDto);

    ChangeLogDto changeLogToChangeLogDto(ChangeLog changeLog);
}
