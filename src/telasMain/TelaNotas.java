package telasMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

import objetos.DataBase;
import objetos.Nota;
import objetos.Perfil;
import telasCadastro.TelaAdicionarNota;
import telasMenu.MenuFerramentas;

public class TelaNotas extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private ResultSet query;
    private JPopupMenu popup, popupOrdenar;
    private JLabel lblAdicionar, lblEditar, lblVoltar;
    private List listaNotas;
	private ArrayList<String> notas = new ArrayList<>();

	public static void main(String[] args) throws MySQLSyntaxErrorException, MySQLNonTransientConnectionException, CommunicationsException, SQLException {
		TelaNotas frame = new TelaNotas();
		frame.setVisible(true);
	}
	

	private void alterarTema() {
		if (Perfil.getTema().equals("claro")) {
			contentPane.setBackground(new Color(220, 220, 220));
			lblAdicionar.setForeground(Color.BLACK);
			lblEditar.setForeground(Color.BLACK);
			lblVoltar.setForeground(Color.BLACK);
			
			listaNotas.setBackground(Color.LIGHT_GRAY);
			listaNotas.setForeground(Color.BLACK );

		} else if (Perfil.getTema().equals("escuro")) {
			contentPane.setBackground(new Color(0, 6, 45));
			lblAdicionar.setForeground(Color.WHITE);
			lblEditar.setForeground(Color.WHITE);
			lblVoltar.setForeground(Color.WHITE);
		
			listaNotas.setBackground(Color.GRAY);
			listaNotas.setForeground(Color.WHITE );
		}
	}

	public TelaNotas() throws MySQLSyntaxErrorException, MySQLNonTransientConnectionException, CommunicationsException, SQLException {
		setResizable(false);
		setTitle("Notas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240)); // o usuário escolhe um tema
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblAdicionar = new JLabel("Opções");
		lblAdicionar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblAdicionar.setBounds(10, 250, 80, 20);
		contentPane.add(lblAdicionar);

		lblEditar = new JLabel("Visualizar");
		lblEditar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setBounds(180, 250, 70, 20);
		contentPane.add(lblEditar);

		listaNotas = new List();
		
		preencherListaNotas();
		
		listaNotas.setFont(new Font("Dialog", Font.PLAIN, 14));
		listaNotas.setBounds(15, 10, 420, 200);
		listaNotas.requestFocus();
		listaNotas.select(0);
		contentPane.add(listaNotas);

		lblVoltar = new JLabel("Voltar");
		lblVoltar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblVoltar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVoltar.setBounds(360, 250, 80, 20);
		contentPane.add(lblVoltar);
		
		alterarTema();

		popupOrdenar = new JPopupMenu();

		JMenuItem ordemAlfabeticaASC = new JMenuItem("Texto (Crescente)");
		popupOrdenar.add(ordemAlfabeticaASC);
		ordemAlfabeticaASC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Ordenar em ordem alfabética A-Z
				ordenarAlf(true);
			}

		});

		JMenuItem ordemAlfabeticaDESC = new JMenuItem("Texto (Decrescente)");
		popupOrdenar.add(ordemAlfabeticaDESC);
		ordemAlfabeticaDESC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Ordenar em ordem alfabética Z-A
				ordenarAlf(false);
			}

		});
		JMenuItem voltar = new JMenuItem("Voltar");
		popupOrdenar.add(voltar);
		voltar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				popup.show(contentPane, 0, 189);
			}

		});
		
		
		popup = new JPopupMenu();
		JMenuItem menuAdd = new JMenuItem("Nova nota");
		popup.add(menuAdd);
		menuAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					new TelaAdicionarNota(-1, notas).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JMenuItem menuDel = new JMenuItem("Excluir");
		popup.add(menuDel);
		menuDel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Nota.apagarNota(notas.get(listaNotas.getSelectedIndex()));
					dispose();
					new TelaNotas().setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (IndexOutOfBoundsException e2) {
					JOptionPane.showMessageDialog(null, "Você deve selecionar um item.");
				}
			}
		});
		
		JMenuItem menuDelAll = new JMenuItem("Excluir tudo");
		popup.add(menuDelAll);
		menuDelAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Nota.apagarTudo();
					dispose();
					new TelaNotas().setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (IndexOutOfBoundsException e2) {
					JOptionPane.showMessageDialog(null, "Você deve selecionar um item.");
				}
			}
		});
		
		JMenuItem menuOrdenar = new JMenuItem("Ordenar por...");
		popup.add(menuOrdenar);
		menuOrdenar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				popupOrdenar.show(contentPane, 0, 209);
				
			}
		});

		listaNotas.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {

				case KeyEvent.VK_SPACE:
					dispose();
					try {
						new TelaAdicionarNota(listaNotas.getSelectedIndex(), notas).setVisible(true);
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					break;
				case KeyEvent.VK_BACK_SPACE:
					dispose();
					try {
						new TelaInicio().setVisible(true);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case KeyEvent.VK_F1:
					popup.show(contentPane, 0, 189);
					break;
				case KeyEvent.VK_F2:
					dispose();
					new MenuFerramentas().setVisible(true);

				}
			}

		});
	}
	
	private void preencherListaNotas() {
		try {
		DataBase.conectar();
		query = DataBase.lerTabela("notas", "cod_usuario", "" + Perfil.getCodigo());

		while (query.next()) {
			listaNotas.add(query.getString(4));
			this.notas.add(query.getString(1));
		}
		DataBase.fechar();
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}
	
	private void ordenarAlf(boolean a) {
		try {

			DataBase.conectar();
			query = DataBase.lerTabela("notas", "cod_usuario", "" + Perfil.getCodigo(), "texto", a);

			notas.clear();
			listaNotas.removeAll();

			while (query.next()) {
				listaNotas.add(query.getString(4));
				notas.add(query.getString(1));
			}
			DataBase.fechar();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
