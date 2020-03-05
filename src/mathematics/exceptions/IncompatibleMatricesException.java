package mathematics.exceptions;

public class IncompatibleMatricesException extends RuntimeException {
    public IncompatibleMatricesException() {
        super();
    }

    public IncompatibleMatricesException(String message) {
        super(message);
    }

    public IncompatibleMatricesException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncompatibleMatricesException(Throwable cause) {
        super(cause);
    }
}
