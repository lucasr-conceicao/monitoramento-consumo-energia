package br.com.fiap.consumo.energia.adapter.database.postgre.eletrodomestico;

import br.com.fiap.consumo.energia.adapter.database.domain.CasaConsumoEnergia;
import br.com.fiap.consumo.energia.adapter.database.domain.EletrodomesticoConsumoEnergia;
import br.com.fiap.consumo.energia.adapter.database.exceptions.RecursoNaoEncontradoException;
import br.com.fiap.consumo.energia.adapter.database.repository.CasaRepository;
import br.com.fiap.consumo.energia.adapter.database.repository.EletrodomesticoRepository;
import br.com.fiap.consumo.energia.usecase.database.eletrodomestico.EletrodomesticoRequest;
import br.com.fiap.consumo.energia.usecase.database.eletrodomestico.EletrodomesticoResponse;
import br.com.fiap.consumo.energia.usecase.database.eletrodomestico.IAtualizarEletrodomestico;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AtualizarEletrodomestico implements IAtualizarEletrodomestico {

    private final EletrodomesticoRepository eletrodomesticoRepository;
    private final CasaRepository casaRepository;

    @Override
    @Transactional
    public EletrodomesticoResponse atualizarEletrodomestico(EletrodomesticoRequest request, UUID eletrodomesticoId) {
        var eletrodomestico = montarEletrodomesticoRequest(request, eletrodomesticoId);
        eletrodomesticoRepository.save(eletrodomestico);
        return converterResponse(eletrodomestico);
    }

    private EletrodomesticoConsumoEnergia montarEletrodomesticoRequest(EletrodomesticoRequest request, UUID eletrodomesticoId) {
        var eletrodomestico = buscarEletrodomestico(eletrodomesticoId);
        val casa = buscarCasa(request.getCasaId());

        eletrodomestico.setNome(request.getNome());
        eletrodomestico.setPotencia(request.getPotencia());
        eletrodomestico.setCasa(casa);

        return eletrodomestico;
    }

    @Transactional(readOnly = true)
    private EletrodomesticoConsumoEnergia buscarEletrodomestico(UUID eletrodomesticoId) {
        return eletrodomesticoRepository.findById(eletrodomesticoId).orElseThrow(
                () -> new RecursoNaoEncontradoException("O recurso " + eletrodomesticoId + " nao foi encontrado na tabela tb_eletrodomestico_cnsm_enrg."));
    }

    @Transactional(readOnly = true)
    private CasaConsumoEnergia buscarCasa(UUID casaId) {
        return casaRepository.findById(casaId).orElseThrow(
                () -> new RecursoNaoEncontradoException("O recurso " + casaId + " nao foi encontrado na tabela tb_casa_cnsm_enrg."));
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
