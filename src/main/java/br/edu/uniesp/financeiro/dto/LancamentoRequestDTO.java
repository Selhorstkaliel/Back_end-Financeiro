package br.edu.uniesp.financeiro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO de entrada para Lancamento.
 * - Representa os dados que o cliente envia ao criar/atualizar lançamentos.
 */
public record LancamentoRequestDTO(
        @NotBlank String descricao,       // Descrição do lançamento
        @NotNull LocalDate dataVencimento,// Data de vencimento
        LocalDate dataPagamento,          // Data de pagamento (opcional)
        @NotNull BigDecimal valor,        // Valor do lançamento
        String observacao,                // Observações (opcional)
        @NotBlank String tipo,            // Tipo (texto: "RECEITA" ou "DESPESA")
        @NotNull Long idCategoria,        // ID da categoria associada
        @NotNull Long idPessoa            // ID da pessoa associada
) { }
