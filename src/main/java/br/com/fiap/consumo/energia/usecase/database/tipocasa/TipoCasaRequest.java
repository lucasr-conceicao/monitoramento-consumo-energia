package br.com.fiap.consumo.energia.usecase.database.tipocasa;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TipoCasaRequest {

    private String tipoCasaId;
    private String descricao;
}
