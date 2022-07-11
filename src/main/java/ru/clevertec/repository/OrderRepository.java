package ru.clevertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.clevertec.entity.Order;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select max(id) from orders", nativeQuery = true)
    Optional<Long> getSequence();

    @Query(value = "select setval('orders_id_seq', ?1)", nativeQuery = true)
    void setSequence(Long value);
}
