package camphor.game;

/**
 * Created by 37 on 2016/9/30.
 */
public interface Component extends GameEventListener, ActionEventListener {

    void init();

    void ready();

    void suspend();

    void destroy();

    GameObject getParent();

    void sendGameEvent(GameEvent<?> event);

    void sendGameEvent(GameEvent<?> event, long receiverId);

    void fireGameEvent(GameEvent<?> event);

    <H> void sendReplyEvent(GameEvent<?> event, ReplyHandler<H> handler);

    <H> void sendReplyEvent(GameEvent<?> event, long receiverId, ReplyHandler<H> handler);
}
