package ru.clevertec.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void testCreate(){
        System.out.println(User.builder()
                        .id(1L)
                .build());
    }

}