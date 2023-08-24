package br.com.fiap.consumo.energia.adapter.rest.dto.endereco;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EnderecoRequestDto {

    @JsonProperty("rua")
    private String rua;

    @JsonProperty("numero")
    private Integer numero;

    @JsonProperty("cep")
    private String cep;

    @JsonProperty("bairro")
    private String bairro;

    @JsonProperty("cidade")
    private String cidade;

    @JsonProperty("estado")
    private String estado;

    @JsonProperty("pais")
    private String pais;
}
