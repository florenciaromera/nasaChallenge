package ar.com.api.climate.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        if (!paisCreado.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(new GenericResponse(true, "El pais fue creado con éxito", paisCreado.get().getPaisId()));
    }

    // con filtro sin cursos: /api/paises?sinTemperaturas=true
    @GetMapping("/api/paises")
    public ResponseEntity<List<PaisResponse>> listaPaises(
            @RequestParam(value = "sinTemperaturas", required = false) boolean sinTemperaturas) {

        List<Pais> paises = new ArrayList<>();
        if (sinTemperaturas) {
            List<PaisResponse> listaPaisesSinTemp = new ArrayList<>();
            for (Pais p : paises) {
                PaisResponse pR = new PaisResponse();
                pR.codigoPais = p.getCodigoPais();
                pR.nombre = p.getNombre();
                listaPaisesSinTemp.add(pR);
            }
            return ResponseEntity.ok(listaPaisesSinTemp);
        }

        List<PaisResponse> listaPaisesConTemp = new ArrayList<>();
        for (Pais p : paises) {
            PaisResponse pR = new PaisResponse();
            pR.codigoPais = p.getCodigoPais();
            pR.nombre = p.getNombre();
            pR.temperaturas = p.getTemperaturas();
            listaPaisesConTemp.add(pR);
        }
        return ResponseEntity.ok(listaPaisesConTemp);
    }

    @GetMapping("/api/paises/{id}")
    public ResponseEntity<PaisResponse> buscarPorIdPais(@PathVariable Integer id) {
        Pais pais = paisService.buscarPorId(id);
        if (pais == null) {
            return ResponseEntity.notFound().build();
        }
        PaisResponse pR = new PaisResponse();
        pR.codigoPais = pais.getCodigoPais();
        pR.nombre = pais.getNombre();
        return ResponseEntity.ok(pR);
    }

    @PutMapping("/api/paises/{id}")
    public ResponseEntity<GenericResponse> actualizarNombrePais(@PathVariable Integer id,
            @RequestBody PaisModifRequest pMR) {
        Pais pais = paisService.buscarPorId(id);
        if (pais == null) {
            return ResponseEntity.notFound().build();
        }

        pais.setNombre(pMR.nombre);
        paisService.actualizarNombrePais(pais);

        GenericResponse gR = new GenericResponse();
        gR.isOk = true;
        gR.message = "Pais actualizado con éxito";

        return ResponseEntity.ok(gR);
    }

}