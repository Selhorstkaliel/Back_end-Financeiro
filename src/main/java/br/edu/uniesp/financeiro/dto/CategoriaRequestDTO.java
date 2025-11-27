package br.edu.uniesp.financeiro.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO de entrada para Categoria.
 * - Usado em requisições POST/PUT de categoria.
 */
public record CategoriaRequestDTO(
        @NotBlank String nome   // Nome da categoria
) { }
