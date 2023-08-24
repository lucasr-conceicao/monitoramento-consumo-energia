package br.com.fiap.consumo.energia.adapter.database.repository;

import br.com.fiap.consumo.energia.adapter.database.domain.TipoCasaConsumoEnergia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoCasaRepository extends JpaRepository<TipoCasaConsumoEnergia, String> {

}