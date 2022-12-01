package bubble.test.ex05;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

// class Player -> new 가능한 애들!! 게임에 존재할 수 있음. (추상메서드를 가질 수 없다.)
@Getter
@Setter
public class Player extends JLabel implements Movable {

	
	private int x;
	private int y;

	
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	private final int SPEED = 4;
	private final int JUMPSPEED = 2;
	
	
	
	private ImageIcon playerR, playerL;

	public Player() {
		initObject();
		initSetting();
	}

	private void initObject() {
		playerR = new ImageIcon("image/playerR.png");
		playerL = new ImageIcon("image/playerL.png");
	}

	private void initSetting() {
		x = 55;
		y = 535;

		left = false;
		right = false;
		up = false;
		down = false;

		setIcon(playerR);
		setSize(50, 50);
		setLocation(x, y);
	}


	@Override
	public void left() {
		System.out.println("left");
		left = true;
		new Thread(()-> {
			while(left) {
				setIcon(playerL);
				x = x - SPEED;
				setLocation(x, y);
				try {
					Thread.sleep(10); // 0.01초
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
			}
		}).start();

	}

	@Override
	public void right() {
		System.out.println("right");
		right = true;
		new Thread(()-> {
			while(right) {
				setIcon(playerR);
				x = x + SPEED;
				setLocation(x, y);
				try {
					Thread.sleep(10); // 0.01초
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
			}
		}).start();
		

	}

	//left up, right up
	@Override
	public void up() {
		up = true;
		new Thread(()->{
			for(int i = 0; i<130/JUMPSPEED; i ++) {
				y = y -JUMPSPEED;
				setLocation(x,y);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
			up = false; //jump is done so up is false
			down(); //down is start
			
		}).start();
		System.out.println("jump");

	}

	@Override
	public void down() {
		down = true;
		new Thread(()->{
			for(int i = 0; i<130/JUMPSPEED; i ++) {
				y = y + JUMPSPEED;
				setLocation(x,y);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
			down = false;
			
		}).start();
	}
}
