package br.com.fiap.consumo.energia.usecase.database.pessoa;

import java.util.UUID;

public interface IBuscarPessoa {

    PessoaResponse buscarPessoa(UUID pessoaId);
}
