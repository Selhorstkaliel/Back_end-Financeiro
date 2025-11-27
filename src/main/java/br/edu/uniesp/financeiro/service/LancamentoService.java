package br.edu.uniesp.financeiro.service;

import br.edu.uniesp.financeiro.dto.FiltroLancamentoDTO;
import br.edu.uniesp.financeiro.dto.LancamentoRequestDTO;
import br.edu.uniesp.financeiro.entity.*;
import br.edu.uniesp.financeiro.repository.CategoriaRepository;
import br.edu.uniesp.financeiro.repository.LancamentoRepository;
import br.edu.uniesp.financeiro.repository.PessoaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço de Lancamento.
 * - Centraliza a lógica de criação, atualização, exclusão e busca (inclusive com filtro).
 */
@Service // Indica que esta classe é um serviço gerenciado pelo Spring
public class LancamentoService {

    private final LancamentoRepository repository;
    private final PessoaRepository pessoaRepository;
    private final CategoriaRepository categoriaRepository;

    // EntityManager permite criar consultas JPQL dinâmicas manualmente
    @PersistenceContext // Injeta o EntityManager gerenciado pelo Spring
    private EntityManager em; // Gerenciador de entidades JPA


    // -----------------------------||-------------------------------- \\ 

    public LancamentoService(LancamentoRepository repository, // Construtor com injeção de dependências
                             PessoaRepository pessoaRepository, // Repositório de Pessoa
                             CategoriaRepository categoriaRepository) { // Repositório de Categoria
        this.repository = repository;   // Repositório de Lancamento
        this.pessoaRepository = pessoaRepository; // Repositório de Pessoa
        this.categoriaRepository = categoriaRepository; // Repositório de Categoria
    }

    // -----------------------------||-------------------------------- \\ 

    /**
     * Cria um novo lançamento a partir do DTO.
     */
    public Lancamento salvar(LancamentoRequestDTO dto) {    // Recebe o DTO como parâmetro
        Lancamento l = new Lancamento();               // Cria uma nova instância de Lancamento
        preencherLancamento(l, dto);               // Preenche os campos do lançamento com os dados do DTO
        return repository.save(l);                // Salva a entidade no banco e retorna a entidade persistida
    }

    // -----------------------------||-------------------------------- \\ 

    /**
     * Atualiza um lançamento existente.
     */
    public Lancamento atualizar(Long id, LancamentoRequestDTO dto) {    // Recebe o ID e o DTO como parâmetros  
        Lancamento l = repository.findById(id)  // Busca o lançamento pelo ID
                .orElseThrow(() -> new RuntimeException("Lançamento não encontrado"));  // Lança exceção se não encontrar
        preencherLancamento(l, dto);               // Preenche os campos do lançamento com os dados do DTO
        return repository.save(l);                // Salva a entidade no banco e retorna a entidade persistida
    }

    // -----------------------------||-------------------------------- \\ 

    /**
     * Retorna a lista completa de lançamentos.
     */
    public List<Lancamento> listar() { // Retorna uma lista de lançamentos
        return repository.findAll();    // Busca todos os lançamentos no banco via repositório
    }

    // -----------------------------||-------------------------------- \\ 

    /**
     * Busca um lançamento pelo ID.
     */
    public Lancamento buscarPorId(Long id) { // Recebe o ID do lançamento como parâmetro
        return repository.findById(id) // Busca o lançamento pelo ID no repositório
                .orElseThrow(() -> new RuntimeException("Lançamento não encontrado")); // Lança exceção se não encontrar
    }

    // -----------------------------||-------------------------------- \\ 

    /**
     * Exclui um lançamento pelo ID.
     */
    public void excluir(Long id) { // Recebe o ID do lançamento como parâmetro
        repository.deleteById(id); // Exclui o lançamento pelo ID no repositório
    }

    // -----------------------------||-------------------------------- \\ 

    /**
     * Método interno para preencher um objeto Lancamento
     * a partir das informações do DTO.
     */
    private void preencherLancamento(Lancamento l, LancamentoRequestDTO dto) { // Recebe o objeto Lancamento e o DTO como parâmetros
        // Copia campos simples
        l.setDescricao(dto.descricao()); // Define a descrição do lançamento
        l.setDataVencimento(dto.dataVencimento());  // Define a data de vencimento do lançamento
        l.setDataPagamento(dto.dataPagamento()); // Define a data de pagamento do lançamento
        l.setValor(dto.valor()); // Define o valor do lançamento
        l.setObservacao(dto.observacao()); // Define a observação do lançamento

        // Converte String "RECEITA"/"DESPESA" para enum TipoLancamento
        l.setTipo(TipoLancamento.valueOf(dto.tipo().toUpperCase()));    // Define o tipo do lançamento

        // Busca a pessoa relacionada no banco (FK)
        Pessoa pessoa = pessoaRepository.findById(dto.idPessoa())   // Busca a pessoa pelo ID no repositório
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));  // Lança exceção se não encontrar
       
        // Busca a categoria relacionada no banco (FK)  
        Categoria categoria = categoriaRepository.findById(dto.idCategoria())   // Busca a categoria pelo ID no repositório
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));  // Lança exceção se não encontrar

        // Atribui as relações no objeto de domínio
        l.setPessoa(pessoa); // Define a pessoa associada ao lançamento
        l.setCategoria(categoria);// Define a categoria associada ao lançamento
    }

    // -----------------------------||-------------------------------- \\

    /**
     * Filtra lançamentos com base nos campos fornecidos em FiltroLancamentoDTO.
     * - Monta uma query JPQL dinâmica e adiciona parâmetros conforme os filtros preenchidos.
     */
    public List<Lancamento> filtrar(FiltroLancamentoDTO filtro) { // Recebe o DTO de filtro como parâmetro
        
        // StringBuilder para montar a consulta JPQL
        StringBuilder jpql = new StringBuilder("SELECT l FROM Lancamento l WHERE 1 = 1 "); // Início da consulta JPQL

        // Adiciona condições dinamicamente de acordo com os parâmetros
        if (filtro.dataInicial() != null) { // Parâmetro dataInicial
            jpql.append("AND l.dataVencimento >= :dataInicial "); // Parâmetro dataInicial
        }
        if (filtro.dataFinal() != null) { // Parâmetro dataFinal
            jpql.append("AND l.dataVencimento <= :dataFinal "); // Parâmetro dataFinal
        }
        if (filtro.tipo() != null && !filtro.tipo().isBlank()) {    // Parâmetro tipo
            jpql.append("AND l.tipo = :tipo "); // Parâmetro tipo
        }
        if (filtro.idCategoria() != null) { // Parâmetro idCategoria
            jpql.append("AND l.categoria.id = :idCategoria "); // Parâmetro idCategoria
        }
        if (filtro.idPessoa() != null) { // Parâmetro idPessoa
            jpql.append("AND l.pessoa.id = :idPessoa "); // Parâmetro idPessoa
        }

        // Cria a TypedQuery com o JPQL montado
        TypedQuery<Lancamento> query = em.createQuery(jpql.toString(), Lancamento.class); // Cria a consulta tipada

        // Define os parâmetros se eles estiverem presentes
        if (filtro.dataInicial() != null) { // Parâmetro dataInicial
            query.setParameter("dataInicial", filtro.dataInicial()); // Define o parâmetro dataInicial
        }
        if (filtro.dataFinal() != null) {// Parâmetro dataFinal
            query.setParameter("dataFinal", filtro.dataFinal()); // Define o parâmetro dataFinal
        }
        if (filtro.tipo() != null && !filtro.tipo().isBlank()) {    // Parâmetro tipo
            query.setParameter("tipo", TipoLancamento.valueOf(filtro.tipo().toUpperCase())); // Define o parâmetro tipo
        }
        if (filtro.idCategoria() != null) { // Parâmetro idCategoria
            query.setParameter("idCategoria", filtro.idCategoria()); // Define o parâmetro idCategoria
        }
        if (filtro.idPessoa() != null) { // Parâmetro idPessoa
            query.setParameter("idPessoa", filtro.idPessoa()); // Define o parâmetro idPessoa
        }

        // Executa a consulta e retorna os resultados
        return query.getResultList(); // Retorna a lista de lançamentos filtrados
    }
}

    // -----------------------------||-------------------------------- \\ 
