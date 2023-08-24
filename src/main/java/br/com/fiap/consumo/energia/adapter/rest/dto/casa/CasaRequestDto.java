package br.com.fiap.consumo.energia.adapter.rest.dto.casa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class CasaRequestDto {

    @JsonProperty("id_endereco")
    private UUID enderecoId;

    @JsonProperty("id_tipo_casa")
    private String tipoCasaId;
}
