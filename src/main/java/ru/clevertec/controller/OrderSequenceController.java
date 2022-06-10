package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.dto.SequenceDto;
import ru.clevertec.service.OrderSequenceService;

@RestController
@RequestMapping("/api/order_sequence")
@RequiredArgsConstructor
public class OrderSequenceController {

    private final OrderSequenceService sequenceService;

    @GetMapping
    public ResponseEntity<Long> get(){
        return ResponseEntity.ok(sequenceService.getSequence());
    }

    @PostMapping
    public ResponseEntity<?> set(@RequestBody SequenceDto sequenceDto){
        sequenceService.setSequence(sequenceDto);
        return ResponseEntity.noContent().build();
    }
}
