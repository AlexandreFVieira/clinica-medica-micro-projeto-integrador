package br.edu.imepac.agendamento.controller;

import br.edu.imepac.agendamento.dto.NovoAgendamentoDTO;
import br.edu.imepac.agendamento.model.Medico;
import br.edu.imepac.agendamento.model.Paciente;
import br.edu.imepac.agendamento.repository.MedicoRepository;
import br.edu.imepac.agendamento.repository.PacienteRepository;
import br.edu.imepac.agendamento.service.AgendamentoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


import br.edu.imepac.agendamento.model.Agendamento;
@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    public AgendamentoController(AgendamentoService agendamentoService,
                                 PacienteRepository pacienteRepository,
                                 MedicoRepository medicoRepository) {
        this.agendamentoService = agendamentoService;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }

    @PostMapping
    public ResponseEntity<Void> agendar(@RequestBody NovoAgendamentoDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.getIdPaciente())
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));

        Medico medico = medicoRepository.findById(dto.getIdMedico())
                .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado"));

        agendamentoService.agendarConsulta(paciente, medico, dto.getDataHora());

        return ResponseEntity.ok().build();
    }
}
