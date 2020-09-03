package ar.com.api.climate.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.api.climate.entities.Temperatura;
import ar.com.api.climate.models.requests.TemperaturaRequest;
import ar.com.api.climate.models.responses.GenericResponse;
import ar.com.api.climate.models.responses.TemperaturaPorAnioResponse;
import ar.com.api.climate.services.PaisService;
import ar.com.api.climate.services.TemperaturaService;

@RestController
public class TemperaturaController {
    @Autowired
    TemperaturaService temperaturaService;

    @Autowired
    PaisService paisService;

    @PostMapping("/api/temperaturas")
    public ResponseEntity<GenericResponse> registrarTemperatura(@RequestBody TemperaturaRequest tR) {
        Optional<Temperatura> temp = temperaturaService.registrarTemp(tR.codigoPais, tR.anio, tR.grados);
        if (temp.isEmpty()) {
            // agregar mensaje de temperatura ya registrada para ese año y pais
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity
                .ok(new GenericResponse(true, "Temperatura registrada con éxito", temp.get().getPais().getCodigoPais(), temp.get().getTemperaturaId()));
    }

    @GetMapping("/api/temperaturas/paises/{codigoPais}")
    public ResponseEntity<List<Temperatura>> listaTemperaturasPorPais(@PathVariable Integer codigoPais) {
        List<Temperatura> listaTemp = paisService.buscarPorCodigo(codigoPais).get().getTemperaturas();
        return ResponseEntity.ok(listaTemp);
    }

    @GetMapping("/api/temperaturas/anios/{anio}")
    public ResponseEntity<List<TemperaturaPorAnioResponse>> listaTemperaturasPorAnio(@PathVariable Integer anio) {
        List<Temperatura> listaTempAnio = temperaturaService.buscarPorAnio(anio).get();
        List<TemperaturaPorAnioResponse> listaTAR = new ArrayList<>();
        for(Temperatura t : listaTempAnio){
            listaTAR.add(new TemperaturaPorAnioResponse(t.getPais().getNombre(), t.getGrados()));
        }
        return ResponseEntity.ok(listaTAR);
    }

    // DELETE/temperaturas/{id}: no se borrará la temp id, debe cambiar el año a 0
    @DeleteMapping("/api/temperaturas/{id}")
    public ResponseEntity<GenericResponse> eliminarTemp(@PathVariable int id) {
        return temperaturaService.delete(id) ? ResponseEntity.ok(new GenericResponse(true, "Temperatura eliminada"))
                : ResponseEntity.badRequest().build();
    }
}