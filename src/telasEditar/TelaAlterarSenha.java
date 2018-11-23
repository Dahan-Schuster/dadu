package telasEditar;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import objetos.Perfil;
import telasEditar.TelaConfigurarPerfil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import java.awt.Color;

public class TelaAlterarSenha extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public class setVisible {

	}

	private JPanel contentPane;
	private JPasswordField txtSenhaAtual;
	private JPasswordField txtNovaSenha;
	private JLabel lblConfirmarSenha;
	private JPasswordField txtConfirmarSenha;
	private JLabel lblSalvar;
	private JLabel lblCancelar;
	private JLabel lblSenhaAtual, lblNovaSenha, lblAsSenhasNaoConferem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAlterarSenha frame = new TelaAlterarSenha();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void alterarTema() {
		if (Perfil.getTema().equals("claro")) {
			contentPane.setBackground(new Color(220, 220, 220));
			lblSenhaAtual.setForeground(Color.black);
			lblNovaSenha.setForeground(Color.black);
			lblConfirmarSenha.setForeground(Color.black);
			lblSalvar.setForeground(Color.black);
			lblCancelar.setForeground(Color.black);

		} else if (Perfil.getTema().equals("escuro")) {
			contentPane.setBackground(new Color(0, 6, 45));
			lblSenhaAtual.setForeground(Color.white);
			lblNovaSenha.setForeground(Color.white);
			lblConfirmarSenha.setForeground(Color.white);
			lblSalvar.setForeground(Color.white);
			lblCancelar.setForeground(Color.white);
		}
	}

	/**
	 * Create the frame.
	 */
	public TelaAlterarSenha() {
		setTitle("Alterar Senha");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 290, 190);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblSenhaAtual = new JLabel("Senha Atual:");
		lblSenhaAtual.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblSenhaAtual.setBounds(10, 21, 91, 14);
		contentPane.add(lblSenhaAtual);

		txtSenhaAtual = new JPasswordField();
		txtSenhaAtual.setBounds(105, 20, 161, 20);
		contentPane.add(txtSenhaAtual);
		txtSenhaAtual.setColumns(10);

		lblNovaSenha = new JLabel("Nova Senha:");
		lblNovaSenha.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNovaSenha.setBounds(10, 52, 91, 14);
		contentPane.add(lblNovaSenha);

		txtNovaSenha = new JPasswordField();
		txtNovaSenha.setBounds(105, 51, 161, 20);
		contentPane.add(txtNovaSenha);
		txtNovaSenha.setColumns(10);

		lblConfirmarSenha = new JLabel("Confirmar:");
		lblConfirmarSenha.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblConfirmarSenha.setBounds(10, 83, 116, 14);
		contentPane.add(lblConfirmarSenha);

		txtConfirmarSenha = new JPasswordField();
		txtConfirmarSenha.setBounds(105, 82, 161, 20);
		contentPane.add(txtConfirmarSenha);
		txtConfirmarSenha.setColumns(10);

		lblSalvar = new JLabel("Salvar");
		lblSalvar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblSalvar.setBounds(10, 135, 46, 14);
		contentPane.add(lblSalvar);

		lblCancelar = new JLabel("Cancelar");
		lblCancelar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblCancelar.setBounds(210, 135, 65, 14);
		contentPane.add(lblCancelar);

		lblAsSenhasNaoConferem = new JLabel("*As senhas n\u00E3o conferem*");
		lblAsSenhasNaoConferem.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblAsSenhasNaoConferem.setForeground(Color.RED);
		lblAsSenhasNaoConferem.setBackground(Color.WHITE);
		lblAsSenhasNaoConferem.setBounds(118, 108, 138, 14);
		lblAsSenhasNaoConferem.setVisible(false);
		contentPane.add(lblAsSenhasNaoConferem);

		alterarTema();

		txtSenhaAtual.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {

				case (KeyEvent.VK_DOWN):
					txtNovaSenha.requestFocus();

					break;

				case (KeyEvent.VK_F1):
					testarSenhas();
					break;
				case (KeyEvent.VK_F2):
					voltar();
					break;

				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				testarSemelhanca();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				testarSemelhanca();
			}
		});

		txtNovaSenha.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {

				case (KeyEvent.VK_UP):
					txtSenhaAtual.requestFocus();

					break;

				case (KeyEvent.VK_DOWN):
					txtConfirmarSenha.requestFocus();

					break;

				case (KeyEvent.VK_F1):
					testarSenhas();
					break;
				case (KeyEvent.VK_F2):
					voltar();
					break;
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				testarSemelhanca();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				testarSemelhanca();
			}
		});
		txtConfirmarSenha.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {

				case (KeyEvent.VK_UP):
					txtNovaSenha.requestFocus();

					break;

				case (KeyEvent.VK_F1):
					testarSenhas();
					break;
				case (KeyEvent.VK_F2):
					voltar();
					break;
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				testarSemelhanca();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				testarSemelhanca();
			}
		});

	}

	@SuppressWarnings("deprecation")
	private void testarSenhas() {
		try {
			if ((Perfil.getSenha().equals(txtSenhaAtual.getText()))) {
				if (txtNovaSenha.getText().equals(txtConfirmarSenha.getText())) {

					if (!txtNovaSenha.getText().trim().isEmpty()) {
						if (txtNovaSenha.getText().length() > 12) {
							JOptionPane.showMessageDialog(this,
									"A senha deve ter no máximo 12 caracteres.\nTente novamente.", "Erro",
									JOptionPane.ERROR_MESSAGE);
						} else {
							enviarSenha(txtNovaSenha.getText());
							dispose();
						}
					} else {
						JOptionPane.showMessageDialog(contentPane, "A senha não pode ser vazia!");
						txtSenhaAtual.setText("");
						txtNovaSenha.setText("");
						txtConfirmarSenha.setText("");
						txtSenhaAtual.requestFocus();
					}

				} else {
					JOptionPane.showMessageDialog(contentPane, "As senhas não conferem!");
					txtSenhaAtual.setText("");
					txtNovaSenha.setText("");
					txtConfirmarSenha.setText("");
					txtSenhaAtual.requestFocus();
				}
			} else {
				JOptionPane.showMessageDialog(contentPane, "Senha atual inválida!");
				txtSenhaAtual.setText("");
				txtNovaSenha.setText("");
				txtConfirmarSenha.setText("");
				txtSenhaAtual.requestFocus();
			}
		} catch (HeadlessException e1) {
			e1.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	private void testarSemelhanca() {
		if (txtNovaSenha.getText().equals(txtConfirmarSenha.getText())) {
			lblAsSenhasNaoConferem.setVisible(false);
		} else {
			lblAsSenhasNaoConferem.setVisible(true);
		}
	}

	public static void enviarSenha(String senha) {

		TelaConfigurarPerfil.alterarSenha(senha);

	}

	private void voltar() {
		dispose();
		TelaConfigurarPerfil.main(null);
	}

	public boolean verificarSenha(String usuario, String senha) throws HeadlessException, SQLException {

		if (Perfil.entrar(usuario, senha)) {
			JOptionPane.showMessageDialog(this, "Senha Alterada com sucesso ", "", JOptionPane.PLAIN_MESSAGE);
			return true;
		} else {
			return false;
		}

	}
}