package br.com.fiap.consumo.energia.usecase.database.tipocasa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoCasaResponse {

    private String tipoCasaId;
    private String descricao;
}
