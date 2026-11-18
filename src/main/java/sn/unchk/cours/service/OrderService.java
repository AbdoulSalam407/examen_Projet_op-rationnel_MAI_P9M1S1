package sn.unchk.cours.service;

import sn.unchk.cours.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> findAll();
    Optional<Order> findById(Long id);
    Order save(Order order);
    Order update(Long id, Order order);
    void deleteById(Long id);
}
