package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.dto.CertificateDto;
import ru.clevertec.dto.TagDto;
import ru.clevertec.service.impl.CertificateServiceImpl;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CertificateController {

    private final CertificateServiceImpl certificateServiceImpl;

    @GetMapping("/certificates")
    public ResponseEntity<List<CertificateDto>> getAllCertificates(){
        List<CertificateDto> certificates = certificateServiceImpl.getAllCertificates();
        return new ResponseEntity<>(certificates, HttpStatus.OK);
    }

    @PutMapping("/certificates")
    public ResponseEntity<CertificateDto> updateCertificate(@RequestBody CertificateDto certificateDto){
        CertificateDto certificate = certificateServiceImpl.updateCertificate(certificateDto);
        return new ResponseEntity<>(certificate, HttpStatus.OK);
    }
}
