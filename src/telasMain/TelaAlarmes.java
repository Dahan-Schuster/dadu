package telasMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

import objetos.DataBase;
import objetos.Perfil;
import telasCadastro.TelaAdicionarAlarme;
import telasMenu.MenuFerramentas;

public class TelaAlarmes extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private ResultSet query;
    private JLabel lblAdicionar, lblEditar, lblVoltar;
    private List listaAlarmes;
	private ArrayList<String> alarmes = new ArrayList<>();

	public static void main(String[] args) throws MySQLSyntaxErrorException, MySQLNonTransientConnectionException, CommunicationsException, SQLException {
		TelaAlarmes frame = new TelaAlarmes();
		frame.setVisible(true);
	}
	

	private void alterarTema() {
		if (Perfil.getTema().equals("claro")) {
			contentPane.setBackground(new Color(220, 220, 220));
			lblAdicionar.setForeground(Color.BLACK);
			lblEditar.setForeground(Color.BLACK);
			lblVoltar.setForeground(Color.BLACK);
			
			listaAlarmes.setBackground(Color.LIGHT_GRAY);
			listaAlarmes.setForeground(Color.BLACK );

		} else if (Perfil.getTema().equals("escuro")) {
			contentPane.setBackground(new Color(0, 6, 45));
			lblAdicionar.setForeground(Color.WHITE);
			lblEditar.setForeground(Color.WHITE);
			lblVoltar.setForeground(Color.WHITE);
		
			listaAlarmes.setBackground(Color.GRAY);
			listaAlarmes.setForeground(Color.WHITE );
		}
	}

	public TelaAlarmes() throws MySQLSyntaxErrorException, MySQLNonTransientConnectionException, CommunicationsException, SQLException {
		setResizable(false);
		setTitle("Alarmes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240)); // o usuário escolhe um tema
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblAdicionar = new JLabel("Novo");
		lblAdicionar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblAdicionar.setBounds(10, 250, 80, 20);
		contentPane.add(lblAdicionar);

		lblEditar = new JLabel("Visualizar");
		lblEditar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setBounds(180, 250, 70, 20);
		contentPane.add(lblEditar);

		listaAlarmes = new List();
		
		preencherListaAlarmes();
		
		listaAlarmes.setFont(new Font("Dialog", Font.PLAIN, 14));
		listaAlarmes.setBounds(15, 10, 420, 200);
		listaAlarmes.requestFocus();
		listaAlarmes.select(0);
		contentPane.add(listaAlarmes);

		lblVoltar = new JLabel("Voltar");
		lblVoltar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblVoltar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVoltar.setBounds(360, 250, 80, 20);
		contentPane.add(lblVoltar);
		
		alterarTema();

		

		listaAlarmes.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {

				case KeyEvent.VK_SPACE:
					
					
					try {
						new TelaAdicionarAlarme(listaAlarmes.getSelectedIndex(), alarmes).setVisible(true);
						dispose();
					} catch (SQLException e2) {
						e2.printStackTrace();
					} catch (ParseException e1) {
						e1.printStackTrace();
					} catch (IndexOutOfBoundsException e3) {
						e3.printStackTrace();
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
				case KeyEvent.VK_F2:
					dispose();
					new MenuFerramentas().setVisible(true);
					break;
				case KeyEvent.VK_F1:
					try {
						dispose();
						TelaAdicionarAlarme.main(null);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}

				}
			}

		});
	}
	
	private void preencherListaAlarmes() {
		try {
		DataBase.conectar();
		query = DataBase.lerTabela("alarmes", "cod_usuario", "" + Perfil.getCodigo());

		while (query.next()) {
			listaAlarmes.add(query.getString(3));
			this.alarmes.add(query.getString(1));
		}
		DataBase.fechar();
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}
	

}
