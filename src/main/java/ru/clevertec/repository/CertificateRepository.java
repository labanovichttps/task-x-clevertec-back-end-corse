package ru.clevertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.clevertec.entity.Certificate;

import java.util.List;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {

    @Query(value = "select c from Certificate c join fetch c.tags t where t.name like %:tagName%")
    List<Certificate> findCertificatesBy(@Param("tagName") String tagName);

}
