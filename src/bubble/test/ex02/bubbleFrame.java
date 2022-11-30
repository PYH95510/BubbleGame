package bubble.test.ex02;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class bubbleFrame extends JFrame{
	
		private JLabel backgroundMap;
		private Player player;
	
		public bubbleFrame() {
			initObject();
			initSetting();
			setVisible(true);
		}
	
		private void initObject() {
			backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
			setContentPane(backgroundMap);
			
			player = new Player();
			add(player);
//			backgroundMap.setSize(100,100);
//			backgroundMap.setLocation(300,300);
//			backgroundMap.setSize(1000,600);
//			add(backgroundMap); //JLabel will be added to JFrame
			
		}
		
		private void initSetting() {
			setSize(1000,640);
			setLayout(null); //absolute, we could freely draw
			setLocationRelativeTo(null); //locate to middle
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //terminate JVM
		}
	
	
	public static void main (String[]args) {
		new bubbleFrame();
	}
}
