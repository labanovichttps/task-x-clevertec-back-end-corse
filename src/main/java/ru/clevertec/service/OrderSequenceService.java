package ru.clevertec.service;

import ru.clevertec.dto.SequenceDto;

public interface OrderSequenceService {

    void setSequence(SequenceDto sequenceDto);
    Long getSequence();
}
