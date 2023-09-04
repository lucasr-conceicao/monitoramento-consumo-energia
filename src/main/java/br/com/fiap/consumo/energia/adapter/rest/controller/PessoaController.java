package br.com.fiap.consumo.energia.adapter.rest.controller;

import br.com.fiap.consumo.energia.adapter.rest.dto.endereco.EnderecoRequestDto;
import br.com.fiap.consumo.energia.adapter.rest.dto.endereco.EnderecoResponseDto;
import br.com.fiap.consumo.energia.adapter.rest.dto.parentesco.ParentescoRequestDto;
import br.com.fiap.consumo.energia.adapter.rest.dto.parentesco.ParentescoResponseDto;
import br.com.fiap.consumo.energia.adapter.rest.dto.pessoa.PessoaRequestDto;
import br.com.fiap.consumo.energia.adapter.rest.dto.pessoa.PessoaResponseDto;
import br.com.fiap.consumo.energia.usecase.database.parentesco.ParentescoRequest;
import br.com.fiap.consumo.energia.usecase.database.parentesco.ParentescoResponse;
import br.com.fiap.consumo.energia.usecase.database.pessoa.*;
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
public class PessoaController {

    private static final Logger logger = LoggerFactory.getLogger(PessoaController.class);

    private final ICadastrarPessoa cadastrarPessoa;
    private final IAtualizarPessoa atualizarPessoa;
    private final IDeletarPessoa deletarPessoa;
    private final IBuscarPessoa buscarPessoa;

    @PostMapping("/pessoa")
    public ResponseEntity<PessoaResponseDto> cadastrarPessoa(@RequestBody PessoaRequestDto requestDto) {
        var response = cadastrarPessoa.cadastrarPessoa(montarRequest(requestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(converterResponse(response));
    }

    @GetMapping("/pessoa/{pessoaId}")
    public ResponseEntity<PessoaResponseDto> buscarPessoaId(@PathVariable(value = "pessoaId") UUID pessoaId) {
        var pessoa = buscarPessoa.buscarPessoa(pessoaId);
        return ResponseEntity.status(HttpStatus.OK).body(converterResponse(pessoa));
    }

    @PutMapping("/pessoa/{pessoaId}")
    public ResponseEntity<PessoaResponseDto> atualizarPessoa(@RequestBody PessoaRequestDto requestDto,
                                                             @PathVariable(value = "pessoaId") UUID pessoaId) {
        var response = atualizarPessoa.atualizarPessoa(montarRequest(requestDto), pessoaId);
        return ResponseEntity.status(HttpStatus.CREATED).body(converterResponse(response));
    }

    @DeleteMapping("/pessoa/{pessoaId}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable(value = "pessoaId") UUID pessoaId) {
        deletarPessoa.deletarPessoa(pessoaId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
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
