package br.edu.imepac.atendimento.controllers;

import br.edu.imepac.comum.dtos.consulta.ConsultaDto;
import br.edu.imepac.comum.dtos.consulta.ConsultaRequest;
import br.edu.imepac.comum.services.ConsultaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConsultaDto criarConsulta(@RequestBody ConsultaRequest consultaRequest) {
        log.info("Recebida requisição para criar nova consulta: {}", consultaRequest);
        return consultaService.criarConsulta(consultaRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ConsultaDto atualizarConsulta(@PathVariable Long id, @RequestBody ConsultaDto consultaDto) {
        log.info("Recebida requisição para atualizar consulta com ID {}: {}", id, consultaDto);
        return consultaService.atualizarConsulta(id, consultaDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirConsulta(@PathVariable Long id) {
        log.info("Recebida requisição para remover consulta com ID: {}", id);
        consultaService.excluirConsulta(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ConsultaDto buscarConsultaPorId(@PathVariable Long id) {
        log.info("Recebida requisição para buscar consulta com ID: {}", id);
        return consultaService.buscarConsultaPorId(id);
    }

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<ConsultaDto> listarConsultas() {
        log.info("Recebida requisição para listar todas as consultas");
        return consultaService.listarConsultas();
    }
}