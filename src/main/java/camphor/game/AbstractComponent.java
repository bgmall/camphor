package camphor.game;

/**
 * Created by Administrator on 2016/10/16.
 */
public abstract class AbstractComponent implements Component {

    private GameObject parent;

    void setParent(GameObject parent) {
        this.parent = parent;
    }

    public GameObject getParent() {
        return this.parent;
    }

    public void init() {

    }

    public void ready() {

    }

    public void suspend() {

    }

    public void destroy() {

    }

    public void sendGameEvent(GameEvent<?> event) {
        parent.sendGameEvent(event);
    }

    public void sendGameEvent(GameEvent<?> event, long receiverId) {
        parent.sendGameEvent(event, receiverId);
    }

    public void fireGameEvent(GameEvent<?> event) {
        parent.fireGameEvent(event);
    }
}
