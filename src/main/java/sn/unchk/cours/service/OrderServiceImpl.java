package sn.unchk.cours.service;

import sn.unchk.cours.entity.Order;
import sn.unchk.cours.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update(Long id, Order order) {
        Order existing = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found: " + id));
        existing.setOrderDate(order.getOrderDate());
        existing.setQuantity(order.getQuantity());
        existing.setTotalAmount(order.getTotalAmount());
        existing.setClient(order.getClient());
        existing.setProducts(order.getProducts());
        return orderRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found: " + id);
        }
        orderRepository.deleteById(id);
    }
}
