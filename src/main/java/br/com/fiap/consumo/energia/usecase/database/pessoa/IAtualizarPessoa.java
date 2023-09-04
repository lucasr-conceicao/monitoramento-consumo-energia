package br.com.fiap.consumo.energia.usecase.database.pessoa;

import java.util.UUID;

public interface IAtualizarPessoa {

    PessoaResponse atualizarPessoa(PessoaRequest request, UUID pessoaId);
}
