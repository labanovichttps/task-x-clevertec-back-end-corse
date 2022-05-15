package ru.clevertec.utils;

import ru.clevertec.dto.CertificateDto;
import ru.clevertec.dto.ReadUserDto;
import ru.clevertec.dto.TagDto;
import ru.clevertec.entity.Certificate;
import ru.clevertec.entity.Order;
import ru.clevertec.entity.Tag;
import ru.clevertec.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class DataForTests {

    public static Tag getTag(Long tagId, String tagName) {
        return Tag.builder()
                .id(tagId)
                .name(tagName)
                .build();
    }

    public static TagDto getTagDto(Long tagId, String tagName) {
        return TagDto.builder()
                .id(tagId)
                .name(tagName)
                .build();
    }

    public static Certificate createCertificate(Long id, String name, String description, BigDecimal price,
                                                Long duration, LocalDateTime createDate, LocalDateTime lastUpdateDate,
                                                List<Tag> tags) {
        return Certificate.builder()
                .id(id)
                .name(name)
                .description(description)
                .price(price)
                .duration(duration)
                .createDate(createDate)
                .lastUpdateDate(lastUpdateDate)
                .tags(tags)
                .build();
    }


    public static CertificateDto createCertificateDto(Long id, String name, String description, BigDecimal price,
                                                      Long duration, LocalDateTime createDate, LocalDateTime lastUpdateDate,
                                                      List<TagDto> tags) {
        return CertificateDto.builder()
                .id(id)
                .name(name)
                .description(description)
                .price(price)
                .duration(duration)
                .createDate(createDate)
                .lastUpdateDate(lastUpdateDate)
                .tags(tags)
                .build();
    }

    public static List<Tag> createTags() {
        return new ArrayList<>(Arrays.asList(
                new Tag(1L, "First tag"),
                new Tag(2L, "Second tag"),
                new Tag(3L, "Third tag"),
                new Tag(4L, "Fourth tag"),
                new Tag(5L, "Fives tag")
        ));
    }

    public static List<TagDto> createTagsDto() {
        return new ArrayList<>(Arrays.asList(
                new TagDto(1L, "First tag"),
                new TagDto(2L, "Second tag"),
                new TagDto(3L, "Third tag"),
                new TagDto(4L, "Fourth tag"),
                new TagDto(5L, "Fives tag")
        ));
    }


    public static Order createOrder(Long id, User user, Certificate certificate,
                                    LocalDateTime orderDate, BigDecimal totalPrice) {
        return Order.builder()
                .id(id)
                .user(user)
                .certificate(certificate)
                .orderDate(orderDate)
                .totalPrice(totalPrice)
                .build();
    }

    public static User createUser(Long id, String login,
                                  List<Order> orders) {
        return User.builder()
                .id(id)
                .login(login)
                .orders(orders)
                .build();
    }

    public static ReadUserDto createReadUserDto(Long id, String login){
        return ReadUserDto.builder()
                .id(id)
                .login(login)
                .build();
    }
}
