package telasFinais;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

import objetos.DataBase;
import objetos.Perfil;
import objetos.Pessoa;
import telasCadastro.TelaEscolherGrupo;
import telasMain.TelaContatos;

import javax.swing.JButton;
import javax.swing.JPopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TelaVisualizarContato extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panel;
	private static JTextField txtNome;
	private static JTextField txtApelido;
	private static JTextField txtSobrenome;
	private static JButton btnGrupo;
	private static JTextField txtEmail1;
	private static JTextField txtEmail2;
	private static JTextField txtTelefone1;
	private static JTextField txtTelefone2;
	private static ResultSet informacoes;
	private static JPopupMenu popupMenu;

	private static JLabel lblNome, lblSobrenome, lblEmail, lblEmail_2, lblGrupo, lblApelido, lblTelefone, lblTelefone_2,
			lblVoltar, lblOpes, lblSalvar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaVisualizarContato frame = new TelaVisualizarContato();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void Escolhergrupo(String grupo) {
		try {

			btnGrupo.setText(grupo);

		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	private void alterarTema() {
		if (Perfil.getTema().equals("claro")) {
			contentPane.setBackground(new Color(220, 220, 220));
			panel.setBackground(new Color(200, 200, 200));
			lblNome.setForeground(Color.black);
			lblSobrenome.setForeground(Color.black);
			lblEmail.setForeground(Color.black);
			lblEmail_2.setForeground(Color.black);
			lblGrupo.setForeground(Color.black);
			lblApelido.setForeground(Color.black);
			lblTelefone.setForeground(Color.black);
			lblTelefone_2.setForeground(Color.black);
			lblVoltar.setForeground(Color.black);
			lblOpes.setForeground(Color.black);
			lblSalvar.setForeground(Color.black);

			btnGrupo.setBackground(Color.LIGHT_GRAY);
			btnGrupo.setForeground(Color.BLACK);

		} else if (Perfil.getTema().equals("escuro")) {
			contentPane.setBackground(new Color(0, 6, 45));
			panel.setBackground(new Color(0, 6, 60));
			lblNome.setForeground(Color.white);
			lblSobrenome.setForeground(Color.white);
			lblEmail.setForeground(Color.white);
			lblEmail_2.setForeground(Color.white);
			lblGrupo.setForeground(Color.white);
			lblApelido.setForeground(Color.white);
			lblTelefone.setForeground(Color.white);
			lblTelefone_2.setForeground(Color.white);
			lblVoltar.setForeground(Color.white);
			lblOpes.setForeground(Color.white);
			lblSalvar.setForeground(Color.white);

			btnGrupo.setBackground(Color.GRAY);
			btnGrupo.setForeground(Color.WHITE);
		}
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 * @throws CommunicationsException
	 * @throws MySQLNonTransientConnectionException
	 * @throws MySQLSyntaxErrorException
	 */
	public TelaVisualizarContato() throws MySQLSyntaxErrorException, MySQLNonTransientConnectionException,
			CommunicationsException, SQLException {
		requestFocus();
		setResizable(false);
		setTitle("Informações do Contato");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBounds(15, 20, 420, 180);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel);
		panel.setLayout(null);

		lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNome.setBounds(10, 11, 50, 14);
		panel.add(lblNome);

		lblSobrenome = new JLabel("Sobrenome:");
		lblSobrenome.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblSobrenome.setBounds(190, 11, 90, 14);
		panel.add(lblSobrenome);

		lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblEmail.setBounds(10, 78, 60, 14);
		panel.add(lblEmail);

		lblEmail_2 = new JLabel("E-mail 2:");
		lblEmail_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblEmail_2.setBounds(10, 113, 65, 14);
		panel.add(lblEmail_2);

		lblTelefone = new JLabel("Tel:");
		lblTelefone.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblTelefone.setBounds(10, 147, 66, 14);
		panel.add(lblTelefone);

		lblTelefone_2 = new JLabel("Tel 2:");
		lblTelefone_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblTelefone_2.setBounds(215, 147, 72, 14);
		panel.add(lblTelefone_2);

		lblApelido = new JLabel("Apelido:");
		lblApelido.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblApelido.setBounds(10, 43, 60, 14);
		panel.add(lblApelido);

		lblGrupo = new JLabel("Grupo:");
		lblGrupo.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblGrupo.setBounds(194, 43, 50, 14);
		panel.add(lblGrupo);
		

		txtNome = new JTextField();
		txtNome.setEditable(false);
		txtNome.setBounds(60, 10, 120, 20);
		panel.add(txtNome);
		txtNome.setColumns(10);

		txtApelido = new JTextField();
		txtApelido.setEditable(false);
		txtApelido.setBounds(70, 42, 110, 20);
		panel.add(txtApelido);
		txtApelido.setColumns(10);

		txtSobrenome = new JTextField();
		txtSobrenome.setEditable(false);
		txtSobrenome.setBounds(285, 10, 120, 20);
		panel.add(txtSobrenome);
		txtSobrenome.setColumns(10);

		btnGrupo = new JButton("Grupo");
		btnGrupo.setBackground(Color.WHITE);
		btnGrupo.setEnabled(false);
		btnGrupo.setFocusable(false);
		btnGrupo.setBounds(265, 43, 139, 23);
		panel.add(btnGrupo);

		txtEmail1 = new JTextField();
		txtEmail1.setEditable(false);
		txtEmail1.setBounds(75, 77, 330, 20);
		panel.add(txtEmail1);
		txtEmail1.setColumns(10);

		txtEmail2 = new JTextField();
		txtEmail2.setEditable(false);
		txtEmail2.setBounds(75, 112, 330, 20);
		panel.add(txtEmail2);
		txtEmail2.setColumns(10);

		txtTelefone1 = new JTextField();
		txtTelefone1.setEditable(false);
		txtTelefone1.setBounds(75, 145, 130, 20);
		panel.add(txtTelefone1);
		txtTelefone1.setColumns(10);

		txtTelefone2 = new JTextField();
		txtTelefone2.setEditable(false);
		txtTelefone2.setBounds(275, 145, 130, 20);
		panel.add(txtTelefone2);
		txtTelefone2.setColumns(10);

		lblVoltar = new JLabel("Voltar");
		lblVoltar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblVoltar.setBounds(378, 236, 46, 14);
		contentPane.add(lblVoltar);

		lblOpes = new JLabel("Opções");
		lblOpes.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblOpes.setBounds(10, 238, 55, 14);
		lblOpes.setVisible(true);
		contentPane.add(lblOpes);

		lblSalvar = new JLabel("Salvar");
		lblSalvar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblSalvar.setBounds(10, 238, 55, 14);
		lblSalvar.setVisible(false);
		contentPane.add(lblSalvar);

		alterarTema();
		preencherTextFields();

		popupMenu = new JPopupMenu();
		JMenuItem menuAdd = new JMenuItem("Alterar Dados");
		popupMenu.add(menuAdd);
		menuAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				txtNome.setEditable(true);
				txtSobrenome.setEditable(true);
				txtApelido.setEditable(true);
				btnGrupo.setEnabled(true);
				btnGrupo.setFocusable(true);
				txtEmail1.setEditable(true);
				txtEmail2.setEditable(true);
				txtTelefone1.setEditable(true);
				txtTelefone2.setEditable(true);
				lblSalvar.setVisible(true);
				lblOpes.setVisible(false);

				txtNome.requestFocus();

			}

		});

		JMenuItem menuDelete = new JMenuItem("Excluir");
		popupMenu.add(menuDelete);
		menuDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				Pessoa.excluirContato(TelaContatos.codigo);
				try {
					new TelaContatos().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		txtNome.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case (KeyEvent.VK_RIGHT):
					txtSobrenome.requestFocus();
					break;
				case (KeyEvent.VK_DOWN):
					txtApelido.requestFocus();
					break;
				case (KeyEvent.VK_F1):
					eventoF1();
					break;
				case (KeyEvent.VK_F2):
					voltar();
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
					eventoF1();
					break;
				case (KeyEvent.VK_F2):
					voltar();
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
					eventoF1();
					break;
				case (KeyEvent.VK_F2):
					voltar();
					break;
				}
			}
		});

		btnGrupo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				new TelaEscolherGrupo((byte) 1).setVisible(true);

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
					eventoF1();
					break;
				case (KeyEvent.VK_F2):
					voltar();
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
					eventoF1();
					break;
				case (KeyEvent.VK_F2):
					voltar();
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
					eventoF1();
					break;
				case (KeyEvent.VK_F2):
					voltar();
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
					eventoF1();
					break;
				case (KeyEvent.VK_F2):
					voltar();
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
					eventoF1();
					break;
				case (KeyEvent.VK_F2):
					voltar();
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
		String[] valores = new String[8];

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

			// if (btnGrupo.getText().equals("Família")) {
			valores[3] = "F";

			// }
			/*
			 * if (btnGrupo.getText().equals("Favoritos")) { valores[3] = "P";
			 * 
			 * } if (btnGrupo.getText().equals("Trabalho")) { valores[3] = "T";
			 * 
			 * } if (btnGrupo.getText().equals("Amigos")) { valores[3] = "A";
			 * 
			 * }
			 */
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

			if (Pessoa.atualizarContato(valores, TelaContatos.codigo)) {
				JOptionPane.showMessageDialog(null, "Contato Alterado com sucesso!", "Contato Alterado",
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
			informacoes = DataBase.lerTabela("contatos", "cod_contato", "" + TelaContatos.codigo, "cod_usuario",
					"" + Perfil.getCodigo());

			while (informacoes.next()) {

				txtNome.setText(informacoes.getString(2));
				txtSobrenome.setText(informacoes.getString(3));
				txtApelido.setText(informacoes.getString(4));

				if (informacoes.getString(5).equals("F")) {
					btnGrupo.setText("Família");

				}
				if (informacoes.getString(5).equals("P")) {
					btnGrupo.setText("Favoritos");

				}
				if (informacoes.getString(5).equals("A")) {
					btnGrupo.setText("Amigos");

				}
				if (informacoes.getString(5).equals("T")) {
					btnGrupo.setText("Trabalho");

				}
				txtEmail1.setText(informacoes.getString(6));
				txtEmail2.setText(informacoes.getString(7));
				txtTelefone1.setText(informacoes.getString(8));
				txtTelefone2.setText(informacoes.getString(9));
			}
			DataBase.fechar();
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}

	private void eventoF1() {
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
	}

	private void voltar() {
		dispose();
		try {
			new TelaContatos().setVisible(true);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}