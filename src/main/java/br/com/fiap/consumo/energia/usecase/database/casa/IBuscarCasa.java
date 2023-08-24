package br.com.fiap.consumo.energia.usecase.database.casa;

import java.util.UUID;

public interface IBuscarCasa {

    CasaResponse buscarCasa(UUID casaId);
}
