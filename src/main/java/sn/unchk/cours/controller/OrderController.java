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
@RequestMapping("/api/orders")
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
        Order order = new Order();
        order.setOrderDate(request.orderDate() != null ? request.orderDate() : java.time.LocalDateTime.now());
        order.setTotalAmount(request.totalAmount());
        order.setQuantity(request.quantity());
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(order));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> update(@PathVariable Long id, @Valid @RequestBody CreateOrderDTO request) {
        try {
            Order order = new Order();
            order.setOrderDate(request.orderDate() != null ? request.orderDate() : java.time.LocalDateTime.now());
            order.setTotalAmount(request.totalAmount());
            order.setQuantity(request.quantity());
            return ResponseEntity.ok(orderService.update(id, order));
        } catch (RuntimeException exception) {
            return ResponseEntity.notFound().build();
        }
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
