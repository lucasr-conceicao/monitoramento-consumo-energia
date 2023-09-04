package br.com.fiap.consumo.energia.adapter.rest.controller;

import br.com.fiap.consumo.energia.adapter.rest.dto.endereco.EnderecoRequestDto;
import br.com.fiap.consumo.energia.adapter.rest.dto.endereco.EnderecoResponseDto;
import br.com.fiap.consumo.energia.usecase.database.endereco.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/consumo-energia/v1")
@RequiredArgsConstructor
public class EnderecoController {

    private static final Logger logger = LoggerFactory.getLogger(EnderecoController.class);

    private final ICadastrarEndereco cadastrarEndereco;
    private final IAtualizarEndereco atualizarEndereco;
    private final IDeletarEndereco deletarEndereco;
    private final IBuscarEndereco buscarEndereco;

    @PostMapping("/endereco")
    public ResponseEntity<EnderecoResponseDto> cadastrarEndereco(@RequestBody EnderecoRequestDto requestDto) {
        var response = cadastrarEndereco.cadastrarEndereco(montarRequest(requestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(converterResponse(response));
    }

    @GetMapping("/endereco/{enderecoId}")
    public ResponseEntity<EnderecoResponseDto> buscarEnderecoId(@PathVariable(value = "enderecoId") UUID enderecoId) {
        var endereco = buscarEndereco.buscarEndereco(enderecoId);
        return ResponseEntity.status(HttpStatus.OK).body(converterResponse(endereco));
    }

    @PutMapping("/endereco/{enderecoId}")
    public ResponseEntity<EnderecoResponseDto> atualizarEndereco(@RequestBody EnderecoRequestDto requestDto,
                                                                 @PathVariable(value = "enderecoId") UUID enderecoId) {
        var response = atualizarEndereco.atualizarEndereco(montarRequest(requestDto), enderecoId);
        return ResponseEntity.status(HttpStatus.CREATED).body(converterResponse(response));
    }

    @DeleteMapping("/endereco/{enderecoId}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable(value = "enderecoId") UUID enderecoId) {
        deletarEndereco.deletarEndereco(enderecoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private EnderecoRequest montarRequest(EnderecoRequestDto requestDto) {
        return EnderecoRequest.builder()
                .rua(requestDto.getRua())
                .numero(requestDto.getNumero())
                .cep(requestDto.getCep())
                .bairro(requestDto.getBairro())
                .cidade(requestDto.getCidade())
                .estado(requestDto.getEstado())
                .pais(requestDto.getPais())
                .build();
    }

    private EnderecoResponseDto converterResponse(EnderecoResponse response) {
        return EnderecoResponseDto.builder()
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
