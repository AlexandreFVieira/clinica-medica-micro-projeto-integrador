package br.edu.imepac.agendamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "br.edu.imepac.agendamento.feign")
public class ClinicaMedicaAgendamentoApp {
    public static void main(String[] args) {
        SpringApplication.run(ClinicaMedicaAgendamentoApp.class, args);
    }
}
