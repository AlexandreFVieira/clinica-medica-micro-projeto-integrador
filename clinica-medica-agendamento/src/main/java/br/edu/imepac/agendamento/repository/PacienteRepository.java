package br.edu.imepac.agendamento.repository;

import br.edu.imepac.agendamento.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
