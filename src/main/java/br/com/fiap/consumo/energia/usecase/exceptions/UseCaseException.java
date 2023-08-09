package br.com.fiap.consumo.energia.usecase.exceptions;

public class UseCaseException extends RuntimeException {
    public UseCaseException(String mensagem) {
        super(mensagem);
    }
}
