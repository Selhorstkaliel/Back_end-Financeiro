package br.edu.uniesp.financeiro.service;

import br.edu.uniesp.financeiro.dto.PessoaRequestDTO;
import br.edu.uniesp.financeiro.entity.Pessoa;
import br.edu.uniesp.financeiro.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Camada de serviço de Pessoa.
 * - Contém a regra de negócio relacionada a Pessoa.
 * - Faz a ponte entre o controller (API) e o repositório (banco).
 */
@Service // Indica que esta classe é um serviço gerenciado pelo Spring
public class PessoaService {

    // Dependência do repositório, injetado pelo Spring via construtor
    private final PessoaRepository repository;

    // -----------------------------||-------------------------------- \\ 
    
    /**
     * Construtor com injeção de dependência.
     * - O Spring injeta automaticamente uma instância de PessoaRepository aqui.
     */
    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    
    // -----------------------------||-------------------------------- \\ 

    /**
     * Cria e salva uma nova Pessoa a partir do DTO de entrada.
     */
    public Pessoa salvar(PessoaRequestDTO dto) { // Recebe o DTO como parâmetro
        Pessoa p = new Pessoa(); // Cria uma nova instância de Pessoa
        p.setNome(dto.nome()); // Preenche os campos da entidade com os dados do DTO
        p.setAtivo(dto.ativo()); // Preenche o campo "ativo" da entidade com o dado do DTO
        p.setEndereco(dto.endereco()); // Preenche o campo "endereco" da entidade com o dado do DTO
        return repository.save(p);  // Salva a entidade no banco e retorna a entidade persistida
    }


    // -----------------------------||-------------------------------- \\ 
    
    /**
     * Retorna a lista completa de pessoas cadastradas.
     */
    public List<Pessoa> listar() {  // Retorna uma lista de pessoas
        return repository.findAll(); // Busca todas as pessoas no banco via repositório
    }

    // -----------------------------||-------------------------------- \\ 

    /**
     * Busca uma pessoa pelo ID.
     * - Se não encontrar, lança uma RuntimeException.
     */
    public Pessoa buscarPorId(Long id) { // Recebe o ID da pessoa como parâmetro
        return repository.findById(id)  // Busca a pessoa pelo ID no repositório
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));  // Lança exceção se não encontrar
    }

    // -----------------------------||-------------------------------- \\ 

    /**
     * Atualiza os dados de uma pessoa existente.
     * - Primeiro busca pelo ID, depois altera os campos e salva novamente.
     */
    public Pessoa atualizar(Long id, PessoaRequestDTO dto) { // Recebe o ID e o DTO como parâmetros
        Pessoa p = buscarPorId(id); // Busca a pessoa existente pelo ID
         // Atualiza os campos da entidade com os dados do DTO
        p.setNome(dto.nome());// Atualiza o nome
        p.setAtivo(dto.ativo()); // Atualiza o status ativo
        p.setEndereco(dto.endereco());// Atualiza o endereço
        return repository.save(p);//  Salva a entidade atualizada no banco e retorna a entidade persistida
    }

    // -----------------------------||-------------------------------- \\ 

    /**
     * Exclui uma pessoa pelo ID.
     */
    public void excluir(Long id) { // Recebe o ID da pessoa como parâmetro
        repository.deleteById(id);// Chama o repositório para excluir a pessoa pelo ID
    }
}

    // -----------------------------||-------------------------------- \\ 