package br.com.lojaonline.ecommerce.repository;

import br.com.lojaonline.ecommerce.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
