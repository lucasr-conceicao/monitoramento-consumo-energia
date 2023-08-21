package br.com.fiap.consumo.energia.entity.exceptions;

public class EntityException extends RuntimeException {
    public EntityException(String mensagem) {
        super(mensagem);
    }
}
