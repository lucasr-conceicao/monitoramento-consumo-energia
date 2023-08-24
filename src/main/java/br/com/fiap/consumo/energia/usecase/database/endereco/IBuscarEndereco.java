package br.com.fiap.consumo.energia.usecase.database.endereco;

import java.util.UUID;

public interface IBuscarEndereco {

    EnderecoResponse buscarEndereco(UUID enderecoId);
}