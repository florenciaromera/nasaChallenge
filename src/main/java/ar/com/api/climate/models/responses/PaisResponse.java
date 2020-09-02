package ar.com.api.climate.models.responses;

import java.util.List;

import ar.com.api.climate.entities.Temperatura;

public class PaisResponse {
    public String nombre;
    public Integer codigoPais;
    public List<Temperatura> temperaturas;

    public PaisResponse(){

    }

    public PaisResponse(String nombre, Integer codigoPais){
        this.nombre = nombre;
        this.codigoPais = codigoPais;
    }

    public PaisResponse(String nombre, Integer codigoPais, List<Temperatura> temperaturas){
        this.nombre = nombre;
        this.codigoPais = codigoPais;
        this.temperaturas = temperaturas;
    }
}