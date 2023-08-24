package br.com.fiap.consumo.energia.adapter.database.postgre.casa;

import br.com.fiap.consumo.energia.adapter.database.domain.CasaConsumoEnergia;
import br.com.fiap.consumo.energia.adapter.database.domain.EnderecoConsumoEnergia;
import br.com.fiap.consumo.energia.adapter.database.domain.TipoCasaConsumoEnergia;
import br.com.fiap.consumo.energia.adapter.database.exceptions.RecursoNaoEncontradoException;
import br.com.fiap.consumo.energia.adapter.database.repository.CasaRepository;
import br.com.fiap.consumo.energia.adapter.database.repository.EnderecoRepository;
import br.com.fiap.consumo.energia.adapter.database.repository.TipoCasaRepository;
import br.com.fiap.consumo.energia.usecase.database.casa.CasaRequest;
import br.com.fiap.consumo.energia.usecase.database.casa.CasaResponse;
import br.com.fiap.consumo.energia.usecase.database.casa.IAtualizarCasa;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AtualizarCasa implements IAtualizarCasa {

    private final CasaRepository casaRepository;
    private final EnderecoRepository enderecoRepository;
    private final TipoCasaRepository tipoCasaRepository;

    @Override
    public CasaResponse atualizarCasa(CasaRequest request, UUID casaId) {
        var casa = montarCasaRequest(request, casaId);
        casaRepository.save(casa);
        return converterResponse(casa);
    }

    private CasaConsumoEnergia montarCasaRequest(CasaRequest request, UUID casaId) {

        var casa = buscarCasa(casaId);
        val endereco = buscarEndereco(request.getEnderecoId());
        val tipoCasa = buscarTipoCasa(request.getTipoCasaId());

        casa.setEndereco(endereco);
        casa.setTipoCasa(tipoCasa);

        return casa;
    }

    private CasaConsumoEnergia buscarCasa(UUID casaId) {
        return casaRepository.findById(casaId).orElseThrow(
                () -> new RecursoNaoEncontradoException("O recurso nao foi encontrado na base de dados."));
    }

    private EnderecoConsumoEnergia buscarEndereco(UUID enderecoId) {
        return enderecoRepository.findById(enderecoId).orElseThrow(
                () -> new RecursoNaoEncontradoException("O recurso nao foi encontrado na base de dados."));
    }

    private TipoCasaConsumoEnergia buscarTipoCasa(String tipoCasa) {
        return tipoCasaRepository.findById(tipoCasa).orElseThrow(
                () -> new RecursoNaoEncontradoException("O tipo da Casa nao foi encontrado na base de dados."));
    }

    private CasaResponse converterResponse(CasaConsumoEnergia response) {
        return CasaResponse.builder()
                .casaId(response.getCasaId())
                .enderecoId(response.getEndereco().getEnderecoId())
                .tipoCasaId(response.getTipoCasa().getTipoCasaId())
                .build();
    }
}
