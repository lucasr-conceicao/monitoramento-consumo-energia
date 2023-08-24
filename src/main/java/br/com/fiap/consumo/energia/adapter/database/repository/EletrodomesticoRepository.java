package br.com.fiap.consumo.energia.adapter.database.repository;

import br.com.fiap.consumo.energia.adapter.database.domain.EletrodomesticoConsumoEnergia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EletrodomesticoRepository extends JpaRepository<EletrodomesticoConsumoEnergia, UUID> {

}
