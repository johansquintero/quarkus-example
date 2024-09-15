package quarkus.presentation.advice.exception;

public class TemperatureException extends RuntimeException{
    public TemperatureException(String msj){
        super(msj);
    }
}
