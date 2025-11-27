package br.edu.uniesp.financeiro.controller;

import br.edu.uniesp.financeiro.dto.FiltroLancamentoDTO;
import br.edu.uniesp.financeiro.dto.LancamentoRequestDTO;
import br.edu.uniesp.financeiro.dto.LancamentoResponseDTO;
import br.edu.uniesp.financeiro.entity.Lancamento;
import br.edu.uniesp.financeiro.service.LancamentoService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Controller REST de Lancamento.
 * - Fornece endpoints para CRUD de lançamentos e um endpoint especial de filtro.
 */
@RestController // indica para o spring que esta classe é um rest e retorna um (JSON)
@RequestMapping("/lancamentos") // define o prefixo para as rotas 
public class LancamentoController { 

    private final LancamentoService service; // injeção do serviço que contém a lógica de negócio

    public LancamentoController(LancamentoService service) { // construtor utilizado pelo spring para injetar o service
        this.service = service; // atribui o service injetado ao atributo da classe
    }


    // -----------------------------||-------------------------------- \\ 
    /**
     * Cria um novo lançamento.
     * - POST /lancamentos
     */
    @PostMapping // mapeia requisições post para o HTTP 
    public ResponseEntity<LancamentoResponseDTO> salvar(@RequestBody @Valid LancamentoRequestDTO dto) { // lê o corpo da requisição JSON e converte para DTO, ativa as validações do DTO
        Lancamento l = service.salvar(dto); // chama o service para salvar
        return ResponseEntity.ok(new LancamentoResponseDTO(l)); // converte para DTO e retorna
    }
    // @resquestbody lê o corpo da requisição JSON e converte para DTO
    // @valid ativa as validações do DTO 


    // -----------------------------||-------------------------------- \\ 

    /**
     * Lista todos os lançamentos.
     * - GET /lancamentos
     */
    @GetMapping // mapeia requisições get para o HTTP 
    public ResponseEntity<List<LancamentoResponseDTO>> listar() { // retorna uma lista de LancamentoResponseDTO
        List<LancamentoResponseDTO> list = service.listar()//  chama o service para listar todos os lançamentos
                .stream() // transforma a lista em um stream para manipulação
                .map(LancamentoResponseDTO::new) // converte cada Lancamento para LancamentoResponseDTO
                .toList(); // converte o stream de volta para lista
        return ResponseEntity.ok(list); // retorna a lista como resposta HTTP
    }

    // -----------------------------||-------------------------------- \\

    /**
     * Busca um lançamento pelo ID.
     * - GET /lancamentos/{id}
     */
    @GetMapping("/{id}") // mapeia requisições get para o HTTP com o caminho /{id}
    public ResponseEntity<LancamentoResponseDTO> buscarPorId(@PathVariable Long id) { // extrai o id da URL
        Lancamento l = service.buscarPorId(id); // busca o lançamento via service
        return ResponseEntity.ok(new LancamentoResponseDTO(l)); // converte para DTO e retorna
    }

    // @pathVariable lê os valores da URl e os atribui aos parâmetros do método 

    // -----------------------------||-------------------------------- \\

    /**
     * Atualiza um lançamento existente.
     * - PUT /lancamentos/{id}
     */
    @PutMapping("/{id}") // mapeia requisições put para o HTTP com o caminho /{id}
    public ResponseEntity<LancamentoResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid LancamentoRequestDTO dto) { // extrai o id da URL e lê o corpo JSON com os novos dados
        Lancamento l = service.atualizar(id, dto); // atualiza o lançamento via service
        return ResponseEntity.ok(new LancamentoResponseDTO(l)); // converte para DTO e retorna
    }

    // @pathVariable lê os valores da url e os atribui aos parametros do método
    // @requestbody lê oo corpo da requisição JSON e converte para DTO
    // @valid ativa as validações do DTO 


    // -----------------------------||-------------------------------- \\

    /**
     * Remove um lançamento.
     * - DELETE /lancamentos/{id}
     */
    @DeleteMapping("/{id}")// mapeia requisições delete para o HTTP com o caminho /{id}
    public ResponseEntity<Void> excluir(@PathVariable Long id) { // extrai o id da URL
        service.excluir(id); // remove o lançamento via service
        return ResponseEntity.noContent().build(); // retorna status 204 (sem conteúdo)
    }
    
    // @pathvariable lê os valores da URL e os atribui aos parametros do método



    // -----------------------------||-------------------------------- \\

    /**
     * Endpoint de filtro de lançamentos.
     * - GET /lancamentos/filtro
     * - Todos os parâmetros são opcionais, passados via query string.
     *
     * Exemplo de chamada:
     *   /lancamentos/filtro?dataInicial=2025-01-01&dataFinal=2025-12-31&tipo=RECEITA&idCategoria=1&idPessoa=2
     */
    @GetMapping("/filtro") // mapeia requisições get para o HTTP com o caminho /filtro
    public List<Lancamento> filtrar( // define os parâmetros de filtro
            // Datas no formato ISO (yyyy-MM-dd), aceitas opcionalmente
            @RequestParam(required = false) // indica que o parâmetro é opcional
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) // define o formato da data como ISO
            LocalDate dataInicial, // data inicial

            @RequestParam(required = false) // indica que o parâmetro é opcional
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) // define o formato da data como ISO
            LocalDate dataFinal, // data final

            // Tipo de lançamento (RECEITA/DESPESA)
            @RequestParam(required = false) // indica que o parâmetro é opcional
            String tipo, // tipo de lançamento

            // ID da categoria
            @RequestParam(required = false) // indica que o parâmetro é opcional
            Long idCategoria,   // ID da categoria

            // ID da pessoa
            @RequestParam(required = false) // indica que o parâmetro é opcional
            Long idPessoa   // ID da pessoa
    ) {
        // Monta o DTO de filtro a partir dos parâmetros recebidos
        FiltroLancamentoDTO filtro = new FiltroLancamentoDTO( // cria um novo objeto FiltroLancamentoDTO
                dataInicial,    // data inicial
                dataFinal, // data final
                tipo, // tipo de lançamento
                idCategoria,   // ID da categoria
                idPessoa    // ID da pessoa
        );

        // Delegamos para o serviço executar a lógica de filtro
        return service.filtrar(filtro); // retorna a lista de lançamentos filtrados
    }
}

    //@ requestparam lê os parâmetros da query string e os atribui aos parametros do método 
    //@ dataTimeFormat define o formato esperado para datas nos parâmetros 

  // -----------------------------||-------------------------------- \\