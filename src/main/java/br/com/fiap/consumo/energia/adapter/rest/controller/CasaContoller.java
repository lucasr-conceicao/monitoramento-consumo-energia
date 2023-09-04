package br.com.fiap.consumo.energia.adapter.rest.controller;

import br.com.fiap.consumo.energia.adapter.rest.dto.casa.CasaRequestDto;
import br.com.fiap.consumo.energia.adapter.rest.dto.casa.CasaResponseDto;
import br.com.fiap.consumo.energia.usecase.database.casa.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/consumo-energia/v1")
@RequiredArgsConstructor
public class CasaContoller {

    private final IAtualizarCasa atualizarCasa;
    private final ICadastrarCasa cadastrarCasa;
    private final IDeletarCasa deletarCasa;
    private final IBuscarCasa buscarCasa;

    @PostMapping("/casa")
    public ResponseEntity<CasaResponseDto> cadastrarEndereco(@RequestBody CasaRequestDto requestDto) {
        var response = cadastrarCasa.cadastrarCasa(montarRequest(requestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(converterResponse(response));
    }

    @GetMapping("/casa/{casaId}")
    public ResponseEntity<CasaResponseDto> buscarCasaId(@PathVariable(value = "casaId") UUID casaId) {
        var response = buscarCasa.buscarCasa(casaId);
        return ResponseEntity.status(HttpStatus.OK).body(converterResponse(response));
    }

    @PutMapping("/casa/{casaId}")
    public ResponseEntity<CasaResponseDto> atualizarCasa(@RequestBody CasaRequestDto requestDto,
                                                         @PathVariable(value = "casaId") UUID casaId) {
        var response = atualizarCasa.atualizarCasa(montarRequest(requestDto), casaId);
        return ResponseEntity.status(HttpStatus.CREATED).body(converterResponse(response));
    }

    @DeleteMapping("/casa/{casaId}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable(value = "casaId") UUID casaId) {
        deletarCasa.deletarCasa(casaId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private CasaRequest montarRequest(CasaRequestDto requestDto) {
        return CasaRequest.builder()
                .enderecoId(requestDto.getEnderecoId())
                .tipoCasaId(requestDto.getTipoCasaId())
                .pessoaId(requestDto.getPessoaId())
                .build();
    }

    private CasaResponseDto converterResponse(CasaResponse response) {
        return CasaResponseDto.builder()
                .casaId(response.getCasaId())
                .enderecoId(response.getEnderecoId())
                .tipoCasaId(response.getTipoCasaId().toString())
                .build();
    }
}
