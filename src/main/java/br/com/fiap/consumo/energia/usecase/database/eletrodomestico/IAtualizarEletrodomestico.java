package br.com.fiap.consumo.energia.usecase.database.eletrodomestico;

import java.util.UUID;

public interface IAtualizarEletrodomestico {

    EletrodomesticoResponse atualizarEletrodomestico(EletrodomesticoRequest request, UUID eletrodomesticoId);
}
