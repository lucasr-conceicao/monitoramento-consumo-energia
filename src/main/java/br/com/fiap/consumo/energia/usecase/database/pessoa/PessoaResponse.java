package br.com.fiap.consumo.energia.usecase.database.pessoa;

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
public class PessoaResponse {

    private UUID pessoaId;
    private String nome;
    private String cpf;
    private int idade;
    private String genero;
    private Date dataNascimento;
    private String email;
}
