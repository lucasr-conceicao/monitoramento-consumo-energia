package br.com.fiap.consumo.energia.adapter.database.postgre.parentesco;

import br.com.fiap.consumo.energia.adapter.database.domain.ParentescoConsumoEnergia;
import br.com.fiap.consumo.energia.adapter.database.exceptions.RecursoNaoEncontradoException;
import br.com.fiap.consumo.energia.adapter.database.repository.ParentescoRepository;
import br.com.fiap.consumo.energia.usecase.database.parentesco.IDeletarParentesco;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeletarParentesco implements IDeletarParentesco {

    private final ParentescoRepository repository;

    @Override
    @Transactional
    public void deletarParentesco(UUID parentescoId) {
        var parentesco = repository.findById(parentescoId);
        var parentescoValidado = validarParentesco(parentesco, parentescoId);
        repository.delete(parentescoValidado.get());
    }

    private Optional<ParentescoConsumoEnergia> validarParentesco(Optional<ParentescoConsumoEnergia> response, UUID parentescoId) {
        if (!response.isPresent())
            throw new RecursoNaoEncontradoException("O recurso " + parentescoId + " não foi encontrado na tabela tb_parentesco_cnsm_enrg.");
        return response;
    }
}
