package br.com.fiap.consumo.energia.adapter.database.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_pessoa_cnsm_enrg")
public class PessoaConsumoEnergia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pessoa")
    private UUID pessoaId;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cpf;

    @Column()
    private int idade;

    @Column()
    private String genero;

    @Column(name = "data_nascimento", nullable = false)
    private Date dataNascimento;

    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "pessoa1", cascade = CascadeType.ALL)
    private Set<ParentescoConsumoEnergia> parentescos1 = new HashSet<>();

    @OneToMany(mappedBy = "pessoa2", cascade = CascadeType.ALL)
    private Set<ParentescoConsumoEnergia> parentescos2 = new HashSet<>();

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CasaConsumoEnergia> casa = new ArrayList<>();
}