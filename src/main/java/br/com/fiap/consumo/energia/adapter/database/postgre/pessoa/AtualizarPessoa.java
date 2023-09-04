package br.com.fiap.consumo.energia.adapter.database.postgre.pessoa;

import br.com.fiap.consumo.energia.adapter.database.domain.PessoaConsumoEnergia;
import br.com.fiap.consumo.energia.adapter.database.exceptions.RecursoNaoEncontradoException;
import br.com.fiap.consumo.energia.adapter.database.repository.PessoaRepository;
import br.com.fiap.consumo.energia.usecase.database.pessoa.IAtualizarPessoa;
import br.com.fiap.consumo.energia.usecase.database.pessoa.PessoaRequest;
import br.com.fiap.consumo.energia.usecase.database.pessoa.PessoaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AtualizarPessoa implements IAtualizarPessoa {

    private final PessoaRepository repository;

    @Override
    public PessoaResponse atualizarPessoa(PessoaRequest request, UUID pessoaId) {

        var pessoa = montarPessoaRequest(request, pessoaId);
        repository.save(pessoa);
        return converterResponse(pessoa);
    }

    private PessoaConsumoEnergia montarPessoaRequest(PessoaRequest request, UUID pessoaId) {
        var pessoa = buscarPessoa(pessoaId);
        pessoa.setNome(request.getNome());
        pessoa.setCpf(request.getCpf());
        pessoa.setEmail(request.getEmail());
        pessoa.setIdade(request.getIdade());
        pessoa.setEmail(request.getEmail());
        pessoa.setGenero(request.getGenero());
        pessoa.setDataNascimento(request.getDataNascimento());
        return pessoa;
    }

    private PessoaResponse converterResponse(PessoaConsumoEnergia response) {
        return PessoaResponse.builder()
                .pessoaId(response.getPessoaId())
                .nome(response.getNome())
                .cpf(response.getCpf())
                .email(response.getEmail())
                .idade(response.getIdade())
                .genero(response.getGenero())
                .dataNascimento(response.getDataNascimento())
                .build();
    }

    @Transactional(readOnly = true)
    private PessoaConsumoEnergia buscarPessoa(UUID pessoaId) {
        return repository.findById(pessoaId).orElseThrow(
                () -> new RecursoNaoEncontradoException("O recurso " + pessoaId + " nao foi encontrado na base de dados."));
    }
}
