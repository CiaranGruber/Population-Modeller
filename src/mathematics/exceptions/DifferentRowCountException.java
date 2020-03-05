package mathematics.exceptions;

public class DifferentRowCountException extends RuntimeException {
    public DifferentRowCountException() {
        super();
    }

    public DifferentRowCountException(String message) {
        super(message);
    }

    public DifferentRowCountException(String message, Throwable cause) {
        super(message, cause);
    }

    public DifferentRowCountException(Throwable cause) {
        super(cause);
    }
}
