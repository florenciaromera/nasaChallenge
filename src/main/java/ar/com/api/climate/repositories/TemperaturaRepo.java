package ar.com.api.climate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.api.climate.entities.Temperatura;

@Repository
public interface TemperaturaRepo extends JpaRepository<Temperatura, Integer> {

}