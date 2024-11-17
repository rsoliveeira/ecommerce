package br.com.lojaonline.ecommerce.repository;

import br.com.lojaonline.ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
