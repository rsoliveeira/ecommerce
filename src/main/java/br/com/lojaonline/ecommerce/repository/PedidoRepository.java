package br.com.lojaonline.ecommerce.repository;

import br.com.lojaonline.ecommerce.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
