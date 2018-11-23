package spaceGame;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public class Controller {

	private LinkedList<EntityA> ea = new LinkedList<>();
	private LinkedList<EntityB> eb = new LinkedList<>();

	private EntityA entA;
	private EntityB entB;
	private Textures tex;
	
	private Game game;

	private Random r = new Random();

	public Controller(Textures tex, Game game) {
		this.tex = tex;
		this.game = game;

		// addEntity(new Enemy(100, 0, tex));

	}

	public void createEnemy(int e_count) {
		for (int i = 0; i < e_count; i++) {
			addEntity(new Enemy(r.nextInt(400), -10, tex, game, this));
		}
	}

	public void tick() {
		// A
		for (int i = 0; i < ea.size(); i++) {
			entA = ea.get(i);

			entA.tick();
		}

		// B
		for (int i = 0; i < eb.size(); i++) {
			entB = eb.get(i);

			entB.tick();
		}
	}

	public void render(Graphics g) {
		// A
		for (int i = 0; i < ea.size(); i++) {
			entA = ea.get(i);

			entA.render(g);
		}
		
		// B
		for (int i = 0; i < eb.size(); i++) {
			entB = eb.get(i);

			entB.render(g);
		}

	}

	// A
	public void addEntity(EntityA block) {
		ea.add(block);
	}

	public void removeEntity(EntityA block) {
		ea.remove(block);
	}
	
	// B
	public void addEntity(EntityB block) {
		eb.add(block);
	}

	public void removeEntity(EntityB block) {
		eb.remove(block);
	}

	public LinkedList<EntityA> getEa() {
		return ea;
	}

	public LinkedList<EntityB> getEb() {
		return eb;
	}
	
	
}
