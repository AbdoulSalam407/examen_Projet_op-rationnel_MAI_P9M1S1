package sn.unchk.cours.repository;

import sn.unchk.cours.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
