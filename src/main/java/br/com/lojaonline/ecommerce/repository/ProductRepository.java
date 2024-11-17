package br.com.lojaonline.ecommerce.repository;

import br.com.lojaonline.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product, Integer> {
}
