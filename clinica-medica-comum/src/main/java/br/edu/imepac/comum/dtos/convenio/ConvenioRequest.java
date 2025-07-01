package br.edu.imepac.comum.dtos.convenio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConvenioRequest {
    private LocalDateTime dataHorario;
    private String sintomas;
    private Boolean eRetorno;
    private Boolean estaAtiva;
}
