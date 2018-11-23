package telasMain;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import objetos.DataBase;
import objetos.Perfil;
import objetos.Pessoa;
import telasCadastro.TelaAdicionarContato;
import telasFinais.TelaVisualizarContato;
import telasMenu.MenuContatos;

import java.awt.List;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPopupMenu;

public class TelaContatos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ResultSet query;
	public static String codigo;

	private static JLabel lblAdicionar, lblEditar, lblVoltar;
	private static List listaContatos;
	private static JPopupMenu popupMenu, popupExibir, popupOrdenar;
	private static JMenuItem ordemAlfabeticaASC, ordemAlfabeticaDESC, ordemGrupo;
	private static JMenuItem grupoA, grupoF, grupoP, grupoT, todosContatos, menuVoltar;
	private static JMenuItem menuAdd, menuDelete, menuDeleteTudo, menuOrdenar, menuExibir;

	private ArrayList<String> codContatos = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaContatos frame = new TelaContatos();
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
			lblAdicionar.setForeground(Color.BLACK);
			lblEditar.setForeground(Color.BLACK);
			lblVoltar.setForeground(Color.BLACK);

			listaContatos.setBackground(Color.LIGHT_GRAY);
			listaContatos.setForeground(Color.BLACK );

		} else if (Perfil.getTema().equals("escuro")) {
			contentPane.setBackground(new Color(0, 6, 45));
			lblAdicionar.setForeground(Color.WHITE);
			lblEditar.setForeground(Color.WHITE);
			lblVoltar.setForeground(Color.WHITE);

			listaContatos.setBackground(Color.GRAY);
			listaContatos.setForeground(Color.WHITE);
		}
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public TelaContatos() throws SQLException {
		setResizable(false);
		setTitle("Contatos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblAdicionar = new JLabel("Opções");
		lblAdicionar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblAdicionar.setBounds(10, 240, 60, 14);
		contentPane.add(lblAdicionar);

		lblEditar = new JLabel("Visualizar");
		lblEditar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblEditar.setBounds(180, 240, 80, 14);
		contentPane.add(lblEditar);

		lblVoltar = new JLabel("Voltar");
		lblVoltar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblVoltar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVoltar.setBounds(380, 240, 46, 14);
		contentPane.add(lblVoltar);

		listaContatos = new List();

		preencherListaContatos();

		listaContatos.setFont(new Font("Dialog", Font.PLAIN, 14));
		listaContatos.setBounds(15, 10, 420, 200);
		listaContatos.requestFocus();
		listaContatos.select(0);
		contentPane.add(listaContatos);

		popupOrdenar = new JPopupMenu();

		ordemAlfabeticaASC = new JMenuItem("Nome (Crescente)");
		popupOrdenar.add(ordemAlfabeticaASC);
		ordemAlfabeticaASC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Ordenar em ordem alfabética A-Z
				ordenarAlf(true);
			}

		});

		ordemAlfabeticaDESC = new JMenuItem("Nome (Decrescente)");
		popupOrdenar.add(ordemAlfabeticaDESC);
		ordemAlfabeticaDESC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Ordenar em ordem alfabética Z-A
				ordenarAlf(false);
			}

		});
		ordemGrupo = new JMenuItem("Grupo");
		popupOrdenar.add(ordemGrupo);
		ordemGrupo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Lista os contatos com seus grupos
				ordenarGrupo();
			}

		});

		popupExibir = new JPopupMenu();

		grupoA = new JMenuItem("Grupo Amigos");
		popupExibir.add(grupoA);
		grupoA.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Lista apenas os contatos do grupo Amigos
				exibirApenasGrupo("A");
			}

		});
		grupoF = new JMenuItem("Grupo Família");
		popupExibir.add(grupoF);
		grupoF.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Lista apenas os contatos do grupo Familia
				exibirApenasGrupo("F");
			}

		});
		grupoP = new JMenuItem("Grupo Favoritos");
		popupExibir.add(grupoP);
		grupoP.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Lista apenas os contatos do grupo Favoritos
				exibirApenasGrupo("P");
			}

		});
		grupoT = new JMenuItem("Grupo Trabalho");
		popupExibir.add(grupoT);
		grupoT.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Lista apenas os contatos do grupo Trabalho
				exibirApenasGrupo("T");
			}

		});
		todosContatos = new JMenuItem("Todos os contatos");
		popupExibir.add(todosContatos);
		todosContatos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				exibirTudo();
			}

		});
		
		menuVoltar = new JMenuItem("Voltar");
		menuVoltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				popupMenu.show(contentPane, 0, 170);
				
			}
		});

		popupMenu = new JPopupMenu();

		menuAdd = new JMenuItem("Adicionar");
		popupMenu.add(menuAdd);
		menuAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					new TelaAdicionarContato().setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		menuDelete = new JMenuItem("Excluir");
		popupMenu.add(menuDelete);
		menuDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

				try {
					Pessoa.excluirContato(codContatos.get(listaContatos.getSelectedIndex()));
					dispose();
					new TelaContatos().setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (IndexOutOfBoundsException e2) {
					JOptionPane.showMessageDialog(null, "Você deve selecionar um item.");
				}

			}
		});

		menuDeleteTudo = new JMenuItem("Excluir Tudo");
		popupMenu.add(menuDeleteTudo);
		menuDeleteTudo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					Pessoa.excluirTodosContatos();
					dispose();
					new TelaContatos().setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (IndexOutOfBoundsException e2) {
					JOptionPane.showMessageDialog(null, "Você deve selecionar um item.");

				}
			}

		});

		menuOrdenar = new JMenuItem("Ordenar Por ");
		popupMenu.add(menuOrdenar);
		menuOrdenar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					popupOrdenar.add(menuVoltar);
					popupOrdenar.show(contentPane, 0, 190);

				} catch (IndexOutOfBoundsException e2) {
					JOptionPane.showMessageDialog(null, "Você deve selecionar um item.");

				}
			}

		});
		menuExibir = new JMenuItem("Exibir");
		popupMenu.add(menuExibir);
		menuExibir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					popupExibir.add(menuVoltar);
					popupExibir.show(contentPane, 0, 155);

				} catch (IndexOutOfBoundsException e2) {
					JOptionPane.showMessageDialog(null, "Você deve selecionar um item.");

				}
			}
		});

		alterarTema();

		// EVENTOS

		listaContatos.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {

				case KeyEvent.VK_SPACE:

					try {
						// TelaVisualizarContato.EnviarInformacoesDeContato(codContatos.get(listaContatos.getSelectedIndex()));
						codigo = codContatos.get(listaContatos.getSelectedIndex());
						dispose();
						new TelaVisualizarContato().setVisible(true);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (IndexOutOfBoundsException i) {
						doNothing();
					}

					break;
				case KeyEvent.VK_BACK_SPACE:
					dispose();
					TelaInicio.main(null);
					break;
				case KeyEvent.VK_F2:
					dispose();
					MenuContatos.main(null);
					break;
				case KeyEvent.VK_F1:
					popupMenu.show(contentPane, 0, 170);
					break;
				}
			}

		});
	}

	// MÉTODOS DE EXIBIÇÃO

	private void preencherListaContatos() {
		try {

			DataBase.conectar();
			query = DataBase.lerTabela("contatos", "cod_usuario", "" + Perfil.getCodigo());

			while (query.next()) {
				this.codContatos.add(query.getString(1));

				listaContatos.add(query.getString(2));
			}
			DataBase.fechar();
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}

	private void ordenarAlf(boolean a) {
		try {

			DataBase.conectar();
			query = DataBase.lerTabela("contatos", "cod_usuario", "" + Perfil.getCodigo(), "nome", a);

			codContatos.clear();
			listaContatos.removeAll();

			while (query.next()) {
				listaContatos.add(query.getString(2));
				codContatos.add(query.getString(1));
			}
			DataBase.fechar();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void ordenarGrupo() {

		boolean a = false;
		try {

			DataBase.conectar();
			query = DataBase.lerTabela("contatos", "cod_usuario", "" + Perfil.getCodigo(), "grupo", a);

			codContatos.clear();
			listaContatos.removeAll();
			while (query.next()) {
				if (query.getString(5).equals("A")) {
					listaContatos.add(" |   AMIGOS  | " + query.getString(2));

					codContatos.add(query.getString(1));
				}
				if (query.getString(5).equals("F")) {
					listaContatos.add(" |  FAMÍLIA  | " + query.getString(2));

					codContatos.add(query.getString(1));
				}
				if (query.getString(5).equals("P")) {
					listaContatos.add(" | FAVORITOS | " + query.getString(2));

					codContatos.add(query.getString(1));
				}
				if (query.getString(5).equals("T")) {
					listaContatos.add(" |  TRABALHO | " + query.getString(2));

					codContatos.add(query.getString(1));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void exibirApenasGrupo(String grupo) {
		listaContatos.removeAll();
		codContatos.clear();
		try {

			DataBase.conectar();
			query = DataBase.lerTabela("contatos", "grupo", grupo, "cod_usuario", ""+Perfil.getCodigo());

			while (query.next()) {
				listaContatos.add(query.getString(2));
				codContatos.add(query.getString(1));

			}
			DataBase.fechar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void exibirTudo() {

		listaContatos.removeAll();
		codContatos.clear();

		preencherListaContatos();
	}

	// FIM MÉTODOS EXIBIÇÃO

	private void doNothing() {
		// *Doing nothing*
		// What more did you think that method should do? :v
		// It's here just to keep the code beautiful,
		// clean of these curly brackets "{ }" with nothin' in there.
		// Even his method is full of comments just to fill it out, lol
		// Act how it had some useful code, witch does not.
	}
}