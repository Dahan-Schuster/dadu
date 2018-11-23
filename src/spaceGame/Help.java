package spaceGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Help {
	
	private Rectangle backButton = new Rectangle( 380, 250, 60, 30);
	
	public void render(Graphics g) {
		
		g.setColor(new Color(200, 50, 255));
		
		g.setFont(new Font("arial", Font.PLAIN, 18));
		
		g.drawString("Bem vindo ao 2D Space Game.", 90, 100);
		g.drawString("Use as setas do teclado para mover a nave;", 35, 130);
		g.drawString("Espaço para atirar;", 150, 155);
		g.drawString("ESC para voltar à tela de menu", 85, 180);
		g.drawString("Divirta-se!", 180, 230);
		
		Graphics2D g2d = (Graphics2D) g;
		
		// Back button
		g2d.draw(backButton);
		g.setFont(new Font("arial", Font.BOLD, 16));
		g.drawString("Voltar", 385, 270);
		
		
		
	}
}

