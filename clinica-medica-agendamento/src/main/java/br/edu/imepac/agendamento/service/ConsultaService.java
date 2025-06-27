package br.edu.imepac.agendamento.service;

import br.edu.imepac.agendamento.dto.AgendamentoConsultaDTO;
import br.edu.imepac.agendamento.dto.CancelamentoConsultaDTO;
import br.edu.imepac.agendamento.model.Consulta;
import br.edu.imepac.agendamento.model.MotivoCancelamento;
import br.edu.imepac.agendamento.repository.ConsultaRepository;
import br.edu.imepac.agendamento.feign.PacienteClient;
import br.edu.imepac.agendamento.feign.MedicoClient;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteClient pacienteClient;
    private final MedicoClient medicoClient;

    public ConsultaService(ConsultaRepository consultaRepository,
                           PacienteClient pacienteClient,
                           MedicoClient medicoClient) {
        this.consultaRepository = consultaRepository;
        this.pacienteClient = pacienteClient;
        this.medicoClient = medicoClient;
    }

    @Transactional
    public Consulta agendarConsulta(AgendamentoConsultaDTO dto) {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime dataHora = dto.dataHora;

        // 1. Consulta deve ser com no mínimo 30 minutos de antecedência
        if (dataHora.isBefore(agora.plusMinutes(30))) {
            throw new RuntimeException("Consultas devem ser agendadas com pelo menos 30 minutos de antecedência.");
        }

        // 2. A clínica só funciona de segunda a sábado, das 07h às 19h
        if (dataHora.getHour() < 7 || dataHora.getHour() >= 19 ||
                dataHora.getDayOfWeek() == DayOfWeek.SUNDAY) {
            throw new RuntimeException("Horário inválido: a clínica só funciona de segunda a sábado, das 07h às 19h.");
        }

        // 3. Verifica se o paciente está ativo via outro microserviço
        if (!pacienteClient.estaAtivo(dto.idPaciente)) {
            throw new RuntimeException("O paciente está inativo.");
        }

        // 4. Verifica se já tem outra consulta no mesmo dia
        boolean pacienteJaTemConsulta = consultaRepository
                .existsByIdPacienteAndDataHoraBetween(dto.idPaciente,
                        dataHora.toLocalDate().atStartOfDay(),
                        dataHora.toLocalDate().atTime(23, 59));
        if (pacienteJaTemConsulta) {
            throw new RuntimeException("Paciente já possui consulta agendada para este dia.");
        }

        Long idMedico = dto.idMedico;

        if (idMedico == null) {
            // 5. Selecionar médico aleatório disponível (exemplo simplificado)
            idMedico = medicoClient.buscarMedicoAleatorioDisponivel(dataHora);
        } else {
            // 6. Verifica se o médico está ativo
            if (!medicoClient.estaAtivo(idMedico)) {
                throw new RuntimeException("O médico está inativo.");
            }

            // 7. Verifica se o médico já tem consulta nesse horário
            if (consultaRepository.existsByIdMedicoAndDataHora(idMedico, dataHora)) {
                throw new RuntimeException("O médico já tem uma consulta neste horário.");
            }
        }

        // 8. Criar e salvar a consulta
        Consulta consulta = new Consulta();
        consulta.setIdPaciente(dto.idPaciente);
        consulta.setIdMedico(idMedico);
        consulta.setDataHora(dataHora);
        consulta.setSintomas(dto.sintomas);

        return consultaRepository.save(consulta);
    }

    @Transactional
    public void cancelarConsulta(CancelamentoConsultaDTO dto) {
        Consulta consulta = consultaRepository.findById(dto.idConsulta)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        LocalDateTime agora = LocalDateTime.now();
        if (consulta.getDataHora().isBefore(agora.plusHours(24))) {
            throw new RuntimeException("Consultas só podem ser canceladas com no mínimo 24 horas de antecedência.");
        }

        if (dto.motivo == null) {
            throw new RuntimeException("Motivo do cancelamento é obrigatório.");
        }

        consulta.setEstaAtiva(false);
        consulta.setMotivoCancelamento(dto.motivo);

        consultaRepository.save(consulta);
    }
}