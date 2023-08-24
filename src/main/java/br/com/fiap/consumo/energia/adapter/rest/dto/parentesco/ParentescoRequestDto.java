package br.com.fiap.consumo.energia.adapter.rest.dto.parentesco;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class ParentescoRequestDto {

    @JsonProperty("descricao_relacionamento")
    private String descricaoRelacionamento;

    @JsonProperty("id_pessoa1")
    private UUID pessoa1;

    @JsonProperty("id_pessoa2")
    private UUID pessoa2;
}
