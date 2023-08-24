package br.com.fiap.consumo.energia.adapter.rest.dto.pessoa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PessoaResponseDto {

    @JsonProperty("id_pessoa")
    private UUID pessoaId;

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