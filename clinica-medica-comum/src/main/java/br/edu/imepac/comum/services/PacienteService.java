package br.edu.imepac.comum.services;


import br.edu.imepac.comum.dtos.paciente.PacienteDto;
import br.edu.imepac.comum.dtos.paciente.PacienteRequest;
import br.edu.imepac.comum.models.Paciente;
import br.edu.imepac.comum.repositories.PacienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final ModelMapper modelMapper;

    public PacienteService(PacienteRepository pacienteRepository, ModelMapper modelMapper) {
        this.pacienteRepository = pacienteRepository;
        this.modelMapper = modelMapper;
    }

    public PacienteDto adicionarPaciente(PacienteRequest pacienteRequest) {
        log.info("Cadastro de Paciente - service: {}", pacienteRequest);

        // Mapeia o DTO de requisição para a entidade Paciente
        Paciente paciente = modelMapper.map(pacienteRequest, Paciente.class);

        // Salva a nova entidade no banco de dados
        paciente = pacienteRepository.save(paciente);

        // Mapeia a entidade salva para o DTO de resposta e o retorna
        return modelMapper.map(paciente, PacienteDto.class);
    }

    public PacienteDto atualizarPaciente(Long id, PacienteDto pacienteDto){
        log.info("Atualizando paciente com ID: {}", id);

        // Busca o paciente no banco de dados ou lança uma exceção se não encontrar
        Paciente pacienteExistente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com ID: " + id));

        // Atualiza os dados da entidade existente com os dados do DTO
        modelMapper.map(pacienteDto, pacienteExistente);

        //Salva a entidade atualizada
        Paciente pacienteAtualizado = pacienteRepository.save(pacienteExistente);

        return modelMapper.map(pacienteAtualizado, PacienteDto.class);
    }

    public void removerPaciente(Long id){
        log.info("Removendo paciente com ID: {}", id);

        //Garante que o paciente existe antes de tentar deletar
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com ID: " + id));

        pacienteRepository.delete(paciente);
    }

    public PacienteDto buscarPacientePorId(Long id){
        log.info("Buscando paciente com ID: {}", id);

        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com ID: " + id));
        return modelMapper.map(paciente, PacienteDto.class);
    }

    public List<PacienteDto> listarTodosPacientes(){
        log.info("Listando todos os pacientes");

        List<Paciente> pacientes = pacienteRepository.findAll();

        //Usa Stream para mapear a lista de entidades para uma lista de DTOs
        return pacientes.stream()
                .map(paciente -> modelMapper.map(paciente, PacienteDto.class))
                .collect(Collectors.toList());
    }
}
