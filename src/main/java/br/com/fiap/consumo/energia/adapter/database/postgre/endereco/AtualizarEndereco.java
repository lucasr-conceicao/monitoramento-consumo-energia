package br.com.fiap.consumo.energia.adapter.database.postgre.endereco;

import br.com.fiap.consumo.energia.adapter.database.domain.EnderecoConsumoEnergia;
import br.com.fiap.consumo.energia.adapter.database.exceptions.RecursoNaoEncontradoException;
import br.com.fiap.consumo.energia.adapter.database.repository.EnderecoRepository;
import br.com.fiap.consumo.energia.usecase.database.endereco.EnderecoRequest;
import br.com.fiap.consumo.energia.usecase.database.endereco.EnderecoResponse;
import br.com.fiap.consumo.energia.usecase.database.endereco.IAtualizarEndereco;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AtualizarEndereco implements IAtualizarEndereco {

    private final EnderecoRepository repository;

    @Override
    @Transactional
    public EnderecoResponse atualizarEndereco(EnderecoRequest request, UUID enderecoId) {
        var endereco = montarEndereco(request, enderecoId);
        repository.save(endereco);
        return converterResponse(endereco);
    }

    private EnderecoConsumoEnergia montarEndereco(EnderecoRequest request, UUID enderecoId) {
        var endereco = buscarEndereco(enderecoId);
        endereco.setRua(request.getRua());
        endereco.setNumero(request.getNumero());
        endereco.setCep(request.getCep());
        endereco.setBairro(request.getBairro());
        endereco.setCidade(request.getCidade());
        endereco.setEstado(request.getEstado());
        endereco.setPais(request.getPais());
        return endereco;
    }

    private EnderecoResponse converterResponse(EnderecoConsumoEnergia response) {
        return EnderecoResponse.builder()
                .enderecoId(response.getEnderecoId())
                .rua(response.getRua())
                .numero(response.getNumero())
                .cep(response.getCep())
                .bairro(response.getBairro())
                .cidade(response.getCidade())
                .estado(response.getEstado())
                .pais(response.getPais())
                .build();
    }

    private EnderecoConsumoEnergia buscarEndereco(UUID enderecoId) {
        return repository.findById(enderecoId).orElseThrow(
                () -> new RecursoNaoEncontradoException("O endereco " + enderecoId + "nao foi encontrado na tabela tb_endereco_cnsm_enrg."));
    }
}
