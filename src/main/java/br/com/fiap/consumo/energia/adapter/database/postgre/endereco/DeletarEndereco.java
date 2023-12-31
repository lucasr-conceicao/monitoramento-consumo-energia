package br.com.fiap.consumo.energia.adapter.database.postgre.endereco;

import br.com.fiap.consumo.energia.adapter.database.domain.EnderecoConsumoEnergia;
import br.com.fiap.consumo.energia.adapter.database.exceptions.RecursoNaoEncontradoException;
import br.com.fiap.consumo.energia.adapter.database.repository.EnderecoRepository;
import br.com.fiap.consumo.energia.usecase.database.endereco.IDeletarEndereco;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeletarEndereco implements IDeletarEndereco {

    private final EnderecoRepository repository;

    @Override
    @Transactional
    public void deletarEndereco(UUID enderecoId) {
        var endereco = repository.findById(enderecoId);
        var enderecoValidado = validaEndereco(endereco, enderecoId);
        repository.delete(enderecoValidado.get());
    }

    private Optional<EnderecoConsumoEnergia> validaEndereco(Optional<EnderecoConsumoEnergia> response, UUID endereco) {
        if (!response.isPresent())
            throw new RecursoNaoEncontradoException("O recurso " + endereco + " não foi encontrado na tabela tb_endereco_cnsm_enrg.");
        return response;
    }
}
