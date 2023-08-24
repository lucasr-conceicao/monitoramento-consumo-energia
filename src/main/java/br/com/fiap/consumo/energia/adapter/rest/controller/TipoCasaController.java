package br.com.fiap.consumo.energia.adapter.rest.controller;

import br.com.fiap.consumo.energia.adapter.rest.dto.tipocasa.TipoCasaRequestDto;
import br.com.fiap.consumo.energia.adapter.rest.dto.tipocasa.TipoCasaResponseDto;
import br.com.fiap.consumo.energia.usecase.database.tipocasa.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.fiap.consumo.energia.adapter.util.ValidarCorrelationId.validarCorrelationId;

@RestController
@RequestMapping("/consumo-energia/v1")
@RequiredArgsConstructor
public class TipoCasaController {

    private final ICadastrarTipoCasa cadastrarTipoCasa;
    private final IAtualizarTipoCasa atualizarTipoCasa;
    private final IDeletarTipoCasa deletarTipoCasa;
    private final IBuscarTipoCasa buscarTipoCasa;

    @PostMapping("/tipo_casa")
    public ResponseEntity<TipoCasaResponseDto> cadastrarEndereco(@RequestBody TipoCasaRequestDto requestDto,
                                                                 @RequestHeader(value = "correlationId", required = false) String correlationId) {
        var correlationIdVlidado = validarCorrelationId(correlationId);
        var response = cadastrarTipoCasa.cadastrarTipoCasa(montarRequest(requestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(converterResponse(response));
    }

    @GetMapping("/tipo_casa/{tipoCasaId}")
    public ResponseEntity<TipoCasaResponseDto> buscarEnderecoId(@PathVariable(value = "tipoCasaId") String tipoCasaId,
                                                                @RequestHeader(value = "correlationId", required = false) String correlationId) {
        var correlationIdVlidado = validarCorrelationId(correlationId);
        var response = buscarTipoCasa.buscarTipoCasa(tipoCasaId);
        return ResponseEntity.status(HttpStatus.OK).body(converterResponse(response));
    }

    @PutMapping("/tipo_casa/{tipoCasaId}")
    public ResponseEntity<TipoCasaResponseDto> atualizarTipoCasa(@RequestBody TipoCasaRequestDto requestDto,
                                                                 @PathVariable(value = "tipoCasaId") String tipoCasaId,
                                                                 @RequestHeader(value = "correlationId", required = false) String correlationId) {
        var correlationIdVlidado = validarCorrelationId(correlationId);
        var response = atualizarTipoCasa.atualizarTipoCasa(montarRequest(requestDto), tipoCasaId);
        return ResponseEntity.status(HttpStatus.CREATED).body(converterResponse(response));
    }

    @DeleteMapping("/tipo_casa/{tipoCasaId}")
    public ResponseEntity<Void> deletarTipoCasa(@PathVariable(value = "tipoCasaId") String tipoCasaId,
                                                @RequestHeader(value = "correlationId", required = false) String correlationId) {
        var correlationIdVlidado = validarCorrelationId(correlationId);
        deletarTipoCasa.deletarTipoCasa(tipoCasaId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    private TipoCasaRequest montarRequest(TipoCasaRequestDto requestDto) {
        return TipoCasaRequest.builder()
                .tipoCasaId(requestDto.getTipoCasaId())
                .descricao(requestDto.getDescricao())
                .build();
    }

    private TipoCasaResponseDto converterResponse(TipoCasaResponse response) {
        return TipoCasaResponseDto.builder()
                .tipoCasaId(response.getTipoCasaId())
                .descricao(response.getDescricao())
                .build();
    }
}
