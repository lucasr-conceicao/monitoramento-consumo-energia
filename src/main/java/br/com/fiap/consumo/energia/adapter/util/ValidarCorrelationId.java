package br.com.fiap.consumo.energia.adapter.util;

import java.util.UUID;

public class ValidarCorrelationId {

    public static String validarCorrelationId(String correlationId) {
        return !correlationId.isEmpty() ? correlationId : gerarCorrelationId();
    }

    private static String gerarCorrelationId() {
        return UUID.randomUUID().toString();
    }
}
