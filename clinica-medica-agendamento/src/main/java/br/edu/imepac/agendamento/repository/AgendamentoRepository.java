package br.edu.imepac.agendamento.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import br.edu.imepac.agendamento.repository.AgendamentoRepository;


import br.edu.imepac.agendamento.model.Agendamento;
import br.edu.imepac.agendamento.model.Paciente;
import br.edu.imepac.agendamento.model.Medico;

public interface AgendamentoRepository {
    void salvar(Agendamento agendamento);

    List<Agendamento> buscarPorPacienteEData(Paciente paciente, LocalDate data);

    List<Agendamento> buscarPorMedicoEDataHora(Medico medico, LocalDateTime dataHora);
}
