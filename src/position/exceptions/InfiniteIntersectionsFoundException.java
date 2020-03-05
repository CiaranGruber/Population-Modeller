package position.exceptions;

public class InfiniteIntersectionsFoundException extends RuntimeException {
    public InfiniteIntersectionsFoundException() {
        super();
    }

    public InfiniteIntersectionsFoundException(String message) {
        super(message);
    }

    public InfiniteIntersectionsFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public InfiniteIntersectionsFoundException(Throwable cause) {
        super(cause);
    }
}
