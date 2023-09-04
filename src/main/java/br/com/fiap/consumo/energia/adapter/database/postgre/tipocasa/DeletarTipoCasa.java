package br.com.fiap.consumo.energia.adapter.database.postgre.tipocasa;

import br.com.fiap.consumo.energia.adapter.database.domain.TipoCasaConsumoEnergia;
import br.com.fiap.consumo.energia.adapter.database.exceptions.RecursoNaoEncontradoException;
import br.com.fiap.consumo.energia.adapter.database.repository.TipoCasaRepository;
import br.com.fiap.consumo.energia.usecase.database.tipocasa.IDeletarTipoCasa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeletarTipoCasa implements IDeletarTipoCasa {

    private final TipoCasaRepository repository;

    @Override
    @Transactional
    public void deletarTipoCasa(String tipoCasaId) {
        var tipoCasa = repository.findById(tipoCasaId);
        var tipoCasaValidado = validarTipoCasa(tipoCasa, tipoCasaId);
        repository.delete(tipoCasaValidado.get());
    }

    private Optional<TipoCasaConsumoEnergia> validarTipoCasa(Optional<TipoCasaConsumoEnergia> response, String tipoCasaId) {
        if (!response.isPresent())
            throw new RecursoNaoEncontradoException("O recurso " + tipoCasaId + " não foi encontrado na base de dados.");
        return response;
    }
}
