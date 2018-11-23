package spaceGame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.sql.SQLException;

import objetos.Jogo;

public class Player extends GameObject implements EntityA {
	
	private double velX = 0;
	private double velY = 0;
	
	private Game game;
	private Controller c;
	
	Animation anim;
	
	public Player(double x, double y, Textures tex, Game game, Controller c) {
		super(x, y);
		this.game = game;
		this.c = c;
		
		anim  = new Animation(3, tex.player[0], tex.player[1], tex.player[2]);
		
	}
	
	public void tick() {
		x+=velX;
		y+=velY;
		
		if (x >= (450 - 32))
			x = (450 -32);
		
		if (x <= 0) 
			x = 0;
		
		if (y >= 300 - 32)
			y = 300 - 32;
		
		if (y <= 0)
			y = 0;
		
		for (int i = 0; i < game.eb.size(); i++) {
			EntityB tempEnt = game.eb.get(i);
			
			if (Physics.Collision(this, tempEnt)) {
				c.removeEntity(tempEnt);
				Game.HEALTH -= 10;
				if (Game.HEALTH <= 0) {
					Game.State = Game.STATE.OVER;
					try {
						Jogo.salvarPontuacao(Game.SCORE);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				Game.SCORE++;
				game.setEnemy_killed(game.getEnemy_killed() + 1);
			}
		}
		
		anim.runAnimation();
	}
	
	public void render(Graphics g) {
		//g.drawImage(tex.player[0], (int) x, (int) y, null);
		
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

	public double getVelX() {
		return velX;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}
	
}
