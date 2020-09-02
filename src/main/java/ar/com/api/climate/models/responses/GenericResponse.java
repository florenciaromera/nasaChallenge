package ar.com.api.climate.models.responses;

public class GenericResponse {
    public boolean isOk;
    public String message;
    public Integer paisId;
    public Integer tempId;

    public GenericResponse(){
        
    }

    public GenericResponse(boolean isOk, String message, Integer paisId){
        this.isOk = isOk;
        this.message = message;
        this.paisId = paisId;
    }

    public GenericResponse(boolean isOk, String message, Integer paisId, Integer tempId){
        this.isOk = isOk;
        this.message = message;
        this.paisId = paisId;
        this.tempId = tempId;
    }
}