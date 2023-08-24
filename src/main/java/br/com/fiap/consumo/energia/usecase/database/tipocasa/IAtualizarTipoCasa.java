package br.com.fiap.consumo.energia.usecase.database.tipocasa;

import java.util.UUID;

public interface IAtualizarTipoCasa {

    TipoCasaResponse atualizarTipoCasa(TipoCasaRequest request, String tipoCasaId);
}
