package by.huryanchyk.exceptions;

/**
 * Created by Alexei Huryanchyk on 05.12.2015.
 * <p>
 * Throw of dao layer
 */
public class DaoException extends RuntimeException {

    /**
     * Throw of dao layer
     *
     * @param message mistakes message
     * @param cause   cause of exception
     */
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
