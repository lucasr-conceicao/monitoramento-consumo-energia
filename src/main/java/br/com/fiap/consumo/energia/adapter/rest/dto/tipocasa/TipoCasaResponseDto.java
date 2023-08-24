package br.com.fiap.consumo.energia.adapter.rest.dto.tipocasa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoCasaResponseDto {

    @JsonProperty("id_tipo_casa")
    private String tipoCasaId;

    @JsonProperty("descricao")
    private String descricao;
}
