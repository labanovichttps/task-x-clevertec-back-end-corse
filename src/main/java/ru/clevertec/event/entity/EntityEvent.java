package ru.clevertec.event.entity;

import org.springframework.context.ApplicationEvent;

import java.util.EventObject;

public class EntityEvent extends ApplicationEvent {

    public EntityEvent(Object source) {
        super(source);
    }
}
