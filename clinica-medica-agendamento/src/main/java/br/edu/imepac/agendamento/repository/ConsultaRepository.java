package br.edu.imepac.agendamento.repository;

import br.edu.imepac.agendamento.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByIdPacienteAndDataHoraBetween(Long idPaciente, LocalDateTime inicio, LocalDateTime fim);
    boolean existsByIdMedicoAndDataHora(Long idMedico, LocalDateTime dataHora);
}
