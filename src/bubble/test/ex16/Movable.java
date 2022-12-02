package bubble.test.ex16;
/*if we write default, interface also can write method that has method body. (it is because java doesn't have multiple extends) so it is better to have 
 * default patterns than adapter.
 * */
public interface Movable {
	public abstract void left();
	public abstract void right();
	public abstract void up();
	default public void down() {}; 
	default public void attack() {}; 
}
