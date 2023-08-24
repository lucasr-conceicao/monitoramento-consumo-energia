package br.com.fiap.consumo.energia.adapter.rest.handler;

import br.com.fiap.consumo.energia.adapter.database.exceptions.RecursoNaoEncontradoException;
import br.com.fiap.consumo.energia.adapter.exceptions.AdapterException;
import br.com.fiap.consumo.energia.entity.exceptions.EntityException;
import br.com.fiap.consumo.energia.usecase.exceptions.UseCaseException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Clock;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerHandler {

    @ExceptionHandler(AdapterException.class)
    private ResponseEntity<JsonHandler> handlerAdapterException(Exception ex) {

        return null;
    }

    @ExceptionHandler(UseCaseException.class)
    private ResponseEntity<JsonHandler> handlerUseCaseException(Exception ex) {

        return null;
    }

    @ExceptionHandler(EntityException.class)
    private ResponseEntity<JsonHandler> handlerEntityException(Exception ex) {

        return null;
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    private ResponseEntity<JsonHandler> handlerRecursoNaoEncontradoException(Exception ex, HttpServletRequest httpServlet) {
        return montarRetorno(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), httpServlet.getRequestURI(), ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    private ResponseEntity<JsonHandler> handlerMethodArgumentTypeMismatchException(Exception ex, HttpServletRequest httpServlet) {
        return montarRetorno(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), httpServlet.getRequestURI(), ex.getMessage());
    }

    private ResponseEntity<JsonHandler> montarRetorno(HttpStatus httpStatus, Integer code, String path, String mensagem) {
        return new ResponseEntity<>(new JsonHandler(LocalDateTime.now(), code , httpStatus, path, mensagem), httpStatus);
    }
}
