package br.com.fiap.consumo.energia.usecase.database.parentesco;

import java.util.UUID;

public interface IAtualizarParentesco {

    ParentescoResponse atualizarParentesco(ParentescoRequest request, UUID parentescoId);
}
