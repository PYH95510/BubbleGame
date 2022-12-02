package bubble.game.component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import bubble.game.BubbleFrame;
import bubble.game.Movable;
import bubble.game.service.BackgroundEnemyService;
import bubble.game.state.EnemyDirection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Enemy extends JLabel implements Movable {

	private BubbleFrame mContext;
	private Player player; 
	

	private int x;
	private int y;
	

	private EnemyDirection enemyDirection;


	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	private int state; 
	

	private final int SPEED = 3;
	private final int JUMPSPEED = 1;

	private ImageIcon enemyR, enemyL;

	public Enemy(BubbleFrame mContext, EnemyDirection enemyDirection) {
		this.mContext = mContext;
		this.player = mContext.getPlayer();
		initObject();
		initSetting();
		initBackgroundEnemyService();
		initEnemyDirection(enemyDirection);
	}

	private void initObject() {
		enemyR = new ImageIcon("image/enemyR.png");
		enemyL = new ImageIcon("image/enemyL.png");
	}

	private void initSetting() {
		x = 480;
		y = 178;

		left = false;
		right = false;
		up = false;
		down = false;
		
		state = 0;

		setSize(50, 50);
		setLocation(x, y);
	}
	
	private void initEnemyDirection(EnemyDirection enemyDirection) {
		if(EnemyDirection.RIGHT == enemyDirection) {
			enemyDirection = EnemyDirection.RIGHT;
			setIcon(enemyR);
			right();
		}else {
			enemyDirection = EnemyDirection.LEFT;
			setIcon(enemyL);
			left();
		}
	}
	
	private void initBackgroundEnemyService() {
		new Thread(new BackgroundEnemyService(this)).start();
	}

	@Override
	public void left() {
		//System.out.println("left");
		enemyDirection = EnemyDirection.LEFT;
		left = true;
		new Thread(()-> {
			while(left) {
				setIcon(enemyL);
				x = x - SPEED;
				setLocation(x, y);
				if (Math.abs(x - player.getX()) < 50 && Math.abs(y - player.getY()) < 50) {
					if (player.getState() == 0 && getState() == 0) 
						player.die();
				}
				try {
					Thread.sleep(10); 
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
			}
		}).start();

	}

	@Override
	public void right() {

		enemyDirection = EnemyDirection.RIGHT;
		right = true;
		new Thread(()-> {
			while(right) {
				setIcon(enemyR);
				x = x + SPEED;
				setLocation(x, y);
				if (Math.abs(x - player.getX()) < 50 && Math.abs(y - player.getY()) < 50) {
					if (player.getState() == 0 && getState() == 0) 
						player.die();
				}
				try {
					Thread.sleep(10); // 
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
			}
		}).start();
		

	}

	@Override
	public void up() {

		up = true;
		new Thread(()->{
			for(int i=0; i<130/JUMPSPEED; i++) {
				y = y - JUMPSPEED;
				setLocation(x, y);
				if (Math.abs(x - player.getX()) < 50 && Math.abs(y - player.getY()) < 50) {
					if (player.getState() == 0 && getState() == 0) 
						player.die();
				}
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			up = false;
			down();
			
		}).start();
	}

	@Override
	public void down() {

		down = true;
		new Thread(()->{
			while(down) {
				y = y + JUMPSPEED;
				setLocation(x, y);
				if (Math.abs(x - player.getX()) < 50 && Math.abs(y - player.getY()) < 50) {
					if (player.getState() == 0 && getState() == 0) 
						player.die();
				}
				try {
					Thread.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			down = false;
		}).start();
	}
}