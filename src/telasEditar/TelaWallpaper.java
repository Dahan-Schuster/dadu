package telasEditar;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import com.towel.swing.img.JImagePanel;

import objetos.BufferedImageLoader;
import objetos.Tema;
import telasMenu.MenuConfiguracoes;

public class TelaWallpaper extends JFrame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JImagePanel contentPane;
	private static JLabel salvar, voltar, esq, dir;

	private static ArrayList<String> wp = new ArrayList<>();
	private static int i = 0;

	private static TelaWallpaper frame;

	public static void main(String[] args) throws IOException {
		preencherArrayList();

		frame = new TelaWallpaper();
		frame.setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int kc = e.getKeyCode();
		if (kc == KeyEvent.VK_RIGHT) {

			if (i == 4) {
				i = 0;
			} else {
				i++;
			}
			try {
				mudarWallpaper();
			} catch (IOException io) {
				io.printStackTrace();
			}
		} else if (kc == KeyEvent.VK_LEFT) {
			if (i == 0) {
				i = 4;
			} else {
				i--;
			}

			try {
				mudarWallpaper();
			} catch (IOException io) {
				io.printStackTrace();
			}
		} else if (kc == KeyEvent.VK_F1) {
			try {
				salvar();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else if (kc == KeyEvent.VK_F2) {
			dispose();
			MenuConfiguracoes.main(null);
		}

	}

	private void mudarWallpaper() throws IOException {
		contentPane.setImage(loadImage());
		frame.setContentPane(contentPane);
	}

	public TelaWallpaper() throws IOException {

		requestFocus();
		addKeyListener(this);

		setSize(450, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Wallpaper");
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

		salvar = new JLabel("Salvar");
		salvar.setBounds(10, 240, 60, 20);
		salvar.setBackground(Color.white);
		salvar.setOpaque(true);
		salvar.setForeground(Color.BLACK);
		salvar.setHorizontalAlignment(SwingConstants.CENTER);
		salvar.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPane.add(salvar);

		voltar = new JLabel("Voltar");
		voltar.setBounds(380, 240, 60, 20);
		voltar.setBackground(Color.white);
		voltar.setOpaque(true);
		voltar.setForeground(Color.BLACK);
		voltar.setHorizontalAlignment(SwingConstants.CENTER);
		voltar.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPane.add(voltar);
	}

	private static void preencherArrayList() throws IOException {
		String path;
		for (int i = 0; i < 5; i++) {
			path = String.format("/wallpaper%d.jpg", (i + 1));
			wp.add(path);
		}
	}

	private BufferedImage loadImage() {
		BufferedImage image;

		image = new BufferedImageLoader().loadImage(wp.get(i));

		return image;
	}

	private static void salvar() throws MySQLSyntaxErrorException, MySQLNonTransientConnectionException,
			CommunicationsException, SQLException {
		if (i == 0) {
			Tema.salvarWallpaper("A");
		} else if (i == 1) {
			Tema.salvarWallpaper("B");
		} else if (i == 2) {
			Tema.salvarWallpaper("C");
		} else if (i == 3) {
			Tema.salvarWallpaper("D");
		} else if (i == 4) {
			Tema.salvarWallpaper("E");
		}

		JOptionPane.showMessageDialog(contentPane, "Salvo!");
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
