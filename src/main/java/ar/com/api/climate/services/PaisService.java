package ar.com.api.climate.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.api.climate.entities.Pais;
import ar.com.api.climate.repositories.PaisRepo;

@Service
public class PaisService {
    @Autowired
    PaisRepo paisRepo;

    public Optional<Pais> crearPais(Integer codigoPais, String nombre) {
        Pais pais = new Pais(codigoPais, nombre);
        if(!paisRepo.existsById(codigoPais)){
            paisRepo.save(pais);
        }
        return pais != null ? Optional.of(pais) : Optional.empty();           
    }

    public Pais actualizarNombrePais(Pais pais) {
        return paisRepo.save(pais);
    }

    public Pais buscarPorId(Integer id) {
        Optional<Pais> opPais = paisRepo.findById(id);

        if (opPais.isPresent())
            return opPais.get();
        else
            return null;

    }

    public Pais buscarPorCodigo(Integer codigoPais) {
        Pais paisEncontrado = paisRepo.buscarPorCodigo(codigoPais);
        if (paisEncontrado == null) {
            return null;
        }
        return paisEncontrado;
    }

}