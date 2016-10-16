/**
 *
 */
package camphor.game;


import java.util.*;

public class GameObjectManager {

    private HashMap<Long, GameObject> objects = new HashMap<>();

    public Map<Long, GameObject> getObjects() {
        return Collections.unmodifiableMap(objects);
    }

    private final GameService gameService;

    public GameObjectManager(GameService gameService) {
        this.gameService = gameService;
    }

    public GameObject getGameObjectById(long id) {
        return objects.get(id);
    }

    // destruction =================================
    public void destroyGameObject(GameObject gameObject) {
        objects.remove(gameObject.getId());
        gameObject.destroy();
    }

    // creation ======================================
    public GameObject createGameObject(String type, long id) throws GameObjectCreateException {
        List<Class<? extends Component>> components = GlobalComponentClass.getInstance().getComponents(type);
        if (components == null) {
            throw new GameObjectCreateException("Can't find components, by gameObjType=" + type);
        }
        if (objects.containsKey(id)) {
            throw new GameObjectCreateException("This gameObject id already exist, id=" + id);
        }
        GameObject gameObject = new GameObject(type, id);
        objects.put(id, gameObject);
        gameObject.setManager(this);
        gameObject.init();
        return gameObject;
    }

    public GameService getGameService() {
        return gameService;
    }
}
