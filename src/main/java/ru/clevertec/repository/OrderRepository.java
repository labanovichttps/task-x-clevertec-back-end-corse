package ru.clevertec.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.clevertec.entity.Order;

import java.util.Optional;
import java.util.OptionalLong;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findAllByUserId(Long userId, Pageable pageable);

    @Query(value = "select max(id) from orders", nativeQuery = true)
    Optional<Long> getSequence();

    @Query(value = "select setval('orders_id_seq', ?1)", nativeQuery = true)
    void setSequence(Long value);
}
