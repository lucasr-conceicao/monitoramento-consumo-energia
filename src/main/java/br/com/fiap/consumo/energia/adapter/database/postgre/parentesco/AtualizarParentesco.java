package br.com.fiap.consumo.energia.adapter.database.postgre.parentesco;

import br.com.fiap.consumo.energia.adapter.database.domain.ParentescoConsumoEnergia;
import br.com.fiap.consumo.energia.adapter.database.exceptions.RecursoNaoEncontradoException;
import br.com.fiap.consumo.energia.adapter.database.repository.ParentescoRepository;
import br.com.fiap.consumo.energia.usecase.database.parentesco.IAtualizarParentesco;
import br.com.fiap.consumo.energia.usecase.database.parentesco.ParentescoRequest;
import br.com.fiap.consumo.energia.usecase.database.parentesco.ParentescoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AtualizarParentesco implements IAtualizarParentesco {

    private final ParentescoRepository repository;

    @Override
    @Transactional
    public ParentescoResponse atualizarParentesco(ParentescoRequest request, UUID parentescoId) {
        var parentesco = montarParentescoRequest(request, parentescoId);
        repository.save(parentesco);
        return converterResponse(parentesco);
    }

    private ParentescoConsumoEnergia montarParentescoRequest(ParentescoRequest request, UUID parentescoId) {
        var parentesco = buscarParentesco(parentescoId);
        parentesco.setDescricaoRelacionamento(request.getDescricaoRelacionamento());
        return parentesco;
    }

    @Transactional(readOnly = true)
    private ParentescoConsumoEnergia buscarParentesco(UUID parentescoId) {
        return repository.findById(parentescoId).orElseThrow(
                () -> new RecursoNaoEncontradoException("O recurso " + parentescoId + " nao foi encontrado na base de dados."));
    }

    private ParentescoResponse converterResponse(ParentescoConsumoEnergia response) {
        return ParentescoResponse.builder()
                .parentescoId(response.getParentescoId())
                .descricaoRelacionamento(response.getDescricaoRelacionamento())
                .pessoa1(response.getPessoa1().getPessoaId())
                .pessoa2(response.getPessoa2().getPessoaId())
                .build();
    }
}
