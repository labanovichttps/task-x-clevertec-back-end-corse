package ru.clevertec.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.clevertec.constants.ApplicationConstants;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReadUserDto {

    @Positive
    private Long id;

    @Pattern(regexp = ApplicationConstants.VALID_STRING_REGEX)
    private String login;
}
