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
import javax.swing.SwingConstants;

import objetos.Jogo;
import objetos.Perfil;
import telasMain.TelaInicio;
import telasMain.TelaPontuacoes;

public class MenuEntretenimento extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private List list;
	private JLabel lblAbrir;
	private JLabel lblVoltar;

	public static void main(String[] args) {

		MenuEntretenimento frame = new MenuEntretenimento();
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
	
	public MenuEntretenimento() {
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setTitle("Entretenimento");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(null);
		setContentPane(contentPane);

		// CONSTRUÇÃO E DEFINIÇÃO DE COMPONENTES

		list = new List();
		list.setFont(new Font("dialog", Font.PLAIN, 14));
		list.add("2D Space Game");
		list.add("Pontuações");
		list.select(0);
		list.setBounds(15, 10, 420, 200);
		contentPane.add(list);

		// Labels de ações
		lblAbrir = new JLabel("Abrir");
		lblAbrir.setBounds(200, 250, 50, 20);
		lblAbrir.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAbrir.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblAbrir);

		lblVoltar = new JLabel("Voltar");
		lblVoltar.setBounds(360, 250, 80, 20);
		lblVoltar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVoltar.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPane.add(lblVoltar);
		
		alterarTema();

		// EVENTOS

		list.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {

				case KeyEvent.VK_SPACE:

					if (list.getSelectedIndex() == 0) {

						dispose();
						Jogo.abrirJogo();

					} else {

						try {
							dispose();
							new TelaPontuacoes().setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

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
					try {
						new TelaMenu().setVisible(true);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;

				}
			}

		});

	}

}
