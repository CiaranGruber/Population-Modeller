package graphing.exceptions;

public class IDAlreadyExistsException extends RuntimeException {
    public IDAlreadyExistsException() {
        super();
    }

    public IDAlreadyExistsException(String message) {
        super(message);
    }

    public IDAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public IDAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
