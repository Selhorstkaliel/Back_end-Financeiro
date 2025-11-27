package br.edu.uniesp.financeiro.controller;

import br.edu.uniesp.financeiro.dto.PessoaRequestDTO;
import br.edu.uniesp.financeiro.dto.PessoaResponseDTO;
import br.edu.uniesp.financeiro.entity.Pessoa;
import br.edu.uniesp.financeiro.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST responsável por expor endpoints HTTP para manipular
 * a entidade Pessoa.
 *
 * Aqui ficam as rotas:
 *   - POST    /pessoas        → criar pessoa
 *   - GET     /pessoas        → listar todas
 *   - GET     /pessoas/{id}   → buscar por ID
 *   - PUT     /pessoas/{id}   → atualizar
 *   - DELETE  /pessoas/{id}   → excluir
 */
@RestController                       // Indica que esta classe é um controller REST (retorna JSON)
@RequestMapping("/pessoas")           // Define o prefixo para as rotas 
public class PessoaController {

    // Injeção do serviço que contém a lógica de negócio
    private final PessoaService service;

    /**
     * Construtor utilizado pelo Spring para injetar o service.
     */
    public PessoaController(PessoaService service) {
        this.service = service;
    }

  // -----------------------------||-------------------------------- \\

    /**
     * Cria uma nova pessoa.
     * Método: POST
     * URL: /pessoas
     *
     */
    @PostMapping // Mapeia requisições POST para o HTTP 
    public ResponseEntity<PessoaResponseDTO> salvar(@RequestBody @Valid PessoaRequestDTO dto) { // Lê o corpo JSON e ativa validações
        Pessoa p = service.salvar(dto);              // Chama o service para salvar
        return ResponseEntity.ok(new PessoaResponseDTO(p)); // Converte para DTO e retorna
    }


    //@requestbody l~e o corpo da requisição em JSON e o converte para DTO 
    //@valid ativa as validações do DTO

    // -----------------------------||-------------------------------- \\
    /**
     * Lista todas as pessoas cadastradas.
     * Método: GET
     * URL: /pessoas
     *
     * Retorna uma lista de PessoaResponseDTO.
     */
    @GetMapping // Mapeia requisições GET para o HTTP
    public ResponseEntity<List<PessoaResponseDTO>> listar() { // Retorna uma lista de PessoaResponseDTO

        // Converte cada entidade Pessoa para PessoaResponseDTO

        List<PessoaResponseDTO> list = service.listar() // Chama o service para listar todas as pessoas
                .stream() // Transforma a lista em um stream para manipulação
                .map(PessoaResponseDTO::new)// Converte cada Pessoa para PessoaResponseDTO
                .toList();// Converte o stream de volta para lista

        // Retorna a lista em formato JSON
        return ResponseEntity.ok(list); // Retorna a lista como resposta HTTP
    }



    // -----------------------------||-------------------------------- \\

    /**
     * Busca uma pessoa pelo ID.
     * Método: GET
     * URL: /pessoas/{id}
     *
     * @PathVariable → extrai o "id" da URL.
     */
    @GetMapping("/{id}") // Mapeia requisições GET para o HTTP com o caminho /{id}
     public ResponseEntity<PessoaResponseDTO> buscarPorId(@PathVariable Long id) { // Extrai o id da URL
        Pessoa p = service.buscarPorId(id); // Busca a pessoa via service
        return ResponseEntity.ok(new PessoaResponseDTO(p)); // Retorna a pessoa como resposta HTTP
    }

    //@pathvariable lê os valores da URL e os atribui aos parâmetros do método

    // -----------------------------||-------------------------------- \\ 

    /**
     * Atualiza os dados de uma pessoa.
     * Método: PUT
     * URL: /pessoas/{id}
     *
     * Recebe o DTO com os novos dados e aplica a atualização.
     */
    @PutMapping("/{id}") // Mapeia requisições PUT para /pessoas/{id}   
    public ResponseEntity<PessoaResponseDTO> atualizar(
            @PathVariable Long id, // Extrai o ID da URL
            @RequestBody @Valid PessoaRequestDTO dto) { // Lê o corpo JSON com os novos dados

        Pessoa p = service.atualizar(id, dto);               // Atualiza no service
        return ResponseEntity.ok(new PessoaResponseDTO(p));  // Retorna JSON atualizado
    }

    // @pathvariable lê os valores da url e os atribui aos paâmetros do método
    // @requestbody lê o corpo da requisição JSON e o converte para DTO
    // @valid ativa as validações da DTO

    // -----------------------------||-------------------------------- \\


    /**
     * Remove uma pessoa pelo ID.
     * Método: DELETE
     * URL: /pessoas/{id}
     *
     * Retorna apenas HTTP 204 (sem conteúdo).
     */
    @DeleteMapping("/{id}") // Mapeia requisições DELETE para /pessoas/{id}
    public ResponseEntity<Void> excluir(@PathVariable Long id) { // Extrai o ID da URL

        service.excluir(id);                    // Remove via service

        return ResponseEntity.noContent().build(); // Retorna status 204
    }
}


    // @ pathvariable lê os valores da url e os atribui aos parâmetros do método 

// -----------------------------||-------------------------------- \\ 