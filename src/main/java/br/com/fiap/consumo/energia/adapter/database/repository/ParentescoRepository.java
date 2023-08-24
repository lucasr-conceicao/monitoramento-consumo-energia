package br.com.fiap.consumo.energia.adapter.database.repository;

import br.com.fiap.consumo.energia.adapter.database.domain.ParentescoConsumoEnergia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParentescoRepository extends JpaRepository<ParentescoConsumoEnergia, UUID> {

}