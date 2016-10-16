package camphor.game;

/**
 * Created by 37 on 2016/9/23.
 */
public class GameEvent<T> {

    private final String id;
    private final T data;
    private ReplyHandler<?> handler;

    private GameEvent(String id, T data) {
        this.data = data;
        assert (id != null && id.trim().length() > 0);
        this.id = id.intern();
    }

    private GameEvent(String id) {
        this(id, null);
    }

    public static GameEvent<?> getInstance(String id) {
        return new GameEvent(id);
    }

    public static <T> GameEvent<T> getInstance(String id, T data) {
        return new GameEvent<T>(id, data);
    }

    public boolean isId(String id) {
        assert (id != null);
        return this.id.equals(id.intern());
    }

    public String getId() {
        return id;
    }

    public T getData() {
        return data;
    }

    <H> void setReplyHandler(ReplyHandler<H> replyHandler) {
        this.handler = replyHandler;
    }

    @SuppressWarnings("unchecked")
    public <H> ReplyHandler<H> getReplyHandler() {
        return (ReplyHandler<H>) handler;
    }
}
