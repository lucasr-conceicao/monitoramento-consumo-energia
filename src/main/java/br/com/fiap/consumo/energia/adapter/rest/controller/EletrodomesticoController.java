package br.com.fiap.consumo.energia.adapter.rest.controller;

import br.com.fiap.consumo.energia.adapter.rest.dto.eletrodomestico.EletrodomesticoRequestDto;
import br.com.fiap.consumo.energia.adapter.rest.dto.eletrodomestico.EletrodomesticoResponseDto;
import br.com.fiap.consumo.energia.usecase.database.eletrodomestico.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/consumo-energia/v1")
@RequiredArgsConstructor
public class EletrodomesticoController {

    private final IAtualizarEletrodomestico atualizarEletrodomestico;
    private final ICadastrarEletrodomestico cadastrarEletrodomestico;
    private final IDeletarEletrodomestico deletarEletrodomestico;
    private final IBuscarEletrodomestico buscarEletrodomestico;

    @PostMapping("/eletrodomestico")
    public ResponseEntity<EletrodomesticoResponseDto> cadastrarEndereco(@RequestBody EletrodomesticoRequestDto requestDto) {
        var response = cadastrarEletrodomestico.cadastrarEletrodomestico(montarRequest(requestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(converterResponse(response));
    }

    @GetMapping("/eletrodomestico/{eletrodomesticoId}")
    public ResponseEntity<EletrodomesticoResponseDto> buscarCasaId(@PathVariable(value = "eletrodomesticoId") UUID eletrodomesticoId) {
        var response = buscarEletrodomestico.buscarEletrodomestico(eletrodomesticoId);
        return ResponseEntity.status(HttpStatus.OK).body(converterResponse(response));
    }

    @PutMapping("/eletrodomestico/{eletrodomesticoId}")
    public ResponseEntity<EletrodomesticoResponseDto> atualizarCasa(@RequestBody EletrodomesticoRequestDto requestDto,
                                                                    @PathVariable(value = "eletrodomesticoId") UUID eletrodomesticoId) {
        var response = atualizarEletrodomestico.atualizarEletrodomestico(montarRequest(requestDto), eletrodomesticoId);
        return ResponseEntity.status(HttpStatus.CREATED).body(converterResponse(response));
    }

    @DeleteMapping("/eletrodomestico/{eletrodomesticoId}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable(value = "eletrodomesticoId") UUID eletrodomesticoId) {
        deletarEletrodomestico.deletarEletrodomestico(eletrodomesticoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private EletrodomesticoRequest montarRequest(EletrodomesticoRequestDto requestDto) {
        return EletrodomesticoRequest.builder()
                .nome(requestDto.getNome())
                .potencia(requestDto.getPotencia())
                .casaId(requestDto.getCasaId())
                .build();
    }

    private EletrodomesticoResponseDto converterResponse(EletrodomesticoResponse response) {
        return EletrodomesticoResponseDto.builder()
                .eletrodomesticoId(response.getEletrodomesticoId())
                .nome(response.getNome())
                .potencia(response.getPotencia())
                .casaId(response.getCasaId())
                .build();
    }
}
