package ar.com.api.climate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "temperatura")
public class Temperatura {
    @Id
    @Column(name = "temperatura_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer temperaturaId;
    @Column(name = "anio_temperatura")
    private Integer anioTemperatura;
    private Double grados;
    @ManyToOne
    @JoinColumn(name = "pais_id", referencedColumnName = "pais_id")
    private Pais pais;

    public Temperatura(){

    }

    public Temperatura(Pais pais, Integer anio, Double grados){
        this.pais = pais;
        this.anioTemperatura = anio;
        this.grados = grados;
    }

    public Integer getTemperaturaId() {
        return temperaturaId;
    }

    public void setTemperaturaId(Integer temperaturaId) {
        this.temperaturaId = temperaturaId;
    }

    public Integer getAnioTemperatura() {
        return anioTemperatura;
    }

    public void setAnioTemperatura(Integer anioTemperatura) {
        this.anioTemperatura = anioTemperatura;
    }

    public Double getGrados() {
        return grados;
    }

    public void setGrados(Double grados) {
        this.grados = grados;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
        this.pais.getTemperaturas().add(this);
    }

}