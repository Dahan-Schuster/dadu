package telasMain;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;

import objetos.Perfil;
import telasEditar.TelaEscolherAtalho;
import telasMain.TelaInicio;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TelaAtalhos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panel, panel_1, panel_2, panel_3;
	
	private static JButton btnLeft;
	private static JButton btnUp;
	private static JButton btnDown;
	private static JButton btnRight;
	
	private JLabel lblVoltar;
	
	private JLabel up, down, right, left;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAtalhos frame = new TelaAtalhos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void atalhoUp(String atalho, Runnable run){
		btnUp.setText(atalho);
		TelaInicio.setRunUp(run);
	}
	public static void atalhoDown(String atalho, Runnable run){
		btnDown.setText(atalho);
		TelaInicio.setRunDown(run);
	}
	public static void atalhoLeft(String atalho, Runnable run){
		btnLeft.setText(atalho);
		TelaInicio.setRunLeft(run);
	}
	public static void atalhoRight(String atalho, Runnable run){
		btnRight.setText(atalho);
		TelaInicio.setRunRight(run);;
	}
	

	private void alterarTema() {
		if (Perfil.getTema().equals("claro")) {
			contentPane.setBackground(new Color(220, 220, 220));
			panel.setBackground(new Color(200, 200, 200));
			panel_1.setBackground(new Color(200, 200, 200));
			panel_2.setBackground(new Color(200, 200, 200));
			panel_3.setBackground(new Color(200, 200, 200));
			
			btnUp.setBackground(Color.LIGHT_GRAY);
			btnUp.setForeground(Color.black);
			
			btnDown.setBackground(Color.LIGHT_GRAY);
			btnDown.setForeground(Color.black);
			
			btnRight.setBackground(Color.LIGHT_GRAY);
			btnRight.setForeground(Color.black);
			
			btnLeft.setBackground(Color.LIGHT_GRAY);
			btnLeft.setForeground(Color.black);
			
			up.setForeground(Color.black);
			down.setForeground(Color.black);
			left.setForeground(Color.black);
			right.setForeground(Color.black);
			
			lblVoltar.setForeground(Color.black);


		} else if (Perfil.getTema().equals("escuro")) {
			contentPane.setBackground(new Color(0, 6, 45));
			panel.setBackground(new Color(0, 6, 60));
			panel_1.setBackground(new Color(0, 6, 60));
			panel_2.setBackground(new Color(0, 6, 60));
			panel_3.setBackground(new Color(0, 6, 60));
			
			btnUp.setBackground(new Color(0, 6, 120));
			btnUp.setForeground(Color.white);
			
			btnDown.setBackground(new Color(0, 6, 120));
			btnDown.setForeground(Color.white);
			
			btnRight.setBackground(new Color(0, 6, 120));
			btnRight.setForeground(Color.white);
			
			btnLeft.setBackground(new Color(0, 6, 120));
			btnLeft.setForeground(Color.white);
			

			up.setForeground(Color.white);
			down.setForeground(Color.white);
			left.setForeground(Color.white);
			right.setForeground(Color.white);
			
			lblVoltar.setForeground(Color.white);


		}
	}
	
	/**
	 * Create the frame.
	 */
	public TelaAtalhos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Atalhos");
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.setBackground(Color.WHITE);
		panel.setBounds(20, 46, 190, 70);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnUp = new JButton("Notas");
		btnUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				new TelaEscolherAtalho((byte)0).setVisible(true);
				
			}});
		btnUp.setBackground(Color.LIGHT_GRAY);
		btnUp.setBounds(60, 22, 120, 23);
		panel.add(btnUp);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(231, 46, 190, 70);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		btnRight = new JButton("Cronômetro");
		btnRight.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				new TelaEscolherAtalho((byte)2).setVisible(true);
				
			}});
		btnRight.setBackground(Color.LIGHT_GRAY);
		btnRight.setBounds(60, 22, 120, 23);
		panel_1.add(btnRight);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(20, 148, 190, 70);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		btnDown = new JButton("Calculadora");
		btnDown.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new TelaEscolherAtalho((byte)1).setVisible(true);
				
			}});
		btnDown.setBackground(Color.LIGHT_GRAY);
		btnDown.setBounds(60, 22, 120, 23);
		panel_2.add(btnDown);
		
		panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(231, 148, 190, 70);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		btnLeft = new JButton("Perfil");
		
		btnLeft.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				new TelaEscolherAtalho((byte)3).setVisible(true);
				
			}});
		btnLeft.setBackground(Color.LIGHT_GRAY);
		btnLeft.setBounds(60, 22, 120, 23);
		panel_3.add(btnLeft);
		

		lblVoltar = new JLabel("Voltar");
		lblVoltar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblVoltar.setBounds(378, 238, 46, 14);
		contentPane.add(lblVoltar);	

		up = new JLabel("↑");
		up.setFont(new Font("Dialog", Font.PLAIN, 25));
		up.setHorizontalAlignment(SwingConstants.CENTER);
		up.setBounds(10, 25, 50, 20);
		panel.add(up);
		
		down = new JLabel("↓");
		down.setFont(new Font("Dialog", Font.PLAIN, 25));
		down.setHorizontalAlignment(SwingConstants.CENTER);
		down.setBounds(10, 25, 50, 20);
		panel_2.add(down);
		
		left = new JLabel("←");
		left.setFont(new Font("Dialog", Font.PLAIN, 25));
		left.setHorizontalAlignment(SwingConstants.CENTER);
		left.setBounds(10, 25, 50, 20);
		panel_3.add(left);
		
		right = new JLabel("→");
		right.setFont(new Font("Dialog", Font.PLAIN, 25));
		right.setHorizontalAlignment(SwingConstants.CENTER);
		right.setBounds(10, 25, 50, 20);
		panel_1.add(right);
		
		alterarTema();
		
		btnUp.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e){
				switch (e.getKeyCode()) {
				case (KeyEvent.VK_RIGHT):
					btnRight.requestFocus();
					break;
				case (KeyEvent.VK_DOWN):
					btnDown.requestFocus();
					break;
				case (KeyEvent.VK_F2):
					dispose();
					TelaInicio.main(null);
					break;
				}
			}
			
		});
		
		btnDown.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e){
				switch (e.getKeyCode()) {
				case (KeyEvent.VK_RIGHT):
					btnLeft.requestFocus();
					break;
				case (KeyEvent.VK_UP):
					btnUp.requestFocus();
					break;
				case (KeyEvent.VK_F2):
					dispose();
					TelaInicio.main(null);
					break;
				}
			}
			
		});
		
		btnRight.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e){
				switch (e.getKeyCode()) {
				case (KeyEvent.VK_LEFT):
					btnUp.requestFocus();
					break;
				case (KeyEvent.VK_DOWN):
					btnLeft.requestFocus();
					break;
				case (KeyEvent.VK_F2):
					dispose();
					TelaInicio.main(null);
					break;
				}
			}
			
		});
		
		btnLeft.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e){
				switch (e.getKeyCode()) {
				case (KeyEvent.VK_LEFT):
					btnDown.requestFocus();
					break;
				case (KeyEvent.VK_UP):
					btnRight.requestFocus();
					break;
				case (KeyEvent.VK_F2):
					dispose();
					TelaInicio.main(null);
					break;
				}
			}
			
		});
		
		btnUp.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnUp.setForeground(Color.black);
					btnUp.setBackground(Color.LIGHT_GRAY);
				} else {

					btnUp.setForeground(Color.WHITE);
					btnUp.setBackground(new Color(0, 6, 120));
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnUp.setForeground(Color.black);
					btnUp.setBackground(Color.white);
				} else {

					btnUp.setForeground(Color.WHITE);
					btnUp.setBackground(new Color(0, 6, 180));
				}
			}
		});
		
		btnDown.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnDown.setForeground(Color.black);
					btnDown.setBackground(Color.LIGHT_GRAY);
				} else {

					btnDown.setForeground(Color.WHITE);
					btnDown.setBackground(new Color(0, 6, 120));
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnDown.setForeground(Color.black);
					btnDown.setBackground(Color.white);
				} else {

					btnDown.setForeground(Color.WHITE);
					btnDown.setBackground(new Color(0, 6, 180));
				}
			}
		});
		
		btnLeft.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnLeft.setForeground(Color.black);
					btnLeft.setBackground(Color.LIGHT_GRAY);
				} else {

					btnLeft.setForeground(Color.WHITE);
					btnLeft.setBackground(new Color(0, 6, 120));
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnLeft.setForeground(Color.black);
					btnLeft.setBackground(Color.white);
				} else {

					btnLeft.setForeground(Color.WHITE);
					btnLeft.setBackground(new Color(0, 6, 180));
				}
			}
		});
		
		btnRight.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnRight.setForeground(Color.black);
					btnRight.setBackground(Color.LIGHT_GRAY);
				} else {

					btnRight.setForeground(Color.WHITE);
					btnRight.setBackground(new Color(0, 6, 120));
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnRight.setForeground(Color.black);
					btnRight.setBackground(Color.white);
				} else {

					btnRight.setForeground(Color.WHITE);
					btnRight.setBackground(new Color(0, 6, 180));
				}
			}
		});
		
	}
	
}