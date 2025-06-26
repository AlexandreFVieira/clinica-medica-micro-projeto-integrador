package br.edu.imepac.administrativo.controllers;

import br.edu.imepac.comum.dtos.especialidade.EspecialidadeDto;
import br.edu.imepac.comum.dtos.especialidade.EspecialidadeRequest;
import br.edu.imepac.comum.services.EspecialidadeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j // **@Slf4j**: Gera automaticamente um logger para a classe, permitindo registrar mensagens de log.
@RestController // **@RestController**: Indica que esta classe é um controlador REST, retornando dados diretamente como resposta (ex.: JSON).
@RequestMapping("/especialidades") // **@RequestMapping**: Define o endpoint base para todos os métodos desta classe.
public class EspecialidadeController {

    // **Injeção de Dependência**: O serviço `EspecialidadeService` é injetado no controlador
    // para que os métodos do serviço possam ser utilizados para manipular os dados.
    private final EspecialidadeService especialidadeService;

    // **Construtor**: O controlador recebe o serviço como dependência através do construtor.
    public EspecialidadeController(EspecialidadeService especialidadeService) {
        this.especialidadeService = especialidadeService;
    }

    // **@PostMapping**: Define que este método será chamado para requisições HTTP POST.
    // **@ResponseStatus(HttpStatus.CREATED)**: Retorna o status HTTP 201 (Created) ao criar uma nova especialidade.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EspecialidadeDto criarEspecialidade(@RequestBody EspecialidadeRequest especialidadeRequest) {
        // **Log**: Registra informações sobre a requisição recebida.
        log.info("Criando especialidade - controller: {}", especialidadeRequest);
        // Chama o serviço para adicionar uma nova especialidade.
        return especialidadeService.adicionarEspecialidade(especialidadeRequest);
    }

    // **@PutMapping("/{id}")**: Define que este método será chamado para requisições HTTP PUT no endpoint com um ID.
    // **@ResponseStatus(HttpStatus.OK)**: Retorna o status HTTP 200 (OK) ao atualizar uma especialidade.
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EspecialidadeDto atualizarEspecialidade(@PathVariable Long id, @RequestBody EspecialidadeDto especialidadeDto) {
        // **Log**: Registra informações sobre a atualização da especialidade.
        log.info("Atualizar especialidade - controller: {}", especialidadeDto);
        // Chama o serviço para atualizar a especialidade com o ID fornecido.
        return especialidadeService.atualizarEspecialidade(id, especialidadeDto);
    }

    // **@DeleteMapping("/{id}")**: Define que este método será chamado para requisições HTTP DELETE no endpoint com um ID.
    // **@ResponseStatus(HttpStatus.NO_CONTENT)**: Retorna o status HTTP 204 (No Content) ao remover uma especialidade.
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerEspecialidade(@PathVariable Long id) {
        // **Log**: Registra informações sobre a remoção da especialidade.
        log.info("Remover especialidade - controller: {}", id);
        // Chama o serviço para remover a especialidade com o ID fornecido.
        especialidadeService.removerEspecialidade(id);
    }

    // **@GetMapping("/{id}")**: Define que este método será chamado para requisições HTTP GET no endpoint com um ID.
    // **@ResponseStatus(HttpStatus.OK)**: Retorna o status HTTP 200 (OK) ao buscar uma especialidade.
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EspecialidadeDto buscarEspecialidadePorId(@PathVariable Long id) {
        // **Log**: Registra informações sobre a busca da especialidade.
        log.info("Buscar especialidade - controller: {}", id);
        // Chama o serviço para buscar a especialidade com o ID fornecido.
        return especialidadeService.buscarEspecialidadePorId(id);
    }

    // **@GetMapping("/listar")**: Define que este método será chamado para requisições HTTP GET no endpoint "/listar".
    // **@ResponseStatus(HttpStatus.OK)**: Retorna o status HTTP 200 (OK) ao listar todas as especialidades.
    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<EspecialidadeDto> listarEspecialidades() {
        // **Log**: Registra informações sobre a listagem de especialidades.
        log.info("Listar especialidade - controller");
        // Chama o serviço para listar todas as especialidades.
        return especialidadeService.listarEspecialidades();
    }
}