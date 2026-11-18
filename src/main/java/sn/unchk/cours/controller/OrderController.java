package sn.unchk.cours.controller;

import jakarta.validation.Valid;
import sn.unchk.cours.DTO.CreateOrderDTO;
import sn.unchk.cours.entity.Order;
import sn.unchk.cours.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<Order> getAll() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id) {
        return orderService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Order> create(@Valid @RequestBody CreateOrderDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> update(@PathVariable Long id, @Valid @RequestBody CreateOrderDTO request) {
        return ResponseEntity.ok(orderService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            orderService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException exception) {
            return ResponseEntity.notFound().build();
        }
    }
}
