package Arkanoid;
import javax.swing.*;
public class Main {
	public static JFrame frame;
	public static Game game;
	
	public static void main(String [] args) {
		
		frame = new JFrame("Arkanoid");
		
		frame.setSize(900, 700);
		frame.setLocationRelativeTo(null);
		game = new Game(frame, 10, 3);
		
		game.setSize(frame.getSize());
		frame.add(game);
		
		frame.setVisible(true);
		
	
	}

}
