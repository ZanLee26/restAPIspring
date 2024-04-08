package net.proselyte.springbootdemo.repository;

import net.proselyte.springbootdemo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
