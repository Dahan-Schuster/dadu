package telasMenu;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import objetos.Perfil;
import telasCadastro.TelaAdicionarContato;
import telasMain.TelaContatos;
import telasMain.TelaInicio;

import java.awt.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JLabel;
import java.awt.Font;

public class MenuContatos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblAbrir, lblVoltar;
	private List list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuContatos frame = new MenuContatos();
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
	
	/**
	 * Create the frame.
	 */
	public MenuContatos() {
		setTitle("Comunicação");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		list = new List();
		list.setBounds(10, 34, 414, 185);

		list.add("Salvar Novo");
		list.add("Lista de contatos");
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

					if (list.getSelectedIndex() == 0) {

						try {
							dispose();
							new TelaAdicionarContato().setVisible(true);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					} else {

						try {
							dispose();
							new TelaContatos().setVisible(true);
						} catch (SQLException e1) {
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

				}
			}

		});

	}
}



