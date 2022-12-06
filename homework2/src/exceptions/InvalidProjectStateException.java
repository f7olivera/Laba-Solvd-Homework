package exceptions;

public class InvalidProjectStateException extends Exception {
    public InvalidProjectStateException(String errorMessage) {
        super(errorMessage);
    }

    public InvalidProjectStateException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
