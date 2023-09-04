package br.com.fiap.consumo.energia.adapter.database.postgre.tipocasa;

import br.com.fiap.consumo.energia.adapter.database.domain.TipoCasaConsumoEnergia;
import br.com.fiap.consumo.energia.adapter.database.exceptions.RecursoNaoEncontradoException;
import br.com.fiap.consumo.energia.adapter.database.repository.TipoCasaRepository;
import br.com.fiap.consumo.energia.usecase.database.tipocasa.IBuscarTipoCasa;
import br.com.fiap.consumo.energia.usecase.database.tipocasa.TipoCasaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BuscarTipoCasa implements IBuscarTipoCasa {

    private final TipoCasaRepository repository;

    @Override
    @Transactional
    public TipoCasaResponse buscarTipoCasa(String tipoCasaId) {
        var tipoCasa = repository.findById(tipoCasaId);
        var tipoCasaValidado = validarTipoCasa(tipoCasa, tipoCasaId);
        return converterResponse(tipoCasaValidado);
    }

    private Optional<TipoCasaConsumoEnergia> validarTipoCasa(Optional<TipoCasaConsumoEnergia> response, String tipoCasaId) {
        if (!response.isPresent())
            throw new RecursoNaoEncontradoException("O recurso " + tipoCasaId + " não foi encontrado na base de dados.");

        return response;
    }

    private TipoCasaResponse converterResponse(Optional<TipoCasaConsumoEnergia> response) {
        return TipoCasaResponse.builder()
                .tipoCasaId(response.get().getTipoCasaId())
                .descricao(response.get().getDescricao())
                .build();
    }
}
