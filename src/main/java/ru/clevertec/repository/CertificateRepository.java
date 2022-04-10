package ru.clevertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.entiry.Certificate;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
}
