package ru.clevertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
