package br.com.fiap.consumo.energia.adapter.database.postgre.tipocasa;

import br.com.fiap.consumo.energia.adapter.database.domain.TipoCasaConsumoEnergia;
import br.com.fiap.consumo.energia.adapter.database.repository.TipoCasaRepository;
import br.com.fiap.consumo.energia.usecase.database.tipocasa.ICadastrarTipoCasa;
import br.com.fiap.consumo.energia.usecase.database.tipocasa.TipoCasaRequest;
import br.com.fiap.consumo.energia.usecase.database.tipocasa.TipoCasaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarTipoCasa implements ICadastrarTipoCasa {

    private final TipoCasaRepository repository;

    @Override
    public TipoCasaResponse cadastrarTipoCasa(TipoCasaRequest request) {
        var tipoCasa = montarTipoCasaRequest(request);
        repository.save(tipoCasa);
        return converterResponse(tipoCasa);
    }

    private TipoCasaConsumoEnergia montarTipoCasaRequest(TipoCasaRequest request) {
        return TipoCasaConsumoEnergia.builder()
                .tipoCasaId(request.getTipoCasaId())
                .descricao(request.getDescricao())
                .build();
    }

    private TipoCasaResponse converterResponse(TipoCasaConsumoEnergia response) {
        return TipoCasaResponse.builder()
                .tipoCasaId(response.getTipoCasaId())
                .descricao(response.getDescricao())
                .build();
    }
}
