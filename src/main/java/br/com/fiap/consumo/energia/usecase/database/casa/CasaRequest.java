package br.com.fiap.consumo.energia.usecase.database.casa;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class CasaRequest {

    private UUID enderecoId;
    private String tipoCasaId;
}