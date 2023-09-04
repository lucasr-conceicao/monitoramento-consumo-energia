package br.com.fiap.consumo.energia.adapter.database.postgre.eletrodomestico;

import br.com.fiap.consumo.energia.adapter.database.domain.EletrodomesticoConsumoEnergia;
import br.com.fiap.consumo.energia.adapter.database.exceptions.RecursoNaoEncontradoException;
import br.com.fiap.consumo.energia.adapter.database.repository.EletrodomesticoRepository;
import br.com.fiap.consumo.energia.usecase.database.eletrodomestico.EletrodomesticoResponse;
import br.com.fiap.consumo.energia.usecase.database.eletrodomestico.IBuscarEletrodomestico;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BuscarEletrodomestico implements IBuscarEletrodomestico {

    private final EletrodomesticoRepository repository;

    @Override
    @Transactional
    public EletrodomesticoResponse buscarEletrodomestico(UUID eletrodomesticoId) {
        var eletrodomestico = repository.findById(eletrodomesticoId);
        var eletrodomesticoValidado = validarCasa(eletrodomestico, eletrodomesticoId);
        return converterResponse(eletrodomesticoValidado);
    }

    private Optional<EletrodomesticoConsumoEnergia> validarCasa(Optional<EletrodomesticoConsumoEnergia> response, UUID eletrodomesticoId) {
        if (!response.isPresent())
            throw new RecursoNaoEncontradoException("O recurso " + eletrodomesticoId + " não foi encontrado na tabela tb_eletrodomestico_cnsm_enrg.");
        return response;
    }

    private EletrodomesticoResponse converterResponse(Optional<EletrodomesticoConsumoEnergia> response) {
        return EletrodomesticoResponse.builder()
                .eletrodomesticoId(response.get().getEletrodomesticoId())
                .nome(response.get().getNome())
                .potencia(response.get().getPotencia())
                .casaId(response.get().getCasa().getCasaId())
                .build();

    }
}
