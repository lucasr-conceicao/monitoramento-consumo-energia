package br.com.fiap.consumo.energia.usecase.database.parentesco;

import java.util.UUID;

public interface IBuscarParentesco {

    ParentescoResponse buscarParentesco(UUID parentescoId);
}
