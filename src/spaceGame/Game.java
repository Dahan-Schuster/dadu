package spaceGame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JFrame;

import objetos.BufferedImageLoader;
import telasMain.TelaInicio;
import telasMenu.MenuEntretenimento;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	/***********************/
	/****** ATRIBUTOS ******/
	/***********************/

	private static JFrame frame;

	public static final int WIDTH = 450; // largura
	public static final int HEIGHT = 300; // altura
	public static final int SCALE = 1; // escala
	public final String TITLE = "2D Space Game"; // título

	private boolean running = false; // indica se o jogo programa começou
	private Thread thread; // o thread do jogo

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); // um plano de fundo
																								// negro
	private BufferedImage spriteSheet = null; // a imagem que contém todos os sprites
	private BufferedImage background = null;

	private boolean is_shooting = false;

	private int enemy_count = 5;
	private int enemy_killed = 0;

	private Player p;
	private Controller c;
	private Textures t;

	private Menu menu;
	private Help help;
	private Over over;

	public static int HEALTH = 100;
	public static int SCORE = 0;

	public LinkedList<EntityA> ea;
	public LinkedList<EntityB> eb;

	public static enum STATE {
		MENU, GAME, HELP, OVER;
	};

	public static STATE State = STATE.MENU;

	/***********************/
	/******* MÉTODOS *******/
	/***********************/

	/**
	 * Carrega as imagens
	 */
	private void init() {
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader(); // o objeto que carrega imagens do sistema

		spriteSheet = loader.loadImage("/sprite-sheet.png"); // inicializa o atributo com a imagem que contém todos
																// os sprites

		background = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_3BYTE_BGR);

		addKeyListener(new KeyInput(this));

		t = new Textures(this);

		c = new Controller(t, this);
		p = new Player(WIDTH / 2, 300, t, this, c);

		menu = new Menu();
		help = new Help();
		over = new Over();

		ea = c.getEa();
		eb = c.getEb();

		c.createEnemy(enemy_count);

	}

	/**
	 * inicia o jogo
	 */
	private synchronized void start() {
		if (running)
			return;

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * encerra o jogo
	 * 
	 * @throws IOException
	 * @throws SQLException
	 */
	private synchronized void stop(int arg) throws IOException, SQLException {
		if (!running)
			return;

		running = false;

		frame.dispose();
		if (arg == 0) {
			new MenuEntretenimento().setVisible(true);
		} else if (arg == 1) {
			new TelaInicio().setVisible(true);
		}

	}

	@SuppressWarnings("unused")
	@Override
	public void run() {
		init();
		long lastTime = System.nanoTime(); // o tempo em que o jogo iniciou
		final double amountOfticks = 60.0; // quantidade de 'atualizações' da cena do jogo por segundo
		double ns = 1000000000 / amountOfticks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();

		while (running) {
			// loooooop :v
			// "the heart of the game"
			long now = System.nanoTime(); // o tempo do início de cada loop
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			try {
				render();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println(updates + " Ticks, Fps " + frames);
				updates = 0;
				frames = 0;

			}

		}
		try {
			stop(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void tick() {
		if (State == STATE.GAME) {
			p.tick();
			c.tick();
		}

		if (enemy_killed >= enemy_count) {
			enemy_count += 2;
			enemy_killed = 0;

			c.createEnemy(enemy_count);
		}
	}

	/**
	 * renderiza
	 * 
	 * @throws IOException
	 */
	private void render() throws IOException {

		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) { // só cria a BufferStrategy se ainda não tiver sido criada
							// evitando criar uma todo santo loop
							// milhões de vezes '-'
			createBufferStrategy(2);
			return;
		}

		if (frame.isVisible()) {
			Graphics g = bs.getDrawGraphics();

			/*********************************/

			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

			g.drawImage(background, 0, 0, null);

			if (State == STATE.GAME) {
				background = new BufferedImageLoader().loadImage("/background.png");
				g.drawImage(background, 0, 0, null);
				p.render(g);
				c.render(g);

				g.setColor(Color.GRAY);
				g.fillRect(5, 5, 100, 25);

				g.setColor(Color.GREEN);
				g.fillRect(5, 5, HEALTH, 25);

				g.setColor(Color.WHITE);
				g.drawRect(5, 5, 100, 25);

				g.setColor(Color.GRAY);
				g.fillRect(WIDTH * SCALE - 100, 5, 100, 25);

				g.setColor(Color.WHITE);
				g.drawString("SCORE: " + SCORE, WIDTH * SCALE - 95, 23);

			} else if (State == STATE.MENU) {
				menu.render(g);
			} else if (State == STATE.HELP) {
				help.render(g);
			} else {
				over.render(g);
			}

			/*********************************/

			g.dispose();
		}
		if (frame.isVisible()) {
			bs.show();
		}

	}

	public static void main(String[] args) {
		Game game = new Game();
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack(); // ajustar a dimensão do frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null); // gerar no centro da tela
		frame.setVisible(true);

		game.start();
	}

	public void keyPressed(KeyEvent e) throws IOException, SQLException {

		int k = e.getKeyCode();

		if (State == STATE.GAME) {

			if (k == KeyEvent.VK_RIGHT) {

				p.setVelX(5);

			} else if (k == KeyEvent.VK_LEFT) {

				p.setVelX(-5);
			} else if (k == KeyEvent.VK_DOWN) {

				p.setVelY(5);

			} else if (k == KeyEvent.VK_UP) {

				p.setVelY(-5);

			} else if (k == KeyEvent.VK_SPACE && !is_shooting) {
				is_shooting = true;
				c.addEntity(new Bullet(p.getX(), p.getY(), t, this));

			} else if (k == KeyEvent.VK_ESCAPE) {
				State = STATE.MENU;

			} else if (k == KeyEvent.VK_BACK_SPACE) {
				stop(1);
			}

		} else if (State == STATE.MENU) {

			if (k == KeyEvent.VK_F1) {

				State = STATE.GAME;

			} else if (k == KeyEvent.VK_SPACE) {

				State = STATE.HELP;

			} else if (k == KeyEvent.VK_F2) {
				stop(0);
			} else if (k == KeyEvent.VK_BACK_SPACE) {
				stop(1);
			}

		} else if (State == STATE.HELP) {

			if (k == KeyEvent.VK_F2) {
				State = STATE.MENU;

			} else if (k == KeyEvent.VK_BACK_SPACE) {
				stop(1);
			}

		} else if (State == STATE.OVER) {
			if (k == KeyEvent.VK_F1) {

				State = STATE.GAME;

			} else if (k == KeyEvent.VK_SPACE) {

				State = STATE.MENU;

			} else if (k == KeyEvent.VK_F2) {

				stop(0);

			} else if (k == KeyEvent.VK_BACK_SPACE) {
				stop(1);
			}
		}

	}

	public void keyReleased(KeyEvent e) {

		int k = e.getKeyCode();

		if (k == KeyEvent.VK_RIGHT) {
			p.setVelX(0);
		} else if (k == KeyEvent.VK_LEFT) {
			p.setVelX(0);
		} else if (k == KeyEvent.VK_DOWN) {
			p.setVelY(0);
		} else if (k == KeyEvent.VK_UP) {
			p.setVelY(0);
		} else if (k == KeyEvent.VK_SPACE) {
			is_shooting = false;
		}
	}

	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

	public int getEnemy_count() {
		return enemy_count;
	}

	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}

	public int getEnemy_killed() {
		return enemy_killed;
	}

	public void setEnemy_killed(int enemy_killed) {
		this.enemy_killed = enemy_killed;
	}

}
