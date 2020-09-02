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

    public Temperatura registrarTemp(Integer codigoPais, Integer anio, Double grados) {
        Pais pais = paisService.buscarPorCodigo(codigoPais);
        if (pais == null) {
            return null;
        }
        Temperatura temp = new Temperatura();
        temp.setPais(pais);
        temp.setAnioTemperatura(anio);
        temp.setGrados(grados);
        tempRepo.save(temp);
        return temp;
    }

    public Temperatura obtenerPorId(Integer id) {
        Optional<Temperatura> opTemp = tempRepo.findById(id);
        if (opTemp == null) {
            return null;
        }
        return opTemp.get();
    }

    public void delete(Temperatura temp) {
        tempRepo.save(temp);
    }

}