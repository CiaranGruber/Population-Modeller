package mathematics.exceptions;

public class ValueOutOfRangeException extends RuntimeException {
    public ValueOutOfRangeException() {
        super();
    }

    public ValueOutOfRangeException(String message) {
        super(message);
    }

    public ValueOutOfRangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValueOutOfRangeException(Throwable cause) {
        super(cause);
    }
}
