package br.com.fiap.consumo.energia.usecase.database.eletrodomestico;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Getter
@Builder
public class EletrodomesticoRequest {

    private String nome;
    private BigDecimal potencia;
    private UUID casaId;
    private UUID pessoaId;
}
