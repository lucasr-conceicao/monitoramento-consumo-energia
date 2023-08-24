package br.com.fiap.consumo.energia.usecase.database.parentesco;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParentescoResponse {

    private UUID parentescoId;
    private String descricaoRelacionamento;
    private UUID pessoa1;
    private UUID pessoa2;
}
