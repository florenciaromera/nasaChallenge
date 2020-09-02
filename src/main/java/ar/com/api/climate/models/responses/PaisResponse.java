package ar.com.api.climate.models.responses;

import java.util.List;

import ar.com.api.climate.entities.Temperatura;

public class PaisResponse {
    public String nombre;
    public Integer codigoPais;
    public List<Temperatura> temperaturas;
}