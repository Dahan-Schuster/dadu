package telasCadastro;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

import objetos.Jogo;
import objetos.Perfil;
import objetos.Tema;
import objetos.dataHora;
import telasFinais.TelaLogin;
import telasFinais.TelaTour;
import telasMain.TelaInicio;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class TelaCadastroUsuario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtSenha;
	private JPasswordField txtConfSenha;
	private JTextField txtNome;
	private JTextField txtSobrenome;
	private JPanel panel;
	private JLabel lblCadastrar;
	private JLabel lblCancelar;
	private JLabel senhasNaoConferem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroUsuario frame = new TelaCadastroUsuario();
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
	public TelaCadastroUsuario() {
		setResizable(false);
		setTitle("Cadastro Usuário");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(15, 15, 420, 160);
		contentPane.add(panel);
		panel.setLayout(null);

		// CONSTRUÇÃO E DEFINIÇÃO DE COMPONENTES

		// JLabel para confirmação de senhas
		senhasNaoConferem = new JLabel("As senhas não conferem.");
		senhasNaoConferem.setBounds(200, 85, 200, 15);
		senhasNaoConferem.setFont(new Font("dialog", Font.BOLD, 10));
		senhasNaoConferem.setForeground(Color.RED);
		senhasNaoConferem.setVisible(false);
		panel.add(senhasNaoConferem);

		JLabel lblUsuario = new JLabel("Usu\u00E1rio:");
		lblUsuario.setBounds(6, 20, 70, 15);
		panel.add(lblUsuario);
		lblUsuario.setFont(new Font("Dialog", Font.PLAIN, 14));

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(6, 67, 56, 14);
		panel.add(lblSenha);
		lblSenha.setFont(new Font("Dialog", Font.PLAIN, 14));

		JLabel lblConfirmarSenha = new JLabel("Confirmar:");
		lblConfirmarSenha.setBounds(200, 67, 85, 14);
		panel.add(lblConfirmarSenha);
		lblConfirmarSenha.setFont(new Font("Dialog", Font.PLAIN, 14));

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(6, 122, 50, 14);
		panel.add(lblNome);
		lblNome.setFont(new Font("Dialog", Font.PLAIN, 14));

		JLabel lblSobrenome = new JLabel("Sobrenome:");
		lblSobrenome.setBounds(190, 122, 90, 14);
		panel.add(lblSobrenome);
		lblSobrenome.setFont(new Font("Dialog", Font.PLAIN, 14));

		txtUsuario = new JTextField();
		txtUsuario.setBounds(72, 20, 126, 20);
		panel.add(txtUsuario);
		txtUsuario.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtUsuario.setColumns(10);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(72, 65, 114, 20);
		panel.add(txtSenha);
		txtSenha.setFont(new Font("Dialog", Font.PLAIN, 14));

		txtConfSenha = new JPasswordField();
		txtConfSenha.setBounds(290, 65, 114, 20);
		panel.add(txtConfSenha);
		txtConfSenha.setFont(new Font("Dialog", Font.PLAIN, 14));

		txtNome = new JTextField();
		txtNome.setBounds(72, 120, 114, 20);
		panel.add(txtNome);
		txtNome.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtNome.setColumns(10);

		txtSobrenome = new JTextField();
		txtSobrenome.setBounds(290, 120, 114, 20);
		panel.add(txtSobrenome);
		txtSobrenome.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtSobrenome.setColumns(10);

		lblCadastrar = new JLabel("Cadastrar");
		lblCadastrar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblCadastrar.setBounds(10, 236, 77, 14);
		contentPane.add(lblCadastrar);

		lblCancelar = new JLabel("Cancelar");
		lblCancelar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblCancelar.setBounds(359, 236, 65, 14);
		contentPane.add(lblCancelar);

		// EVENTOS

		// Eventos to teclado para cada componente

		txtUsuario.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case (KeyEvent.VK_DOWN):
					txtSenha.requestFocus();
					break;
				case (KeyEvent.VK_F2):
					dispose();
					new TelaLogin().setVisible(true);
					break;
				case (KeyEvent.VK_F1):
					try {
						coletarInformacoes();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				}
			}
		});

		txtSenha.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case (KeyEvent.VK_DOWN):
					txtNome.requestFocus();
					break;
				case (KeyEvent.VK_UP):
					txtUsuario.requestFocus();
					break;
				case (KeyEvent.VK_RIGHT):
					txtConfSenha.requestFocus();
					break;
				case (KeyEvent.VK_F2):
					dispose();
					new TelaLogin().setVisible(true);
					break;
				case (KeyEvent.VK_F1):
					try {
						coletarInformacoes();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				verificarSemelhanca();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				verificarSemelhanca();
			}

		});

		txtConfSenha.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case (KeyEvent.VK_DOWN):
					txtSobrenome.requestFocus();
					break;
				case (KeyEvent.VK_LEFT):
					txtSenha.requestFocus();
					break;
				case (KeyEvent.VK_F2):
					dispose();
					new TelaLogin().setVisible(true);
					break;
				case (KeyEvent.VK_F1):
					try {
						coletarInformacoes();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				}
			}
		});

		txtNome.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case (KeyEvent.VK_UP):
					txtSenha.requestFocus();
					break;
				case (KeyEvent.VK_RIGHT):
					txtSobrenome.requestFocus();
					break;
				case (KeyEvent.VK_F2):
					dispose();
					new TelaLogin().setVisible(true);
					break;
				case (KeyEvent.VK_F1):
					try {
						coletarInformacoes();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				}
			}
		});

		txtSobrenome.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case (KeyEvent.VK_UP):
					txtConfSenha.requestFocus();
					break;
				case (KeyEvent.VK_LEFT):
					txtNome.requestFocus();
					break;
				case (KeyEvent.VK_F2):
					dispose();
					new TelaLogin().setVisible(true);
					break;
				case (KeyEvent.VK_F1):
					try {
						coletarInformacoes();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				}
			}
		});
		// fim eventos do teclado

		// Verifica a semelhança entre as senhas
		txtConfSenha.addFocusListener(new FocusAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void focusLost(FocusEvent arg0) {

				if (txtSenha.getText().equals(txtConfSenha.getText())) {
					senhasNaoConferem.setVisible(false);
				} else {
					senhasNaoConferem.setVisible(true);
				}
			}
		});

		// fim da verificação de senhas
	}

	// MÉTODOS PRINCIPAIS

	/**
	 * Coleta os dados dos campos de texto e envia para a classe Perfil com o método
	 * enviarInformacoes
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	private void coletarInformacoes() throws SQLException, IOException {

		if (txtSenha.getText().length() > 12) {
			JOptionPane.showMessageDialog(this, "A senha deve ter no máximo 12 caracteres.\nTente novamente.", "Erro",
					JOptionPane.ERROR_MESSAGE);
			txtConfSenha.setText("");
			txtSenha.selectAll();
		} else {
			String[] valores = new String[11];

			valores[0] = txtUsuario.getText();
			valores[1] = txtSenha.getText();
			valores[2] = txtNome.getText();
			valores[3] = txtSobrenome.getText();
			valores[4] = "";
			valores[5] = "";
			valores[6] = "";
			valores[7] = "";
			valores[8] = "";
			valores[9] = "";
			valores[10] = "";

			byte tour = enviarInformacoes(valores);

			if (tour != 2) {
				if (tour == 0) {
					dispose();
					new TelaTour().setVisible(true);
				}
				else
					abrirTelaInicio();
			} else {
				JOptionPane.showMessageDialog(this, "Ocorreu um erro ao cadastrar\num novo usuário.\nTente novamente.",
						"Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Envia as informações coletadas para o método salvarDados da classe Perfil
	 * 
	 * @param u
	 * @param s
	 * @throws SQLException
	 */
	@SuppressWarnings("deprecation")
	private byte enviarInformacoes(String[] valores) throws SQLException {
		String[] escolha = { "Sim", "Não" };
		if (Perfil.salvarUsuario(valores)) {

			// Após cadastrar, realiza o login automaticamente
			Perfil.entrar(txtUsuario.getText(), txtSenha.getText());

			// Cria a tabela de pontuações do jogo 2D Space Game
			Jogo.abrirPontuacao(0);

			// Cria a tabela de configurações de data e hora
			dataHora.abrirConfiguracoes();

			// Cria a tabela de configurações de tema
			Tema.abrirTema();

			Perfil.atualizarTema();
			Perfil.atualizarWallpaper();

			// Confere se o novo usuário deseja realizar um tour
			return (byte) JOptionPane.showOptionDialog(this,
					"Bem vindo ao D.A.D.U., " + Perfil.getNome() + "!\nDeseja fazer um tour pelo sistema?",
					"Cadastro realizado", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, escolha,
					escolha[0]);
		} else {
			return 2; // O Option Dialog retorna 0 ou 1 (para 'sim' e 'não', respectivamente). Quando
						// o método retorna 2 significa que algum erro ocorreu ao salvar o usuário
		}
	}

	/**
	 * Chama a classe TelaInicio enviando como parâmetro o inteiro referente à
	 * escolha do usuário
	 * 
	 * @param tour
	 * @throws IOException
	 * @throws SQLException
	 */
	private void abrirTelaInicio() throws IOException, SQLException {
		dispose();
		new TelaInicio().setVisible(true);
	}

	@SuppressWarnings("deprecation")
	private void verificarSemelhanca() {
		if (txtSenha.getText().equals(txtConfSenha.getText())) {
			senhasNaoConferem.setVisible(false);
		} else {
			senhasNaoConferem.setVisible(true);
		}
	}
}
