package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.dto.CertificateDto;
import ru.clevertec.dto.CertificateFilter;
import ru.clevertec.dto.PageResponse;
import ru.clevertec.dto.UpdateCertificatePriceDto;
import ru.clevertec.service.CertificateService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RequestMapping("/api/certificates")
@RestController
public class CertificateController {

    private final CertificateService certificateService;

    @GetMapping
    public PageResponse<CertificateDto> find(@Valid CertificateFilter filter, Pageable pageable) {
        Page<CertificateDto> certificates = certificateService.find(filter, pageable);
        return PageResponse.of(certificates);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CertificateDto> findById(@PathVariable @Positive Long id) {
        CertificateDto certificateDto = certificateService.findById(id);
        return new ResponseEntity<>(certificateDto, HttpStatus.OK);
    }

    @GetMapping("/tag/{tagName}")
    public ResponseEntity<List<CertificateDto>> findByTagName(@PathVariable @NotBlank String tagName) {
        List<CertificateDto> certificates = certificateService.findByTagName(tagName);
        return new ResponseEntity<>(certificates, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CertificateDto> create(@RequestBody @Valid CertificateDto certificateDto) {
        CertificateDto saveCertificate = certificateService.save(certificateDto);
        return new ResponseEntity<>(saveCertificate, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CertificateDto> update(@PathVariable @Positive Long id,
                                                 @RequestBody @Valid CertificateDto certificateDto) {
        CertificateDto updateCertificate = certificateService.update(id, certificateDto);
        return new ResponseEntity<>(updateCertificate, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CertificateDto> updatePrice(@PathVariable @Positive Long id,
                                                      @RequestBody @Valid UpdateCertificatePriceDto updateCertificatePriceDto) {
        CertificateDto updateCertificate = certificateService.updatePrice(id, updateCertificatePriceDto);
        return new ResponseEntity<>(updateCertificate, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable @Positive Long id) {
        certificateService.remove(id);
        return ResponseEntity.noContent().build();
    }
}
