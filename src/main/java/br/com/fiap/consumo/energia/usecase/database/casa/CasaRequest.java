package br.com.fiap.consumo.energia.usecase.database.casa;

import br.com.fiap.consumo.energia.usecase.database.eletrodomestico.EletrodomesticoResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class CasaRequest {

    private UUID enderecoId;
    private String tipoCasaId;
    private UUID pessoaId;
}