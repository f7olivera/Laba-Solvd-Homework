package exceptions;

public class NoWorkersAvailableException extends RuntimeException {
    public NoWorkersAvailableException(String errorMessage) {
        super(errorMessage);
    }

    public NoWorkersAvailableException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
