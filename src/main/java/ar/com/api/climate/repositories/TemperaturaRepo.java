package ar.com.api.climate.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.com.api.climate.entities.Temperatura;

@Repository
public interface TemperaturaRepo extends JpaRepository<Temperatura, Integer> {
    @Query("select t from Temperatura t where t.anioTemperatura=:anio")
	Optional<List<Temperatura>> findByYear(Integer anio);
}