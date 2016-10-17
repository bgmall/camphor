package camphor.game;


public class GameEvent<T> {

    private final String id;
    private final T data;
    private final ReplyHandler<?> replyHandler;

    private GameEvent(String id, T data, ReplyHandler<?> replyHandler) {
        this.data = data;
        assert (id != null && id.trim().length() > 0);
        this.id = id.intern();
        this.replyHandler = replyHandler;
    }

    public static <T> GameEvent<T> getInstance(String id, T data, ReplyHandler<?> handler) {
        return new GameEvent<>(id, data, handler);
    }

    public boolean isId(String id) {
        assert (id != null);
        return this.id == id.intern();
    }

    public String getId() {
        return id;
    }

    public T getData() {
        return data;
    }

    @SuppressWarnings("unchecked")
    public <H> ReplyHandler<H> getReplyHandler() {
        return (ReplyHandler<H>) replyHandler;
    }

    public boolean hasReplyHandler() {
        return replyHandler != null;
    }
}
