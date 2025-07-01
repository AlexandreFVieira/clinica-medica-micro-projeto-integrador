package br.edu.imepac.administrativo.controllers;

import br.edu.imepac.comum.dtos.convenio.ConvenioDto;
import br.edu.imepac.comum.dtos.convenio.ConvenioRequest;
import br.edu.imepac.comum.services.ConvenioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/convenios")
public class ConvenioController {
    private final ConvenioService convenioService;

    public ConvenioController(ConvenioService convenioService) {
        this.convenioService = convenioService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConvenioDto criarConvenio(@RequestBody ConvenioRequest convenioRequest) {
        log.info("Criando um convenio - controller: {}", convenioRequest);
        return convenioService.criarConvenio(convenioRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ConvenioDto atualizarConvenio(@PathVariable Long id, @RequestBody ConvenioDto convenioDto) {
        log.info("Atualizar convenio - controller: {}", convenioDto);
        return convenioService.atualizarConvenio(id, convenioDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirConvenio(@PathVariable Long id){
        log.info("Excluino convenio - controller: {}", id);
        convenioService.excluirConvenio(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ConvenioDto buscarConvenioPorId(@PathVariable Long id){
        log.info("Buscando um convenio - controller: {}", id);
        return convenioService.buscarConvenioPorId(id);
    }

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<ConvenioDto> listarConvenio(){
        log.info("Listar Convenio - controller");
        return convenioService.listarConvenio();
    }


}
