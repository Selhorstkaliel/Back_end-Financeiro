package br.edu.uniesp.financeiro.dto;

import br.edu.uniesp.financeiro.entity.Lancamento;
import br.edu.uniesp.financeiro.entity.TipoLancamento;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO de saída para Lancamento.
 * - Retorna informações do lançamento e alguns dados das relações (nome da categoria, nome da pessoa).
 */
public record LancamentoResponseDTO(
        Long id,
        String descricao,
        LocalDate dataVencimento,
        LocalDate dataPagamento,
        BigDecimal valor,
        String observacao,
        TipoLancamento tipo,
        Long idCategoria,
        String nomeCategoria,
        Long idPessoa,
        String nomePessoa
) {
    /**
     * Construtor auxiliar que recebe a entidade Lancamento e preenche o DTO.
     */
    public LancamentoResponseDTO(Lancamento l) {
        this(
                l.getId(),
                l.getDescricao(),
                l.getDataVencimento(),
                l.getDataPagamento(),
                l.getValor(),
                l.getObservacao(),
                l.getTipo(),
                l.getCategoria().getId(),
                l.getCategoria().getNome(),
                l.getPessoa().getId(),
                l.getPessoa().getNome()
        );
    }
}
