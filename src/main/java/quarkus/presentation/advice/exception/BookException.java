package quarkus.presentation.advice.exception;

public class BookException extends RuntimeException {
    public BookException(String msj) {
        super(msj);
    }
}
