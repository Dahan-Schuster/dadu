package telasEditar;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

import objetos.DataBase;
import objetos.Perfil;
import telasFinais.TelaLogin;
import telasMenu.MenuConfiguracoes;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class TelaConfigurarPerfil extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panel;
	private JLabel lblNome, lblSenha, lblEmail, lblEmail_2, lblTelefone, lblTelefone_1, lblEmpresa, lblProfissao,
			lblEndereo, lblNome_1, lblSobrenome, lblOpcoes, lblVoltar;
	private JTextField txtUsuario;
	public static JPasswordField txtsenha;
	private JTextField txtEmail;
	private JTextField txtEmail2;
	private JTextField txtTelefone;
	private JTextField txtTelefone2;
	private JTextField txtEndereco;
	private JTextField txtEmpresa;
	private JTextField txtProfissao;
	private ResultSet informacoes;
	private JTextField txtNome;
	private JTextField txtSobrenome;
	private String codigoUsuario;
	private JButton btnAlterar;
	public static String senha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConfigurarPerfil frame = new TelaConfigurarPerfil();
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
			panel.setBackground(new Color(200, 200, 200));
			lblNome.setForeground(Color.black);
			lblSenha.setForeground(Color.black);
			lblEmail.setForeground(Color.black);
			lblEmail_2.setForeground(Color.black);
			lblEmpresa.setForeground(Color.black);
			lblEndereo.setForeground(Color.black);
			lblTelefone.setForeground(Color.black);
			lblTelefone_1.setForeground(Color.black);
			lblProfissao.setForeground(Color.black);
			lblNome_1.setForeground(Color.black);
			lblSobrenome.setForeground(Color.black);
			lblOpcoes.setForeground(Color.black);
			lblVoltar.setForeground(Color.black);

			btnAlterar.setForeground(Color.black);
			btnAlterar.setBackground(Color.LIGHT_GRAY);

		} else if (Perfil.getTema().equals("escuro")) {
			contentPane.setBackground(new Color(0, 6, 45));
			panel.setBackground(new Color(0, 6, 30));
			lblNome.setForeground(Color.white);
			lblSenha.setForeground(Color.white);
			lblEmail.setForeground(Color.white);
			lblEmail_2.setForeground(Color.white);
			lblEmpresa.setForeground(Color.white);
			lblEndereo.setForeground(Color.white);
			lblTelefone.setForeground(Color.white);
			lblTelefone_1.setForeground(Color.white);
			lblProfissao.setForeground(Color.white);
			lblNome_1.setForeground(Color.white);
			lblSobrenome.setForeground(Color.white);
			lblOpcoes.setForeground(Color.white);
			lblVoltar.setForeground(Color.white);

			btnAlterar.setForeground(Color.WHITE);
			btnAlterar.setBackground(new Color(0, 6, 120));
		}
	}

	public static void alterarSenha(String senha) {
		txtsenha.setText(senha);
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 * @throws CommunicationsException
	 * @throws MySQLNonTransientConnectionException
	 * @throws MySQLSyntaxErrorException
	 * @throws ParseException
	 */
	public TelaConfigurarPerfil() throws MySQLSyntaxErrorException, MySQLNonTransientConnectionException,
			CommunicationsException, SQLException, ParseException {
		setResizable(false);
		setTitle("Perfil");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(15, 11, 420, 220);
		contentPane.add(panel);
		panel.setLayout(null);

		lblNome = new JLabel("User:");
		lblNome.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNome.setBounds(10, 11, 50, 14);
		panel.add(lblNome);

		txtUsuario = new JTextField();
		txtUsuario.setEditable(false);
		txtUsuario.setFocusable(false);
		txtUsuario.setBounds(60, 10, 120, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);

		lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblSenha.setBounds(190, 11, 50, 14);
		panel.add(lblSenha);

		txtsenha = new JPasswordField();
		txtsenha.setEditable(false);
		txtsenha.setBounds(245, 10, 70, 20);
		panel.add(txtsenha);
		txtsenha.setColumns(10);

		btnAlterar = new JButton("Alterar ");
		btnAlterar.setEnabled(false);
		btnAlterar.setFocusable(false);
		btnAlterar.setVerticalAlignment(SwingConstants.TOP);
		btnAlterar.setToolTipText("");
		btnAlterar.setFont(new Font("Dialog", Font.PLAIN, 11));
		btnAlterar.setBounds(325, 9, 80, 23);
		panel.add(btnAlterar);

		lblNome_1 = new JLabel("Nome:");
		lblNome_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNome_1.setBounds(10, 38, 50, 14);
		panel.add(lblNome_1);

		txtNome = new JTextField();
		txtNome.setEditable(false);
		txtNome.setBounds(60, 36, 120, 20);
		panel.add(txtNome);
		txtNome.setColumns(10);

		lblSobrenome = new JLabel("Sobrenome:");
		lblSobrenome.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblSobrenome.setBounds(190, 38, 90, 19);
		panel.add(lblSobrenome);

		txtSobrenome = new JTextField();
		txtSobrenome.setEditable(false);
		txtSobrenome.setBounds(284, 36, 120, 20);
		panel.add(txtSobrenome);
		txtSobrenome.setColumns(10);

		lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblEmail.setBounds(10, 68, 50, 14);
		panel.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setEditable(false);
		txtEmail.setBounds(85, 66, 319, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);

		lblEmail_2 = new JLabel("E-mail 2:");
		lblEmail_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblEmail_2.setBounds(10, 102, 65, 14);
		panel.add(lblEmail_2);

		txtEmail2 = new JTextField();
		txtEmail2.setEditable(false);
		txtEmail2.setBounds(85, 100, 319, 20);
		panel.add(txtEmail2);
		txtEmail2.setColumns(10);

		MaskFormatter masktel = new MaskFormatter("(##)#####-####");
		masktel.setPlaceholderCharacter('X');

		lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblTelefone.setBounds(10, 132, 70, 14);
		panel.add(lblTelefone);

		txtTelefone = new JFormattedTextField(masktel);
		txtTelefone.setEditable(false);
		txtTelefone.setBounds(85, 130, 125, 20);
		panel.add(txtTelefone);
		txtTelefone.setColumns(10);

		lblTelefone_1 = new JLabel("Tel 2:");
		lblTelefone_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblTelefone_1.setBounds(220, 132, 79, 17);
		panel.add(lblTelefone_1);

		txtTelefone2 = new JFormattedTextField(masktel);
		txtTelefone2.setEditable(false);
		txtTelefone2.setBounds(279, 130, 125, 20);
		panel.add(txtTelefone2);
		txtTelefone2.setColumns(10);

		lblEndereo = new JLabel("Endereço:");
		lblEndereo.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblEndereo.setBounds(10, 162, 75, 14);
		panel.add(lblEndereo);

		txtEndereco = new JTextField();
		txtEndereco.setEditable(false);
		txtEndereco.setBounds(86, 160, 318, 20);
		panel.add(txtEndereco);
		txtEndereco.setColumns(10);

		lblEmpresa = new JLabel("Empresa:");
		lblEmpresa.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblEmpresa.setBounds(10, 192, 70, 14);
		panel.add(lblEmpresa);

		txtEmpresa = new JTextField();
		txtEmpresa.setEditable(false);
		txtEmpresa.setBounds(85, 190, 110, 20);
		panel.add(txtEmpresa);
		txtEmpresa.setColumns(10);

		lblProfissao = new JLabel("Profissão:");
		lblProfissao.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblProfissao.setBounds(205, 192, 75, 14);
		panel.add(lblProfissao);

		txtProfissao = new JTextField();
		txtProfissao.setEditable(false);
		txtProfissao.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtProfissao.setBounds(282, 190, 122, 20);
		panel.add(txtProfissao);
		txtProfissao.setColumns(10);

		lblOpcoes = new JLabel("Opções");
		lblOpcoes.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblOpcoes.setBounds(10, 240, 63, 14);
		contentPane.add(lblOpcoes);

		lblVoltar = new JLabel("Voltar");
		lblVoltar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblVoltar.setBounds(378, 240, 46, 14);
		contentPane.add(lblVoltar);

		alterarTema();

		JPopupMenu popupMenu = new JPopupMenu();

		JMenuItem menuAdd = new JMenuItem("Alterar Dados");
		popupMenu.add(menuAdd);
		menuAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				btnAlterar.setEnabled(true);
				btnAlterar.setFocusable(true);
				txtNome.setEditable(true);
				txtSobrenome.setEditable(true);
				txtEmail.setEditable(true);
				txtEmail2.setEditable(true);
				txtTelefone.setEditable(true);
				txtTelefone2.setEditable(true);
				txtEndereco.setEditable(true);
				txtProfissao.setEditable(true);
				txtEmpresa.setEditable(true);
				lblOpcoes.setText("Salvar");

			}

		});
		JMenuItem menuSair = new JMenuItem("Sair da Conta");
		popupMenu.add(menuSair);
		menuSair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaLogin().setVisible(true);

			}
		});

		JMenuItem menuDeleteTudo = new JMenuItem("Excluir Conta");
		popupMenu.add(menuDeleteTudo);
		menuDeleteTudo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				Perfil.excluirConta(codigoUsuario);

			}
		});
		
		
		txtNome.requestFocus();
		

		btnAlterar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				new TelaAlterarSenha().setVisible(true);

			}
		});

		btnAlterar.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {

				case (KeyEvent.VK_LEFT):
					txtNome.requestFocus();

					break;

				case (KeyEvent.VK_DOWN):
					txtSobrenome.requestFocus();

					break;

				case (KeyEvent.VK_F1):
					if (txtNome.isEditable()) {
						try {
							coletarInformacoes();
						} catch (SQLException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {

						popupMenu.show(contentPane, 0, 215);
					}
					break;
				case (KeyEvent.VK_F2):
					dispose();
					new MenuConfiguracoes().setVisible(true);
					break;

				}
			}
		});

		txtNome.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {

				case (KeyEvent.VK_UP):
					
					btnAlterar.requestFocus();
					break;

				case (KeyEvent.VK_RIGHT):
					txtSobrenome.requestFocus();

					break;
				case (KeyEvent.VK_DOWN):
					txtEmail.requestFocus();
					break;
				case (KeyEvent.VK_F1):
					if (txtNome.isEditable()) {
						try {
							coletarInformacoes();
						} catch (SQLException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {

						popupMenu.show(contentPane, 0, 215);
					}
					break;
				case (KeyEvent.VK_F2):
					dispose();
					new MenuConfiguracoes().setVisible(true);
					break;

				}
			}
		});

		txtSobrenome.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {

				case (KeyEvent.VK_UP):
					btnAlterar.requestFocus();

					break;

				case (KeyEvent.VK_LEFT):
					txtNome.requestFocus();

					break;
				case (KeyEvent.VK_DOWN):
					txtEmail.requestFocus();
					break;
				case (KeyEvent.VK_F1):
					if (txtNome.isEditable()) {
						try {
							coletarInformacoes();
						} catch (SQLException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {

						popupMenu.show(contentPane, 0, 215);
					}
					break;
				case (KeyEvent.VK_F2):
					dispose();
					new MenuConfiguracoes().setVisible(true);
					break;

				}
			}
		});
		txtEmail.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {

				case (KeyEvent.VK_UP):
					txtNome.requestFocus();

					break;

				case (KeyEvent.VK_DOWN):
					txtEmail2.requestFocus();
					break;
				case (KeyEvent.VK_F1):
					if (txtNome.isEditable()) {
						try {
							coletarInformacoes();
						} catch (SQLException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {

						popupMenu.show(contentPane, 0, 215);
					}
					break;
				case (KeyEvent.VK_F2):
					dispose();
					new MenuConfiguracoes().setVisible(true);
					break;

				}
			}
		});

		txtEmail2.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {

				case (KeyEvent.VK_UP):
					txtEmail.requestFocus();

					break;

				case (KeyEvent.VK_DOWN):
					txtTelefone.requestFocus();
					break;
				case (KeyEvent.VK_F1):
					if (txtNome.isEditable()) {
						try {
							coletarInformacoes();
						} catch (SQLException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {

						popupMenu.show(contentPane, 0, 215);
					}
					break;
				case (KeyEvent.VK_F2):
					dispose();
					new MenuConfiguracoes().setVisible(true);
					break;

				}
			}
		});
		txtTelefone.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {

				case (KeyEvent.VK_UP):
					txtEmail2.requestFocus();

					break;

				case (KeyEvent.VK_RIGHT):
					txtTelefone2.requestFocus();

					break;
				case (KeyEvent.VK_DOWN):
					txtEndereco.requestFocus();
					break;
				case (KeyEvent.VK_F1):
					if (txtNome.isEditable()) {
						try {
							coletarInformacoes();
						} catch (SQLException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {

						popupMenu.show(contentPane, 0, 215);
					}
					break;
				case (KeyEvent.VK_F2):
					dispose();
					new MenuConfiguracoes().setVisible(true);
					break;

				}
			}
		});
		txtTelefone2.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {

				case (KeyEvent.VK_UP):
					txtEmail2.requestFocus();

					break;

				case (KeyEvent.VK_LEFT):
					txtTelefone2.requestFocus();

					break;
				case (KeyEvent.VK_DOWN):
					txtEndereco.requestFocus();
					break;
				case (KeyEvent.VK_F1):
					if (txtNome.isEditable()) {
						try {
							coletarInformacoes();
						} catch (SQLException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {

						popupMenu.show(contentPane, 0, 215);
					}
					break;
				case (KeyEvent.VK_F2):
					dispose();
					new MenuConfiguracoes().setVisible(true);
					break;

				}
			}
		});
		txtEndereco.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {

				case (KeyEvent.VK_UP):
					txtTelefone.requestFocus();

					break;

				case (KeyEvent.VK_DOWN):
					txtEmpresa.requestFocus();
					break;
				case (KeyEvent.VK_F1):
					if (txtNome.isEditable()) {
						try {
							coletarInformacoes();
						} catch (SQLException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {

						popupMenu.show(contentPane, 0, 215);
					}
					break;
				case (KeyEvent.VK_F2):
					dispose();
					new MenuConfiguracoes().setVisible(true);
					break;

				}
			}
		});

		txtEmpresa.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {

				case (KeyEvent.VK_UP):
					txtEndereco.requestFocus();

					break;

				case (KeyEvent.VK_RIGHT):
					txtProfissao.requestFocus();

					break;

				case (KeyEvent.VK_F1):
					if (txtNome.isEditable()) {
						try {
							coletarInformacoes();
						} catch (SQLException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						popupMenu.show(contentPane, 0, 215);
					}
					break;
				case (KeyEvent.VK_F2):
					dispose();
					new MenuConfiguracoes().setVisible(true);
					break;

				}
			}
		});
		txtProfissao.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {

				case (KeyEvent.VK_UP):
					txtEndereco.requestFocus();

					break;

				case (KeyEvent.VK_LEFT):
					txtEmpresa.requestFocus();

					break;
				case (KeyEvent.VK_F1):
					if (txtNome.isEditable()) {
						try {
							coletarInformacoes();
						} catch (SQLException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						popupMenu.show(contentPane, 0, 215);
					}
					break;
				case (KeyEvent.VK_F2):
					dispose();
					new MenuConfiguracoes().setVisible(true);
					break;
				}
			}
		});
		
		btnAlterar.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnAlterar.setForeground(Color.black);
					btnAlterar.setBackground(Color.LIGHT_GRAY);
				} else {

					btnAlterar.setForeground(Color.WHITE);
					btnAlterar.setBackground(new Color(0, 6, 120));
				}
				
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnAlterar.setForeground(Color.black);
					btnAlterar.setBackground(Color.white);
				} else {

					btnAlterar.setForeground(Color.WHITE);
					btnAlterar.setBackground(new Color(0, 6, 180));
				}	
			}
		});
		
		preencherTextFields();

	}

	// Adicionando as informa??es no campo
	@SuppressWarnings("deprecation")
	private void coletarInformacoes() throws SQLException, IOException {
		String[] valores = new String[11];

		if (txtUsuario.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "É obrigatório que seja preenchido o campo do Usuario ", "Erro",
					JOptionPane.INFORMATION_MESSAGE);

		} else {

			valores[0] = txtUsuario.getText();
			valores[1] = txtsenha.getText();
			if (txtNome.getText().trim().isEmpty()) {

				valores[2] = " ";
			} else {

				valores[2] = txtNome.getText();
			}

			if (txtSobrenome.getText().trim().isEmpty()) {

				valores[3] = " ";
			} else {

				valores[3] = txtSobrenome.getText();
			}

			if (txtEmail.getText().trim().isEmpty()) {

				valores[4] = " ";
			} else {

				valores[4] = txtEmail.getText();
			}
			if (txtEmail2.getText().trim().isEmpty()) {

				valores[5] = " ";
			} else {

				valores[5] = txtEmail2.getText();
			}

			if (txtTelefone.getText().contains("X")) {

				valores[6] = " ";
			} else {

				valores[6] = txtTelefone.getText();
			}

			if (txtTelefone2.getText().contains("X")) {

				valores[7] = " ";
			} else {

				valores[7] = txtTelefone2.getText();
			}

			if (txtEndereco.getText().trim().isEmpty()) {

				valores[8] = " ";
			} else {

				valores[8] = txtEndereco.getText();
			}

			if (txtProfissao.getText().trim().isEmpty()) {

				valores[9] = " ";
			} else {

				valores[9] = txtProfissao.getText();
			}

			if (txtEmpresa.getText().trim().isEmpty()) {

				valores[10] = " ";
			} else {

				valores[10] = txtEmpresa.getText();
			}

			if (Perfil.atualizarPerfil(valores, codigoUsuario)) {
				JOptionPane.showMessageDialog(null, "Informações alteradas com sucesso!", "Informações Alteradas",
						JOptionPane.INFORMATION_MESSAGE);

			} else {
				JOptionPane.showMessageDialog(null, "Erro ao Alterar informações!", "Erro",
						JOptionPane.INFORMATION_MESSAGE);

			}
		}

	}

	
	private void preencherTextFields() {
		try {
		DataBase.conectar();
		informacoes = DataBase.lerTabela("usuarios", "codigo", ""+Perfil.getCodigo());

		while (informacoes.next()) {
			codigoUsuario = informacoes.getString(1);
			txtUsuario.setText(informacoes.getString(2));
			txtsenha.setText(informacoes.getString(3));
			txtNome.setText(informacoes.getString(4));
			txtSobrenome.setText(informacoes.getString(5));
			txtEmail.setText(informacoes.getString(6));
			txtEmail2.setText(informacoes.getString(7));
			txtTelefone.setText(informacoes.getString(8));
			txtTelefone2.setText(informacoes.getString(9));
			txtEndereco.setText(informacoes.getString(10));
			txtProfissao.setText(informacoes.getString(11));
			txtEmpresa.setText(informacoes.getString(12));
			
			txtNome.requestFocus();
		}
		DataBase.fechar();
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}

}
