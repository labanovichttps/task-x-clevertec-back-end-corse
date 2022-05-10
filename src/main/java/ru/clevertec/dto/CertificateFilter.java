package ru.clevertec.dto;

import lombok.Builder;
import lombok.Value;
import ru.clevertec.constants.ApplicationConstants;

import javax.validation.constraints.Pattern;

@Value
@Builder
public class CertificateFilter {

    @Pattern(regexp = ApplicationConstants.VALID_STRING_REGEX)
    String name;

    @Pattern(regexp = ApplicationConstants.VALID_STRING_REGEX)
    String description;
}
