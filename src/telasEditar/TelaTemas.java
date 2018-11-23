package telasEditar;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

import objetos.BufferedImageLoader;
import objetos.Tema;
import telasMenu.MenuConfiguracoes;


public class TelaTemas extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	
	private static JLabel lblTema;

	private static JLabel lblSalvar;
	private static JLabel lblVoltar;
	
	private static JButton btnClaro, btnEscuro;
	private static ImageIcon claro, escuro; // ícones dos botões
	
	private static String tema;
	

	
	private static boolean salvo = true;
	
	public static void main(String[] args) throws SQLException {
		TelaTemas frame = new TelaTemas();
		frame.setVisible(true);
	}

	public TelaTemas() throws MySQLSyntaxErrorException, MySQLNonTransientConnectionException, CommunicationsException, SQLException {
		tema = Tema.getTema();
		
		setSize(450, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Temas");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTema = new JLabel("Escolha um tema:");
		lblTema.setFont(new Font("Dialog", Font.PLAIN, 17));
		lblTema.setBounds(20, 20, 160, 25);
		contentPane.add(lblTema);
		
		claro = new ImageIcon(new BufferedImageLoader().loadImage("/tema-claro.png"));
		btnClaro = new JButton("Claro");
		btnClaro.requestFocus();
		btnClaro.setIcon(claro);
		btnClaro.setVerticalAlignment(SwingConstants.BOTTOM);
		btnClaro.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnClaro.setHorizontalTextPosition(SwingConstants.CENTER);
		
		btnClaro.setBorder(null);
		btnClaro.setBounds(50, 60, 160, 160);
		btnClaro.setBackground(Color.LIGHT_GRAY);;
		contentPane.add(btnClaro);
		
		escuro = new ImageIcon(new BufferedImageLoader().loadImage("/tema-escuro.png"));
		btnEscuro = new JButton("Escuro");
		btnEscuro.setIcon(escuro);
		btnEscuro.setVerticalAlignment(SwingConstants.BOTTOM);
		btnEscuro.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEscuro.setHorizontalTextPosition(SwingConstants.CENTER);
		
		btnEscuro.setBorder(null);
		btnEscuro.setBounds(230, 60, 160, 160);
		btnEscuro.setBackground(Color.LIGHT_GRAY);;
		contentPane.add(btnEscuro);
		
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
		
		alterarTema(tema);
		
		// EVENTOS
		
		btnClaro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				alterarTema("claro");
			}
		});
		
		btnEscuro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				alterarTema("escuro");
			}
		});
		
		
		btnClaro.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					btnEscuro.requestFocus();
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
		
		btnEscuro.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					btnClaro.requestFocus();
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
		
	}
	
	
	private void alterarTema(String t) {
		if (t.equals("claro")) {
			contentPane.setBackground(null);
			lblTema.setForeground(Color.BLACK);
			lblSalvar.setForeground(Color.BLACK);
			lblVoltar.setForeground(Color.BLACK);
			
			salvo = false;
			tema = t;
		} else if(t.equals("escuro")) {
			contentPane.setBackground(new Color(0, 6, 45));
			lblTema.setForeground(Color.WHITE);
			lblSalvar.setForeground(Color.WHITE);
			lblVoltar.setForeground(Color.WHITE);
			
			salvo = false;
			tema = t;
		}
	}
	
	private void salvar() throws MySQLSyntaxErrorException, MySQLNonTransientConnectionException, CommunicationsException, SQLException {
		
		Tema.salvarTema(tema);
		
		salvo = true;
		
		JOptionPane.showMessageDialog(contentPane, "Salvo!");
	}
	
	private void voltar() {
		String[] opcoes = {"Sim", "Não"}; // para verificar se o usuário deseja sair sem salvar
		// testar se já salvou
		if (!salvo) {
			if (JOptionPane.showOptionDialog(contentPane, "Salvar antes de sair?", "Sair", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[1]) == 0) {
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








