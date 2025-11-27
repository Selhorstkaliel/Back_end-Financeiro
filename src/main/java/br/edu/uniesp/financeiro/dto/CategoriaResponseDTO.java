package br.edu.uniesp.financeiro.dto;

import br.edu.uniesp.financeiro.entity.Categoria;

/**
 * DTO de saída para Categoria.
 * - Construtor auxiliar recebe a entidade e cria o DTO.
 */
public record CategoriaResponseDTO(
        Long id,
        String nome
) {
    public CategoriaResponseDTO(Categoria categoria) {
        this(categoria.getId(), categoria.getNome());
    }
}
