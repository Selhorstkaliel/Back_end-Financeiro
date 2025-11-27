package br.edu.uniesp.financeiro.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Entidade Pessoa.
 * - Representa uma tabela "pessoa" no banco de dados.
 * - Contém informações básicas da pessoa e seu endereço embutido.
 */
@Entity                             // Indica que esta classe é uma entidade JPA
@Table(name = "pessoa")            // Define o nome da tabela no banco de dados
public class Pessoa {

    @Id                             // Indica que o campo é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // ID é gerado automaticamente pelo banco (AUTO_INCREMENT)
    private Long id;

    @NotBlank                       // Validação: nome não pode ser nulo nem vazio
    private String nome;

    @NotNull                        // Validação: não pode ser nulo
    private Boolean ativo = Boolean.TRUE;   // Indica se a pessoa está ativa (true por padrão)

    @Embedded                       // Indica que os campos de Endereco serão embutidos nesta tabela
    @Valid                          // Validação recursiva nos campos de Endereco
    private Endereco endereco;

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

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
