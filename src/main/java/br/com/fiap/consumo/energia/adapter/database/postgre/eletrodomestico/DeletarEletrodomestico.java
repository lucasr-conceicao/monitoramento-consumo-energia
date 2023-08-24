package br.com.fiap.consumo.energia.adapter.database.postgre.eletrodomestico;

import br.com.fiap.consumo.energia.adapter.database.domain.EletrodomesticoConsumoEnergia;
import br.com.fiap.consumo.energia.adapter.database.exceptions.RecursoNaoEncontradoException;
import br.com.fiap.consumo.energia.adapter.database.repository.EletrodomesticoRepository;
import br.com.fiap.consumo.energia.usecase.database.eletrodomestico.IDeletarEletrodomestico;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeletarEletrodomestico implements IDeletarEletrodomestico {

    private final EletrodomesticoRepository repository;

    @Override
    public void deletarEletrodomestico(UUID eletrodomesticoId) {
        var eletrodomestico = repository.findById(eletrodomesticoId);
        var eletrodomesticoValidado = validarEletrodomestico(eletrodomestico, eletrodomesticoId);
        repository.delete(eletrodomesticoValidado.get());
    }

    private Optional<EletrodomesticoConsumoEnergia> validarEletrodomestico(Optional<EletrodomesticoConsumoEnergia> response, UUID casaId) {
        if (!response.isPresent())
            throw new RecursoNaoEncontradoException("O recurso " + casaId + " não foi encontrado na base de dados.");

        return response;
    }
}
