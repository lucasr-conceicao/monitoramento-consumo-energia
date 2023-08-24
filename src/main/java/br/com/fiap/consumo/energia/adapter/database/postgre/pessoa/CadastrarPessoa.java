package br.com.fiap.consumo.energia.adapter.database.postgre.pessoa;

import br.com.fiap.consumo.energia.adapter.database.domain.PessoaConsumoEnergia;
import br.com.fiap.consumo.energia.adapter.database.repository.PessoaRepository;
import br.com.fiap.consumo.energia.usecase.database.pessoa.ICadastrarPessoa;
import br.com.fiap.consumo.energia.usecase.database.pessoa.PessoaRequest;
import br.com.fiap.consumo.energia.usecase.database.pessoa.PessoaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarPessoa implements ICadastrarPessoa {

    private final PessoaRepository repository;

    @Override
    public PessoaResponse cadastrarPessoa(PessoaRequest request) {
        var parentesco = montarParentescoRequest(request);
        repository.save(parentesco);
        return converterResponse(parentesco);
    }

    private PessoaConsumoEnergia montarParentescoRequest(PessoaRequest request) {
        return PessoaConsumoEnergia.builder()
                .nome(request.getNome())
                .cpf(request.getCpf())
                .idade(request.getIdade())
                .genero(request.getGenero())
                .dataNascimento(request.getDataNascimento())
                .email(request.getEmail())
                .build();
    }

    private PessoaResponse converterResponse(PessoaConsumoEnergia response) {
        return PessoaResponse.builder()
                .pessoaId(response.getPessoaId())
                .nome(response.getNome())
                .cpf(response.getCpf())
                .idade(response.getIdade())
                .genero(response.getGenero())
                .dataNascimento(response.getDataNascimento())
                .email(response.getEmail())
                .build();
    }
}
