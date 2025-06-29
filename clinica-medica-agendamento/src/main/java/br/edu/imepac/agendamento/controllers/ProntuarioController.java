package br.edu.imepac.agendamento.controllers;


import br.edu.imepac.comum.dtos.prontuario.ProntuarioDto;
import br.edu.imepac.comum.dtos.prontuario.ProntuarioRequest;
import br.edu.imepac.comum.services.ProntuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/prontuario")
public class ProntuarioController {
    private final ProntuarioService prontuarioService;

    public ProntuarioController(ProntuarioService prontuarioService){
        this.prontuarioService = prontuarioService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProntuarioDto criarProntuario(@RequestBody ProntuarioRequest prontuarioRequest){
        log.info("Criando um Prontuario - controller: {}", prontuarioRequest);
        return prontuarioService.criarProntuario(prontuarioRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProntuarioDto atualizarProntuario(@PathVariable Long id, @RequestBody ProntuarioDto prontuarioDto){
        log.info("Atualizar prontuario - controller: {}", prontuarioDto);
        return prontuarioService.atualizarProntuario(id, prontuarioDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirProntuario(@PathVariable Long id) {
        log.info("excluir prontuario - controller: {}", id);
        prontuarioService.excluirProntuario(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProntuarioDto buscarProntuarioPorId(@PathVariable Long id) {
        log.info("Buscar prontuario - controller: {}", id);
        return prontuarioService.buscarProntuarioPorId(id);
    }

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<ProntuarioDto> listarProntuario() {
        log.info("Listar Prontuario - controller");
        return prontuarioService.listarProntuario();
    }
}
