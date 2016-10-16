package camphor.game;

/**
 * Created by Administrator on 2016/10/15.
 */
public class ActionEvent {

    private final Integer id;
    private Object message;

    public ActionEvent(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
