package br.com.fiap.consumo.energia.usecase.database.eletrodomestico;

import java.util.UUID;

public interface IBuscarEletrodomestico {

    EletrodomesticoResponse buscarEletrodomestico(UUID eletrodomesticoId);
}
