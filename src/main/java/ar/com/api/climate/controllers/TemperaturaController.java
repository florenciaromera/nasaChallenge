package ar.com.api.climate.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.api.climate.entities.Temperatura;
import ar.com.api.climate.models.requests.TemperaturaRequest;
import ar.com.api.climate.models.responses.GenericResponse;
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
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new GenericResponse(true, "Temperatura registrada con éxito", temp.get().getTemperaturaId()));
    }

    @GetMapping("/api/temperaturas/paises/{codigoPais}")
    public ResponseEntity<List<Temperatura>> listaTemperaturasPorPais(@PathVariable Integer codigoPais) {
        List<Temperatura> listaTemp = paisService.buscarPorCodigo(codigoPais).getTemperaturas();
        return ResponseEntity.ok(listaTemp);
    }

    // DELETE/temperaturas/{id}: no se borrará la temp id, debe cambiar el año a 0
    @DeleteMapping("/api/temperaturas/{id}")
    public ResponseEntity<GenericResponse> eliminarTemp(@PathVariable int id) {
        Temperatura temp = temperaturaService.obtenerPorId(id);
        if (temp == null) {
            return ResponseEntity.notFound().build();
        }
        temp.setAnioTemperatura(0);
        temperaturaService.delete(temp);

        GenericResponse gR = new GenericResponse();
        gR.isOk = true;
        gR.message = "Temperatura eliminada";
        return ResponseEntity.ok(gR);
    }
}