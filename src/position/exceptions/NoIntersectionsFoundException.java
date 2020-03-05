package position.exceptions;

public class NoIntersectionsFoundException extends RuntimeException {
    public NoIntersectionsFoundException() {
        super();
    }

    public NoIntersectionsFoundException(String message) {
        super(message);
    }

    public NoIntersectionsFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoIntersectionsFoundException(Throwable cause) {
        super(cause);
    }
}
