package ar.com.api.climate.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.api.climate.entities.Pais;
import ar.com.api.climate.entities.Temperatura;
import ar.com.api.climate.repositories.TemperaturaRepo;

@Service
public class TemperaturaService {
    @Autowired
    TemperaturaRepo tempRepo;

    @Autowired
    PaisService paisService;

    public Optional<Temperatura> registrarTemp(Integer codigoPais, Integer anio, Double grados) {
        Pais pais = paisService.buscarPorCodigo(codigoPais);
        if (pais == null) {
            return Optional.empty();
        }
        Temperatura temp = new Temperatura(pais, anio, grados);
        tempRepo.save(temp);
        return Optional.of(temp);
    }

    public Optional<Temperatura> obtenerPorId(Integer id) {
        Optional<Temperatura> opTemp = tempRepo.findById(id);
        return opTemp.get() != null ? opTemp : Optional.empty();
    }

    public boolean delete(Integer id) {
        Optional<Temperatura> tempOp = obtenerPorId(id);
        if (tempOp.isEmpty()) {
            return false;
        }
        tempOp.get().setAnioTemperatura(0);
        tempRepo.save(tempOp.get());
        return true;
    }
}