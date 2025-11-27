package br.edu.uniesp.financeiro.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

/**
 * Entidade Categoria.
 * - Representa um tipo de categoria de lançamento (ex: Salário, Aluguel, Lazer).
 */
@Entity // Indica que esta classe é uma entidade JPA
@Table(name = "categoria") // Mapeia a entidade para a tabela "categoria" no banco de dados
public class Categoria {

    @Id // Indica que este campo é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define a estratégia de geração automática do ID
    private Long id;                // Identificador único da categoria

    @NotBlank // Valida que o nome não pode ser nulo ou vazio
    private String nome;           // Nome da categoria

    // Getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
