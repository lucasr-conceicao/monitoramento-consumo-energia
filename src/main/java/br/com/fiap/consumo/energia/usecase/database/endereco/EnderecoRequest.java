package br.com.fiap.consumo.energia.usecase.database.endereco;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EnderecoRequest {

    private String rua;
    private Integer numero;
    private String cep;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
}