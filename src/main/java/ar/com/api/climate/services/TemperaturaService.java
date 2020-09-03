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

    public Optional<Temperatura> obtenerPorId(Integer id) {
        Optional<Temperatura> opTemp = tempRepo.findById(id);
        return opTemp.get() != null ? opTemp : Optional.empty();
    }

    public Optional<Temperatura> registrarTemp(Integer codigoPais, Integer anio, Double grados) {
        Optional<Pais> paisOp = paisService.buscarPorCodigo(codigoPais);
        if (paisOp.isEmpty()) {
            return Optional.empty();
        }

        Temperatura temp = new Temperatura(paisOp.get(), anio, grados);
        List<Temperatura> temperaturas = paisOp.get().getTemperaturas();
        boolean añoTemp = temperaturas.stream().anyMatch(t -> t.getAnioTemperatura().equals(temp.getAnioTemperatura()));
        if(!añoTemp){
            tempRepo.save(temp);
        }
        return !añoTemp ? Optional.of(temp) : Optional.empty();
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