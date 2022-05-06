package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.dto.MakeOrderDto;
import ru.clevertec.dto.PageResponse;
import ru.clevertec.dto.ReadOrderDto;
import ru.clevertec.service.OrderService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/user/{id}")
    public PageResponse<ReadOrderDto> findByUserId(@PathVariable Long id,
                                                   Pageable pageable) {
        Page<ReadOrderDto> userOrders = orderService.find(id, pageable);
        return PageResponse.of(userOrders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadOrderDto> findById(@PathVariable Long id){
        ReadOrderDto order = orderService.findById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReadOrderDto> makeOrder(@RequestBody MakeOrderDto orderDto) {
        ReadOrderDto order = orderService.makeOrder(orderDto);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
