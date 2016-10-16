package camphor.game;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/10/15.
 */
public final class GlobalComponentClass {

    private static final GlobalComponentClass instance = new GlobalComponentClass();

    public static GlobalComponentClass getInstance() {
        return instance;
    }

    private GlobalComponentClass() {}

    private final HashMap<String, List<Class<? extends Component>>> gameObjTypeToCompClass = new HashMap<>();
    private final HashMap<String, List<String>> gameEventIdToCompName = new HashMap<>();
    private final HashMap<Integer, String> actionEventIdToCompName = new HashMap<>();

    public void addComponent(Class<? extends Component> componentClass) {

    }

    public List<Class<? extends Component>> getComponents(String gameObjType) {
        return gameObjTypeToCompClass.get(gameObjType);
    }

    public List<String> getCompNamesByObjTypeAndGameEventId(String gameObjType, String gameEventId) {
        return gameEventIdToCompName.get(gameEventId);
    }

    public String getCompNameByObjTypeAndActionEventId(String gameObjType, Integer actionEventId) {
        return actionEventIdToCompName.get(actionEventId);
    }
}
