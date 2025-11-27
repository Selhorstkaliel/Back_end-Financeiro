package br.edu.uniesp.financeiro.dto;

import br.edu.uniesp.financeiro.entity.Endereco;
import br.edu.uniesp.financeiro.entity.Pessoa;

/**
 * DTO de saída para Pessoa.
 * - Usado para devolver dados ao cliente com segurança.
 * - Recebe a entidade no construtor e extrai os campos relevantes.
 */
public record PessoaResponseDTO(
        Long id,
        String nome,
        Boolean ativo,
        Endereco endereco
) {
    // Construtor auxiliar que recebe a entidade e preenche o DTO automaticamente
    public PessoaResponseDTO(Pessoa pessoa) {
        this(pessoa.getId(), pessoa.getNome(), pessoa.getAtivo(), pessoa.getEndereco());
    }
}
