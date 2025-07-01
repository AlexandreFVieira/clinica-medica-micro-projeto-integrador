package br.edu.imepac.comum.services;


import br.edu.imepac.comum.dtos.prontuario.ProntuarioDto;
import br.edu.imepac.comum.dtos.prontuario.ProntuarioRequest;
import br.edu.imepac.comum.models.Prontuario;
import br.edu.imepac.comum.repositories.ProntuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProntuarioService {
    private final ModelMapper modelMapper;
    private final ProntuarioRepository prontuarioRepository;

    public ProntuarioService(ModelMapper modelMapper, ProntuarioRepository prontuarioRepository) {
        this.modelMapper = modelMapper;
        this.prontuarioRepository = prontuarioRepository;
    }

    public ProntuarioDto criarProntuario(ProntuarioRequest prontuarioRequest){
    log.info("Criar Prontuario - service: {}", prontuarioRequest);
    Prontuario prontuario = modelMapper.map(prontuarioRequest, Prontuario.class);
    prontuario = prontuarioRepository.save(prontuario);
    return modelMapper.map(prontuario, ProntuarioDto.class);
    }

    public ProntuarioDto atualizarProntuario(Long id, ProntuarioDto prontuarioDto){
        log.info("Atualizando prontuario com ID: {}", id);
        Prontuario prontuarioExistente = prontuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prontuario não encontrado com ID: " + id));
        modelMapper.map(prontuarioDto, prontuarioExistente);
        Prontuario prontuarioAtualizado = prontuarioRepository.save(prontuarioExistente);
        return modelMapper.map(prontuarioAtualizado, ProntuarioDto.class);
    }

    public void excluirProntuario(Long id){
        log.info("Excluido prontuario com ID: {}", id);
        Prontuario prontuario = prontuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prontuario não encontrado com ID: " + id));
        prontuarioRepository.delete(prontuario);
    }


    public ProntuarioDto buscarProntuarioPorId(Long id){
        log.info("Buscando prontuario com ID: {}", id);
        Prontuario prontuario = prontuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prontuario não encontrado com ID: " + id));
        return modelMapper.map(prontuario, ProntuarioDto.class);
    }

    public List<ProntuarioDto> listarProntuario(){
        log.info("Listando todos os prontuarios");
        List<Prontuario> prontuarios = prontuarioRepository.findAll();
        return prontuarios.stream()
                .map(prontuario -> modelMapper.map(prontuario, ProntuarioDto.class))
                .toList();
    }
}
