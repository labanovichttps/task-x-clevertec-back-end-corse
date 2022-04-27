package ru.clevertec.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.repository.CertificateRepository;
import ru.clevertec.mapper.CertificateMapper;
import ru.clevertec.service.CertificateService;

@ExtendWith(MockitoExtension.class)
class CertificateServiceImplTest {

    @Mock
    private CertificateRepository certificateRepository;

    @Mock
    private CertificateMapper certificateMapper;

    @InjectMocks
    private CertificateService certificateService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllCertificates() {
    }

    @Test
    void getCertificateById() {
    }

    @Test
    void getCertificatesByTagName() {
    }

    @Test
    void saveCertificate() {
    }

    @Test
    void updateCertificate() {
    }

    @Test
    void removeCertificate() {
    }
}