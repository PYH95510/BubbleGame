package bubble.test.ex17;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bubble extends JLabel implements Movable{
	

	private BubbleFrame mContext;
	private Player player;
	private Enemy enemy;
	private BackgroundBubbleService backgroundBubbleService;
	

	private int x;
	private int y;


	private boolean left;
	private boolean right;
	private boolean up;
	

	private int state; 
	
	private ImageIcon bubble; 
	private ImageIcon bubbled; 
	private ImageIcon bomb; 

	public Bubble(BubbleFrame mContext) {
		this.mContext = mContext;
		this.player = mContext.getPlayer();
		this.enemy = mContext.getEnemy();
		initObject();
		initSetting();
	}
	
	private void initObject() {
		bubble = new ImageIcon("image/bubble.png");
		bubbled = new ImageIcon("image/bubbled.png");
		bomb = new ImageIcon("image/bomb.png");
		
		backgroundBubbleService = new BackgroundBubbleService(this);
	}
	
	private void initSetting() {
		left = false;
		right = false;
		up = false;
		
		x = player.getX();
		y = player.getY();
		
		setIcon(bubble);
		setSize(50, 50);
		
		state = 0;
	}

	@Override
	public void left() {
		left = true;
		for(int i=0; i<400; i++) {
			x--;
			setLocation(x, y);
			
			if(backgroundBubbleService.leftWall()) {
				left = false;
				break;
			}
			
			
			if((Math.abs(x - enemy.getX()) < 10 ) && 
					Math.abs(y - enemy.getY()) > 0 && Math.abs(y - enemy.getY()) < 50 ) {
				System.out.println("Bubble got the enemy");
				if(enemy.getState() == 0) {
					attack();
					break;
				}
			}
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		up();
	}

	@Override
	public void right() {
		right = true;
		for(int i=0; i<400; i++) {
			x++;
			setLocation(x, y);
			
			if(backgroundBubbleService.rightWall()) {
				right = false;
				break;
			}
			
			
			if((Math.abs(x - enemy.getX()) < 10 ) && 
					Math.abs(y - enemy.getY()) > 0 && Math.abs(y - enemy.getY()) < 50 ) {
				System.out.println("Bubble got the Enemy");
				if(enemy.getState() == 0) {
					attack();
					break;
				}
			}
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		up();
	}

	@Override
	public void up() {
		up = true;
		while(up) {
			y--;
			setLocation(x, y);
			
			if(backgroundBubbleService.topWall()) {
				up = false;
				break;
			}
			
			try {
				if(state==0) { // bubble that captures the enemy
					Thread.sleep(1);
				}else {  // just normal bubble
					Thread.sleep(10);
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(state == 0) clearBubble(); //if bubble didn't capture the enemy, the bubble get eliminated.
	}
	
	@Override
	public void attack() {
		state = 1;
		enemy.setState(1);
		setIcon(bubbled);
		mContext.remove(enemy); 
		mContext.repaint(); 
	}
	
	
	
	private void clearBubble() {
		try {
			Thread.sleep(3000);
			setIcon(bomb);
			Thread.sleep(500);
			mContext.remove(this); 
			mContext.repaint(); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void clearBubbled() {
		new Thread(()->{
			System.out.println("Bubble Cleared");
			try {
				up = false;
				setIcon(bomb);
				Thread.sleep(1000);
				mContext.remove(this);
				mContext.repaint();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();

	}
}
