package exceptions;

public class ProjectNotFoundException extends Exception {
    public ProjectNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public ProjectNotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}