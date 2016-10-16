package camphor.game;

/**
 * Created by 37 on 2016/9/30.
 */
public interface GameService {

    String getName();

    String getAddress();

    GameObjectManager getObjectManager();

    GameEventManager getEventManager();

    void registerService(Class<?> interfaceClass, Object impObj);

    <T> T createService(Class<?> interfaceClass, String serviceAddress);
}
