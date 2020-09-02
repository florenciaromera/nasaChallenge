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

    public Optional<Pais> actualizarNombrePais(Integer id, String nombre) {
        Optional<Pais> paisOp = buscarPorId(id);
        if(paisOp.isEmpty()){
            return Optional.empty();
        }
        paisOp.get().setNombre(nombre);
        paisRepo.save(paisOp.get());
        return paisOp;
    }

    public Optional<Pais> buscarPorId(Integer id) {
        return paisRepo.findById(id);

    }

    public Pais buscarPorCodigo(Integer codigoPais) {
        Pais paisEncontrado = paisRepo.buscarPorCodigo(codigoPais);
        if (paisEncontrado == null) {
            return null;
        }
        return paisEncontrado;
    }

}