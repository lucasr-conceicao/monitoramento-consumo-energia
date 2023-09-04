package br.com.fiap.consumo.energia.adapter.database.postgre.casa;

import br.com.fiap.consumo.energia.adapter.database.domain.CasaConsumoEnergia;
import br.com.fiap.consumo.energia.adapter.database.domain.EnderecoConsumoEnergia;
import br.com.fiap.consumo.energia.adapter.database.domain.PessoaConsumoEnergia;
import br.com.fiap.consumo.energia.adapter.database.domain.TipoCasaConsumoEnergia;
import br.com.fiap.consumo.energia.adapter.database.exceptions.RecursoNaoEncontradoException;
import br.com.fiap.consumo.energia.adapter.database.repository.*;
import br.com.fiap.consumo.energia.usecase.database.casa.CasaRequest;
import br.com.fiap.consumo.energia.usecase.database.casa.CasaResponse;
import br.com.fiap.consumo.energia.usecase.database.casa.ICadastrarCasa;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CadastrarCasa implements ICadastrarCasa {

    private final CasaRepository casaRepository;
    private final EnderecoRepository enderecoRepository;
    private final TipoCasaRepository tipoCasaRepository;
    private final PessoaRepository pessoaRepository;
    private final EletrodomesticoRepository eletrodomesticoRepository;

    @Override
    @Transactional
    public CasaResponse cadastrarCasa(CasaRequest request) {
        var casa = montarCasaRequest(request);
        casaRepository.save(casa);
        return converterResponse(casa);
    }

    private CasaConsumoEnergia montarCasaRequest(CasaRequest request) {
        val endereco = buscarEndereco(request);
        val tipoCasa = buscarTipoCasa(request);
        val pessoa = buscarPessoa(request);
        return CasaConsumoEnergia.builder()
                .endereco(endereco)
                .tipoCasa(tipoCasa)
                .pessoa(pessoa)
                .build();
    }

    @Transactional(readOnly = true)
    private EnderecoConsumoEnergia buscarEndereco(CasaRequest request) {
        return enderecoRepository.findById(request.getEnderecoId()).orElseThrow(
                () -> new RecursoNaoEncontradoException("O recurso " + request.getEnderecoId() + " nao foi encontrado na base de dados."));
    }

    @Transactional(readOnly = true)
    private TipoCasaConsumoEnergia buscarTipoCasa(CasaRequest request) {
        return tipoCasaRepository.findById(request.getTipoCasaId()).orElseThrow(
                () -> new RecursoNaoEncontradoException("A recurso " + request.getTipoCasaId() + " nao foi encontrado na base de dados."));
    }

    @Transactional(readOnly = true)
    private PessoaConsumoEnergia buscarPessoa(CasaRequest request) {
        return pessoaRepository.findById(request.getPessoaId()).orElseThrow(
                () -> new RecursoNaoEncontradoException("O recurso " + request.getPessoaId() + " nao foi encontrado na base de dados."));
    }

    private CasaResponse converterResponse(CasaConsumoEnergia response) {
        return CasaResponse.builder()
                .casaId(response.getCasaId())
                .enderecoId(response.getEndereco().getEnderecoId())
                .tipoCasaId(response.getTipoCasa().getTipoCasaId())
                .build();
    }
}