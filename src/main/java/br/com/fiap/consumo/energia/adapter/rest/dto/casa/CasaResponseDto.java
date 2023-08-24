package br.com.fiap.consumo.energia.adapter.rest.dto.casa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CasaResponseDto {

    @JsonProperty("id_casa")
    private UUID casaId;

    @JsonProperty("id_endereco")
    private UUID enderecoId;

    @JsonProperty("id_tipo_casa")
    private String tipoCasaId;
}
