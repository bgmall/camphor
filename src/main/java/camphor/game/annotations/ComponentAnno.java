package camphor.game.annotations;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2016/10/16.
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ComponentAnno {
    String serviceName() default "";
    String gameobjectName() default "";
    int[] actionEventIds();
    String[] gameEventIds();
}
