package br.com.fiap.consumo.energia.adapter.rest.controller;

import br.com.fiap.consumo.energia.adapter.rest.dto.parentesco.ParentescoRequestDto;
import br.com.fiap.consumo.energia.adapter.rest.dto.parentesco.ParentescoResponseDto;
import br.com.fiap.consumo.energia.usecase.database.parentesco.ICadastrarParentesco;
import br.com.fiap.consumo.energia.usecase.database.parentesco.ParentescoRequest;
import br.com.fiap.consumo.energia.usecase.database.parentesco.ParentescoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.fiap.consumo.energia.adapter.util.ValidarCorrelationId.validarCorrelationId;

@RestController
@RequestMapping("/consumo-energia/v1")
@RequiredArgsConstructor
public class ParentescoController {

    private final ICadastrarParentesco cadastrarParentesco;

    @PostMapping("/parentesco")
    public ResponseEntity<ParentescoResponseDto> cadastrarParentesco(@RequestBody ParentescoRequestDto requestDto,
                                                                     @RequestHeader(value = "correlationId") String correlationId) {
        var correlationIdVlidado = validarCorrelationId(correlationId);
        var response = cadastrarParentesco.cadastrarParentesco(montarRequest(requestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(converterResponse(response));
    }

    private ParentescoRequest montarRequest(ParentescoRequestDto requestDto) {
        return ParentescoRequest.builder()
                .descricaoRelacionamento(requestDto.getDescricaoRelacionamento())
                .pessoa1(requestDto.getPessoa1())
                .pessoa2(requestDto.getPessoa2())
                .build();
    }

    private ParentescoResponseDto converterResponse(ParentescoResponse response) {
        return ParentescoResponseDto.builder()
                .parentescoId(response.getParentescoId())
                .descricaoRelacionamento(response.getDescricaoRelacionamento())
                .pessoa1(response.getPessoa1())
                .pessoa2(response.getPessoa2())
                .build();
    }
}
