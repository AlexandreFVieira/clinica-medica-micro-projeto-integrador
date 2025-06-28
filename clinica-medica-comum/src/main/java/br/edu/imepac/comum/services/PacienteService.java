package br.edu.imepac.comum.services;

import br.edu.imepac.comum.repositories.PacienteRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final ModelMapper modelMapper;

    public pacienteService(PacienteRepository pacienteRepository, ModelMapper modelMapper){
        this.pacienteRepository = pacienteRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional

}
