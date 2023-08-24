package br.com.fiap.consumo.energia.adapter.rest.dto.pessoa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class PessoaRequestDto {

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("idade")
    private int idade;

    @JsonProperty("genero")
    private String genero;

    @JsonProperty("data_nascimento")
    private Date dataNascimento;

    @JsonProperty("email")
    private String email;
}