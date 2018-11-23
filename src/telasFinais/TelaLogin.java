package telasFinais;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import objetos.Perfil;
import telasCadastro.TelaCadastroUsuario;
import telasMain.TelaInicio;

import java.awt.event.KeyAdapter;

public class TelaLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel loginInvalido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
					frame.setResizable(false);
					frame.setTitle("Login");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// CRIAÇÃO E DEFINIÇÃO DE COMPONENTES

		JPanel panel1 = new JPanel();
		panel1.setBorder(new LineBorder(new Color(0)));
		panel1.setLayout(null);
		panel1.setBounds(75, 30, 300, 150);
		contentPane.add(panel1);

		// Label de login inválido
		loginInvalido = new JLabel("Usuário ou senha inválidos.");
		loginInvalido.setBounds(15, 120, 200, 15);
		loginInvalido.setFont(new Font("dialog", Font.BOLD, 10));
		loginInvalido.setForeground(Color.RED);
		loginInvalido.setVisible(false);
		panel1.add(loginInvalido);

		JLabel lblUsuario = new JLabel("Usuário:");
		lblUsuario.setForeground(Color.BLACK);
		lblUsuario.setFont(new Font("dialog", Font.PLAIN, 14));
		lblUsuario.setBounds(15, 32, 60, 15);
		panel1.add(lblUsuario);

		JTextField txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("dialog", Font.PLAIN, 14));
		txtUsuario.setBounds(90, 30, 200, 18);
		panel1.add(txtUsuario);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("dialog", Font.PLAIN, 14));
		lblSenha.setBounds(15, 82, 60, 15);
		panel1.add(lblSenha);
		
		/*
		JTextField txtSenha = new JTextField();
		txtSenha.setFont(new Font("dialog", Font.PLAIN, 14));
		txtSenha.setBounds(90, 80, 200, 18);
		panel1.add(txtSenha);
		*/
		
		JPasswordField pSenha = new JPasswordField();
		pSenha.setBounds(90, 80, 200, 20);
		panel1.add(pSenha);

		JLabel lblEntrar = new JLabel("Entrar");
		lblEntrar.setFont(new Font("dialog", Font.PLAIN, 14));
		lblEntrar.setBounds(10, 222, 50, 50);
		contentPane.add(lblEntrar);

		JLabel lblCadastrar = new JLabel("Cadastrar novo");
		lblCadastrar.setFont(new Font("dialog", Font.PLAIN, 14));
		lblCadastrar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCadastrar.setBounds(315, 222, 110, 50);
		contentPane.add(lblCadastrar);

		// EVENTOS

		txtUsuario.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case (KeyEvent.VK_F1):
					// enviar informações e entrar
					try {
						if (enviarInformacoes(txtUsuario.getText(), pSenha.getText())) {
							dispose();
							new TelaInicio().setVisible(true);
						} else {
							pSenha.setText("");
							txtUsuario.setText("");
							txtUsuario.requestFocus();
							loginInvalido.setVisible(true);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case (KeyEvent.VK_F2):
					dispose();
					new TelaCadastroUsuario().setVisible(true);
					break;
				case (KeyEvent.VK_DOWN):
					pSenha.requestFocus();
				}
			}
		});

		pSenha.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case (KeyEvent.VK_F1):
					// enviar informações e entrar
					try {
						if (enviarInformacoes(txtUsuario.getText(), pSenha.getText())) {
							dispose();
							new TelaInicio().setVisible(true);
						} else {
							pSenha.setText("");
							txtUsuario.setText("");
							txtUsuario.requestFocus();
							loginInvalido.setVisible(true);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case (KeyEvent.VK_F2):
					dispose();
					new TelaCadastroUsuario().setVisible(true);
					break;
				case (KeyEvent.VK_UP):
					txtUsuario.requestFocus();
				}
			}
		});

	}

	// MÉTODOS PRINCIPAIS

	/**
	 * Envia as informações coletadas para o método entrar da classe Perfil
	 * 
	 * @param u
	 * @param s
	 * @throws SQLException
	 */
	private boolean enviarInformacoes(String u, String s) throws SQLException { // recebe
																				// usuario
																				// e
																				// senha
		if (Perfil.entrar(u, s)) {
			JOptionPane.showMessageDialog(this, "Bem vindo, " + Perfil.getNome() + "!", "Login",
					JOptionPane.PLAIN_MESSAGE);
			return true;
		} else {
			return false;
		}
	}
}



