package graphing.exceptions;

public class TooManyDataSetsException extends RuntimeException {
    public TooManyDataSetsException() {
        super();
    }

    public TooManyDataSetsException(String message) {
        super(message);
    }

    public TooManyDataSetsException(String message, Throwable cause) {
        super(message, cause);
    }

    public TooManyDataSetsException(Throwable cause) {
        super(cause);
    }
}
