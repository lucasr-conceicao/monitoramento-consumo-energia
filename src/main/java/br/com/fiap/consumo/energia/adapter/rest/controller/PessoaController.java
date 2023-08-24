package br.com.fiap.consumo.energia.adapter.rest.controller;

import br.com.fiap.consumo.energia.adapter.rest.dto.parentesco.ParentescoRequestDto;
import br.com.fiap.consumo.energia.adapter.rest.dto.parentesco.ParentescoResponseDto;
import br.com.fiap.consumo.energia.adapter.rest.dto.pessoa.PessoaRequestDto;
import br.com.fiap.consumo.energia.adapter.rest.dto.pessoa.PessoaResponseDto;
import br.com.fiap.consumo.energia.usecase.database.parentesco.ParentescoRequest;
import br.com.fiap.consumo.energia.usecase.database.parentesco.ParentescoResponse;
import br.com.fiap.consumo.energia.usecase.database.pessoa.ICadastrarPessoa;
import br.com.fiap.consumo.energia.usecase.database.pessoa.PessoaRequest;
import br.com.fiap.consumo.energia.usecase.database.pessoa.PessoaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.fiap.consumo.energia.adapter.util.ValidarCorrelationId.validarCorrelationId;

@RestController
@RequestMapping("/consumo-energia/v1")
@RequiredArgsConstructor
public class PessoaController {

    private final ICadastrarPessoa cadastrarPessoa;

    @PostMapping("/pessoa")
    public ResponseEntity<PessoaResponseDto> cadastrarParentesco(@RequestBody PessoaRequestDto requestDto,
                                                                 @RequestHeader(value = "correlationId") String correlationId) {
        var correlationIdVlidado = validarCorrelationId(correlationId);
        var response = cadastrarPessoa.cadastrarPessoa(montarRequest(requestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(converterResponse(response));
    }

    private PessoaRequest montarRequest(PessoaRequestDto requestDto) {
        return PessoaRequest.builder()
                .nome(requestDto.getNome())
                .cpf(requestDto.getCpf())
                .idade(requestDto.getIdade())
                .genero(requestDto.getGenero())
                .dataNascimento(requestDto.getDataNascimento())
                .email(requestDto.getEmail())
                .build();
    }

    private PessoaResponseDto converterResponse(PessoaResponse response) {
        return PessoaResponseDto.builder()
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
