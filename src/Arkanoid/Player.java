package Arkanoid;
import java.awt.*;
public class Player {
	public static int standartPlayerWidth = 80;
	public static int standartPlayerHeight = 20;
	private Rectangle hitBox = new Rectangle(0,0,100,30);
	private Game instance;
	public Player(Game inst, int x, int y, int width, int height) {
		instance = inst;

		hitBox = new Rectangle(x, y , width, height);
	}
	
	public void setY(int y) {
		hitBox.y = y;
		
	}
	
	public void moveOnXAxis(int speed) {
		
		hitBox.x += speed;
		/*
		if (hitBox.x < 0) hitBox.x = 0;
		if (hitBox.x > instance.getGameDimension().width - instance.getPlayer().hitBox.width) hitBox.x = instance.getGameDimension().width - instance.getPlayer().hitBox.width ;
		*/
		hitBox.x = ((hitBox.x < 0)) ? 0:((hitBox.x > instance.getGameDimension().width - instance.getPlayer().hitBox.width)?instance.getGameDimension().width - instance.getPlayer().hitBox.width:hitBox.x+speed));
	}


	public void render(Graphics g) {
		g.setColor(new Color (200, 200, 200));
		g.fillRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
	}
	
	

}
