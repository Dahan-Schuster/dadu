package telasMenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import objetos.Perfil;
import telasFinais.TelaCalculadora;
import telasFinais.TelaCronometro;
import telasMain.TelaAlarmes;
import telasMain.TelaCalendario;
import telasMain.TelaEventos;
import telasMain.TelaInicio;
import telasMain.TelaNotas;

public class MenuFerramentas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblAbrir, lblVoltar;
	private List list;

	public static void main(String[] args) {
		MenuFerramentas frame = new MenuFerramentas();
		frame.setVisible(true);
	}

	private void alterarTema() {
		if (Perfil.getTema().equals("claro")) {
			contentPane.setBackground(new Color(220, 220, 220));
			lblAbrir.setForeground(Color.black);
			lblVoltar.setForeground(Color.black);

			list.setBackground(Color.LIGHT_GRAY);
			list.setForeground(Color.black);

		} else if (Perfil.getTema().equals("escuro")) {
			contentPane.setBackground(new Color(0, 6, 45));
			lblAbrir.setForeground(Color.white);
			lblVoltar.setForeground(Color.white);

			list.setBackground(Color.GRAY);
			list.setForeground(Color.white);

		}
	}

	public MenuFerramentas() {
		setResizable(false);
		setTitle("Ferramentas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		list = new List();
		list.setBounds(10, 34, 414, 185);

		list.add("Bloco de notas");
		list.add("Cronômetro");
		list.add("Calendário");
		list.add("Calculadora");
		list.add("Alarme");
		list.add("Eventos");
		list.select(0);
		contentPane.add(list);

		lblAbrir = new JLabel("Abrir");
		lblAbrir.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblAbrir.setBounds(195, 236, 37, 14);
		contentPane.add(lblAbrir);

		lblVoltar = new JLabel("Voltar");
		lblVoltar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblVoltar.setBounds(378, 238, 46, 14);
		contentPane.add(lblVoltar);
		
		alterarTema();

		list.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {

				case KeyEvent.VK_SPACE:
					dispose();
					switch (list.getSelectedIndex()) {
					case 0:
						try {
							new TelaNotas().setVisible(true);
						} catch (SQLException e2) {
							e2.printStackTrace();
						}
						break;
					case 1:
						new TelaCronometro().setVisible(true);
						break;
					case 2:
						new TelaCalendario().setVisible(true);
						break;
					case 3:
						try {
							new TelaCalculadora().setVisible(true);
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						break;
					case 4:
						try {
							new TelaAlarmes().setVisible(true);
						} catch (SQLException e2) {
							e2.printStackTrace();
						}
						break;
					case 5:
						new TelaEventos(0).setVisible(true);
					}
					break;
				case KeyEvent.VK_BACK_SPACE:
					dispose();
					try {
						new TelaInicio().setVisible(true);
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case KeyEvent.VK_F2:
					dispose();
					try {
						new TelaMenu().setVisible(true);
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}

		});

	}
}
