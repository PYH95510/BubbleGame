package bubble.game.component;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import bubble.game.BubbleFrame;
import bubble.game.Movable;
import bubble.game.music.GameOverBGM;
import bubble.game.service.BackgroundPlayerService;
import bubble.game.state.PlayerDirection;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Player extends JLabel implements Movable {

	private BubbleFrame mContext;
	private List<Bubble> bubbleList;
	private GameOver gameOver;
	

	private int x;
	private int y;
	

	private PlayerDirection playerDirection;


	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	

	private boolean leftWallCrash;
	private boolean rightWallCrash;
	

	private final int SPEED = 4;
	private final int JUMPSPEED = 2; // up, down
	
	private int state = 0; // 0 : live , 1 : die

	private ImageIcon playerR, playerL, playerRdie, playerLdie;

	public Player(BubbleFrame mContext) {
		this.mContext = mContext;
		initObject();
		initSetting();
		initBackgroundPlayerService();
	}

	private void initObject() {
		playerR = new ImageIcon("image/playerR.png");
		playerL = new ImageIcon("image/playerL.png");
		playerRdie = new ImageIcon("image/playerRdie.png");
		playerLdie = new ImageIcon("image/playerLdie.png");
		bubbleList = new ArrayList<>();
	}

	private void initSetting() {
		x = 80;
		y = 535;

		left = false;
		right = false;
		up = false;
		down = false;
		
		leftWallCrash = false;
		rightWallCrash = false;

		playerDirection = PlayerDirection.RIGHT;
		
		setIcon(playerR);
		setSize(50, 50);
		setLocation(x, y);
	}
	
	private void initBackgroundPlayerService() {
		new Thread(new BackgroundPlayerService(this)).start();
	}
	
	@Override
	public void attack() {
		new Thread(()->{
			Bubble bubble = new Bubble(mContext);
			mContext.add(bubble);
			bubbleList.add(bubble);
			if(playerDirection == PlayerDirection.LEFT) {
				bubble.left();
			}else {
				bubble.right();
			}
		}).start();
	}

	// 이벤트 핸들러
	@Override
	public void left() {
		//System.out.println("left");
		playerDirection = PlayerDirection.LEFT;
		left = true;
		new Thread(()-> {
			while(left && getState() == 0) {
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
		//System.out.println("right");
		playerDirection = PlayerDirection.RIGHT;
		right = true;
		new Thread(()-> {
			while(right && getState() == 0) {
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

	// left + up, right + up
	@Override
	public void up() {
		//System.out.println("up");
		up = true;
		new Thread(()->{
			for(int i=0; i<130/JUMPSPEED; i++) {
				y = y - JUMPSPEED;
				setLocation(x, y);
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
		//System.out.println("down");
		down = true;
		new Thread(()->{
			while(down) {
				y = y + JUMPSPEED;
				setLocation(x, y);
				try {
					Thread.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			down = false;
		}).start();
	}
	
	public void die() {
		new Thread(() -> {
			setState(1);
			setIcon(PlayerDirection.RIGHT == playerDirection ? playerRdie : playerLdie);
			new GameOverBGM();
			mContext.getBgm().stopBGM();

			try {				
				if(!isUp() && !isDown()) up();
				gameOver = new GameOver(mContext);
				mContext.add(gameOver);
				Thread.sleep(2000);
				mContext.remove(this);
				mContext.repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Player is Dead");
		}).start();
	}
}