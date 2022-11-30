package bubble.test.ex03;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel implements Movable {

	private int x;
	private int y;
	
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	private ImageIcon PlayerR, PlayerL;

	public Player() {
		initObject();
		initSetting();

	}

	private void initObject() {
		PlayerR = new ImageIcon("image/PlayerR.png");
		PlayerL = new ImageIcon("image/PlayerL.png");
	}

	private void initSetting() {
		x = 55;
		y = 535;
		
		left = false;
		right = false;
		up = false;
		down = false;
		
		
		setIcon(PlayerR);
		setSize(50, 50);
		setLocation(x, y);

	}

	@Override
	public void left() {
		setIcon(PlayerL);
		x = x-10;
		setLocation(x,y);

	}

	@Override
	public void right() {
		
		setIcon(PlayerR);
		x = x+10;
		setLocation(x,y);

	}

	@Override
	public void up() {
		// TODO Auto-generated method stub

	}

	@Override
	public void down() {
		// TODO Auto-generated method stub

	}

}
