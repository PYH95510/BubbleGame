package bubble.game.service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import bubble.game.component.Enemy;


public class BackgroundEnemyService implements Runnable{

	private BufferedImage image;
	private Enemy enemy;
	private int JUMPCOUNT = 0; 
	private int FIRST = 0;   
	private int BOTTOMCOLOR = -131072; // floor is red
	

	public BackgroundEnemyService(Enemy enemy) {
		this.enemy = enemy;
		try {
			image = ImageIO.read(new File("image/backgroundMapService.png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	} 
	
	@Override
	public void run() {
		while(enemy.getState()==0) {
			

			Color leftColor = new Color(image.getRGB(enemy.getX() - 10, enemy.getY() + 25));
			Color rightColor = new Color(image.getRGB(enemy.getX() + 50 + 15, enemy.getY() + 25));

			int bottomColor = image.getRGB(enemy.getX() + 10, enemy.getY() + 50 + 5) // -1
					+ image.getRGB(enemy.getX()+50 - 10, enemy.getY() + 50 + 5); // -1
			

			if(bottomColor != -2) { 

				enemy.setDown(false);
			}else { 
				if(!enemy.isUp() && !enemy.isDown()) {

					enemy.down();
				}
			}
			

			if (bottomColor == BOTTOMCOLOR)	FIRST = 1;
			

			if(JUMPCOUNT >= 3) {
				JUMPCOUNT = 0;
				FIRST = 0;
			}
			

			if (JUMPCOUNT < 3 
					&& FIRST == 1 
					&& rightColor.getRed() == 255
					&& rightColor.getGreen() == 0 
					&& rightColor.getBlue() == 0) {
				enemy.setRight(false);
				enemy.setLeft(true);
				if(!enemy.isUp() && !enemy.isDown()) {
					JUMPCOUNT++;
					if(JUMPCOUNT == 3) enemy.left();
					enemy.up();
				}

			}else if(JUMPCOUNT < 3 
					&& FIRST == 1 
					&& leftColor.getRed() == 255
					&& leftColor.getGreen() == 0 
					&& leftColor.getBlue() == 0) {
				enemy.setLeft(false);
				enemy.setRight(true);
				if(!enemy.isUp() && !enemy.isDown()) {
					JUMPCOUNT++;
					if(JUMPCOUNT == 3) enemy.right();
					enemy.up();
				}
			}else if(leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
				enemy.setLeft(false);
				if(!enemy.isRight()) {
					enemy.right();	
				}
			}else if(rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
				enemy.setRight(false);
				if(!enemy.isLeft()) {
					enemy.left();	
				}
			}
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}