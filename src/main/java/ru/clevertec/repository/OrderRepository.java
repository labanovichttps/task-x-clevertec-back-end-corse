package ru.clevertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
