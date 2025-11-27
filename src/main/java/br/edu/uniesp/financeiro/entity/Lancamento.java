package br.edu.uniesp.financeiro.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entidade Lancamento.
 * - Representa um lançamento financeiro (receita ou despesa) no sistema.
 */
@Entity // Indica que esta classe é uma entidade JPA
@Table(name = "lancamento") // Mapeia a entidade para a tabela "lancamento" no banco de dados
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                        // ID do lançamento

    @NotBlank
    private String descricao;               // Descrição do lançamento (ex: "Aluguel de Janeiro")

    @NotNull
    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;       // Data em que o lançamento vence

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;        // Data em que o lançamento foi pago (pode ser nula)

    @NotNull
    private BigDecimal valor;               // Valor monetário do lançamento

    private String observacao;              // Campo livre para comentários adicionais

    @NotNull
    @Enumerated(EnumType.STRING)            // Armazena o nome da constante do enum (RECEITA/DESPESA)
    private TipoLancamento tipo;            // Tipo do lançamento

    @ManyToOne(optional = false)            // Muitos lançamentos -> Uma categoria
    @JoinColumn(name = "id_categoria")      // Nome da coluna de FK no banco
    private Categoria categoria;            // Categoria associada ao lançamento

    @ManyToOne(optional = false)            // Muitos lançamentos -> Uma pessoa
    @JoinColumn(name = "id_pessoa")         // Nome da coluna de FK no banco
    private Pessoa pessoa;                  // Pessoa associada ao lançamento

    // Getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public TipoLancamento getTipo() {
        return tipo;
    }

    public void setTipo(TipoLancamento tipo) {
        this.tipo = tipo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
