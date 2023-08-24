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
import br.com.fiap.consumo.energia.usecase.database.casa.ICadastrarCasa;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarCasa implements ICadastrarCasa {

    private final CasaRepository casaRepository;
    private final EnderecoRepository enderecoRepository;
    private final TipoCasaRepository tipoCasaRepository;

    @Override
    public CasaResponse cadastrarCasa(CasaRequest request) {
        var casa = montarCasaRequest(request);
        casaRepository.save(casa);
        return converterResponse(casa);
    }

    private CasaConsumoEnergia montarCasaRequest(CasaRequest request) {
        val endereco = buscarEndereco(request);
        val tipoCasa = buscarTipoCasa(request);
        return CasaConsumoEnergia.builder()
                .endereco(endereco)
                .tipoCasa(tipoCasa)
                .build();
    }

    private EnderecoConsumoEnergia buscarEndereco(CasaRequest request) {
        return enderecoRepository.findById(request.getEnderecoId()).orElseThrow(
                () -> new RecursoNaoEncontradoException("O recurso nao foi encontrado na base de dados."));
    }

    private TipoCasaConsumoEnergia buscarTipoCasa(CasaRequest request) {
        return tipoCasaRepository.findById(request.getTipoCasaId()).orElseThrow(
                () -> new RecursoNaoEncontradoException("A recurso nao foi encontrado na base de dados."));
    }

    private CasaResponse converterResponse(CasaConsumoEnergia response) {
        return CasaResponse.builder()
                .casaId(response.getCasaId())
                .enderecoId(response.getEndereco().getEnderecoId())
                .tipoCasaId(response.getTipoCasa().getTipoCasaId())
                .build();
    }
}