package br.edu.uniesp.financeiro.repository;

import br.edu.uniesp.financeiro.entity.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório de Lancamento.
 * - Podemos adicionar métodos customizados depois, se necessário.
 */
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> { }
