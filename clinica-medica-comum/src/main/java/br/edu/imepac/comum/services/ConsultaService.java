package br.edu.imepac.comum.services;

import br.edu.imepac.comum.dtos.consulta.ConsultaDto;
import br.edu.imepac.comum.dtos.consulta.ConsultaRequest;
import br.edu.imepac.comum.models.Consulta;
import br.edu.imepac.comum.repositories.ConsultaRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ConsultaService {

    private final ModelMapper modelMapper;
    private final ConsultaRepository consultaRepository;

    public ConsultaService(ModelMapper modelMapper, ConsultaRepository consultaRepository) {
        this.modelMapper = modelMapper;
        this.consultaRepository = consultaRepository;
    }

    public ConsultaDto criarConsulta(ConsultaRequest consultaRequest) {
        log.info("Criando Consulta - service: {}", consultaRequest);
        Consulta consulta = modelMapper.map(consultaRequest, Consulta.class);
        consulta = consultaRepository.save(consulta);
        return modelMapper.map(consulta, ConsultaDto.class);
    }

    public ConsultaDto atualizarConsulta(Long id, ConsultaDto consultaDto) {
        log.info("Atualizando Consulta com ID: {}", id);
        Consulta consultaExistente = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada com ID: " + id));
        modelMapper.map(consultaDto, consultaExistente);
        Consulta consultaAtualizada = consultaRepository.save(consultaExistente);
        return modelMapper.map(consultaAtualizada, ConsultaDto.class);
    }

    public void excluirConsulta(Long id) {
        log.info("Excluindo Consulta com ID: {}", id);
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada com ID: " + id));
        consultaRepository.delete(consulta);
    }

    public ConsultaDto buscarConsultaPorId(Long id) {
        log.info("Buscando Consulta com ID: {}", id);
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada com ID: " + id));
        return modelMapper.map(consulta, ConsultaDto.class);
    }

    public List<ConsultaDto> listarConsultas() {
        log.info("Listando todas as Consultas");
        List<Consulta> consultas = consultaRepository.findAll();
        return consultas.stream()
                .map(consulta -> modelMapper.map(consulta, ConsultaDto.class))
                .collect(Collectors.toList());
    }
}