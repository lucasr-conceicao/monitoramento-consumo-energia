package br.com.fiap.consumo.energia.adapter.database.postgre.casa;

import br.com.fiap.consumo.energia.adapter.database.domain.CasaConsumoEnergia;
import br.com.fiap.consumo.energia.adapter.database.exceptions.RecursoNaoEncontradoException;
import br.com.fiap.consumo.energia.adapter.database.repository.CasaRepository;
import br.com.fiap.consumo.energia.usecase.database.casa.CasaResponse;
import br.com.fiap.consumo.energia.usecase.database.casa.IBuscarCasa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BuscarCasa implements IBuscarCasa {

    private final CasaRepository repository;

    @Override
    public CasaResponse buscarCasa(UUID casaId) {
        var casa = repository.findById(casaId);
        var casaValidado = validarCasa(casa, casaId);
        return converterResponse(casaValidado);
    }

    private Optional<CasaConsumoEnergia> validarCasa(Optional<CasaConsumoEnergia> response, UUID casaId) {
        if (!response.isPresent())
            throw new RecursoNaoEncontradoException("O recurso " + casaId + " não foi encontrado na base de dados.");

        return response;
    }

    private CasaResponse converterResponse(Optional<CasaConsumoEnergia> response) {
        return CasaResponse.builder()
                .casaId(response.get().getCasaId())
                .enderecoId(response.get().getEndereco().getEnderecoId())
                .tipoCasaId(response.get().getTipoCasa().getTipoCasaId())
                .build();

    }
}
