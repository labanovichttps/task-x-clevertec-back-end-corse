package ru.clevertec.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.clevertec.constants.ApplicationConstants;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @Positive
    private Long id;

    @Pattern(regexp = ApplicationConstants.VALID_STRING_REGEX)
    private String login;
    private List<ReadOrderDto> orders;
}
