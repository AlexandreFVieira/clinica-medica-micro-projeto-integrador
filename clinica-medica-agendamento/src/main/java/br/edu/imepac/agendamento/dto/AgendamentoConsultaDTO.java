package br.edu.imepac.agendamento.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class AgendamentoConsultaDTO {

    @NotNull(message = "Id do paciente é obrigatório.")
    public Long idPaciente;

    // Médico é opcional
    public Long idMedico;

    @NotNull(message = "Data e hora da consulta são obrigatórias.")
    @Future(message = "A data/hora deve estar no futuro.")
    public LocalDateTime dataHora;

    public String sintomas;
}