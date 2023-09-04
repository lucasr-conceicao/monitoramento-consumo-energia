package br.com.fiap.consumo.energia.adapter.database.postgre.pessoa;

import br.com.fiap.consumo.energia.adapter.database.domain.EnderecoConsumoEnergia;
import br.com.fiap.consumo.energia.adapter.database.domain.PessoaConsumoEnergia;
import br.com.fiap.consumo.energia.adapter.database.exceptions.RecursoNaoEncontradoException;
import br.com.fiap.consumo.energia.adapter.database.repository.PessoaRepository;
import br.com.fiap.consumo.energia.usecase.database.endereco.EnderecoResponse;
import br.com.fiap.consumo.energia.usecase.database.pessoa.IBuscarPessoa;
import br.com.fiap.consumo.energia.usecase.database.pessoa.PessoaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BuscarPessoa implements IBuscarPessoa {

    private final PessoaRepository repository;

    @Override
    public PessoaResponse buscarPessoa(UUID pessoaId) {
        var pessoa = repository.findById(pessoaId);
        var pessoaValidada = validarPessoa(pessoa, pessoaId);
        return converterResponse(pessoaValidada);
    }

    private Optional<PessoaConsumoEnergia> validarPessoa(Optional<PessoaConsumoEnergia> response, UUID pessoa) {
        if (!response.isPresent())
            throw new RecursoNaoEncontradoException("O recurso " + pessoa + " não foi encontrado na base de dados.");

        return response;
    }

    private PessoaResponse converterResponse(Optional<PessoaConsumoEnergia> response) {
        return PessoaResponse.builder()
                .pessoaId(response.get().getPessoaId())
                .nome(response.get().getNome())
                .cpf(response.get().getCpf())
                .email(response.get().getEmail())
                .idade(response.get().getIdade())
                .genero(response.get().getGenero())
                .dataNascimento(response.get().getDataNascimento())
                .build();
    }
}
