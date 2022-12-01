package bubble.test.ex14;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bubble extends JLabel implements Movable{
	
	

	private BubbleFrame mContext;
	private Player player;
	private BackgroundBubbleService backgroundBubbleService;

	private int x;
	private int y;


	private boolean left;
	private boolean right;
	private boolean up;
	

	private int state; // 0(just bubble), 1(bubble captures enemy)
	
	
	private ImageIcon bubble; 
	private ImageIcon bubbled;//bubble that captures enemy
	private ImageIcon bomb;//bombed bubble

	public Bubble(BubbleFrame mContext) { // sort of dependency injection. Bubble always made from player. Therefore, we need to do DI.
		this.mContext = mContext;
		this.player = mContext.getPlayer();
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
		for(int i = 0 ; i<400 ; i++) {
			x--;
			setLocation(x,y);
			
			if(backgroundBubbleService.leftWall()) {
				left = false;
				break;
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
		for(int i = 0 ; i<400 ; i++) {
			x++;
			setLocation(x,y);
			
			if(backgroundBubbleService.rightWall()) {
				right = false;
				break;
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
			setLocation(x,y);
			if(backgroundBubbleService.topWall()) {
				up = false;
				break;
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
		clearBubble();
	}
	
	private void clearBubble() {
		try {
			Thread.sleep(3000);
			setIcon(bomb);
			Thread.sleep(5000);
			mContext.remove(this); // this here means bubble, and mContext is bubbleFrame, so bubble will be eliminated from mContext.
			mContext.repaint(); //repaint with things that are in the memory.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
