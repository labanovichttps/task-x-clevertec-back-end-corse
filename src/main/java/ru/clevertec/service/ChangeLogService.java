package ru.clevertec.service;

import ru.clevertec.dto.ChangeLogDto;

public interface ChangeLogService {

    ChangeLogDto findById(Long id);

    ChangeLogDto save(ChangeLogDto changeLogDto);

    Long getSequence();

}
