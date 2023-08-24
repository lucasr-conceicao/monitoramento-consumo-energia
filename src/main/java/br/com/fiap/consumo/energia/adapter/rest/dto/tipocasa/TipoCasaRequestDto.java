package br.com.fiap.consumo.energia.adapter.rest.dto.tipocasa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TipoCasaRequestDto {

    @JsonProperty("id_tipo_casa")
    private String tipoCasaId;

    @JsonProperty("descricao")
    private String descricao;
}
