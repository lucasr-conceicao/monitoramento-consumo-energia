package br.com.fiap.consumo.energia.usecase.database.pessoa;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class PessoaRequest {

    private String nome;
    private String cpf;
    private int idade;
    private String genero;
    private Date dataNascimento;
    private String email;
}
