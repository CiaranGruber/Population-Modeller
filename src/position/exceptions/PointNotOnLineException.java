package position.exceptions;

public class PointNotOnLineException extends RuntimeException {
    public PointNotOnLineException() {
        super();
    }

    public PointNotOnLineException(String message) {
        super(message);
    }

    public PointNotOnLineException(String message, Throwable cause) {
        super(message, cause);
    }

    public PointNotOnLineException(Throwable cause) {
        super(cause);
    }
}
