package br.edu.imepac.atendimento.controllers;

import br.edu.imepac.comum.dtos.paciente.PacienteDto;
import br.edu.imepac.comum.dtos.paciente.PacienteRequest;
import br.edu.imepac.comum.models.Paciente;
import br.edu.imepac.comum.services.PacienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PacienteDto adicionarPaciente (@RequestBody PacienteRequest pacienteRequest){
        log.info("Recebia requisião para criar novo paciente: {}", pacienteRequest);
        return pacienteService.adicionarPaciente(pacienteRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PacienteDto atualizarPaciente(@PathVariable Long id, @RequestBody PacienteDto pacienteDto){
        log.info("Recebida requisição para atualizar paciente com ID{}: {}", id, pacienteDto);
        return pacienteService.atualizarPaciente(id, pacienteDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerPaciente(@PathVariable Long id){
        log.info("Recebia requisição para remover paciente com ID: {}", id);
        pacienteService.removerPaciente(id);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PacienteDto buscarPacientePorId(@PathVariable Long id){
        log.info("Recebida requisição para buscar paciente com ID: {}", id);
        return pacienteService.buscarPacientePorId(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PacienteDto> listarTodosPacientes(){
        log.info("Recebida requisição para listar todos os pacientes");
        return pacienteService.listarTodosPacientes();
    }
}
