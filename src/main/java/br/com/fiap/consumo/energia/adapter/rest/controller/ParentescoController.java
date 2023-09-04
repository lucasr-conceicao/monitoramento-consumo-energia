package br.com.fiap.consumo.energia.adapter.rest.controller;

import br.com.fiap.consumo.energia.adapter.rest.dto.parentesco.ParentescoRequestDto;
import br.com.fiap.consumo.energia.adapter.rest.dto.parentesco.ParentescoResponseDto;
import br.com.fiap.consumo.energia.usecase.database.parentesco.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/consumo-energia/v1")
@RequiredArgsConstructor
public class ParentescoController {

    private final ICadastrarParentesco cadastrarParentesco;
    private final IAtualizarParentesco atualizarParentesco;
    private final IDeletarParentesco deletarParentesco;
    private final IBuscarParentesco buscarParentesco;


    @PostMapping("/parentesco")
    public ResponseEntity<ParentescoResponseDto> cadastrarParentesco(@RequestBody ParentescoRequestDto requestDto) {
        var response = cadastrarParentesco.cadastrarParentesco(montarRequest(requestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(converterResponse(response));
    }

    @GetMapping("/parentesco/{parentescoId}")
    public ResponseEntity<ParentescoResponseDto> buscarPessoaId(@PathVariable(value = "parentescoId") UUID parentescoId) {
        var parentesco = buscarParentesco.buscarParentesco(parentescoId);
        return ResponseEntity.status(HttpStatus.OK).body(converterResponse(parentesco));
    }

    @PutMapping("/parentesco/{parentescoId}")
    public ResponseEntity<ParentescoResponseDto> atualizarPessoa(@RequestBody ParentescoRequestDto requestDto,
                                                             @PathVariable(value = "parentescoId") UUID parentescoId) {
        var response = atualizarParentesco.atualizarParentesco(montarRequest(requestDto), parentescoId);
        return ResponseEntity.status(HttpStatus.CREATED).body(converterResponse(response));
    }

    @DeleteMapping("/parentesco/{parentescoId}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable(value = "parentescoId") UUID parentescoId) {
        deletarParentesco.deletarParentesco(parentescoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
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
