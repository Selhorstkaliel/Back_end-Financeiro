package br.edu.uniesp.financeiro;

// Importa a anotação que marca a aplicação como Spring Boot
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação.
 * - @SpringBootApplication informa ao Spring Boot que esta é a classe de inicialização.
 * - Contém o método main, que é o ponto de entrada da aplicação Java.
 */
@SpringBootApplication
public class FinanceiroApplication {

    /**
     * Método main: ponto de entrada da aplicação.
     * - SpringApplication.run(...) inicia todo o contexto do Spring,
     *   sobe o servidor embutido (Tomcat) e configura os beans.
     */
    public static void main(String[] args) {
        SpringApplication.run(FinanceiroApplication.class, args);
    }
}
