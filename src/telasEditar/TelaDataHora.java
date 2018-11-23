package telasEditar;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

import objetos.Perfil;
import objetos.dataHora;
import telasMenu.MenuConfiguracoes;
import telasMenu.MenuFerramentas;

@SuppressWarnings("unused")
public class TelaDataHora extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane, datahora;
	
	private static String horaAtual;
	private static JLabel lblhora;
	private static String dataAtual;
	private static JLabel lblData;
	private static Date date = new Date(System.currentTimeMillis());
	
	private static JLabel lblSalvar;
	private static JLabel lblVoltar;
	
	private static JLabel lblFData;
	private static JLabel lblFHora;
	private static JButton dA, dB, dC, hA, hB;
	
	
	private static boolean h24;
	private static String formato;
	
	Font font = new Font("Dialog", Font.PLAIN, 17);
	
	private static boolean salvo = true;
	
	public static void main(String[] args) throws SQLException {
		TelaDataHora frame = new TelaDataHora();
		frame.setVisible(true);
	}
	
	
	private void alterarTema() {
		if (Perfil.getTema().equals("claro")) {
			contentPane.setBackground(new Color(220, 220, 220));
			datahora.setBackground(new Color(200, 200, 200));
			datahora.setBorder(new TitledBorder(new LineBorder(Color.black), "A data e a hora serão exibidas assim",
					SwingConstants.RIGHT, SwingConstants.CENTER, font, Color.black));
			datahora.setForeground(Color.BLACK);
			lblFData.setForeground(Color.BLACK);
			lblFHora.setForeground(Color.BLACK);
			lblhora.setForeground(Color.BLACK);
			lblData.setForeground(Color.BLACK);
			lblSalvar.setForeground(Color.BLACK);
			lblVoltar.setForeground(Color.BLACK);
			
			dA.setForeground(Color.black);
			dB.setForeground(Color.black);
			dC.setForeground(Color.black);
			hA.setForeground(Color.black);
			hB.setForeground(Color.black);
			
			dA.setBackground(Color.LIGHT_GRAY);
			dB.setBackground(Color.LIGHT_GRAY);
			dC.setBackground(Color.LIGHT_GRAY);
			hA.setBackground(Color.LIGHT_GRAY);
			hB.setBackground(Color.LIGHT_GRAY);

		} else if(Perfil.getTema().equals("escuro")) {
			contentPane.setBackground(new Color(0, 6, 45));
			datahora.setBackground(new Color(0, 6, 30));
			datahora.setBorder(new TitledBorder(new LineBorder(Color.white), "A data e a hora serão exibidas assim",
					SwingConstants.RIGHT, SwingConstants.CENTER, font, Color.WHITE));
			datahora.setForeground(Color.WHITE);
			lblFData.setForeground(Color.WHITE);
			lblFHora.setForeground(Color.WHITE);
			lblhora.setForeground(Color.WHITE);
			lblData.setForeground(Color.WHITE);
			lblSalvar.setForeground(Color.WHITE);
			lblVoltar.setForeground(Color.WHITE);
			
			dA.setForeground(Color.WHITE);
			dB.setForeground(Color.WHITE);
			dC.setForeground(Color.WHITE);
			hA.setForeground(Color.WHITE);
			hB.setForeground(Color.WHITE);
			
			dA.setBackground(new Color(0, 6, 120));
			dB.setBackground(new Color(0, 6, 120));
			dC.setBackground(new Color(0, 6, 120));
			hA.setBackground(new Color(0, 6, 120));
			hB.setBackground(new Color(0, 6, 120));
		}
	}

	public TelaDataHora() throws SQLException {
		formato = dataHora.formatoData();
		h24 = dataHora.formatoHora();
		
		setSize(450, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Data e Hora");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblFData = new JLabel("Formato de data:");
		lblFData.setBounds(20, 20, 160, 25);
		lblFData.setFont(font);
		contentPane.add(lblFData);

		lblFHora = new JLabel("Formato de hora:");
		lblFHora.setBounds(20, 80, 160, 25);
		lblFHora.setFont(font);
		contentPane.add(lblFHora);

		// BOTÕES

		Font rbFont = new Font("Dialog", Font.BOLD, 16);

		// BOTÕES DE FORMATO DE DATA

		dA = new JButton("A");
		dA.setFont(rbFont);
		dA.setBounds(200, 13, 50, 40);
		contentPane.add(dA);

		dB = new JButton("B");
		dB.setFont(rbFont);
		dB.setBounds(280, 13, 50, 40);
		contentPane.add(dB);

		dC = new JButton("C");
		dC.setFont(rbFont);
		dC.setBounds(360, 13, 50, 40);
		contentPane.add(dC);

		// BOTÕES DE FORMATO DE HORA

		hA = new JButton("12h");
		hA.setSelected(true);
		hA.setFont(rbFont);
		hA.setBounds(225, 73, 70, 40);
		contentPane.add(hA);

		hB = new JButton("24h");
		hB.setFont(rbFont);
		hB.setBounds(315, 73, 70, 40);
		contentPane.add(hB);

		// FIM BOTÕES

		datahora = new JPanel();
		datahora.setLayout(null);
		datahora.setBounds(15, 130, 420, 100);
		contentPane.add(datahora);

		// Hora

		lblhora = new JLabel(horaAtual);
		lblhora.setBorder(new EtchedBorder());
		lblhora.setBounds(25, 30, 180, 40);
		lblhora.setHorizontalAlignment(SwingConstants.CENTER);
		lblhora.setFont(new Font("Dialog", Font.BOLD, 26));
		datahora.add(lblhora);
		atualizarHora();

		// Data
		lblData = new JLabel();
		alterarFormatacaoData(formato);
		lblData.setBounds(215, 30, 180, 40);
		lblData.setBorder(new EtchedBorder());
		lblData.setFont(new Font("Dialog", Font.BOLD, 26));
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		datahora.add(lblData);

		// Labels de ações

		lblSalvar = new JLabel("Salvar");
		lblSalvar.setBounds(10, 250, 60, 20);
		lblSalvar.setHorizontalAlignment(SwingConstants.LEFT);
		lblSalvar.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPane.add(lblSalvar);

		lblVoltar = new JLabel("Voltar");
		lblVoltar.setBounds(360, 250, 80, 20);
		lblVoltar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVoltar.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPane.add(lblVoltar);
		
		alterarTema();

		// EVENTOS
		dA.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				alterarFormatacaoData(dA.getText());
			}
		});

		dB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				alterarFormatacaoData(dB.getText());
			}
		});

		dC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				alterarFormatacaoData(dC.getText());
			}
		});
		
		hA.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				h24 = false;
				salvo = false;
			}
		});
		
		hB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				h24 = true;
				salvo = false;
			}
		});
		
		
		dA.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					dB.requestFocus();
					break;
				case KeyEvent.VK_DOWN:
					hA.requestFocus();
					break;
				case KeyEvent.VK_F1:
					try {
						salvar();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case KeyEvent.VK_F2:
					voltar();
					break;
				}
			}
		});
		
		dB.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					dC.requestFocus();
					break;
				case KeyEvent.VK_LEFT:
					dA.requestFocus();
					break;	
				case KeyEvent.VK_DOWN:
					hA.requestFocus();
					break;
				case KeyEvent.VK_F1:
					try {
						salvar();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case KeyEvent.VK_F2:
					voltar();
					break;
				}
			}
		});
		
		dC.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					dB.requestFocus();
					break;
				case KeyEvent.VK_DOWN:
					hB.requestFocus();
					break;
				case KeyEvent.VK_F1:
					try {
						salvar();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case KeyEvent.VK_F2:
					voltar();
					break;
				}
			}
		});
		
		hA.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					hB.requestFocus();
					break;
				case KeyEvent.VK_UP:
					dA.requestFocus();
					break;
				case KeyEvent.VK_F1:
					try {
						salvar();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case KeyEvent.VK_F2:
					voltar();
					break;
				}
			}
		});
		
		hB.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					hA.requestFocus();
					break;
				case KeyEvent.VK_UP:
					dC.requestFocus();
					break;
				case KeyEvent.VK_F1:
					try {
						salvar();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case KeyEvent.VK_F2:
					voltar();
					break;
				}
			}
		});

		// FIM EVENTOS

	}

	private void atualizarHora() {

		// Cria um objeto para agendar um comando para ser feito entre intervalos de
		// tempo até ser cancelado
		ScheduledExecutorService attHora = Executors.newScheduledThreadPool(1);
		attHora.scheduleAtFixedRate(new Runnable() {
			public void run() {
				horaAtual = new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis())); // atualiza a
																											// hora
				if (!h24) {
					if (Integer.parseInt(horaAtual.substring(0, 2)) > 12) {
						horaAtual = String.format("%02d%s", (Integer.parseInt(horaAtual.substring(0, 2)) - 12),
								horaAtual.substring(2));
					}
				}
				lblhora.setText(horaAtual); // atualiza o JLabel
			}
		}, 0, 1, TimeUnit.MILLISECONDS); // começa logo após abrir a tela e atualiza a cada 1 milésimo
	}

	private void alterarFormatacaoData(String f) {
		if (f.equals("A")) {
			dataAtual = new SimpleDateFormat("dd/MM/yyyy").format(date);
		} else if (f.equals("B")) {
			dataAtual = new SimpleDateFormat("MM/dd/yyyy").format(date);
		} else if (f.equals("C")) {
			dataAtual = new SimpleDateFormat("yyyy/MM/dd").format(date);
		}
		
		formato = f;
		
		salvo = false;

		lblData.setText(dataAtual);
	}
	
	private void salvar() throws MySQLSyntaxErrorException, MySQLNonTransientConnectionException, CommunicationsException, SQLException {
		String fHora;
		if (h24)
			fHora = "A";
		else
			fHora = "B";
		
		dataHora.salvarConfiguracao(formato, fHora);
		
		salvo = true;
		
		JOptionPane.showMessageDialog(contentPane, "Salvo!");
	}
	
	private void voltar() {
		String[] opcoes = {"Sim", "Não"}; // para verificar se o usuário deseja sair sem salvar
		// testar se já salvou
		if (!salvo) {
			if (JOptionPane.showOptionDialog(contentPane, "Salvar antes de sair?", "Sair", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]) == 0) {
				try {
					salvar();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		dispose();
		MenuConfiguracoes.main(null);
	}
}
