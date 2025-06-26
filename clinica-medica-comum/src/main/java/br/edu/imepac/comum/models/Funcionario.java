package br.edu.imepac.comum.models;

import br.edu.imepac.comum.domain.EnumTipoFuncionario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String usuario;
    private String senha;

    private String nome;
    private Integer idade;
    private char sexo;

    private String cpf;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String contato;
    private String email;
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    private EnumTipoFuncionario tipoFuncionario;

    @OneToOne
    private Perfil perfil;
}
