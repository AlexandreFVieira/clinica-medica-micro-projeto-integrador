package br.edu.imepac.agendamento.feign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Map;
import java.util.List;


@FeignClient(name = "clinica-medica-medico")
public interface MedicoClient {

    @GetMapping("/api/medicos/ordenados")
    List<Map<String, Object>> listarMedicosOrdenados();
}