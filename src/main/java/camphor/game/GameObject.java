package camphor.game;

import java.util.*;

public final class GameObject implements Component {

    private enum STATE {
        CLOSED,
        INITIALIZED,
        READY,
        SUSPEND,
    }

    private GameObjectManager manager;
    private GameEventManager eventManager;
    private final String type;
    private final long id;
    private STATE state = STATE.CLOSED;
    private ArrayList<Component> components;
    private Map<String, Component> tags;

    public GameObject(String type, long id) {
        this.type = type;
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    void setManager(GameObjectManager manager) {
        this.manager = manager;
    }

    GameObjectManager getManager() {
        return manager;
    }

    void addComponent(Component component) {
        assert (component != null);
        if (!components.contains(component)) {
            components.add(component);
        }
    }

    void addComponent(Component component, String tag) {
        addComponent(component);
        tag(tag, component);
    }

    void tag(String tag, Component component) {
        if (tags == null) {
            tags = new HashMap<String, Component>();
        }
        tags.put(tag, component);
    }

    Component getTagged(String tag) {
        return (tags != null) ? tags.get(tag) : null;
    }

    Component createComponent(Class<? extends AbstractComponent> type) throws ComponentCreateException {
        try {
            AbstractComponent component = type.newInstance();
            component.setParent(this);
            components.add(component);
            return component;
        } catch (Exception e) {
            throw new ComponentCreateException(e);
        }
    }

    Component createComponent(Class<? extends AbstractComponent> type, String tag) throws ComponentCreateException {
        Component component = createComponent(type);
        tag(tag, component);
        return component;
    }

    void createComponents() {

    }

    void removeComponent(String tag) {
        Component component = getTagged(tag);
        component.destroy();
    }

    public void addGameEventListener(String eventId, GameEventListener listener) {
        if (eventManager == null) {
            eventManager = new GameEventManager();
        }
        eventManager.addGameEventListener(eventId, listener);
    }

    public void removeGameEventListener(String eventId, GameEventListener listener) {
        if (eventManager != null) {
            eventManager.removeGameEventListener(eventId, listener);
        }
    }

    @Override
    public void init() {
        if (STATE.CLOSED == state) {
            state = STATE.INITIALIZED;
            components.trimToSize();
            for (int i = 0; i < components.size(); i++) {
                components.get(i).init();
            }
        }
    }

    @Override
    public void ready() {
        if (STATE.INITIALIZED == state || STATE.SUSPEND == state) {
            state = STATE.READY;
            for (int i = 0; i < components.size(); i++) {
                components.get(i).ready();
            }
        }
    }

    @Override
    public void suspend() {
        if (STATE.READY == state) {
            state = STATE.SUSPEND;
            for (int i = 0; i < components.size(); i++) {
                components.get(i).suspend();
            }
        }
    }

    @Override
    public void destroy() {
        if (STATE.CLOSED != state) {
            state = STATE.CLOSED;
            Component comp;
            for (int i = 0; i < components.size(); i++) {
                comp = components.get(i);
                if (comp instanceof GameObject) {
                    getManager().destroyGameObject((GameObject) comp);
                } else {
                    components.get(i).destroy();
                }
            }

            components.clear();
            if (tags != null) {
                tags.clear();
            }
        }
    }

    @Override
    public void handleActionEvent(ActionEvent event) {
        String componentName = GlobalComponentClass.getInstance().getCompNameByObjTypeAndActionEventId(type, event.getId());
        Component component = getTagged(componentName);
        if (component != null) {
            component.handleActionEvent(event);
        }
    }

    @Override
    public void handleGameEvent(GameEvent<?> event) {
        if (eventManager != null) {
            eventManager.fireGameEvent(event);
        }
        List<String> componentNames = GlobalComponentClass.getInstance().getCompNamesByObjTypeAndGameEventId(type, event.getId());
        if (componentNames != null) {
            for (int i = 0; i < componentNames.size(); i++) {
                String compName = componentNames.get(i);
                Component component = getTagged(compName);
                if (component != null) {
                    component.handleGameEvent(event);
                }
            }
        }
    }

    @Override
    public void sendGameEvent(GameEvent<?> event) {
        this.handleGameEvent(event);
    }

    @Override
    public void sendGameEvent(GameEvent<?> event, long receiverId) {
        GameObject gameObject = getManager().getGameObjectById(receiverId);
        if (gameObject != null) {
            gameObject.sendGameEvent(event);
        }
    }

    @Override
    public void fireGameEvent(GameEvent<?> event) {
        getManager().getGameService().getEventManager().fireGameEvent(event);
    }

    @Override
    public String toString() {
        return "GameObject{" +
                "type='" + type + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GameObject other = (GameObject) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
