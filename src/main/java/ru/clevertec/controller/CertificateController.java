package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.constants.ApplicationConstants;
import ru.clevertec.dto.CertificateDto;
import ru.clevertec.dto.CertificateFilter;
import ru.clevertec.dto.PageResponse;
import ru.clevertec.dto.UpdateCertificatePriceDto;
import ru.clevertec.service.CertificateService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.List;

import static ru.clevertec.constants.ApplicationConstants.*;

@RequiredArgsConstructor
@Validated
@RequestMapping("/certificates")
@RestController
public class CertificateController {

    private final CertificateService certificateService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<CertificateDto> findCertificates(@Valid CertificateFilter filter, Pageable pageable) {
        Page<CertificateDto> certificates = certificateService.find(filter, pageable);
        return PageResponse.of(certificates);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CertificateDto findCertificateById(@PathVariable @Positive Long id) {
        return certificateService.findById(id);
    }

    @GetMapping("/tag/{tagName}")
    @ResponseStatus(HttpStatus.OK)
    public List<CertificateDto> findCertificateByTagName(@PathVariable @NotBlank
                                                         @Pattern(regexp = VALID_STRING_REGEX) String tagName) {
        return certificateService.findByTagName(tagName);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CertificateDto createCertificate(@RequestBody @Valid CertificateDto certificateDto) {
        return certificateService.save(certificateDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CertificateDto updateCertificate(@PathVariable @Positive Long id,
                                            @RequestBody @Valid CertificateDto certificateDto) {
        return certificateService.update(id, certificateDto);

    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CertificateDto updatePrice(@PathVariable @Positive Long id,
                                      @RequestBody @Valid UpdateCertificatePriceDto priceDto) {
        return certificateService.updatePrice(id, priceDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Positive Long id) {
        certificateService.remove(id);
    }
}
