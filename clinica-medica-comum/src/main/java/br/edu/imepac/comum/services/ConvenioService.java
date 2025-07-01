package br.edu.imepac.comum.services;


import br.edu.imepac.comum.dtos.convenio.ConvenioDto;
import br.edu.imepac.comum.dtos.convenio.ConvenioRequest;
import br.edu.imepac.comum.models.Convenio;
import br.edu.imepac.comum.models.Prontuario;
import br.edu.imepac.comum.repositories.ConvenioRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ConvenioService {
    private final ModelMapper modelMapper;
    private final ConvenioRepository convenioRepository;

    public ConvenioService(ModelMapper modelMapper, ConvenioRepository convenioRepository) {
        this.modelMapper = modelMapper;
        this.convenioRepository = convenioRepository;
    }

    public ConvenioDto criarConvenio(ConvenioRequest convenioRequest) {
        log.info("Criar Convenio - service: {}", convenioRequest);
        Convenio convenio = modelMapper.map(convenioRequest, Convenio.class);
        convenio = convenioRepository.save(convenio);
        return modelMapper.map(convenio, ConvenioDto.class);
    }

    public ConvenioDto atualizarConvenio(Long id, ConvenioDto convenioDto){
        log.info("Atualizando Convenios com ID: {}", id);
        Convenio convenioExistente = convenioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Convenio não encontrado com ID: " + id));
        modelMapper.map(convenioDto, convenioExistente);
        Convenio convenioAtualizado = convenioRepository.save(convenioExistente);
        return modelMapper.map(convenioAtualizado, ConvenioDto.class);
    }

    public void excluirConvenio(Long id){
        log.info("Excluindo Convenio com ID: {}", id);
        Convenio convenio = convenioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Convenio não encontrado com ID: "+ id));
        convenioRepository.delete(convenio);
    }

    public ConvenioDto buscarConvenioPorId(Long id){
        log.info("Buscando Convenio com ID: {}", id);
        Convenio convenio = convenioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Convenio não encontrado com ID: "+id));
        return modelMapper.map(convenio, ConvenioDto.class);
    }

    public List<ConvenioDto> listarConvenio(){
        log.info("Listando todos os Convenios");
        List<Convenio> convenios = convenioRepository.findAll();
        return convenios.stream()
                .map(convenio -> modelMapper.map(convenio, ConvenioDto.class))
                .toList();
    }
}
