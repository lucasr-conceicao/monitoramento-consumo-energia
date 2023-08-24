package br.com.fiap.consumo.energia.usecase.database.eletrodomestico;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EletrodomesticoResponse {

    private UUID eletrodomesticoId;
    private String nome;
    private BigDecimal potencia;
    private UUID casaId;
}
