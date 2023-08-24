package br.com.fiap.consumo.energia.adapter.rest.dto.endereco;

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
public class EnderecoResponseDto {

    @JsonProperty("id_endereco")
    private UUID enderecoId;

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
