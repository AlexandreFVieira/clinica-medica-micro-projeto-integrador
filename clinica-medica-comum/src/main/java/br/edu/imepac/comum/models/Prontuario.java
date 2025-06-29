package br.edu.imepac.comum.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prontuarios")
public class Prontuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2000)
    private String receituario;

    @Column(length = 2000)
    private String exames;

    @Lob // @Lob indica que o campo pode armazenar objetos grandes, ideal para textos longos.
    private String observacoes;

    @JoinColumn

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consulta_id", referencedColumnName = "id", unique = true, nullable = false)
    private Consulta consulta;
}
