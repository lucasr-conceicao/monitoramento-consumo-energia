package br.com.fiap.consumo.energia.adapter.rest.dto.eletrodomestico;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EletrodomesticoResponseDto {

    @JsonProperty("id_eletrodomestico")
    private UUID eletrodomesticoId;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("potencia")
    private BigDecimal potencia;

    @JsonProperty("id_casa")
    private UUID casaId;
}