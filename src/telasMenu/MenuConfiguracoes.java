package telasMenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import objetos.Perfil;
import telasEditar.TelaConfigurarPerfil;
import telasEditar.TelaDataHora;
import telasFinais.TelaSobre;
import telasMain.TelaInicio;

public class MenuConfiguracoes extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private List list;
	private JLabel lblAbrir;
	private JLabel lblVoltar;

	public static void main(String[] args) {

		MenuConfiguracoes frame = new MenuConfiguracoes();
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

	public MenuConfiguracoes() {
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setTitle("Configurações");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(null);
		setContentPane(contentPane);

		// CONSTRUÇÃO E DEFINIÇÃO DE COMPONENTES

		list = new List();
		list.setFont(new Font("dialog", Font.PLAIN, 14));
		list.select(0);
		list.setBounds(15, 10, 420, 200);
		contentPane.add(list);
		
		// Adicionando itens à lista

		list.add("Data e Hora");
		list.add("Personalizar");
		list.add("Perfil");
		list.add("Sobre o dispositivo");

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
					dispose();
					try {
						switch (list.getSelectedIndex()) {
						case 0:
							TelaDataHora.main(null);
							break;
						case 1:
							MenuPersonalizar.main(null);
							break;
						case 2:
							TelaConfigurarPerfil.main(null);
							break;
						case 3:
							TelaSobre.main(null);
							break;
						}
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					break;
				case KeyEvent.VK_BACK_SPACE:
					dispose();
					TelaInicio.main(null);
					break;
				case KeyEvent.VK_F2:
					dispose();
					TelaMenu.main(null);
					break;

				}
			}

		});

	}
}