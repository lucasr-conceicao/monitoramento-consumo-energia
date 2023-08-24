package br.com.fiap.consumo.energia.adapter.database.postgre.casa;

import br.com.fiap.consumo.energia.adapter.database.domain.CasaConsumoEnergia;
import br.com.fiap.consumo.energia.adapter.database.exceptions.RecursoNaoEncontradoException;
import br.com.fiap.consumo.energia.adapter.database.repository.CasaRepository;
import br.com.fiap.consumo.energia.usecase.database.casa.IDeletarCasa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeletarCasa implements IDeletarCasa {

    private final CasaRepository repository;

    @Override
    public void deletarCasa(UUID casaId) {
        var casa = repository.findById(casaId);
        var casaValidada = validarCasa(casa, casaId);
        repository.delete(casaValidada.get());
    }

    private Optional<CasaConsumoEnergia> validarCasa(Optional<CasaConsumoEnergia> response, UUID casaId) {
        if (!response.isPresent())
            throw new RecursoNaoEncontradoException("O recurso " + casaId + " não foi encontrado na base de dados.");

        return response;
    }
}
