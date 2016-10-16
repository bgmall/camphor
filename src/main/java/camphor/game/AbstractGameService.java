package camphor.game;

/**
 * Created by Administrator on 2016/10/16.
 */
public abstract class AbstractGameService extends AbstractVerticle implements GameService {

    private final GameObjectManager objectManager = new GameObjectManager(this);
    private final GameEventManager eventManager = new GameEventManager();
    private final String name;
    private String address;

    public AbstractGameService(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public GameObjectManager getObjectManager() {
        return this.objectManager;
    }

    public GameEventManager getEventManager() {
        return this.eventManager;
    }
}
