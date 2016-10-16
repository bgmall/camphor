package camphor.game;

/**
 * Created by 37 on 2016/9/30.
 */
@FunctionalInterface
public interface ReplyHandler<T> {

    void reply(T result);
}
