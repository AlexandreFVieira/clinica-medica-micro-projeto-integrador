package br.edu.imepac.agendamento.controller;

import br.edu.imepac.agendamento.dto.AgendamentoConsultaDTO;
import br.edu.imepac.agendamento.dto.CancelamentoConsultaDTO;
import br.edu.imepac.agendamento.model.Consulta;
import br.edu.imepac.agendamento.service.ConsultaService;
import br.edu.imepac.agendamento.feign.MedicoClient;
import br.edu.imepac.agendamento.feign.PacienteClient;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;
    private final MedicoClient medicoClient;
    private final PacienteClient pacienteClient;

    public ConsultaController(ConsultaService consultaService,
                              MedicoClient medicoClient,
                              PacienteClient pacienteClient) {
        this.consultaService = consultaService;
        this.medicoClient = medicoClient;
        this.pacienteClient = pacienteClient;
    }

    /**
     * Agendar uma nova consulta.
     */
    @PostMapping
    public ResponseEntity<Consulta> agendarConsulta(@RequestBody @Valid AgendamentoConsultaDTO dto) {
        Consulta consulta = consultaService.agendarConsulta(dto);
        return ResponseEntity.ok(consulta);
    }

    /**
     * Cancelar uma consulta existente.
     */
    @PostMapping("/cancelamento")
    public ResponseEntity<Void> cancelarConsulta(@RequestBody @Valid CancelamentoConsultaDTO dto) {
        consultaService.cancelarConsulta(dto);
        return ResponseEntity.noContent().build();
    }

    /**
     * Listar médicos (nome, e-mail, CRM, especialidade) ordenados por nome.
     */
    @GetMapping("/medicos")
    public ResponseEntity<List<Map<String, Object>>> listarMedicos() {
        List<Map<String, Object>> medicos = medicoClient.listarMedicosOrdenados();
        return ResponseEntity.ok(medicos);
    }

    /**
     * Listar pacientes paginados (nome, e-mail, CPF), ordenados por nome.
     */
    @GetMapping("/pacientes")
    public ResponseEntity<Page<Map<String, Object>>> listarPacientes(@RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Map<String, Object>> pacientes = pacienteClient.listarPacientesOrdenados(pageable);
        return ResponseEntity.ok(pacientes);
    }
}
