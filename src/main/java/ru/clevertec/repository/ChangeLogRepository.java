package ru.clevertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.clevertec.entity.ChangeLog;

import java.util.Optional;

public interface ChangeLogRepository extends JpaRepository<ChangeLog, Long> {

    @Query(value = "select max(id) from change_log", nativeQuery = true)
    Optional<Long> getSequence();
}
