package br.edu.imepac.comum.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "convenios")
public class Convenio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do convênio é obrigatório.")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "A descrição do convênio é obrigatória.")
    @Column(nullable = false)
    private String descricao;
}