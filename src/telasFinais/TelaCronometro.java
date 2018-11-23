package telasFinais;

import java.awt.Color;
import java.awt.Font;
import java.awt.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import objetos.Perfil;
import telasMain.TelaInicio;
import telasMenu.MenuFerramentas;

public class TelaCronometro extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel relogio, milesimos, lblRun, lblReset, lblVoltar;
	private List marcas;
	private static boolean contando = false;
	private byte sec = 0, min = 0, hour = 0;
	private int mili = 0;

	public static void main(String[] args) {
		TelaCronometro frame = new TelaCronometro();
		frame.setVisible(true);
	}
	private void alterarTema() {
		if (Perfil.getTema().equals("claro")) {
			contentPane.setBackground(new Color(220, 220, 220));
			relogio.setForeground(Color.black);
			milesimos.setForeground(Color.black);
			lblRun.setForeground(Color.black);
			lblReset.setForeground(Color.black);
			lblVoltar.setForeground(Color.black);
			
			marcas.setBackground(Color.LIGHT_GRAY);
			marcas.setForeground(Color.BLACK );

		} else if(Perfil.getTema().equals("escuro")) {
			contentPane.setBackground(new Color(0, 6, 45));
			relogio.setForeground(Color.white);
			milesimos.setForeground(Color.white);
			lblRun.setForeground(Color.white);
			lblReset.setForeground(Color.white);
			lblVoltar.setForeground(Color.white);
			
			marcas.setBackground(Color.gray);
			marcas.setForeground(Color.WHITE);
		}
	}

	public TelaCronometro() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Cronômetro");
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(null);
		setContentPane(contentPane);

		// CONSTRUÇÃO E DEFINIÇÃO DE COMPONENTES

		relogio = new JLabel("00h00m00s");
		milesimos = new JLabel(".000");

		relogio.setFont(new Font("arial", Font.BOLD, 30));
		relogio.setBounds(125, 30, 200, 40);
		// relogio.setBorder(new LineBorder(null));
		contentPane.add(relogio);

		milesimos.setFont(new Font("arial", Font.BOLD, 18));
		milesimos.setBounds(322, 42, 50, 25);
		milesimos.setHorizontalAlignment(SwingConstants.LEFT);
		// milesimos.setBorder(new LineBorder(null));
		contentPane.add(milesimos);

		marcas = new List();
		marcas.setBounds(75, 75, 300, 150);
		marcas.requestFocus();
		marcas.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(marcas);

		// Labels de ações
		lblRun = new JLabel("Iniciar");
		lblRun.setBounds(200, 250, 50, 20);
		lblRun.setFont(new Font("Dialog", Font.BOLD, 14));
		lblRun.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblRun);

		lblReset = new JLabel("Marcar");
		lblReset.setBounds(10, 250, 60, 20);
		lblReset.setHorizontalAlignment(SwingConstants.LEFT);
		lblReset.setFont(new Font("Dialog", Font.BOLD, 14));
		lblReset.setVisible(false);
		contentPane.add(lblReset);

		lblVoltar = new JLabel("Voltar");
		lblVoltar.setBounds(360, 250, 80, 20);
		lblVoltar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVoltar.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPane.add(lblVoltar);
		
		alterarTema();

		// EVENTOS
		marcas.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_SPACE:
					if (!contando) {
						try {
							contar();
							lblRun.setText("Parar");
							lblReset.setText("Marcar");
							lblReset.setVisible(true);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
					} else {
						parar();
						lblRun.setText("Iniciar");
						lblReset.setText("Zerar");
					}

					// System.out.println("contar");
					break;

				case KeyEvent.VK_F1:
					if (lblReset.isVisible()) {
						if (lblReset.getText().equals("Marcar")) {
							marcas.add(relogio.getText() + milesimos.getText());
						} else if (lblReset.getText().equals("Zerar")) {
							lblReset.setVisible(false);
							marcas.removeAll();
							relogio.setText("00h00m00s");
							milesimos.setText(".000");

							mili = 0;
							sec = 0;
							min = 0;
							hour = 0;
						}
					}
					break;
				case KeyEvent.VK_F2:
					dispose();
					new MenuFerramentas().setVisible(true);
					break;
				case KeyEvent.VK_BACK_SPACE:
					try {
						dispose();
						new TelaInicio().setVisible(true);
					} catch (IOException e1) {
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

	ScheduledExecutorService contar;

	private void contar() throws InterruptedException {
		contar = Executors.newScheduledThreadPool(1);

		final Runnable count = new Runnable() {
			@Override
			public void run() {
				if (mili < 999) {
					mili++;
					milesimos.setText(String.format(".%03d", mili));
				} else if (sec < 59) {
					sec++;
					mili = 0;
					relogio.setText(String.format("%02dh%02dm%02ds", hour, min, sec));
				} else if (min < 59) {
					min++;
					mili = 0;
					sec = 0;
					relogio.setText(String.format("%02dh%02dm%02ds", hour, min, sec));
				} else {
					hour++;
					mili = 0;
					sec = 0;
					min = 0;
					relogio.setText(String.format("%02dh%02dm%02ds", hour, min, sec));
				}

			}
		};
		contar.scheduleAtFixedRate(count, 0, 1, TimeUnit.MILLISECONDS);
		contando = true;
	}

	private void parar() {
		contar.schedule(new Runnable() {

			@Override
			public void run() {
				contar.shutdown();
			}
		}, 0, TimeUnit.SECONDS);
		contando = false;
	}

}
