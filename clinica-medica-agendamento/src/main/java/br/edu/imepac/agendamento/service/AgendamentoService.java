package br.edu.imepac.agendamento.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.util.List;

import br.edu.imepac.agendamento.model.Agendamento;
import br.edu.imepac.agendamento.model.Paciente;
import br.edu.imepac.agendamento.model.Medico;

public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    public void agendarConsulta(Paciente paciente,
                                Medico medico,
                                LocalDateTime dataHora) {

        // 1️⃣ Verificação de horário de funcionamento
        verificarHorarioDeFuncionamento(dataHora);

        // 2️⃣ Verificação de antecedência mínima de 30 minutos
        verificarAntecedencia(dataHora);

        // 3️⃣ Verificação de status ativo do paciente e médico
        verificarAtivos(paciente, medico);

        // 4️⃣ Verificação de consulta única por dia para o paciente
        verificarConsultaUnicaPorDia(paciente, dataHora);

        // 5️⃣ Verificação de disponibilidade do médico
        verificarDisponibilidadeMedico(medico, dataHora);

        // ✅ Se todas as verificações passarem, cria e salva o agendamento
        Agendamento agendamento = new Agendamento(paciente, medico, dataHora);
        agendamentoRepository.salvar(agendamento);
    }

    private void verificarHorarioDeFuncionamento(LocalDateTime dataHora) {
        DayOfWeek dia = dataHora.getDayOfWeek();
        LocalTime hora = dataHora.toLocalTime();

        boolean diaUtil = dia != DayOfWeek.SUNDAY;
        boolean horarioValido = !hora.isBefore(LocalTime.of(7, 0))
                && !hora.isAfter(LocalTime.of(19, 0));

        if (!diaUtil || !horarioValido) {
            throw new IllegalArgumentException("A consulta deve ocorrer de segunda a sábado, das 07:00 às 19:00.");
        }
    }

    private void verificarAntecedencia(LocalDateTime dataHora) {
        if (dataHora.isBefore(LocalDateTime.now().plusMinutes(30))) {
            throw new IllegalArgumentException("A consulta deve ser agendada com no mínimo 30 minutos de antecedência.");
        }
    }

    private void verificarAtivos(Paciente paciente, Medico medico) {
        if (!paciente.isAtivo()) {
            throw new IllegalArgumentException("Não é possível agendar para um paciente inativo.");
        }
        if (medico != null && !medico.isAtivo()) {
            throw new IllegalArgumentException("Não é possível agendar para um médico inativo.");
        }
    }

    private void verificarConsultaUnicaPorDia(Paciente paciente, LocalDateTime dataHora) {
        List<Agendamento> agendamentos = agendamentoRepository.buscarPorPacienteEData(paciente, dataHora.toLocalDate());
        if (!agendamentos.isEmpty()) {
            throw new IllegalArgumentException("O paciente já tem uma consulta marcada para esta data.");
        }
    }

    private void verificarDisponibilidadeMedico(Medico medico, LocalDateTime dataHora) {
        List<Agendamento> agendamentos = agendamentoRepository.buscarPorMedicoEDataHora(medico, dataHora);
        if (!agendamentos.isEmpty()) {
            throw new IllegalArgumentException("O médico não está disponível neste horário.");
        }
    }
}
