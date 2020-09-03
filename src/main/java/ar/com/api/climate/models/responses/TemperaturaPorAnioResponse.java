package ar.com.api.climate.models.responses;

public class TemperaturaPorAnioResponse {
    public String nombrePais;
    public Double grados;

    public TemperaturaPorAnioResponse(){

    }

    public TemperaturaPorAnioResponse(String nombrePais, Double grados){
        this.nombrePais = nombrePais;
        this.grados = grados;
    }
}
