package br.com.fiap.consumo.energia.adapter.database.postgre.endereco;

import br.com.fiap.consumo.energia.adapter.database.domain.EnderecoConsumoEnergia;
import br.com.fiap.consumo.energia.adapter.database.repository.EnderecoRepository;
import br.com.fiap.consumo.energia.adapter.rest.controller.EnderecoController;
import br.com.fiap.consumo.energia.usecase.database.endereco.EnderecoRequest;
import br.com.fiap.consumo.energia.usecase.database.endereco.EnderecoResponse;
import br.com.fiap.consumo.energia.usecase.database.endereco.ICadastrarEndereco;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarEndereco implements ICadastrarEndereco {

    private static final Logger logger = LoggerFactory.getLogger(EnderecoController.class);

    private final EnderecoRepository repository;

    @Override
    public EnderecoResponse cadastrarEndereco(EnderecoRequest request) {

        var endereco = montarEnderecoRequest(request);

        repository.save(endereco);

        return converterResponse(endereco);
    }

    private EnderecoConsumoEnergia montarEnderecoRequest(EnderecoRequest request) {
        return EnderecoConsumoEnergia.builder()
                .rua(request.getRua())
                .numero(request.getNumero())
                .cep(request.getCep())
                .bairro(request.getBairro())
                .cidade(request.getCidade())
                .estado(request.getEstado())
                .pais(request.getPais())
                .build();
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
}
