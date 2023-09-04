package br.com.fiap.consumo.energia.adapter.database.postgre.eletrodomestico;

import br.com.fiap.consumo.energia.adapter.database.domain.CasaConsumoEnergia;
import br.com.fiap.consumo.energia.adapter.database.domain.EletrodomesticoConsumoEnergia;
import br.com.fiap.consumo.energia.adapter.database.exceptions.RecursoNaoEncontradoException;
import br.com.fiap.consumo.energia.adapter.database.repository.CasaRepository;
import br.com.fiap.consumo.energia.adapter.database.repository.EletrodomesticoRepository;
import br.com.fiap.consumo.energia.usecase.database.eletrodomestico.EletrodomesticoRequest;
import br.com.fiap.consumo.energia.usecase.database.eletrodomestico.EletrodomesticoResponse;
import br.com.fiap.consumo.energia.usecase.database.eletrodomestico.ICadastrarEletrodomestico;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CadastrarEletrodomestico implements ICadastrarEletrodomestico {

    private final EletrodomesticoRepository eletrodomesticoRepository;
    private final CasaRepository casaRepository;

    @Override
    @Transactional
    public EletrodomesticoResponse cadastrarEletrodomestico(EletrodomesticoRequest request) {
        var eletrodomestico = montarEletrodomesticoRequest(request);
        eletrodomesticoRepository.save(eletrodomestico);
        return converterResponse(eletrodomestico);
    }

    private EletrodomesticoConsumoEnergia montarEletrodomesticoRequest(EletrodomesticoRequest request) {
        val casa = buscarCasa(request);
        return EletrodomesticoConsumoEnergia.builder()
                .nome(request.getNome())
                .potencia(request.getPotencia())
                .casa(casa)
                .build();
    }

    @Transactional(readOnly = true)
    private CasaConsumoEnergia buscarCasa(EletrodomesticoRequest request) {
        return casaRepository.findById(request.getCasaId()).orElseThrow(
                () -> new RecursoNaoEncontradoException("O recurso " + request.getCasaId() + " nao foi encontrado na base de dados."));
    }

    private EletrodomesticoResponse converterResponse(EletrodomesticoConsumoEnergia response) {
        return EletrodomesticoResponse.builder()
                .eletrodomesticoId(response.getEletrodomesticoId())
                .nome(response.getNome())
                .potencia(response.getPotencia())
                .casaId(response.getCasa().getCasaId())
                .build();
    }
}
