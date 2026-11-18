package sn.unchk.cours.repository;

import sn.unchk.cours.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
