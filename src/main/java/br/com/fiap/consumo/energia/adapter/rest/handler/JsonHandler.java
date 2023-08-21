package br.com.fiap.consumo.energia.adapter.rest.handler;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Builder
public class JsonHandler {

    private LocalDateTime data;
    private Integer code;
    private HttpStatus httpStatus;
    private String path;
    private String mensagem;
}
