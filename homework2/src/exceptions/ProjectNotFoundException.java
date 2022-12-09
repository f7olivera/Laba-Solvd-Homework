package exceptions;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public ProjectNotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}