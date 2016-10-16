/**
 * 
 */
package camphor.game;


public interface GameEventListener {
	
	void handleGameEvent(final GameEvent<?> event);
}
