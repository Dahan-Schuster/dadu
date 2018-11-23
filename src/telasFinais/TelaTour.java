package telasFinais;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.towel.swing.img.JImagePanel;

import objetos.BufferedImageLoader;
import telasMain.TelaInicio;

public class TelaTour extends JFrame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JImagePanel contentPane;
	private static JLabel esq, dir;

	private static ArrayList<String> wp = new ArrayList<>();
	private static String[] title = {"Início", "Menu principal", "Menu de Ferramentas", "Menu de Comunicação", "Lista de Contatos", "Menu Configurações"};
	private static int i = 0;
	private static int t = 0;

	private static TelaTour frame;

	public static void main(String[] args) throws IOException {

		frame = new TelaTour();
		frame.setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int kc = e.getKeyCode();
		if (kc == KeyEvent.VK_RIGHT) {

			try {
				
				mudarWallpaper();

			} catch (IOException io) {
				io.printStackTrace();
			}
		} else if (kc == KeyEvent.VK_LEFT) {
			if (i == 0) {
				// doNothing();
			} else {
				i--;
				try {
					mudarWallpaper();
				} catch (IOException io) {
					io.printStackTrace();
				}
			}

		}

	}

	private void mudarWallpaper() throws IOException {
		if (i == 5) 
			finalizarTour();
		 else 
			i++;

		contentPane.setImage(loadImage());
		frame.setContentPane(contentPane);
		t = 0;
	}

	public TelaTour() throws IOException {
		
		preencherArrayList();
		
		requestFocus();
		addKeyListener(this);

		setSize(450, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Tour");
		setResizable(false);
		contentPane = new JImagePanel(loadImage());
		contentPane.setLayout(null);
		contentPane.setIgnoreRepaint(false);
		setContentPane(contentPane);

		esq = new JLabel("<<");
		esq.setBounds(10, 130, 40, 20);
		esq.setBackground(Color.white);
		esq.setOpaque(true);
		esq.setForeground(Color.BLACK);
		esq.setHorizontalAlignment(SwingConstants.CENTER);
		esq.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPane.add(esq);

		dir = new JLabel(">>");
		dir.setBounds(400, 130, 40, 20);
		dir.setBackground(Color.white);
		dir.setOpaque(true);
		dir.setForeground(Color.BLACK);
		dir.setHorizontalAlignment(SwingConstants.CENTER);
		dir.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPane.add(dir);
		
		esperar();

	}

	private static void preencherArrayList() throws IOException {
		String path;
		for (int j = 1; j < 7; j++) {
			path = String.format("/tour%d.png", j);
			System.out.println(path);
			wp.add(path);
		}
	}

	private BufferedImage loadImage() {
		BufferedImage image;

		image = new BufferedImageLoader().loadImage(wp.get(i));
		setTitle("Tour - "+title[i]);

		return image;
	}

	private void finalizarTour() throws IOException {
		String[] opcoes = { "Sim", "Não" };
		if (JOptionPane.showOptionDialog(contentPane, "Realizar o tour novamente?", "Sair", JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[1]) == 0) {
			i = 0;
			mudarWallpaper();
		} else {
			dispose();
			pararContagem();
			TelaInicio.main(null);
		}
	}

	ScheduledExecutorService contar;

	private void esperar() {
		contar = Executors.newScheduledThreadPool(1);

		Runnable tempo = new Runnable() {

			@Override
			public void run() {
				if (t >= 5) {
					try {
						i++;
						System.out.println("5 seg");
						mudarWallpaper();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					t++;
					System.out.println(t+" seg");
				}
			}
		};
		
		contar.scheduleAtFixedRate(tempo, 0, 1, TimeUnit.SECONDS);
	}
	
	private void pararContagem() {
		contar.schedule(new Runnable() {

			@Override
			public void run() {
				contar.shutdown();
			}
		}, 0, TimeUnit.SECONDS);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
