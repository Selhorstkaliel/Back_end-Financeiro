package br.edu.uniesp.financeiro.service;

import br.edu.uniesp.financeiro.dto.CategoriaRequestDTO;
import br.edu.uniesp.financeiro.entity.Categoria;
import br.edu.uniesp.financeiro.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço de Categoria.
 * - Responsável por orquestrar operações relacionadas a categorias.
 */
@Service // Indica que esta classe é um serviço gerenciado pelo Spring
public class CategoriaService {

    private final CategoriaRepository repository; // Dependência do repositório, injetado pelo Spring via construtor

    public CategoriaService(CategoriaRepository repository) { // Construtor com injeção de dependência
        this.repository = repository;// Atribui o repositório injetado ao atributo da classe
    }

    // -----------------------------||-------------------------------- \\ 

    /**
     * Cria e persiste uma nova categoria a partir do DTO de entrada.
     */
    public Categoria salvar(CategoriaRequestDTO dto) { // Recebe o DTO como parâmetro
        Categoria c = new Categoria(); // Cria uma nova instância de Categoria
        c.setNome(dto.nome()); // Preenche os campos da entidade com os dados do DTO
        return repository.save(c);  // Salva a entidade no banco e retorna a entidade persistida
    }

    // -----------------------------||-------------------------------- \\ 

    /**
     * Retorna todas as categorias cadastradas.
     */
    public List<Categoria> listar() { // Retorna uma lista de categorias
        return repository.findAll(); // Busca todas as categorias no banco via repositório
    }

    // -----------------------------||-------------------------------- \\ 

    /**
     * Busca uma categoria pelo ID, lançando exceção se não existir.
     */
    public Categoria buscarPorId(Long id) { // Recebe o ID da categoria como parâmetro
        return repository.findById(id) // Busca a categoria pelo ID no repositório
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada")); // Lança exceção se não encontrar
    }

    // -----------------------------||-------------------------------- \\ 

    /**
     * Atualiza uma categoria existente a partir dos dados do DTO.
     */
    public Categoria atualizar(Long id, CategoriaRequestDTO dto) { // Recebe o ID e o DTO como parâmetros
        Categoria c = buscarPorId(id); // Busca a categoria pelo ID
        c.setNome(dto.nome()); // Atualiza o nome da categoria
        return repository.save(c); // Salva a entidade atualizada no banco e retorna a entidade persistida
    }

    // -----------------------------||-------------------------------- \\ 

    /**
     * Exclui uma categoria pelo ID.
     */
    public void excluir(Long id) { // Recebe o ID da categoria como parâmetro
        repository.deleteById(id); // Exclui a categoria pelo ID no repositório
    }
}

    // -----------------------------||-------------------------------- \\ 
