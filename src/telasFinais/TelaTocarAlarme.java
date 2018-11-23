package telasFinais;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import objetos.Alarme;
import objetos.BufferedImageLoader;
import objetos.Perfil;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaTocarAlarme extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton parar;
	private JLabel lblHora, relogio;
	
	public static boolean isVisible = false;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String[] a = {"A", "00:00", "A", "NNNNNNN"};
					TelaTocarAlarme frame = new TelaTocarAlarme(a);
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
			
			lblHora.setForeground(Color.black);
			parar.setForeground(Color.black);
			parar.setBackground(Color.LIGHT_GRAY);
			parar.setBorder(new LineBorder(Color.BLACK));

		} else if (Perfil.getTema().equals("escuro")) {
			contentPane.setBackground(new Color(0, 6, 45));

			lblHora.setForeground(Color.white);
			parar.setForeground(Color.white);
			parar.setBackground(Color.GRAY);
			parar.setBorder(new LineBorder(Color.WHITE));
		}
	}

	/**
	 * Create the frame.
	 */
	public TelaTocarAlarme(String[] a) {
		isVisible = true;
		
		setTitle("Alarme");
		setAlwaysOnTop(isVisible);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 230);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblHora = new JLabel(a[1]);
		lblHora.setFont(new Font("Dialog", Font.BOLD, 25));
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setBounds(100, 10, 100, 50);
		contentPane.add(lblHora);
		
		relogio = new JLabel(new ImageIcon(new BufferedImageLoader().loadImage("/alarme.gif")));
		relogio.setBounds(100,  50,  100,  100);
		contentPane.add(relogio);
		
		parar = new JButton("PARAR");
		parar.setBounds(110, 150, 80, 25);
		contentPane.add(parar);
		
		alterarTema();
		
		tocar(a);
		
		parar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Alarme.isLineAtivo())
					Alarme.pararMusica();
				
				if (a[2].equals("B")) {
					String[] valores = {a[0], a[1], "A", a[3], ""+Perfil.getCodigo()};
					Alarme.salvarAlarme(valores, a[4]);
				}
				
				isVisible = false;
				dispose();
			}
		});
	}
	
	private void tocar(String[] a) {
		String musica="";
		
		
		if (a[0].equals("A")) {
			musica = "tequila";
		} else if (a[0].equals("B")) {
			musica = "soft-and-hard";
		} else if (a[0].equals("C")) {
			musica = "happy-virus";
		}
		
		Alarme.tocarMusica(musica);
	}


}
