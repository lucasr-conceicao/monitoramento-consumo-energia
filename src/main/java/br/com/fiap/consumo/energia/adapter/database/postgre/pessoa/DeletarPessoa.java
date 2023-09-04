package br.com.fiap.consumo.energia.adapter.database.postgre.pessoa;

import br.com.fiap.consumo.energia.adapter.database.domain.PessoaConsumoEnergia;
import br.com.fiap.consumo.energia.adapter.database.exceptions.RecursoNaoEncontradoException;
import br.com.fiap.consumo.energia.adapter.database.repository.PessoaRepository;
import br.com.fiap.consumo.energia.usecase.database.pessoa.IDeletarPessoa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeletarPessoa implements IDeletarPessoa {

    private final PessoaRepository repository;

    @Override
    @Transactional
    public void deletarPessoa(UUID pessoaId) {
        var pessoa = repository.findById(pessoaId);
        var pessoaValidada = validarPessoa(pessoa, pessoaId);
        repository.delete(pessoaValidada.get());
    }

    private Optional<PessoaConsumoEnergia> validarPessoa(Optional<PessoaConsumoEnergia> response, UUID pessoaId) {
        if (!response.isPresent())
            throw new RecursoNaoEncontradoException("O recurso " + pessoaId + " não foi encontrado na tabela tb_pessoa_cnsm_enrg.");
        return response;
    }
}
