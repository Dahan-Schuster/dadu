package telasCadastro;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import objetos.Perfil;
import objetos.Pessoa;
import telasMenu.MenuContatos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class TelaAdicionarContato extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panel;
	private JTextField txtNome;
	private JTextField txtSobrenome;
	private JTextField txtEmail1;
	private JTextField txtEmail2;
	private JFormattedTextField txtTelefone1;
	private JFormattedTextField txtTelefone2;
	private JTextField txtApelido;
	private JLabel lblSalvar;
	private JLabel lblCancelar;
	private JLabel lblNome, lblEmail, lblEmail_2, lblTelefone, lblTelefone_2, lblApelido, lblGrupo, lblSobrenome;
	private static JButton btnGrupo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAdicionarContato frame = new TelaAdicionarContato();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void Escolhergrupo(String grupo) {

		btnGrupo.setText(grupo);

	}

	private void alterarTema() {
		if (Perfil.getTema().equals("claro")) {
			contentPane.setBackground(new Color(220, 220, 220));
			panel.setBackground(new Color(200, 200, 200));

			lblNome.setForeground(Color.BLACK);
			lblSobrenome.setForeground(Color.BLACK);
			lblEmail.setForeground(Color.BLACK);
			lblEmail_2.setForeground(Color.BLACK);
			lblTelefone.setForeground(Color.BLACK);
			lblTelefone_2.setForeground(Color.BLACK);
			lblApelido.setForeground(Color.BLACK);
			lblGrupo.setForeground(Color.BLACK);
			lblSobrenome.setForeground(Color.BLACK);
			lblSalvar.setForeground(Color.BLACK);
			lblCancelar.setForeground(Color.BLACK);

			btnGrupo.setForeground(Color.black);
			btnGrupo.setBackground(Color.LIGHT_GRAY);

		} else if (Perfil.getTema().equals("escuro")) {
			contentPane.setBackground(new Color(0, 6, 45));
			panel.setBackground(new Color(0, 6, 30));

			lblNome.setForeground(Color.WHITE);
			lblSobrenome.setForeground(Color.WHITE);
			lblEmail.setForeground(Color.WHITE);
			lblEmail_2.setForeground(Color.WHITE);
			lblTelefone.setForeground(Color.WHITE);
			lblTelefone_2.setForeground(Color.WHITE);
			lblApelido.setForeground(Color.WHITE);
			lblGrupo.setForeground(Color.WHITE);
			lblSobrenome.setForeground(Color.WHITE);
			lblSalvar.setForeground(Color.WHITE);
			lblCancelar.setForeground(Color.WHITE);

			btnGrupo.setForeground(Color.WHITE);
			btnGrupo.setBackground(new Color(0, 6, 120));
		}
	}

	/**
	 * Create the frame.
	 * 
	 * @throws ParseException
	 * 
	 */
	public TelaAdicionarContato() throws ParseException {
		setResizable(false);
		setTitle("Contatos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(15, 20, 420, 200);
		contentPane.add(panel);
		panel.setLayout(null);

		lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNome.setBounds(10, 11, 50, 14);
		panel.add(lblNome);

		txtNome = new JTextField();
		txtNome.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtNome.setBounds(60, 10, 118, 20);
		panel.add(txtNome);
		txtNome.setColumns(10);

		lblSobrenome = new JLabel("Sobrenome:");
		lblSobrenome.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblSobrenome.setBounds(190, 11, 90, 14);
		panel.add(lblSobrenome);

		txtSobrenome = new JTextField();
		txtSobrenome.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtSobrenome.setBounds(280, 8, 118, 20);
		panel.add(txtSobrenome);
		txtSobrenome.setColumns(10);

		lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblEmail.setBounds(10, 78, 50, 14);
		panel.add(lblEmail);

		txtEmail1 = new JTextField();
		txtEmail1.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtEmail1.setBounds(75, 74, 330, 22);
		panel.add(txtEmail1);
		txtEmail1.setColumns(10);

		lblEmail_2 = new JLabel("E-mail 2:");
		lblEmail_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblEmail_2.setBounds(10, 113, 65, 14);
		panel.add(lblEmail_2);

		txtEmail2 = new JTextField();
		txtEmail2.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtEmail2.setBounds(75, 110, 330, 20);
		panel.add(txtEmail2);
		txtEmail2.setColumns(10);

		lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblTelefone.setBounds(10, 141, 70, 14);
		panel.add(lblTelefone);

		MaskFormatter masktel1 = new MaskFormatter("(##)#####-####");
		masktel1.setPlaceholderCharacter('X');

		txtTelefone1 = new JFormattedTextField(masktel1);
		txtTelefone1.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtTelefone1.setBounds(95, 140, 130, 20);
		panel.add(txtTelefone1);
		txtTelefone1.setColumns(10);

		lblTelefone_2 = new JLabel("Telefone 2:");
		lblTelefone_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblTelefone_2.setBounds(10, 170, 85, 14);
		panel.add(lblTelefone_2);

		MaskFormatter masktel2 = new MaskFormatter("(##)#####-####");
		masktel2.setPlaceholderCharacter('X');

		txtTelefone2 = new JFormattedTextField(masktel2);
		txtTelefone2.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtTelefone2.setBounds(95, 170, 130, 20);
		panel.add(txtTelefone2);
		txtTelefone2.setColumns(10);

		lblApelido = new JLabel("Apelido:");
		lblApelido.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblApelido.setBounds(10, 43, 60, 14);
		panel.add(lblApelido);

		txtApelido = new JTextField();
		txtApelido.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtApelido.setBounds(70, 42, 108, 20);
		panel.add(txtApelido);
		txtApelido.setColumns(10);

		lblGrupo = new JLabel("Grupo:");
		lblGrupo.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblGrupo.setBounds(210, 43, 50, 14);
		panel.add(lblGrupo);

		btnGrupo = new JButton("Amigos");
		btnGrupo.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnGrupo.setBounds(280, 40, 118, 23);
		panel.add(btnGrupo);

		lblSalvar = new JLabel("Salvar");
		lblSalvar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblSalvar.setBounds(10, 240, 46, 14);
		contentPane.add(lblSalvar);

		lblCancelar = new JLabel("Cancelar");
		lblCancelar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblCancelar.setBounds(370, 240, 70, 14);
		contentPane.add(lblCancelar);

		alterarTema();

		txtNome.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case (KeyEvent.VK_RIGHT):
					txtSobrenome.requestFocus();
					break;
				case (KeyEvent.VK_DOWN):
					txtApelido.requestFocus();
					break;
				case (KeyEvent.VK_F2):
					dispose();
					new MenuContatos().setVisible(true);
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

				case (KeyEvent.VK_LEFT):
					txtNome.requestFocus();
					break;
				case (KeyEvent.VK_RIGHT):
					txtApelido.requestFocus();
					break;

				case (KeyEvent.VK_DOWN):
					btnGrupo.requestFocus();
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
				case (KeyEvent.VK_F2):
					dispose();
					new MenuContatos().setVisible(true);
					break;
				}
			}
		});

		txtApelido.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {

				case (KeyEvent.VK_UP):
					txtNome.requestFocus();
					break;
				case (KeyEvent.VK_RIGHT):
					btnGrupo.requestFocus();
					break;

				case (KeyEvent.VK_DOWN):

					txtEmail1.requestFocus();
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
				case (KeyEvent.VK_F2):
					dispose();
					new MenuContatos().setVisible(true);
					break;
				}
			}
		});

		btnGrupo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				new TelaEscolherGrupo((byte) 0).setVisible(true);

			}
		});

		btnGrupo.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {

				case (KeyEvent.VK_UP):
					txtSobrenome.requestFocus();
					break;
				case (KeyEvent.VK_DOWN):

					txtEmail1.requestFocus();
					break;
				case (KeyEvent.VK_LEFT):

					txtApelido.requestFocus();
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
				case (KeyEvent.VK_F2):
					dispose();
					new MenuContatos().setVisible(true);
					break;
				}
			}
		});

		txtEmail1.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {

				case (KeyEvent.VK_UP):
					txtApelido.requestFocus();
					break;
				case (KeyEvent.VK_DOWN):

					txtEmail2.requestFocus();
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
				case (KeyEvent.VK_F2):
					dispose();
					new MenuContatos().setVisible(true);
					break;
				}
			}
		});

		txtEmail2.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {

				case (KeyEvent.VK_UP):
					txtEmail1.requestFocus();
					break;
				case (KeyEvent.VK_DOWN):

					txtTelefone1.requestFocus();
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
				case (KeyEvent.VK_F2):
					dispose();
					new MenuContatos().setVisible(true);
					break;
				}
			}
		});
		txtTelefone1.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {

				case (KeyEvent.VK_UP):
					txtEmail2.requestFocus();
					break;
				case (KeyEvent.VK_RIGHT):

					txtTelefone2.requestFocus();
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
				case (KeyEvent.VK_F2):
					dispose();
					new MenuContatos().setVisible(true);
					break;
				}
			}
		});

		txtTelefone2.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {

				case (KeyEvent.VK_UP):
					txtEmail1.requestFocus();
					break;
				case (KeyEvent.VK_LEFT):

					txtTelefone1.requestFocus();
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
				case (KeyEvent.VK_F2):
					dispose();
					new MenuContatos().setVisible(true);
					break;
				}
			}

		});
		
		btnGrupo.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnGrupo.setForeground(Color.black);
					btnGrupo.setBackground(Color.LIGHT_GRAY);
				} else {

					btnGrupo.setForeground(Color.WHITE);
					btnGrupo.setBackground(new Color(0, 6, 120));
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnGrupo.setForeground(Color.black);
					btnGrupo.setBackground(Color.white);
				} else {

					btnGrupo.setForeground(Color.WHITE);
					btnGrupo.setBackground(new Color(0, 6, 180));
				}
			}
		});

	}

	private void coletarInformacoes() throws SQLException, IOException {
		String[] valores = new String[9];

		if (txtNome.getText().trim().isEmpty() || txtTelefone1.getText().contains("X")) {
			JOptionPane.showMessageDialog(null,
					"É obrigatório que seja preenchidos os campos: Nome ou Apelido, e Telefone ", "Erro",
					JOptionPane.INFORMATION_MESSAGE);

		} else {

			valores[0] = txtNome.getText();
			if (txtSobrenome.getText().trim().isEmpty()) {

				valores[1] = " ";
			} else {

				valores[1] = txtSobrenome.getText();
			}
			if (txtApelido.getText().trim().isEmpty()) {

				valores[2] = " ";
			} else {

				valores[2] = txtApelido.getText();
			}

			if (btnGrupo.getText().equals("Família")) {
				valores[3] = "F";

			}
			if (btnGrupo.getText().equals("Favoritos")) {
				valores[3] = "P";

			}
			if (btnGrupo.getText().equals("Trabalho")) {
				valores[3] = "T";

			}
			if (btnGrupo.getText().equals("Amigos")) {
				valores[3] = "A";

			}

			if (txtEmail1.getText().trim().isEmpty()) {

				valores[4] = " ";
			} else {

				valores[4] = txtEmail1.getText();
			}
			if (txtEmail2.getText().trim().isEmpty()) {

				valores[5] = " ";
			} else {

				valores[5] = txtEmail2.getText();
			}

			valores[6] = txtTelefone1.getText();

			if (txtTelefone2.getText().contains("X")) {

				valores[7] = " ";
			} else {

				valores[7] = txtTelefone2.getText();
			}
			valores[8] = "" + Perfil.getCodigo();

			if (Pessoa.salvarContato(valores)) {
				JOptionPane.showMessageDialog(null, "Contato Adicionado com sucesso!", "Contato Adicionado",
						JOptionPane.INFORMATION_MESSAGE);

			} else {
				JOptionPane.showMessageDialog(null, "Erro ao Adicionar!", "Erro", JOptionPane.INFORMATION_MESSAGE);

			}
		}

	}
}