package br.edu.imepac.comum.dtos.prontuario;

import br.edu.imepac.comum.models.Consulta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProntuarioDto {

    private Long id;
    private String receituario;
    private String exames;
    private String observacoes;
    private Consulta consulta;
}
