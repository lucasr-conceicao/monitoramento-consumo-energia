package br.com.fiap.consumo.energia.adapter.database.postgre.tipocasa;

import br.com.fiap.consumo.energia.adapter.database.domain.TipoCasaConsumoEnergia;
import br.com.fiap.consumo.energia.adapter.database.exceptions.RecursoNaoEncontradoException;
import br.com.fiap.consumo.energia.adapter.database.repository.TipoCasaRepository;
import br.com.fiap.consumo.energia.usecase.database.tipocasa.IAtualizarTipoCasa;
import br.com.fiap.consumo.energia.usecase.database.tipocasa.TipoCasaRequest;
import br.com.fiap.consumo.energia.usecase.database.tipocasa.TipoCasaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AtualizarTipoCasa implements IAtualizarTipoCasa {

    private final TipoCasaRepository repository;

    @Override
    @Transactional
    public TipoCasaResponse atualizarTipoCasa(TipoCasaRequest request, String tipoCasaId) {
        var tipoCasa = montarTipoCasaRequest(request, tipoCasaId);
        repository.save(tipoCasa);
        return converterResponse(tipoCasa);
    }

    private TipoCasaConsumoEnergia montarTipoCasaRequest(TipoCasaRequest request, String tipoCasaId) {
        var tipoCasa = buscarTipoCasa(tipoCasaId);
        tipoCasa.setDescricao(request.getDescricao());
        return tipoCasa;
    }

    @Transactional(readOnly = true)
    private TipoCasaConsumoEnergia buscarTipoCasa(String tipoCasa) {
        return repository.findById(tipoCasa).orElseThrow(
                () -> new RecursoNaoEncontradoException("O tipo da Casa nao foi encontrado na base de dados."));
    }

    private TipoCasaResponse converterResponse(TipoCasaConsumoEnergia response) {
        return TipoCasaResponse.builder()
                .tipoCasaId(response.getTipoCasaId())
                .descricao(response.getDescricao())
                .build();
    }
}
