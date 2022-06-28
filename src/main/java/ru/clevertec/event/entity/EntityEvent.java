package ru.clevertec.event.entity;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import ru.clevertec.dto.ChangeLogDto;

@Getter
public class EntityEvent extends ApplicationEvent {

    private ChangeLogDto changeLogDto;

    public EntityEvent(Object source, ChangeLogDto changeLogDto) {
        super(source);
        this.changeLogDto = changeLogDto;
    }
}
