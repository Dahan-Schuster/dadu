package telasCadastro;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import objetos.Alarme;
import objetos.Perfil;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TelaEscolherSom extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnSomA, btnSomB, btnSomC, btnSomD;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEscolherSom frame = new TelaEscolherSom();
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
			btnSomA.setForeground(Color.BLACK);
			btnSomA.setBackground(Color.LIGHT_GRAY);
			btnSomB.setForeground(Color.BLACK);
			btnSomB.setBackground(Color.LIGHT_GRAY);
			btnSomC.setForeground(Color.BLACK);
			btnSomC.setBackground(Color.LIGHT_GRAY);
			btnSomD.setForeground(Color.BLACK);
			btnSomD.setBackground(Color.LIGHT_GRAY);

		} else if (Perfil.getTema().equals("escuro")) {
			contentPane.setBackground(new Color(0, 6, 45));
			btnSomA.setForeground(Color.WHITE);
			btnSomA.setBackground(new Color(0, 6, 120));
			btnSomB.setForeground(Color.WHITE);
			btnSomB.setBackground(new Color(0, 6, 120));
			btnSomC.setForeground(Color.WHITE);
			btnSomC.setBackground(new Color(0, 6, 120));
			btnSomD.setForeground(Color.WHITE);
			btnSomD.setBackground(new Color(0, 6, 120));
		}
	}

	/**
	 * Create the frame.
	 */
	public TelaEscolherSom() {
		setTitle("Escolher grupo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 282, 189);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnSomA = new JButton("Tequila!");
		btnSomA.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Alarme.isLineAtivo())
					Alarme.pararMusica();
				else
					Alarme.tocarMusica("tequila");
				
				TelaAdicionarAlarme.setSom("A");
			}
		});
		btnSomA.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnSomA.setBackground(Color.WHITE);
		btnSomA.setBounds(15, 34, 120, 23);
		contentPane.add(btnSomA);

		btnSomB = new JButton("Soft'n'Hard");
		btnSomB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Alarme.isLineAtivo())
					Alarme.pararMusica();
				else
					Alarme.tocarMusica("soft-and-hard");
				
				
				TelaAdicionarAlarme.setSom("B");
			}
		});

		btnSomB.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnSomB.setBackground(Color.WHITE);
		btnSomB.setBounds(147, 34, 120, 23);
		contentPane.add(btnSomB);

		btnSomC = new JButton("Happy Virus");
		btnSomC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Alarme.isLineAtivo())
					Alarme.pararMusica();
				else
					Alarme.tocarMusica("happy-virus");
				
				
				TelaAdicionarAlarme.setSom("C");
			}
		});
		btnSomC.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnSomC.setBackground(Color.WHITE);
		btnSomC.setBounds(15, 91, 120, 23);
		contentPane.add(btnSomC);

		btnSomD = new JButton("Salvar");
		btnSomD.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Alarme.isLineAtivo())
					Alarme.pararMusica();
				
				dispose();
			}
		});
		btnSomD.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnSomD.setBackground(Color.WHITE);
		btnSomD.setBounds(147, 91, 120, 23);
		contentPane.add(btnSomD);

		alterarTema();

		// EVENTOS

		btnSomA.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case (KeyEvent.VK_RIGHT):
					btnSomB.requestFocus();
					break;
				case (KeyEvent.VK_DOWN):
					btnSomC.requestFocus();
					break;
				case (KeyEvent.VK_BACK_SPACE):
					dispose();
					break;

				}
			}
		});

		btnSomB.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case (KeyEvent.VK_LEFT):
					btnSomA.requestFocus();
					break;
				case (KeyEvent.VK_DOWN):
					btnSomD.requestFocus();
					break;
				case (KeyEvent.VK_BACK_SPACE):
					dispose();
					break;

				}
			}
		});
		btnSomC.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case (KeyEvent.VK_RIGHT):
					btnSomD.requestFocus();
					break;
				case (KeyEvent.VK_UP):
					btnSomA.requestFocus();
					break;
				case (KeyEvent.VK_BACK_SPACE):
					dispose();
					break;

				}
			}
		});
		btnSomD.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case (KeyEvent.VK_LEFT):
					btnSomC.requestFocus();
					break;
				case (KeyEvent.VK_UP):
					btnSomB.requestFocus();
					break;
				case (KeyEvent.VK_BACK_SPACE):
					dispose();
					break;
				}
			}
		});

		btnSomC.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnSomC.setForeground(Color.black);
					btnSomC.setBackground(Color.LIGHT_GRAY);
				} else {

					btnSomC.setForeground(Color.WHITE);
					btnSomC.setBackground(new Color(0, 6, 120));
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnSomC.setForeground(Color.black);
					btnSomC.setBackground(Color.white);
				} else {

					btnSomC.setForeground(Color.WHITE);
					btnSomC.setBackground(new Color(0, 6, 180));
				}
			}
		});

		btnSomB.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnSomB.setForeground(Color.black);
					btnSomB.setBackground(Color.LIGHT_GRAY);
				} else {

					btnSomB.setForeground(Color.WHITE);
					btnSomB.setBackground(new Color(0, 6, 120));
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnSomB.setForeground(Color.black);
					btnSomB.setBackground(Color.white);
				} else {

					btnSomB.setForeground(Color.WHITE);
					btnSomB.setBackground(new Color(0, 6, 180));
				}
			}
		});

		btnSomD.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnSomD.setForeground(Color.black);
					btnSomD.setBackground(Color.LIGHT_GRAY);
				} else {

					btnSomD.setForeground(Color.WHITE);
					btnSomD.setBackground(new Color(0, 6, 120));
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnSomD.setForeground(Color.black);
					btnSomD.setBackground(Color.white);
				} else {

					btnSomD.setForeground(Color.WHITE);
					btnSomD.setBackground(new Color(0, 6, 180));
				}
			}
		});

		btnSomA.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnSomA.setForeground(Color.black);
					btnSomA.setBackground(Color.LIGHT_GRAY);
				} else {

					btnSomA.setForeground(Color.WHITE);
					btnSomA.setBackground(new Color(0, 6, 120));
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnSomA.setForeground(Color.black);
					btnSomA.setBackground(Color.white);
				} else {

					btnSomA.setForeground(Color.WHITE);
					btnSomA.setBackground(new Color(0, 6, 180));
				}
			}
		});
	}

}
