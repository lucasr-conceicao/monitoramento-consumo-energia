package br.com.fiap.consumo.energia.adapter.database.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_tipo_casa_cnsm_enrg")
public class TipoCasaConsumoEnergia implements Serializable {

    @Id
    @Column(name = "id_tipo_casa")
    private String tipoCasaId;

    @Column(nullable = false)
    private String descricao;

    @OneToMany(mappedBy = "tipoCasa", cascade = CascadeType.ALL)
    private List<CasaConsumoEnergia> casa = new ArrayList<>();
}
