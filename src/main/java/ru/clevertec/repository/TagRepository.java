package ru.clevertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.entiry.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
