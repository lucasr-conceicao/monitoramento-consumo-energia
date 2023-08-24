package br.com.fiap.consumo.energia.adapter.database.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_parentesco_cnsm_enrg")
public class ParentescoConsumoEnergia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID parentescoId;

    @Column()
    private String descricaoRelacionamento;

    @ManyToOne
    @JoinColumn(name = "id_pessoa1")
    private PessoaConsumoEnergia pessoa1;

    @ManyToOne
    @JoinColumn(name = "id_pessoa2")
    private PessoaConsumoEnergia pessoa2;
}