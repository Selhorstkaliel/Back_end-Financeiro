package br.edu.uniesp.financeiro.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

/**
 * Classe de valor Endereco.
 * - Marcada com @Embeddable, indica que NÃO é uma tabela separada no banco.
 * - Seus campos serão "embutidos" na tabela da entidade que o usar com @Embedded (Pessoa).
 */
@Embeddable // Indica que esta classe pode ser embutida em outras entidades JPA
public class Endereco {

    // Logradouro do endereço (rua, avenida, etc.)
    @NotBlank
    private String logradouro;

    // Número do imóvel
    @NotBlank
    private String numero;

    // Complemento (apto, bloco, etc.), pode ser nulo
    private String complemento;

    // Bairro do endereço
    @NotBlank
    private String bairro;

    // CEP (código postal)
    @NotBlank
    private String cep;

    // Cidade
    @NotBlank
    private String cidade;

    // Estado (UF)
    @NotBlank
    private String estado;

    // Getters e setters padrão para o JPA e para uso da aplicação

    public String getLogradouro() { return logradouro; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getComplemento() { return complemento; }
    public void setComplemento(String complemento) { this.complemento = complemento; }

    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
