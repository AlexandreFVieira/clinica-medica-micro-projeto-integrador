package br.edu.imepac.atendimento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication//Escaneia pacotes para encontrar Controllers, Services, etc.
//scanBasePackages = "br.edu.imepac.atendimento"

@EnableJpaRepositories /* Diz ao Spring onde encontrar os Repositories (que estão no módulo comum)
        basePackages = "br.edu.imepac.comum.repositories"*/

@EntityScan /*Diz ao Spring onde encontrar as Entidades (que estão no módulo comum)
        basePackages = "br.edu.imepac.comum.models"*/

public class ClinicaMedicaAtendimentoApp {
    public static void main(String[] args) {
        SpringApplication.run(ClinicaMedicaAtendimentoApp.class, args);
    }
}
