package br.edu.uniesp.financeiro.controller;

import br.edu.uniesp.financeiro.dto.CategoriaRequestDTO;
import br.edu.uniesp.financeiro.dto.CategoriaResponseDTO;
import br.edu.uniesp.financeiro.entity.Categoria;
import br.edu.uniesp.financeiro.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST de Categoria.
 * - Manipula as rotas relacionadas à entidade Categoria.
 */
@RestController // indica ao spring que esta clase é um REST e retorna um JSON 
@RequestMapping("/categorias") // define o prefixo para as rotas 
public class CategoriaController {

    private final CategoriaService service; // injeção do serviço que contém a lógica de negócio

    public CategoriaController(CategoriaService service) { // construtor utilizado pelo spring para injetar o service
        this.service = service; // atribui o service injetado ao atributo da classe
    }

    // -----------------------------||-------------------------------- \\
    /**
     * Cria uma nova categoria.
     * - POST /categorias
     */
    @PostMapping // mapeia requisições post para o HTTP 
    public ResponseEntity<CategoriaResponseDTO> salvar(@RequestBody @Valid CategoriaRequestDTO dto) { // lê o corpo da requisição JSON e converte para DTO, ativa as validações do DTO
        Categoria c = service.salvar(dto); // chama o service para salvar
        return ResponseEntity.ok(new CategoriaResponseDTO(c)); // converte para DTO e retorna
    }

    // @requestbody lê o corpo da requisição JSON e converte para DTO
    // @valid ativa  avalidaçães do DTO


    // -----------------------------||-------------------------------- \\
    /**
     * Lista todas as categorias.
     * - GET /categorias
     */
    @GetMapping // mapeia requisições get para o HTTP
    public ResponseEntity<List<CategoriaResponseDTO>> listar() { // retorna uma lista de CategoriaResponseDTO
        List<CategoriaResponseDTO> list = service.listar() // chama o service para listar todas as categorias
                .stream()// transforma a lista em um stream para manipulação
                .map(CategoriaResponseDTO::new) // converte cada Categoria para CategoriaResponseDTO
                .toList(); // converte o stream de volta para lista
        return ResponseEntity.ok(list);// retorna a lista como resposta HTTP
    }

    // -----------------------------||-------------------------------- \\

    /**
     * Busca categoria por ID.
     * - GET /categorias/{id}
     */
    @GetMapping("/{id}") // mapeia requisições get para o HTTP com um parametro de caminho {id}
    public Categoria buscarPorId(@PathVariable("id") Long id) { // extrai o valor do parâmetro de caminho {id} e o atribui ao parâmetro id do método
    return service.buscarPorId(id);// chama o service para buscar a categoria pelo ID e retorna a entidade Categoria
    }


    // @pathvariable extrai valores da URl e os atribui aos parametros do método 
    
    // -----------------------------||-------------------------------- \\
    /**
     * Atualiza uma categoria existente.
     * - PUT /categorias/{id}
     */
    @PutMapping("/{id}") // mapeia requisições put para o HTTP com o caminho /{id}
    public ResponseEntity<CategoriaResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid CategoriaRequestDTO dto) { // extrai o id da URL, lê o corpo da requisição JSON e converte para DTO, ativa as validações do DTO
        Categoria c = service.atualizar(id, dto);// atualiza a categoria via service
        return ResponseEntity.ok(new CategoriaResponseDTO(c));// converte para DTO e retorna
    }

    // @pathvariable lê os valores da URL e os atribui aos parâmetros do método
    // @requestbody lê o corpo da requisição JSON e o converte para DTO 
    // @valid ativa a validações do DTO

    // -----------------------------||-------------------------------- \\

    /**
     * Exclui uma categoria.
     * - DELETE /categorias/{id}
     */
    @DeleteMapping("/{id}") // mapeia requisições delete para o HTTP com o caminho /{id}
    public ResponseEntity<Void> excluir(@PathVariable Long id) { // extrai o id da URL
        service.excluir(id);// chama o service para excluir a categoria
        return ResponseEntity.noContent().build();// retorna status 204 (sem conteúdo)
    }
}

    //@pathvariable lê os valores da url e os atribui aos parâmetros do método 


    //-----------------------------||-------------------------------- \\