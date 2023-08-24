package br.com.fiap.consumo.energia.adapter.database.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_eletrodomestico_cnsm_enrg")
public class EletrodomesticoConsumoEnergia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_eletrodomestico")
    private UUID eletrodomesticoId;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigDecimal potencia;

    @ManyToOne
    @JoinColumn(name = "id_casa")
    private CasaConsumoEnergia casa;

    @ManyToMany
    @JoinTable(
            name = "tb_pessoa_eletrodomestico_cnsm_enrg",
            joinColumns = @JoinColumn(name = "id_pessoa"),
            inverseJoinColumns = @JoinColumn(name = "id_eletrodomestico")
    )
    private Set<PessoaConsumoEnergia> pessoas = new HashSet<>();
}
