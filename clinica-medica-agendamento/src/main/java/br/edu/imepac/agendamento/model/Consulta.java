package br.edu.imepac.agendamento.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idPaciente;

    private Long idMedico;

    private LocalDateTime dataHora;

    private boolean estaAtiva = true;

    private boolean eRetorno = false;

    private String sintomas;

    @Enumerated(EnumType.STRING)
    private MotivoCancelamento motivoCancelamento;

    // Getters e Setters
}
