package telasMenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.towel.swing.img.JImagePanel;

import objetos.BufferedImageLoader;
import objetos.Perfil;
import objetos.dataHora;
import telasMain.TelaInicio;

public class TelaMenu extends JFrame {

	private static final long serialVersionUID = -2415568696417108109L;
	private static JImagePanel contentPane;
	private static JButton bEntretenimento;
	private static JButton bFerramentas;
	private static JButton bContatos;
	private static JButton bConfiguracoes;

	private static ImageIcon entret; // icone do botão entretenimento
	private static ImageIcon ferra; // icone do botão ferramentas
	private static ImageIcon conta; // icone do botão contatos
	private static ImageIcon config; // icone do botão configurações

	private static String horaAtual;
	private static JLabel lblhora;
	private static String dataAtual;
	private static JLabel lblData;
	private static Date date = new Date(System.currentTimeMillis());
	
	private static boolean h24;

	public static void main(String[] args) {
		try {
			TelaMenu frame = new TelaMenu();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private void alterarTema() {
		if (Perfil.getTema().equals("claro")) {
			bEntretenimento.setForeground(Color.BLACK);
			bFerramentas.setForeground(Color.BLACK);
			bContatos.setForeground(Color.BLACK);
			bConfiguracoes.setForeground(Color.BLACK);

		} else if(Perfil.getTema().equals("escuro")) {
			bEntretenimento.setForeground(Color.WHITE);
			bFerramentas.setForeground(Color.WHITE);
			bContatos.setForeground(Color.WHITE);
			bConfiguracoes.setForeground(Color.WHITE);
		}
	}

	@SuppressWarnings("deprecation")
	public TelaMenu() throws IOException, SQLException {
		h24 = dataHora.formatoHora();
		
		
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setTitle("Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		try {
			contentPane = new JImagePanel(loadImage(Perfil.getWallpaper()));
		} catch (IOException io) {
			contentPane = new JImagePanel(new BufferedImage(450, 300, BufferedImage.TYPE_3BYTE_BGR));
		}
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setOpaque(false);
		setContentPane(contentPane);

		// CONSTRUÇÃO E DEFINIÇÃO DE COMPONENTES

		// Botão entretenimento

		bEntretenimento = new JButton("Entretenimento");

		bEntretenimento.setVerticalAlignment(SwingConstants.BOTTOM); // Define o alinhamento vertical de tudo no botão
		bEntretenimento.setVerticalTextPosition(SwingConstants.BOTTOM); // Define o alinhamento vertical do texto
		bEntretenimento.setHorizontalTextPosition(SwingConstants.CENTER); // Define o alinhamento horizontal do texto
		bEntretenimento.setForeground(Color.WHITE); // Define a cor do texto

		bEntretenimento.setBorder(null); // Remove a borda
		bEntretenimento.setContentAreaFilled(false); // Remove o plano de fundo

		bEntretenimento.setBounds(5, 20, 220, 120);
		bEntretenimento.requestFocus(); // Define como dono padrão do foco
		contentPane.add(bEntretenimento);

		// Fim botão entretenimento

		// Botão ferramentas

		ferra =  new ImageIcon(new BufferedImageLoader().loadImage("/ferramentas.png"));
		bFerramentas = new JButton("Ferramentas");
		bFerramentas.setIcon(ferra);

		bFerramentas.setVerticalAlignment(SwingConstants.BOTTOM);
		bFerramentas.setVerticalTextPosition(SwingConstants.BOTTOM);
		bFerramentas.setHorizontalTextPosition(SwingConstants.CENTER);
		bFerramentas.setForeground(Color.WHITE);

		bFerramentas.setBorder(null);
		bFerramentas.setContentAreaFilled(false);

		bFerramentas.setBounds(225, 20, 220, 120);
		contentPane.add(bFerramentas);

		// Fim botão ferramentas

		// Botão contatos

		conta = new ImageIcon(new BufferedImageLoader().loadImage("/contatos.png"));
		bContatos = new JButton("Contatos");
		bContatos.setIcon(conta);

		bContatos.setVerticalAlignment(SwingConstants.BOTTOM);
		bContatos.setVerticalTextPosition(SwingConstants.BOTTOM);
		bContatos.setHorizontalTextPosition(SwingConstants.CENTER);
		bContatos.setForeground(Color.WHITE);

		bContatos.setBorder(null);
		bContatos.setContentAreaFilled(false);

		bContatos.setBounds(5, 145, 220, 120);
		contentPane.add(bContatos);

		// Fim botão contatos

		// Botão configurações

		config = new ImageIcon(new BufferedImageLoader().loadImage("/config.png"));
		bConfiguracoes = new JButton("Configurações");
		bConfiguracoes.setIcon(config);

		bConfiguracoes.setVerticalAlignment(SwingConstants.BOTTOM);
		bConfiguracoes.setVerticalTextPosition(SwingConstants.BOTTOM);
		bConfiguracoes.setHorizontalTextPosition(SwingConstants.CENTER);
		bConfiguracoes.setForeground(Color.WHITE);

		bConfiguracoes.setBorder(null);
		bConfiguracoes.setContentAreaFilled(false);

		bConfiguracoes.setBounds(225, 145, 220, 120);
		contentPane.add(bConfiguracoes);

		// Fim botão configurações

		// Hora
		horaAtual = new SimpleDateFormat("HH:mm:ss").format(date);

		lblhora = new JLabel(horaAtual);
		lblhora.setBounds(385, 1, 60, 15);
		lblhora.setHorizontalAlignment(SwingConstants.RIGHT);
		lblhora.setFont(new Font("Dialog", Font.PLAIN, 11));
		lblhora.setForeground(Color.WHITE);
		contentPane.add(lblhora);
		atualizarHora();

		// Data
		dataAtual = getDia((byte) date.getDay()) + ", "+ new SimpleDateFormat("dd").format(date) +" de "+ getMes((byte) date.getMonth());
		lblData = new JLabel(dataAtual);
		lblData.setBounds(1, 1, 180, 10);
		lblData.setFont(new Font("Dialog", Font.PLAIN, 11));
		lblData.setHorizontalAlignment(SwingConstants.LEFT);
		lblData.setForeground(Color.WHITE);
		contentPane.add(lblData);
		
		
		
		alterarTema();

		// FIM CONSTRUÇÃO E DEFINIÇÃO DE COMPONENTES

		/******************************************/

		// EVENTOS

		// Alterar ícones dos botões
		bEntretenimento.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent f) {
				entret = new ImageIcon(new BufferedImageLoader().loadImage("/entretenimento-focused.png"));
				bEntretenimento.setIcon(entret);

			}

			public void focusLost(FocusEvent f) {
				entret = new ImageIcon(new BufferedImageLoader().loadImage("/entretenimento.png"));
				bEntretenimento.setIcon(entret);
			}
		});

		bFerramentas.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent f) {
				ferra = new ImageIcon(new BufferedImageLoader().loadImage("/ferramentas-focused.png"));
				bFerramentas.setIcon(ferra);

			}

			public void focusLost(FocusEvent f) {
				ferra = new ImageIcon(new BufferedImageLoader().loadImage("/ferramentas.png"));
				bFerramentas.setIcon(ferra);
			}
		});

		bContatos.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent f) {
				conta = new ImageIcon(new BufferedImageLoader().loadImage("/contatos-focused.png"));
				bContatos.setIcon(conta);

			}

			public void focusLost(FocusEvent f) {
				conta = new ImageIcon(new BufferedImageLoader().loadImage("/contatos.png"));
				bContatos.setIcon(conta);
			}
		});

		bConfiguracoes.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent f) {
				config = new ImageIcon(new BufferedImageLoader().loadImage("/config-focused.png"));
				bConfiguracoes.setIcon(config);

			}

			public void focusLost(FocusEvent f) {
				config = new ImageIcon(new BufferedImageLoader().loadImage("/config.png"));
				bConfiguracoes.setIcon(config);
			}
		});
		
		// Fim alterar ícones dos botões
		
		
		
		// Eventos do teclado
		bEntretenimento.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent k) {
				switch (k.getKeyCode()) {
				case (KeyEvent.VK_BACK_SPACE):
					dispose();
					try {
						new TelaInicio().setVisible(true);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case (KeyEvent.VK_RIGHT):
					bFerramentas.requestFocus();
					break;
				case (KeyEvent.VK_DOWN):
					bContatos.requestFocus();
					break;
				case (KeyEvent.VK_SPACE):
					dispose();
					new MenuEntretenimento().setVisible(true);
				}
			}
		});
		
		bFerramentas.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent k) {
				switch (k.getKeyCode()) {
				case (KeyEvent.VK_BACK_SPACE):
					dispose();
					try {
						new TelaInicio().setVisible(true);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case (KeyEvent.VK_LEFT):
					bEntretenimento.requestFocus();
					break;
				case (KeyEvent.VK_DOWN):
					bConfiguracoes.requestFocus();
					break;
				case (KeyEvent.VK_SPACE):
					dispose();
					new MenuFerramentas().setVisible(true);
				}
			}
		});
		
		bContatos.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent k) {
				switch (k.getKeyCode()) {
				case (KeyEvent.VK_BACK_SPACE):
					dispose();
					try {
						new TelaInicio().setVisible(true);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case (KeyEvent.VK_RIGHT):
					bConfiguracoes.requestFocus();
					break;
				case (KeyEvent.VK_UP):
					bEntretenimento.requestFocus();
					break;
				case (KeyEvent.VK_SPACE):
					dispose();
					new MenuContatos().setVisible(true);
				}
			}
		});
		
		bConfiguracoes.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent k) {
				switch (k.getKeyCode()) {
				case (KeyEvent.VK_BACK_SPACE):
					dispose();
					try {
						new TelaInicio().setVisible(true);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case (KeyEvent.VK_LEFT):
					bContatos.requestFocus();
					break;
				case (KeyEvent.VK_UP):
					bFerramentas.requestFocus();
					break;
				case (KeyEvent.VK_SPACE):
					dispose();
					new MenuConfiguracoes().setVisible(true);
				}
			}
		});
		
		// Fim eventos do teclado
		
		// FIM EVENTOS
	}

	// MÉTODOS PRINCIPAIS

	private void atualizarHora() {

		// Cria um objeto para agendar um comando para ser feito entre intervalos de
		// tempo até ser cancelado
		ScheduledExecutorService attHora = Executors.newScheduledThreadPool(1);
		attHora.scheduleAtFixedRate(new Runnable() {
			public void run() {
				horaAtual = new SimpleDateFormat("HH:mm").format(new Date(System.currentTimeMillis())); // atualiza a
																											// hora
				if (!h24) {
					if (Integer.parseInt(horaAtual.substring(0, 2)) > 12) {
						horaAtual = String.format("%02d%s", (Integer.parseInt(horaAtual.substring(0, 2)) - 12),
								horaAtual.substring(2));
					}
				}
				lblhora.setText(horaAtual); // atualiza o JLabel
			}
		}, 0, 1, TimeUnit.SECONDS); // começa logo após abrir a tela e repete a cada 60 segundos (1 min)
	}
	
	private String getDia(byte dia) {
		switch (dia) {
		case 0:
			return "Domingo";
		case 1:
			return "Segundo";
		case 2: 
			return "Terça";
		case 3: 
			return "Quarta";
		case 4:
			return "Quinta";
		case 5:
			return "Sexta";
		case 6:
			return "Sábado";
		default:
			return "null";
		}
	}
	
	
	private String getMes(byte mes) {
		switch (mes) {
		case 0:
			return "Janeiro";
		case 1:
			return "Fevereiro";
		case 2: 
			return "Março";
		case 3: 
			return "Abril";
		case 4:
			return "Maio";
		case 5:
			return "Junho";
		case 6:
			return "Julho";
		case 7:
			return "Agosto";
		case 8:
			return "Setembro";
		case 9:
			return "Outubro";
		case 10:
			return "Novembro";
		case 11:
			return "Dezembro";
		default:
			return "null";
		}
	}
	

    // Construtor do JImagePanel
	private BufferedImage loadImage(String wp) throws IOException{

		BufferedImage image;
		
		if (wp.equals("A")) {
			image =  new BufferedImageLoader().loadImage("/wallpaper1.jpg");
		} else if (wp.equals("B")) {
			image =  new BufferedImageLoader().loadImage("/wallpaper2.jpg");
		} else if (wp.equals("C")) {
			image =  new BufferedImageLoader().loadImage("/wallpaper3.jpg");
		} else if (wp.equals("D")) {
			image =  new BufferedImageLoader().loadImage("/wallpaper4.jpg");
		} else if (wp.equals("E")) {
			image =  new BufferedImageLoader().loadImage("/wallpaper5.jpg");
		} else image =  null; // Null
		
		return image;
    }
}