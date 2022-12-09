package exceptions;

public class NoDevelopersException extends Exception {
    public NoDevelopersException(String errorMessage) {
        super(errorMessage);
    }

    public NoDevelopersException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
