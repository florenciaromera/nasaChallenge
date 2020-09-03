package ar.com.api.climate.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.api.climate.entities.Pais;
import ar.com.api.climate.repositories.PaisRepo;

@Service
public class PaisService {
    @Autowired
    PaisRepo paisRepo;

    public Optional<Pais> buscarPorId(Integer id) {
        Optional<Pais> paisOp = paisRepo.findById(id);
        return paisOp.get() != null ? paisOp : Optional.empty();
    }

    public Optional<Pais> crearPais(Integer codigoPais, String nombre) {
        if(!paisRepo.existsById(codigoPais)){
            Pais pais = new Pais(codigoPais, nombre);
            paisRepo.save(pais);
            return Optional.of(pais);
        }
        return Optional.empty();           
    }

    public Optional<Pais> actualizarNombrePais(Integer id, String nombre) {
        Optional<Pais> paisOp = buscarPorId(id);
        if(paisOp.isPresent()){
            paisOp.get().setNombre(nombre);
            paisRepo.save(paisOp.get());
        }
        return paisOp.get() != null ? paisOp : Optional.empty();
    }

    public Optional<Pais> buscarPorCodigo(Integer codigoPais) {
        Optional<Pais> paisEncontrado = paisRepo.buscarPorCodigo(codigoPais);
        return paisEncontrado.get() != null ? paisEncontrado : Optional.empty();
    }

	public List<Pais> obtenerListaPaises() {
		return paisRepo.findAll();
	}
}