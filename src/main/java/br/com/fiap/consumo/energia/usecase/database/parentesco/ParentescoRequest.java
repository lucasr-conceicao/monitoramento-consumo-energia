package br.com.fiap.consumo.energia.usecase.database.parentesco;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class ParentescoRequest {

    private String descricaoRelacionamento;
    private UUID pessoa1;
    private UUID pessoa2;
}
