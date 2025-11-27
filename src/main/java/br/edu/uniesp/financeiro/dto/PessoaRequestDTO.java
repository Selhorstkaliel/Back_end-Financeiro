package br.edu.uniesp.financeiro.dto;

import br.edu.uniesp.financeiro.entity.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO (Data Transfer Object) de entrada para Pessoa.
 * - Usado para receber dados da requisição (JSON -> objeto).
 * - Evita expor diretamente a entidade Pessoa na API.
 */
public record PessoaRequestDTO(
        @NotBlank String nome,          // Nome da pessoa
        @NotNull Boolean ativo,         // Indica se está ativa
        @NotNull Endereco endereco      // Endereço completo
) { }
