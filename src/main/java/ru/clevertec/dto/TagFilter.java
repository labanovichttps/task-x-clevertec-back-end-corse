package ru.clevertec.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import ru.clevertec.constants.ApplicationConstants;

import javax.validation.constraints.Pattern;

@Value
@Builder
@AllArgsConstructor
public class TagFilter {

    @Pattern(regexp = ApplicationConstants.VALID_STRING_REGEX)
    String name;
}
