package br.edu.imepac.agendamento.dto;
import br.edu.imepac.agendamento.model.MotivoCancelamento;

import jakarta.validation.constraints.NotNull;

public class CancelamentoConsultaDTO {

    @NotNull(message = "Id da consulta é obrigatório.")
    public Long idConsulta;

    @NotNull(message = "Motivo do cancelamento é obrigatório.")
    public MotivoCancelamento motivo;
}