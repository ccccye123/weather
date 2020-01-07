package org.ccccye.weather.aspect;

/**
 *
 */
public class RestControllerRequestLimitException extends Exception{

    private static final long serialVersionUID = -6454475988539052779L;

    public RestControllerRequestLimitException() {
        super("HTTP请求超出设定的限制");
    }

    public RestControllerRequestLimitException(String message) {
        super(message);
    }
}
