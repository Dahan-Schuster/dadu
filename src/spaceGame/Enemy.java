package spaceGame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy extends GameObject implements EntityB{
	private Random r = new Random();
	
	
	private Game game;
	private Controller c;
	Animation anim;
	
	private int speed = r.nextInt(3) + 1;
	
	public Enemy(double x, double y, Textures tex, Game game, Controller c) {
		super(x, y);
		this.game = game;
		this.c = c;
		
		anim = new Animation(3, tex.enemy[0], tex.enemy[1], tex.enemy[2]);
	}
	
	public void tick() {
		y += speed;
		
		if (y > Game.HEIGHT) {
			speed = r.nextInt(3) + 1;
			y = -10;
			x = r.nextInt(400);
		}
		
		for (int i = 0; i < game.ea.size(); i++) {
			EntityA tempEnt = game.ea.get(i);
			if (Physics.Collision(this, tempEnt)) {
				//System.out.println("Tá colidindo :p");
				
				c.removeEntity(tempEnt);
				c.removeEntity(this);
				Game.SCORE++;
				game.setEnemy_killed(game.getEnemy_killed() + 1);
			}
		}
		
		
		 
		anim.runAnimation();
	}
	
	public void render(Graphics g) {
		//g.drawImage(tex.enemy[0], (int) x, (int) y, null);
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
