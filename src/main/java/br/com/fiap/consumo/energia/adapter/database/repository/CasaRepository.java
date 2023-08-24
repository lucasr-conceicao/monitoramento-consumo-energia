package br.com.fiap.consumo.energia.adapter.database.repository;

import br.com.fiap.consumo.energia.adapter.database.domain.CasaConsumoEnergia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CasaRepository extends JpaRepository<CasaConsumoEnergia, UUID> {

}