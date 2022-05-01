package ru.clevertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
