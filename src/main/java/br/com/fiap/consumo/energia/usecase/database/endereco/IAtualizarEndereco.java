package br.com.fiap.consumo.energia.usecase.database.endereco;

import java.util.UUID;

public interface IAtualizarEndereco {

    EnderecoResponse atualizarEndereco(EnderecoRequest request, UUID enderecoId);
}