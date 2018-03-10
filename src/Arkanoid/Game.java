package Arkanoid;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel {
	private Dimension gameField = new Dimension(400, 300);
	private boolean isRunning = false;
	private boolean isPaused = false;
	private boolean won = false;
	private boolean lost = false;
	
	
	private int ballCount;
	private Platform[][] platforms;
	private Player player;
	private Ball ball;
	
	
	
	
	public Game(Frame container, int platformsOnX, int platformsOnY) {
				container.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if(won || lost) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) System.exit(0);
					} else if (!isRunning || isPaused  ) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) start();
					} else {
					if (e.getKeyCode() == KeyEvent.VK_RIGHT) player.moveOnXAxis(30);
					if (e.getKeyCode() == KeyEvent.VK_LEFT) player.moveOnXAxis(-30);
					}
				}
			
		}
				);
		platforms = new Platform[platformsOnX][platformsOnY];
		for (int x = 0; x != platforms.length; x++) {
			for (int y = 0; y != platforms[0].length; y++) {
				int pWidth = gameField.width/platformsOnX;
				int pHeight = (gameField.height/4)/platformsOnY;
				platforms[x][y] = new Platform(x*pWidth, y*pHeight, pWidth, pHeight);
			}
		}
		player = new Player(this, (int) ((gameField.getWidth() - Player.standartPlayerWidth)/2), (int) (gameField.getHeight()-Player.standartPlayerHeight), Player.standartPlayerWidth, Player.standartPlayerHeight );
		ball = new Ball(this, gameField.width/2, gameField.height/2, Ball.standartBallRadius);
		ballCount = 3;
		repaint();
	}
	
	public void loseBall(){
		
		pause();
		ballCount -=1;
		if (ballCount <= 0) lost = true;
		ball.setVector(10, 10);
		ball.setPosition(gameField.width/2, gameField.height/2);
		player.setX((int) ((gameField.getWidth()-Player.standartPlayerWidth)/2));
		player.setY(gameField.height - Player.standartPlayerHeight);
		repaint();
	}
	public void playerWon() {
		won = true;
	}
	
	public void start() {
		isPaused = false;
		if (!isRunning) gameThread.start();
	}
	public void pause() {
		isPaused = true;
	
		
	}
	
	public void stop() {
		isRunning = false;
	}
	
	
	public Dimension getGameDimension() {
		return gameField;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public Platform[][] getPlatforms() { 
		return this.platforms;
	}
	
	public void setSize(Dimension size ) {
		super.setSize(size);
	 if (!isRunning) {
			gameField = new Dimension(size.width-200, size.height-200);
			for (int x = 0; x != platforms.length; x++) {
				for (int y = 0; y != platforms[0].length; y++) {
					int pWidth = gameField.width/platforms.length;
					int pHeight = (gameField.height)/4/platforms[0].length;
					platforms[x][y] = new Platform(x*pWidth, y*pHeight, pWidth, pHeight);
				}
			}
			ball.setPosition(gameField.width/2, gameField.height/2);
			player.setX((int) ((gameField.getWidth() - Player.standartPlayerWidth)/2));
			player.setY((int) (gameField.getHeight()-Player.standartPlayerHeight));
	 }
	}
	
	private	Thread gameThread = new Thread(new Runnable() {
		public void run()
		{
			isRunning = true;
			ball.setVector(10, 10);
			while (isRunning) {	
				if (!isPaused) {
				ball.tick();
				
				
				repaint();
				try {
				Thread.sleep(50);
				} catch (Exception e) {}
				}
		}		
	}
	});

	
		
	
	public void paint(Graphics g) {
		super.paint(g);
		
		g.translate((getWidth()-gameField.width)/2, (getHeight()-gameField.height)/2);
		
		g.setColor(new Color(0, 0, 0));
		int radius =4;
		for (int i = 0; i != ballCount; i++) {
			g.fillOval(i*radius*2, (-radius*2+3), radius*2, radius*2);
			
		}

		g.setColor(new Color(255,255,255,100));
		g.fillRect(0, 0, gameField.width, gameField.height);
		
		ball.render(g);
		player.render(g);
		
		for (Platform[] pls : platforms) {
			for (Platform p : pls) {
				p.render(g);
			}
		}

		g.setColor(new Color(0,0,0));
		g.drawRect(0, 0, gameField.width, gameField.height);
		
		
			
		}
		
	}
	
