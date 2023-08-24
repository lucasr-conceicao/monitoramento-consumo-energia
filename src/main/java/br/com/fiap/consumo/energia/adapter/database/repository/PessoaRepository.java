package br.com.fiap.consumo.energia.adapter.database.repository;

import br.com.fiap.consumo.energia.adapter.database.domain.PessoaConsumoEnergia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PessoaRepository extends JpaRepository<PessoaConsumoEnergia, UUID> {

}