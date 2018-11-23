package telasMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import com.towel.swing.img.JImagePanel;

import objetos.BufferedImageLoader;
import objetos.Perfil;
import objetos.dataHora;
import telasEditar.TelaConfigurarPerfil;
import telasFinais.TelaCalculadora;
import telasFinais.TelaCronometro;
import telasFinais.TelaTocarAlarme;
import telasMenu.TelaMenu;

public class TelaInicio extends JFrame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JImagePanel contentPane;
	private static String horaAtual;
	private static JLabel lblhora;
	private static String dataAtual;
	private static JLabel lblData;
	private static Date date = new Date(System.currentTimeMillis());
	private static JLabel lblMenu;
	private static JLabel lblAtalhos;
	private static JLabel lblContatos;

	private static boolean h24;
	private static String formato;

	ArrayList<String[]> alarmes = Perfil.getAlarmes();
	private static int cont = 0;
	
	private static Runnable runUp = new Runnable() {
		@Override
		public void run() {
			try {
				TelaNotas.main(null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	};
	
	private static Runnable runDown = new Runnable() {

		@Override
		public void run() {
			try {
				TelaCalculadora.main(null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	
	private static Runnable runRight = new Runnable() {

		@Override
		public void run() {
			TelaCronometro.main(null);
		}
	};
	
	private static Runnable runLeft = new Runnable() {

		@Override
		public void run() {
			TelaConfigurarPerfil.main(null);
		}
	};

	public static void main(String[] args) {
		try {
			TelaInicio frame = new TelaInicio();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void alterarTema() {
		if (Perfil.getTema().equals("claro")) {
			lblhora.setForeground(Color.BLACK);
			lblData.setForeground(Color.BLACK);
			lblMenu.setForeground(Color.BLACK);
			lblAtalhos.setForeground(Color.BLACK);
			lblContatos.setForeground(Color.BLACK);

		} else if (Perfil.getTema().equals("escuro")) {
			lblhora.setForeground(Color.WHITE);
			lblData.setForeground(Color.WHITE);
			lblMenu.setForeground(Color.WHITE);
			lblAtalhos.setForeground(Color.WHITE);
			lblContatos.setForeground(Color.WHITE);
		}
	}

	// EVENTOS

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case (KeyEvent.VK_F1):
			dispose();
			TelaAtalhos.main(null);
			break;
		case (KeyEvent.VK_F2):
			dispose();
			TelaContatos.main(null);
			break;
		case (KeyEvent.VK_UP):
			dispose();
			runUp.run();
			break;
		case (KeyEvent.VK_DOWN):
			dispose();
			runDown.run();
			break;
		case (KeyEvent.VK_RIGHT):
			dispose();
			runRight.run();
			break;
		case (KeyEvent.VK_LEFT):
			dispose();
			runLeft.run();
			break;
		case (KeyEvent.VK_SPACE):
			try {
				dispose();
				new TelaMenu().setVisible(true);
			} catch (IOException io) {
				io.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;

		}
	}

	@SuppressWarnings("deprecation")
	public TelaInicio() throws IOException, SQLException {
		
		formato = dataHora.formatoData();
		h24 = dataHora.formatoHora();

		addKeyListener(this);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setTitle("Inicio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		try {
			contentPane = new JImagePanel(loadImage(Perfil.getWallpaper()));
		} catch (IOException io) {
			contentPane = new JImagePanel(new BufferedImage(450, 300, BufferedImage.TYPE_3BYTE_BGR));
		}
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// CONSTRUÇÃO E DEFINIÇÃO DE COMPONENTES

		// Hora

		lblhora = new JLabel(horaAtual);
		lblhora.setBorder(new EtchedBorder());
		lblhora.setBounds(125, 100, 215, 50);
		lblhora.setHorizontalAlignment(SwingConstants.CENTER);
		lblhora.setFont(new Font("Dialog", Font.BOLD, 40));
		lblhora.setForeground(Color.WHITE);
		contentPane.add(lblhora);
		atualizarHora();

		// Data

		SimpleDateFormat sdf = new SimpleDateFormat(setFormato());

		dataAtual = getDia((byte) date.getDay()) + " " + sdf.format(date);
		lblData = new JLabel(dataAtual);
		lblData.setBounds(125, 80, 215, 20);
		lblData.setBorder(new EtchedBorder());
		lblData.setFont(new Font("Dialog", Font.BOLD, 14));
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		lblData.setForeground(Color.WHITE);
		contentPane.add(lblData);

		// Labels de ações
		lblMenu = new JLabel("Menu"); // Alterar para ícone
		lblMenu.setBounds(200, 250, 50, 20);
		lblMenu.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenu.setForeground(Color.WHITE);
		contentPane.add(lblMenu);

		lblAtalhos = new JLabel("Atalhos");
		lblAtalhos.setBounds(10, 250, 60, 20);
		lblAtalhos.setHorizontalAlignment(SwingConstants.LEFT);
		lblAtalhos.setForeground(Color.WHITE);
		lblAtalhos.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPane.add(lblAtalhos);

		lblContatos = new JLabel("Contatos");
		lblContatos.setBounds(360, 250, 80, 20);
		lblContatos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContatos.setForeground(Color.WHITE);
		lblContatos.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPane.add(lblContatos);

		alterarTema();
	}

	// MÉTODOS PRINCIPAIS

	SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");

	private void atualizarHora() {

		// Cria um objeto para agendar um comando para ser feito entre intervalos de
		// tempo até ser cancelado
		ScheduledExecutorService attHora = Executors.newScheduledThreadPool(1);
		attHora.scheduleAtFixedRate(new Runnable() {
			public void run() {
				horaAtual = s.format(new Date(System.currentTimeMillis())); // atualiza a hora
				
				if (cont == 0)
					verificarAlarme(horaAtual.substring(0, 5));
				else
					cont = 60 - Integer.parseInt(horaAtual.substring(6));
				
				if (!h24) {
					if (Integer.parseInt(horaAtual.substring(0, 2)) > 12) {
						horaAtual = String.format("%02d%s", (Integer.parseInt(horaAtual.substring(0, 2)) - 12),
								horaAtual.substring(2));
					}
				}
				lblhora.setText(horaAtual); // atualiza o JLabel
			}
		}, 0, 1, TimeUnit.SECONDS); // começa logo após abrir a tela e repete a cada 1 segundo
	}

	@SuppressWarnings("deprecation")
	private void verificarAlarme(String hora) {
		for (String[] a : alarmes) {
			if (a[1].equals(hora)) {
				if (!a[2].equals("A")) {
					if (a[3].charAt(date.getDay()) == 'S' || a[2].equals("B")) {
						cont = 1;
						if (!TelaTocarAlarme.isVisible)
							new TelaTocarAlarme(a).setVisible(true);
						break;

					}
				}
			}
		}
	}
	
	private String getDia(byte dia) {
		switch (dia) {
		case 0:
			return "DOM";
		case 1:
			return "SEG";
		case 2:
			return "TER";
		case 3:
			return "QUA";
		case 4:
			return "QUI";
		case 5:
			return "SEX";
		case 6:
			return "SAB";
		default:
			return "null";
		}
	}

	// Define o formato da data
	private String setFormato() {
		String f;
		if (formato.equals("A"))
			f = "dd/MM/yyyy";
		else if (formato.equals("B"))
			f = "MM/dd/yyyy";
		else
			f = "yyyy/MM/dd";

		return f;
	}

	// Construtor do JImagePanel
	private BufferedImage loadImage(String wp) throws IOException {

		BufferedImage image;

		if (wp.equals("A")) {
			image = new BufferedImageLoader().loadImage("/wallpaper1.jpg");
		} else if (wp.equals("B")) {
			image = new BufferedImageLoader().loadImage("/wallpaper2.jpg");
		} else if (wp.equals("C")) {
			image = new BufferedImageLoader().loadImage("/wallpaper3.jpg");
		} else if (wp.equals("D")) {
			image = new BufferedImageLoader().loadImage("/wallpaper4.jpg");
		} else if (wp.equals("E")) {
			image = new BufferedImageLoader().loadImage("/wallpaper5.jpg");
		} else
			image = null; // Null

		return image;
	}
	
	public static void setRunUp(Runnable run){
		runUp = run;
	}
	public static void setRunDown(Runnable run){
		runDown = run;
	}
	public static void setRunLeft(Runnable run){
		runLeft = run;
	}
	public static void setRunRight(Runnable run){
		runRight = run;
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
