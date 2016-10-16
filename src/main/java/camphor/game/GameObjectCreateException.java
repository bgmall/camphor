package camphor.game;

/**
 * Created by Administrator on 2016/10/16.
 */
public class GameObjectCreateException extends RuntimeException {

    public GameObjectCreateException() {
        super();
    }

    public GameObjectCreateException(String message) {
        super(message);
    }

    public GameObjectCreateException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameObjectCreateException(Throwable cause) {
        super(cause);
    }

    protected GameObjectCreateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
