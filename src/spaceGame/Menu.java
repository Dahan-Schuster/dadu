package spaceGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
	public Rectangle playButton = new Rectangle(40, 150, 100, 50);
	public Rectangle helpButton = new Rectangle(170, 150, 100, 50);
	public Rectangle quitButton = new Rectangle(300, 150, 100, 50);
	
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g.setFont(new Font ("Arial", Font.BOLD, 50));
		g.setColor(new Color(200, 50, 255));
		g.drawString("SPACE GAME", 40,  100);
		
		Font bFont = new Font("Arial", Font.BOLD, 30);
		g.setFont(bFont);
		g.drawString("PLAY",45, 180);
		g2d.draw(playButton);
		
		g.drawString("HELP", 175, 180);
		g2d.draw(helpButton);
		
		g.drawString("QUIT",305, 180);
		g2d.draw(quitButton);
	}
}
