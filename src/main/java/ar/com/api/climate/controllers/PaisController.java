package ar.com.api.climate.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.api.climate.entities.Pais;
import ar.com.api.climate.models.requests.PaisModifRequest;
import ar.com.api.climate.models.requests.PaisRequest;
import ar.com.api.climate.models.responses.GenericResponse;
import ar.com.api.climate.models.responses.PaisResponse;
import ar.com.api.climate.services.PaisService;

@RestController
public class PaisController {
    @Autowired
    PaisService paisService;

    @PostMapping("/api/paises")
    public ResponseEntity<GenericResponse> crearPais(@RequestBody PaisRequest pR) {
        Optional<Pais> paisCreado = paisService.crearPais(pR.codigoPais, pR.nombre);
        if (paisCreado.isEmpty()) {
            // crear mensaje de pais ya existe por eso no creado
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(new GenericResponse(true, "El pais fue creado con éxito", paisCreado.get().getCodigoPais()));
    }

    // con filtro sin cursos: /api/paises?sinTemperaturas=true
    @GetMapping("/api/paises")
    public ResponseEntity<List<PaisResponse>> listaPaises(
            @RequestParam(value = "sinTemperaturas", required = false) boolean sinTemperaturas) {
        List<Pais> paises = paisService.obtenerListaPaises();
        List<PaisResponse> listaPaisesResp = new ArrayList<>();
        if (sinTemperaturas) {
            for (Pais p : paises) {
                PaisResponse pR = new PaisResponse(p.getNombre(), p.getCodigoPais());
                listaPaisesResp.add(pR); 
            }
            return ResponseEntity.ok(listaPaisesResp);
        }
        for (Pais p : paises) {
            PaisResponse pR = new PaisResponse(p.getNombre(), p.getCodigoPais(), p.getTemperaturas());
            listaPaisesResp.add(pR);
        }
        return ResponseEntity.ok(listaPaisesResp);
    }

    @GetMapping("/api/paises/{id}")
    public ResponseEntity<PaisResponse> buscarPorIdPais(@PathVariable Integer id) {
        Optional<Pais> paisOp = paisService.buscarPorId(id);
        if (paisOp.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new PaisResponse(paisOp.get().getNombre(), paisOp.get().getCodigoPais()));
    }

    @PutMapping("/api/paises/{id}")
    public ResponseEntity<GenericResponse> actualizarNombrePais(@PathVariable Integer id,
            @RequestBody PaisModifRequest pMR) {
        Optional<Pais> paisOp = paisService.actualizarNombrePais(id, pMR.nombre);
        if (paisOp.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new GenericResponse(true, "Pais actualizado con éxito", paisOp.get().getCodigoPais()));
    }

}