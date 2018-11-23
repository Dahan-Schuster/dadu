package spaceGame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject implements EntityA{
	
	
	Animation anim;
	
	BufferedImage image;
	
	public Bullet(double x, double y, Textures tex, Game game) {
		super (x, y);

		anim = new Animation(3, tex.missile[0], tex.missile[1], tex.missile[2]);
		
	}
	
	public void tick() {
		y -= 5;
		
		anim.runAnimation();
	}
	
	public void render(Graphics g) {
		//g.drawImage(tex.missile[0], (int) x, (int) y, null);
		anim.drawAnimation(g, x, y, 0);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	
}
