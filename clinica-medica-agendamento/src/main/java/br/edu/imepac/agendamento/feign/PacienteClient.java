package br.edu.imepac.agendamento.feign;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.Map;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "clinica-medica-paciente")
public interface PacienteClient {

    @GetMapping("/api/pacientes/ordenados")
    Page<Map<String, Object>> listarPacientesOrdenados(Pageable pageable);
}
