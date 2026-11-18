package sn.unchk.cours.service;

import sn.unchk.cours.entity.Order;
import sn.unchk.cours.DTO.CreateOrderDTO;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> findAll();
    Optional<Order> findById(Long id);
    Order create(CreateOrderDTO request);
    Order update(Long id, CreateOrderDTO request);
    void deleteById(Long id);
}
