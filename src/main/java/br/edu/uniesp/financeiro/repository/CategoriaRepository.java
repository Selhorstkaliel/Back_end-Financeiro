package br.edu.uniesp.financeiro.repository;

import br.edu.uniesp.financeiro.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório de Categoria.
 * - Não precisamos escrever implementação, o Spring Data JPA gera em runtime.
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Long> { }
