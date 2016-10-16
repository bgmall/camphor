package camphor.game;

/**
 * Created by Administrator on 2016/10/16.
 */
public class ComponentCreateException extends RuntimeException {

    public ComponentCreateException() {
        super();
    }

    public ComponentCreateException(String message) {
        super(message);
    }

    public ComponentCreateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ComponentCreateException(Throwable cause) {
        super(cause);
    }

    protected ComponentCreateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
