package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import ru.clevertec.entiry.dto.CertificateFilter;
import ru.clevertec.service.CertificateService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/certificates")
@RestController
public class CertificateController {

    private final CertificateService certificateService;

    @GetMapping
    public ResponseEntity<?> getAllCertificates(CertificateFilter certificateFilter,
                                                                   Pageable pageable) {
        //todo
        Page<CertificateDto> certificates = certificateService.getAllCertificates(certificateFilter, pageable);
        return new ResponseEntity<>(certificates, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CertificateDto> getCertificateById(@PathVariable Long id) {
        CertificateDto certificateDto = certificateService.getCertificateById(id);
        return new ResponseEntity<>(certificateDto, HttpStatus.OK);
    }

    @GetMapping("/tag/{tagName}")
    public ResponseEntity<List<CertificateDto>> getCertificatesByTagName(@PathVariable String tagName) {
        List<CertificateDto> certificates = certificateService.getCertificatesByTagName(tagName);
        return new ResponseEntity<>(certificates, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CertificateDto> createTag(@RequestBody CertificateDto certificateDto) {
        CertificateDto saveCertificate = certificateService.saveCertificate(certificateDto);
        return new ResponseEntity<>(saveCertificate, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CertificateDto> updateCertificate(@PathVariable Long id,
                                                            @RequestBody CertificateDto certificateDto) {
        CertificateDto updateCertificate = certificateService.updateCertificate(id, certificateDto);
        return new ResponseEntity<>(updateCertificate, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CertificateDto> updateCertificatePrice(@PathVariable Long id,
                                                                 @RequestBody CertificateDto certificateDto){
        CertificateDto updateCertificate = certificateService.updateCertificatePrice(id, certificateDto);
        return new ResponseEntity<>(updateCertificate, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCertificate(@PathVariable Long id) {
        certificateService.removeCertificate(id);
        return ResponseEntity.noContent().build();
    }
}
