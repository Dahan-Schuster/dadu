package telasEditar;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;

import objetos.Perfil;
import telasFinais.TelaCalculadora;
import telasFinais.TelaCronometro;
import telasMain.TelaAtalhos;
import telasMain.TelaNotas;
import telasMenu.MenuConfiguracoes;
import telasMenu.MenuEntretenimento;
import telasMenu.MenuFerramentas;
import telasMain.TelaCalendario;

import javax.swing.JButton;

public class TelaEscolherAtalho extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panel, panel_1;
	private JButton btnEntretenimentos, btnConfiguracao, btnFerramentas, btnCalculadora, btnNotas, btnPerfil, btnCalendario, btnCronometro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEscolherAtalho frame = new TelaEscolherAtalho((byte) -2);
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
			panel.setBackground(new Color(200, 200, 200));
			panel.setBorder(new LineBorder(Color.BLACK));
			
			panel_1.setBackground(new Color(200, 200, 200));
			panel_1.setBorder(new LineBorder(Color.BLACK));

			btnEntretenimentos.setForeground(Color.black);
			btnEntretenimentos.setBackground(Color.LIGHT_GRAY);
			
			btnConfiguracao.setForeground(Color.black);
			btnConfiguracao.setBackground(Color.LIGHT_GRAY);
			
			btnFerramentas.setForeground(Color.black);
			btnFerramentas.setBackground(Color.LIGHT_GRAY);
			
			btnCalculadora.setForeground(Color.black);
			btnCalculadora.setBackground(Color.LIGHT_GRAY);
			
			btnNotas.setForeground(Color.black);
			btnNotas.setBackground(Color.LIGHT_GRAY);
			
			btnPerfil.setForeground(Color.black);
			btnPerfil.setBackground(Color.LIGHT_GRAY);
			
			btnCalendario.setForeground(Color.black);
			btnCalendario.setBackground(Color.LIGHT_GRAY);
			
			btnCronometro.setForeground(Color.black);
			btnCronometro.setBackground(Color.LIGHT_GRAY);

		} else if (Perfil.getTema().equals("escuro")) {
			contentPane.setBackground(new Color(0, 6, 45));
			panel.setBackground(new Color(0, 6, 60));
			panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
			
			panel_1.setBackground(new Color(0, 6, 60));
			panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY));

			btnEntretenimentos.setForeground(Color.white);
			btnEntretenimentos.setBackground(new Color(0, 6, 120));
			
			btnConfiguracao.setForeground(Color.white);
			btnConfiguracao.setBackground(new Color(0, 6, 120));
			
			btnFerramentas.setForeground(Color.white);
			btnFerramentas.setBackground(new Color(0, 6, 120));
			
			btnCalculadora.setForeground(Color.white);
			btnCalculadora.setBackground(new Color(0, 6, 120));
			
			btnNotas.setForeground(Color.white);
			btnNotas.setBackground(new Color(0, 6, 120));
			
			btnPerfil.setForeground(Color.white);
			btnPerfil.setBackground(new Color(0, 6, 120));
			
			btnCalendario.setForeground(Color.white);
			btnCalendario.setBackground(new Color(0, 6, 120));
			
			btnCronometro.setForeground(Color.white);
			btnCronometro.setBackground(new Color(0, 6, 120));
		}
	}

	/**
	 * Create the frame.
	 */
	public TelaEscolherAtalho(byte a) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 294, 262);
		setTitle("Escolher atalho");
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.setBounds(20, 51, 125, 114);
		contentPane.add(panel);
		panel.setLayout(null);

		btnEntretenimentos = new JButton("Entretenimento");
		btnEntretenimentos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Runnable runnable = new Runnable() {

					@Override
					public void run() {
						MenuEntretenimento.main(null);

					}
				};
				
				alterarAtalho(a, "Entretenimento", runnable);
				dispose();
			}
		});

		btnEntretenimentos.setBackground(Color.LIGHT_GRAY);
		btnEntretenimentos.setBounds(10, 11, 107, 23);
		panel.add(btnEntretenimentos);

		btnConfiguracao = new JButton("Configurações");
		btnConfiguracao.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Runnable runnable = new Runnable() {

					@Override
					public void run() {
						MenuConfiguracoes.main(null);

					}
				};

				alterarAtalho(a, "Configurações", runnable);
				dispose();

			}
		});
		btnConfiguracao.setBounds(10, 45, 107, 23);
		panel.add(btnConfiguracao);

		btnFerramentas = new JButton("Ferramentas");
		btnFerramentas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Runnable runnable = new Runnable() {

					@Override
					public void run() {
						MenuFerramentas.main(null);

					}
				};

				alterarAtalho(a, "Ferramentas", runnable);
				dispose();

			}
		});

		btnFerramentas.setBounds(10, 79, 107, 23);
		panel.add(btnFerramentas);

		panel_1 = new JPanel();
		panel_1.setBounds(160, 26, 112, 175);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		btnCalculadora = new JButton("Calculadora");
		btnCalculadora.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Runnable runnable = new Runnable() {

					@Override
					public void run() {
						try {
							TelaCalculadora.main(null);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				};

				alterarAtalho(a, "Calculadora", runnable);
				dispose();

			}
		});
		btnCalculadora.setBounds(10, 11, 89, 23);
		panel_1.add(btnCalculadora);

		btnNotas = new JButton("Notas");
		btnNotas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Runnable runnable = new Runnable() {

					@Override
					public void run() {
						try {
							TelaNotas.main(null);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				};

				alterarAtalho(a, "Notas", runnable);
				dispose();

			}
		});
		btnNotas.setBounds(10, 79, 89, 23);
		panel_1.add(btnNotas);

		btnCronometro = new JButton("Cronômetro");
		btnCronometro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Runnable runnable = new Runnable() {

					@Override
					public void run() {
						TelaCronometro.main(null);

					}
				};

				alterarAtalho(a, "Cronômetro", runnable);
				dispose();
				
			}
		});
		btnCronometro.setBounds(10, 147, 89, 23);
		panel_1.add(btnCronometro);

		btnPerfil = new JButton("Perfil");
		btnPerfil.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Runnable runnable = new Runnable() {

					@Override
					public void run() {
						TelaConfigurarPerfil.main(null);

					}
				};

				alterarAtalho(a, "Perfil", runnable);
				dispose();

			}
		});
		btnPerfil.setBounds(10, 113, 89, 23);
		panel_1.add(btnPerfil);

		btnCalendario = new JButton("Calendário");
		btnCalendario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Runnable runnable = new Runnable() {

					@Override
					public void run() {
						TelaCalendario.main(null);

					}
				};

				alterarAtalho(a, "Calendáro", runnable);
				dispose();

			}
		});
		btnCalendario.setBounds(10, 45, 89, 23);
		panel_1.add(btnCalendario);
		
		alterarTema();
		
		
		btnEntretenimentos.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnEntretenimentos.setForeground(Color.black);
					btnEntretenimentos.setBackground(Color.LIGHT_GRAY);
				} else {

					btnEntretenimentos.setForeground(Color.WHITE);
					btnEntretenimentos.setBackground(new Color(0, 6, 120));
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnEntretenimentos.setForeground(Color.black);
					btnEntretenimentos.setBackground(Color.white);
				} else {

					btnEntretenimentos.setForeground(Color.WHITE);
					btnEntretenimentos.setBackground(new Color(0, 6, 180));
				}
			}
		});
		
		btnConfiguracao.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnConfiguracao.setForeground(Color.black);
					btnConfiguracao.setBackground(Color.LIGHT_GRAY);
				} else {

					btnConfiguracao.setForeground(Color.WHITE);
					btnConfiguracao.setBackground(new Color(0, 6, 120));
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnConfiguracao.setForeground(Color.black);
					btnConfiguracao.setBackground(Color.white);
				} else {

					btnConfiguracao.setForeground(Color.WHITE);
					btnConfiguracao.setBackground(new Color(0, 6, 180));
				}
			}
		});
		
		btnFerramentas.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnFerramentas.setForeground(Color.black);
					btnFerramentas.setBackground(Color.LIGHT_GRAY);
				} else {

					btnFerramentas.setForeground(Color.WHITE);
					btnFerramentas.setBackground(new Color(0, 6, 120));
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnFerramentas.setForeground(Color.black);
					btnFerramentas.setBackground(Color.white);
				} else {

					btnFerramentas.setForeground(Color.WHITE);
					btnFerramentas.setBackground(new Color(0, 6, 180));
				}
			}
		});
		
		btnPerfil.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnPerfil.setForeground(Color.black);
					btnPerfil.setBackground(Color.LIGHT_GRAY);
				} else {

					btnPerfil.setForeground(Color.WHITE);
					btnPerfil.setBackground(new Color(0, 6, 120));
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnPerfil.setForeground(Color.black);
					btnPerfil.setBackground(Color.white);
				} else {

					btnPerfil.setForeground(Color.WHITE);
					btnPerfil.setBackground(new Color(0, 6, 180));
				}
			}
		});
		
		btnNotas.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnNotas.setForeground(Color.black);
					btnNotas.setBackground(Color.LIGHT_GRAY);
				} else {

					btnNotas.setForeground(Color.WHITE);
					btnNotas.setBackground(new Color(0, 6, 120));
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnNotas.setForeground(Color.black);
					btnNotas.setBackground(Color.white);
				} else {

					btnNotas.setForeground(Color.WHITE);
					btnNotas.setBackground(new Color(0, 6, 180));
				}
			}
		});
		
		btnCalculadora.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnCalculadora.setForeground(Color.black);
					btnCalculadora.setBackground(Color.LIGHT_GRAY);
				} else {

					btnCalculadora.setForeground(Color.WHITE);
					btnCalculadora.setBackground(new Color(0, 6, 120));
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnCalculadora.setForeground(Color.black);
					btnCalculadora.setBackground(Color.white);
				} else {

					btnCalculadora.setForeground(Color.WHITE);
					btnCalculadora.setBackground(new Color(0, 6, 180));
				}
			}
		});
		
		btnCalendario.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnCalendario.setForeground(Color.black);
					btnCalendario.setBackground(Color.LIGHT_GRAY);
				} else {

					btnCalendario.setForeground(Color.WHITE);
					btnCalendario.setBackground(new Color(0, 6, 120));
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnCalendario.setForeground(Color.black);
					btnCalendario.setBackground(Color.white);
				} else {

					btnCalendario.setForeground(Color.WHITE);
					btnCalendario.setBackground(new Color(0, 6, 180));
				}
			}
		});
		
		btnCronometro.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnCronometro.setForeground(Color.black);
					btnCronometro.setBackground(Color.LIGHT_GRAY);
				} else {

					btnCronometro.setForeground(Color.WHITE);
					btnCronometro.setBackground(new Color(0, 6, 120));
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {

					btnCronometro.setForeground(Color.black);
					btnCronometro.setBackground(Color.white);
				} else {

					btnCronometro.setForeground(Color.WHITE);
					btnCronometro.setBackground(new Color(0, 6, 180));
				}
			}
		});
	}

	private void alterarAtalho(byte a, String atalho, Runnable runnable) {
		switch (a) {
		case 0:
			TelaAtalhos.atalhoUp(atalho, runnable);
			break;
		case 1:
			TelaAtalhos.atalhoDown(atalho, runnable);
			break;
		case 2:
			TelaAtalhos.atalhoRight(atalho, runnable);
			break;
		case 3:
			TelaAtalhos.atalhoLeft(atalho, runnable);
			break;
		}
	}
}