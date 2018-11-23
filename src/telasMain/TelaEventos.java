package telasMain;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import objetos.DataBase;
import objetos.Evento;
import objetos.Perfil;
import telasCadastro.TelaAdicionarEvento;
import telasFinais.TelaVisualizarEvento;
import telasMenu.MenuFerramentas;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class TelaEventos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private ResultSet query;
	private ArrayList<String> codEventos = new ArrayList<>();
	private List listaEventos;
	private JPopupMenu popupOrdenar;
	private JMenuItem ordemAlfabeticaASC;
	private JMenuItem ordemAlfabeticaDESC;
	private JMenuItem menuVoltar;
	private JPopupMenu popupMenu;
	private JMenuItem menuAdd;
	private JMenuItem menuDelete;
	private JMenuItem menuDeleteTudo;
	private JMenuItem menuOrdenar;
	private JLabel lblOpecoes, lblVoltar, lblVisualizar;

	public static String codigo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEventos frame = new TelaEventos(0);
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
			lblOpecoes.setForeground(Color.BLACK);
			lblVoltar.setForeground(Color.BLACK);
			lblVisualizar.setForeground(Color.BLACK);
			
			listaEventos.setBackground(Color.LIGHT_GRAY);
			listaEventos.setForeground(Color.black);
			
		} else if (Perfil.getTema().equals("escuro")) {
			contentPane.setBackground(new Color(0, 6, 45));
			lblOpecoes.setForeground(Color.white);
			lblVoltar.setForeground(Color.white);
			lblVisualizar.setForeground(Color.white);
			
			listaEventos.setBackground(Color.GRAY);
			listaEventos.setForeground(Color.white);
			

		}
	}

	/**
	 * Create the frame.
	 * 
	 * @param caller : tela que instanciou a TelaEventos. 0 -> telas em geral; 1 -> tela Calendário;
	 */
	public TelaEventos(int caller) {
		setTitle("Eventos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblOpecoes = new JLabel("Op\u00E7\u00F5es");
		lblOpecoes.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblOpecoes.setBounds(10, 240, 58, 14);
		contentPane.add(lblOpecoes);

		lblVoltar = new JLabel("Voltar");
		lblVoltar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblVoltar.setBounds(378, 236, 46, 14);
		contentPane.add(lblVoltar);

		listaEventos = new List();
		listaEventos.setFont(new Font("Dialog", Font.PLAIN, 14));
		listaEventos.setBounds(15, 20, 420, 200);
		contentPane.add(listaEventos);

		
		preencherListaEventos();

		listaEventos.select(0);

		lblVisualizar = new JLabel("Visualizar");
		lblVisualizar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblVisualizar.setBounds(195, 240, 75, 14);
		contentPane.add(lblVisualizar);

		alterarTema();

		popupOrdenar = new JPopupMenu();

		ordemAlfabeticaASC = new JMenuItem("Titulo (Crescente)");
		popupOrdenar.add(ordemAlfabeticaASC);
		ordemAlfabeticaASC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Ordenar em ordem alfabética A-Z
				ordenarAlf(true);
			}

		});

		ordemAlfabeticaDESC = new JMenuItem("Titulo (Decrescente)");
		popupOrdenar.add(ordemAlfabeticaDESC);
		ordemAlfabeticaDESC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Ordenar em ordem alfabética Z-A
				ordenarAlf(false);
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

		menuAdd = new JMenuItem("Adicionar Evento");
		popupMenu.add(menuAdd);
		menuAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					new TelaAdicionarEvento().setVisible(true);
				} catch (ParseException e1) {
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
					Evento.excluirEvento(codEventos.get(listaEventos.getSelectedIndex()));
					preencherListaEventos();
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
					Evento.excluirTodosEventos();
					preencherListaEventos();
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

		listaEventos.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {

				case KeyEvent.VK_SPACE:

					try {
						// TelaVisualizarContato.EnviarInformacoesDeContato(codContatos.get(listaContatos.getSelectedIndex()));
						codigo = codEventos.get(listaEventos.getSelectedIndex());
						dispose();
						new TelaVisualizarEvento().setVisible(true);
					} catch (IndexOutOfBoundsException | ParseException i) {
						JOptionPane.showMessageDialog(null, "Você deve selecionar um item.");

					}

					break;
				case KeyEvent.VK_BACK_SPACE:
					dispose();
					TelaInicio.main(null);
					break;
				case KeyEvent.VK_F2:
					dispose();
					if (caller == 0) {
						MenuFerramentas.main(null);
					} else if (caller == 1) {
						TelaCalendario.main(null);
					}
					break;
				case KeyEvent.VK_F1:
					popupMenu.show(contentPane, 0, 170);
					break;
				}
			}

		});

	}

	private void preencherListaEventos() {
		try {

			DataBase.conectar();
			query = DataBase.lerTabela("eventos", "cod_usuario", "" + Perfil.getCodigo());

			codEventos.clear();
			listaEventos.removeAll();
			while (query.next()) {
				this.codEventos.add(query.getString(1));

				listaEventos.add(query.getString(2));
			}
			DataBase.fechar();
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}

	private void ordenarAlf(boolean a) {
		try {

			DataBase.conectar();
			query = DataBase.lerTabela("eventos", "cod_usuario", "" + Perfil.getCodigo(), "titulo", a);

			codEventos.clear();
			listaEventos.removeAll();

			while (query.next()) {
				listaEventos.add(query.getString(2));
				codEventos.add(query.getString(1));
			}
			DataBase.fechar();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}