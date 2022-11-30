package bubble.test.ex02;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel{
	
	private int x;
	private int y;
	
	private ImageIcon PlayerR, PlyaerL;
	
	public Player() {
		initObject();
		initSetting();
		
	}
	
	private void initObject() {
		PlayerR = new ImageIcon("image/PlayerR.png");
		PlyaerL = new ImageIcon("image/PlayerL.png");
	}
	
	private void initSetting() {
		x = 55;
		y = 535;
		
		setIcon(PlayerR);
		setSize(50,50);
		setLocation(x,y);
		
	}
	
}
