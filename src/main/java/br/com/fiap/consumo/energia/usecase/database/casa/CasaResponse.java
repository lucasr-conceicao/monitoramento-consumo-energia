package br.com.fiap.consumo.energia.usecase.database.casa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CasaResponse {

    private UUID casaId;
    private UUID enderecoId;
    private String tipoCasaId;
}