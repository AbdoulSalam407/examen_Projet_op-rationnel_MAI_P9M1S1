package sn.unchk.cours.service;

import sn.unchk.cours.DTO.CreateOrderDTO;
import sn.unchk.cours.DTO.CreateOrderDetailsDTO;
import sn.unchk.cours.entity.Order;
import sn.unchk.cours.entity.OrderDetails;
import sn.unchk.cours.entity.Product;
import sn.unchk.cours.repository.ClientRepository;
import sn.unchk.cours.repository.OrderRepository;
import sn.unchk.cours.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order create(CreateOrderDTO request) {
        Order order = new Order();
        order.setOrderDate(request.orderDate() != null ? request.orderDate() : java.time.LocalDateTime.now());
        order.setClient(clientRepository.findById(request.clientId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found")));
        order.setOrderDetails(toOrderDetails(request.orderDetails(), order));
        return orderRepository.save(order);
    }

    @Override
    public Order update(Long id, CreateOrderDTO request) {
        Order existing = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found: " + id));
        existing.setOrderDate(request.orderDate() != null ? request.orderDate() : java.time.LocalDateTime.now());
        existing.setClient(clientRepository.findById(request.clientId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found")));
        existing.getOrderDetails().clear();
        existing.getOrderDetails().addAll(toOrderDetails(request.orderDetails(), existing));
        return orderRepository.save(existing);
    }

    private List<OrderDetails> toOrderDetails(List<CreateOrderDetailsDTO> requests, Order order) {
        return requests.stream().map(request -> {
            Product product = productRepository.findById(request.productId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Product not found: " + request.productId()));
            OrderDetails details = new OrderDetails();
            details.setOrder(order);
            details.setProduct(product);
            details.setPrice(request.price());
            details.setQuantity(request.quantity());
            return details;
        }).toList();
    }

    @Override
    public void deleteById(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found: " + id);
        }
        orderRepository.deleteById(id);
    }
}
