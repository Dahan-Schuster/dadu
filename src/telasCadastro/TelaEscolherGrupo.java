package telasCadastro;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import objetos.Perfil;
import telasCadastro.TelaAdicionarContato;
import telasFinais.TelaVisualizarContato;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TelaEscolherGrupo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnFavoritos, btnTrabalho, btnFamilia, btnAmigos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEscolherGrupo frame = new TelaEscolherGrupo((byte) -1);
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
			btnFavoritos.setForeground(Color.BLACK);
			btnFavoritos.setBackground(Color.LIGHT_GRAY);
			btnTrabalho.setForeground(Color.BLACK);
			btnTrabalho.setBackground(Color.LIGHT_GRAY);
			btnFamilia.setForeground(Color.BLACK);
			btnFamilia.setBackground(Color.LIGHT_GRAY);
			btnAmigos.setForeground(Color.BLACK);
			btnAmigos.setBackground(Color.LIGHT_GRAY);

		} else if (Perfil.getTema().equals("escuro")) {
			contentPane.setBackground(new Color(0, 6, 45));
			btnFavoritos.setForeground(Color.WHITE);
			btnFavoritos.setBackground(new Color(0, 6, 120));
			btnTrabalho.setForeground(Color.WHITE);
			btnTrabalho.setBackground(new Color(0, 6, 120));
			btnFamilia.setForeground(Color.WHITE);
			btnFamilia.setBackground(new Color(0, 6, 120));
			btnAmigos.setForeground(Color.WHITE);
			btnAmigos.setBackground(new Color(0, 6, 120));
		}
	}

	/**
	 * Create the frame.
	 */
	public TelaEscolherGrupo(byte a) {
		setTitle("Escolher grupo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 282, 189);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnFavoritos = new JButton("Favoritos");
		btnFavoritos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (a == 0) {
					enviarGrupoAdicionar("Favoritos");
				}
				if (a == 1) {
					enviarGrupoAtualizar("Favoritos");
				}
				dispose();
			}
		});
		btnFavoritos.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnFavoritos.setBackground(Color.WHITE);
		btnFavoritos.setBounds(10, 34, 114, 23);
		contentPane.add(btnFavoritos);

		btnFamilia = new JButton("Fam\u00EDlia");
		btnFamilia.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (a == 0) {
					enviarGrupoAdicionar("Família");
				}
				if (a == 1) {
					enviarGrupoAtualizar("Família");
				}
				dispose();
			}
		});

		btnFamilia.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnFamilia.setBackground(Color.WHITE);
		btnFamilia.setBounds(147, 34, 109, 23);
		contentPane.add(btnFamilia);

		btnAmigos = new JButton("Amigos");
		btnAmigos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (a == 0) {
					enviarGrupoAdicionar("Amigos");
				}
				if (a == 1) {
					enviarGrupoAtualizar("Amigos");
				}
				dispose();
			}
		});
		btnAmigos.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnAmigos.setBackground(Color.WHITE);
		btnAmigos.setBounds(10, 91, 114, 23);
		contentPane.add(btnAmigos);

		btnTrabalho = new JButton("Trabalho");
		btnTrabalho.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (a == 0) {
					enviarGrupoAdicionar("Trabalho");
				}
				if (a == 1) {
					enviarGrupoAtualizar("Trabalho");
				}
				dispose();
			}
		});
		btnTrabalho.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnTrabalho.setBackground(Color.WHITE);
		btnTrabalho.setBounds(147, 93, 109, 23);
		contentPane.add(btnTrabalho);

		alterarTema();

		// EVENTOS

		btnFavoritos.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case (KeyEvent.VK_RIGHT):
					btnFamilia.requestFocus();
					break;
				case (KeyEvent.VK_DOWN):
					btnAmigos.requestFocus();
					break;
				case (KeyEvent.VK_BACK_SPACE):
					dispose();
					break;

				}
			}
		});

		btnFamilia.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case (KeyEvent.VK_LEFT):
					btnFavoritos.requestFocus();
					break;
				case (KeyEvent.VK_DOWN):
					btnTrabalho.requestFocus();
					break;
				case (KeyEvent.VK_BACK_SPACE):
					dispose();
					break;

				}
			}
		});
		btnAmigos.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case (KeyEvent.VK_RIGHT):
					btnTrabalho.requestFocus();
					break;
				case (KeyEvent.VK_UP):
					btnFavoritos.requestFocus();
					break;
				case (KeyEvent.VK_BACK_SPACE):
					dispose();
					break;

				}
			}
		});
		btnTrabalho.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case (KeyEvent.VK_LEFT):
					btnAmigos.requestFocus();
					break;
				case (KeyEvent.VK_UP):
					btnFamilia.requestFocus();
					break;
				case (KeyEvent.VK_BACK_SPACE):
					dispose();
					break;
				}
			}
		});

		btnAmigos.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnAmigos.setForeground(Color.black);
					btnAmigos.setBackground(Color.LIGHT_GRAY);
				} else {

					btnAmigos.setForeground(Color.WHITE);
					btnAmigos.setBackground(new Color(0, 6, 120));
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnAmigos.setForeground(Color.black);
					btnAmigos.setBackground(Color.white);
				} else {

					btnAmigos.setForeground(Color.WHITE);
					btnAmigos.setBackground(new Color(0, 6, 180));
				}
			}
		});

		btnFamilia.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnFamilia.setForeground(Color.black);
					btnFamilia.setBackground(Color.LIGHT_GRAY);
				} else {

					btnFamilia.setForeground(Color.WHITE);
					btnFamilia.setBackground(new Color(0, 6, 120));
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnFamilia.setForeground(Color.black);
					btnFamilia.setBackground(Color.white);
				} else {

					btnFamilia.setForeground(Color.WHITE);
					btnFamilia.setBackground(new Color(0, 6, 180));
				}
			}
		});

		btnTrabalho.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnTrabalho.setForeground(Color.black);
					btnTrabalho.setBackground(Color.LIGHT_GRAY);
				} else {

					btnTrabalho.setForeground(Color.WHITE);
					btnTrabalho.setBackground(new Color(0, 6, 120));
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnTrabalho.setForeground(Color.black);
					btnTrabalho.setBackground(Color.white);
				} else {

					btnTrabalho.setForeground(Color.WHITE);
					btnTrabalho.setBackground(new Color(0, 6, 180));
				}
			}
		});

		btnFavoritos.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnFavoritos.setForeground(Color.black);
					btnFavoritos.setBackground(Color.LIGHT_GRAY);
				} else {

					btnFavoritos.setForeground(Color.WHITE);
					btnFavoritos.setBackground(new Color(0, 6, 120));
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnFavoritos.setForeground(Color.black);
					btnFavoritos.setBackground(Color.white);
				} else {

					btnFavoritos.setForeground(Color.WHITE);
					btnFavoritos.setBackground(new Color(0, 6, 180));
				}
			}
		});
	}

	public static void enviarGrupoAdicionar(String grupo) {

		TelaAdicionarContato.Escolhergrupo(grupo);

	}

	public static void enviarGrupoAtualizar(String grupo) {

		TelaVisualizarContato.Escolhergrupo(grupo);
	}

}
