package br.edu.uniesp.financeiro.dto;

import java.time.LocalDate;

/**
 * DTO usado para receber os filtros de pesquisa de lançamentos.
 * - Todos os campos são opcionais (podem ser nulos).
 * - Se o campo tiver valor, será usado no filtro.
 */
public record FiltroLancamentoDTO(
        LocalDate dataInicial,   // Filtrar data de vencimento a partir desta data
        LocalDate dataFinal,     // Filtrar data de vencimento até esta data
        String tipo,             // "RECEITA" ou "DESPESA"
        Long idCategoria,        // ID da categoria
        Long idPessoa            // ID da pessoa
) { }
