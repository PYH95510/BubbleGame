package bubble.test.ex18;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

public class BackgroundPlayerService implements Runnable{

	private BufferedImage image;
	private Player player;
	private List<Bubble> bubbleList;
	
	public BackgroundPlayerService(Player player) {
		this.player = player;
		this.bubbleList = player.getBubbleList();
		try {
			image = ImageIO.read(new File("image/backgroundMapService.png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void run() {
		while(true) {
			
			//bubble collision check
			for(int i=0; i< bubbleList.size(); i++) {
				Bubble bubble = bubbleList.get(i);
				if(bubble.getState() == 1) {
					if((Math.abs(player.getX() - bubble.getX()) < 10 ) && 
							Math.abs(player.getY() - bubble.getY()) > 0 && Math.abs(player.getY() - bubble.getY()) < 50 ) {
						System.out.println("Enemy Down");
						bubble.clearBubbled();
						break;
					}
				}
			}
			


			Color leftColor = new Color(image.getRGB(player.getX() - 10, player.getY() + 25));
			Color rightColor = new Color(image.getRGB(player.getX() + 50 + 15, player.getY() + 25));

			int bottomColor = image.getRGB(player.getX() + 10, player.getY() + 50 + 5) 
					+ image.getRGB(player.getX()+50 - 10, player.getY() + 50 + 5);
			

			if(bottomColor != -2) {
	
				player.setDown(false);
			}else { 
				if(!player.isUp() && !player.isDown()) {

					player.down();
				}
			}
			

			if(leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {

				player.setLeftWallCrash(true);
				player.setLeft(false);
			}else if(rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {

				player.setRightWallCrash(true);
				player.setRight(false);
			}else {
				player.setLeftWallCrash(false);
				player.setRightWallCrash(false);
			}
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
