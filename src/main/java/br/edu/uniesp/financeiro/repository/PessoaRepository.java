package br.edu.uniesp.financeiro.repository;

import br.edu.uniesp.financeiro.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório de Pessoa.
 * - Estende JpaRepository, ganhando automaticamente métodos CRUD:
 *   save, findById, findAll, deleteById, etc.
 * - O primeiro parâmetro é a entidade, o segundo é o tipo da chave primária.
 */
public interface PessoaRepository extends JpaRepository<Pessoa, Long> { }
