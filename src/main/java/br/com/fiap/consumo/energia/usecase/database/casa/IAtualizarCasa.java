package br.com.fiap.consumo.energia.usecase.database.casa;

import java.util.UUID;

public interface IAtualizarCasa {

    CasaResponse atualizarCasa(CasaRequest request, UUID casaId);
}
