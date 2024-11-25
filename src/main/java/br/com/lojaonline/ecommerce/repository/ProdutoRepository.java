package br.com.lojaonline.ecommerce.repository;

import br.com.lojaonline.ecommerce.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository <Produto, Integer> {
}
