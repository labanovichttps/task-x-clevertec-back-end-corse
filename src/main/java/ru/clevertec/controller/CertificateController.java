package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.entity.dto.CertificateDto;
import ru.clevertec.service.CertificateService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CertificateController {

    private final CertificateService certificateService;

    @GetMapping("/certificates")
    public ResponseEntity<List<CertificateDto>> getAllCertificates(){
        List<CertificateDto> certificates = certificateService.getAllCertificates();
        return new ResponseEntity<>(certificates, HttpStatus.OK);
    }
}
