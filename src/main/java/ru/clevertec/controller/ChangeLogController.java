package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.dto.ChangeLogDto;
import ru.clevertec.service.ChangeLogService;

@RestController
@RequestMapping("/change-log")
@RequiredArgsConstructor
public class ChangeLogController {

    private final ChangeLogService changeLogService;

    @GetMapping("/sequence")
    public Long getSequence(){
        return changeLogService.getSequence();
    }

    @GetMapping("/{id}")
    public ChangeLogDto getChangeLogById(@PathVariable Long id){
        return changeLogService.findById(id);
    }
}
