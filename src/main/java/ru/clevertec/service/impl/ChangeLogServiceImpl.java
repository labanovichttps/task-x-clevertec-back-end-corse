package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.dto.ChangeLogDto;
import ru.clevertec.entity.ChangeLog;
import ru.clevertec.exception.EntityNotFoundException;
import ru.clevertec.mapper.ChangeLogMapper;
import ru.clevertec.repository.ChangeLogRepository;
import ru.clevertec.service.ChangeLogService;

@Service
@RequiredArgsConstructor
@Transactional
public class ChangeLogServiceImpl implements ChangeLogService {

    private final ChangeLogRepository changeLogRepository;
    private final ChangeLogMapper changeLogMapper;

    @Override
    public ChangeLogDto findById(Long id) {
        return changeLogRepository.findById(id)
                .map(changeLogMapper::changeLogToChangeLogDto)
                .orElseThrow(() -> new EntityNotFoundException("ChangeLog", "id", id));
    }

    @Override
    public ChangeLogDto save(ChangeLogDto changeLogDto) {
        ChangeLog changeLog = changeLogMapper.changeLogToChangeLog(changeLogDto);
        ChangeLog savedChangeLog = changeLogRepository.save(changeLog);
        return changeLogMapper.changeLogToChangeLogDto(savedChangeLog);
    }

    @Override
    public Long getSequence() {
        return changeLogRepository.getSequence()
                .orElse(0L);
    }
}
