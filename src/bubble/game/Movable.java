package bubble.game;
/*if we write default, interface also can write method that has method body. (it is because java doesn't have multiple extends) so it is better to have 
 * default patterns than adapter.
 * */
import bubble.game.component.Enemy;
public interface Movable {
	public abstract void left();
	public abstract void right();
	public abstract void up();
	default public void down() {}; 
	default public void attack() {}; 
	default public void attack(Enemy e) {};
}
