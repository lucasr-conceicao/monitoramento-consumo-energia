package br.com.fiap.consumo.energia.adapter.rest.dto.eletrodomestico;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
public class EletrodomesticoRequestDto {

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("potencia")
    private BigDecimal potencia;

    @JsonProperty("id_casa")
    private UUID casaId;
}
