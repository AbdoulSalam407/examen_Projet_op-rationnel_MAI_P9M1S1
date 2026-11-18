package sn.unchk.cours.service;

import sn.unchk.cours.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);
    Product update(Long id, Product product);
    void deleteById(Long id);
}
