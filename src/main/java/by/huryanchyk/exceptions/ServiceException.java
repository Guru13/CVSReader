package by.huryanchyk.exceptions;

/**
 Created by Alexei Huryanchyk on 05.12.2015.
 * <p/>
 * Throw of service layer
 */
public class ServiceException extends RuntimeException {

    /**
     * Throw of service layer
     *
     * @param message mistakes message
     * @param cause   cause of exception
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
