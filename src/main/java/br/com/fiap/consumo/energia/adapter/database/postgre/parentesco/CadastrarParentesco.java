package br.com.fiap.consumo.energia.adapter.database.postgre.parentesco;

import br.com.fiap.consumo.energia.adapter.database.domain.ParentescoConsumoEnergia;
import br.com.fiap.consumo.energia.adapter.database.domain.PessoaConsumoEnergia;
import br.com.fiap.consumo.energia.adapter.database.exceptions.RecursoNaoEncontradoException;
import br.com.fiap.consumo.energia.adapter.database.repository.ParentescoRepository;
import br.com.fiap.consumo.energia.adapter.database.repository.PessoaRepository;
import br.com.fiap.consumo.energia.usecase.database.parentesco.ICadastrarParentesco;
import br.com.fiap.consumo.energia.usecase.database.parentesco.ParentescoRequest;
import br.com.fiap.consumo.energia.usecase.database.parentesco.ParentescoResponse;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CadastrarParentesco implements ICadastrarParentesco {

    private final ParentescoRepository parentescoRepository;
    private final PessoaRepository pessoaRepository;

    @Override
    @Transactional
    public ParentescoResponse cadastrarParentesco(ParentescoRequest request) {
        var parentesco = montarParentescoRequest(request);
        parentescoRepository.save(parentesco);
        return converterResponse(parentesco);
    }

    private ParentescoConsumoEnergia montarParentescoRequest(ParentescoRequest request) {
        val pessoa1 = request.getPessoa1();
        val pessoa2 = request.getPessoa2();
        val pessoa1Bd = buscarPessoa(pessoa1);
        val pessoa2Bd = buscarPessoa(pessoa2);

        return ParentescoConsumoEnergia.builder()
                .descricaoRelacionamento(request.getDescricaoRelacionamento())
                .pessoa1(pessoa1Bd)
                .pessoa2(pessoa2Bd)
                .build();
    }

    @Transactional(readOnly = true)
    private PessoaConsumoEnergia buscarPessoa(UUID pessoaId) {
        return pessoaRepository.findById(pessoaId).orElseThrow(
                () -> new RecursoNaoEncontradoException("A recurso " + pessoaId + " nao foi encontrado na base de dados."));
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
