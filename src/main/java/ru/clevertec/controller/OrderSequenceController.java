package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.dto.SequenceDto;
import ru.clevertec.service.OrderSequenceService;

@RestController
@RequestMapping("/order_sequence")
@RequiredArgsConstructor
public class OrderSequenceController {

    private final OrderSequenceService sequenceService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Long getSequence() {
        return sequenceService.getSequence();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setSequence(@RequestBody SequenceDto sequenceDto) {
        sequenceService.setSequence(sequenceDto);
    }
}
