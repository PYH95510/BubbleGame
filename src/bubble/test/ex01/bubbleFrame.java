package bubble.test.ex01;

import javax.swing.JFrame;
import java.awt.GridLayout;

//window frame
//window frame has panel inside
public class bubbleFrame extends JFrame {

	public bubbleFrame() {
	setSize(1000,640);
	getContentPane().setLayout(null);
	setVisible(true);
}
	public static void main(String[] args) {
		new bubbleFrame();

	}

}
