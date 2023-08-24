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
@Table(name = "tb_casa_cnsm_enrg")
public class CasaConsumoEnergia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_casa")
    private UUID casaId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    private EnderecoConsumoEnergia endereco;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_tipo_casa")
    private TipoCasaConsumoEnergia tipoCasa;
}