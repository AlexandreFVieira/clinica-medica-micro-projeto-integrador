package br.edu.imepac.comum.dtos.convenio;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConvenioRequest {

    @NotBlank(message = "O nome do convênio é obrigatório.")
    private String nome;

    @NotBlank(message = "A descrição do convênio é obrigatória.")
    private String descricao;
}