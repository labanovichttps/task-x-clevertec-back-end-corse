package ru.clevertec.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.clevertec.constants.ApplicationConstants;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReadCertificateDto {

    @Positive
    private Long id;

    @Pattern(regexp = ApplicationConstants.VALID_STRING_REGEX)
    private String name;

    @Pattern(regexp = ApplicationConstants.VALID_STRING_REGEX)
    private String description;

    @Positive
    private BigDecimal price;

    @Positive
    private Long duration;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
}
