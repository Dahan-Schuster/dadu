package telasCadastro;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

import objetos.Alarme;
import objetos.Perfil;
import telasMain.TelaAlarmes;

public class TelaAdicionarAlarme extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblSalvar, lblVoltar;
	private static JLabel lblSom;
	private JFormattedTextField txtHora;
	private JTextField status;
	private JButton btnSom;
	private JCheckBox dom, seg, ter, qua, qui, sex, sab;

	private String[] allStatus = { "<<    DESLIGADO    >>", "<<  TOCAR UMA VEZ  >>", "<< SELECIONAR DIAS >>" };
	private int i = 0;

	private ArrayList<String> alarmes = new ArrayList<>();
	private int index;

	private static String som = "A";
	private String Status = "A";
	private String dias = "";

	public static void main(String[] args) throws ParseException {
		TelaAdicionarAlarme frame;
		try {
			frame = new TelaAdicionarAlarme(-1, new ArrayList<String>());
			frame.setVisible(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void alterarTema() {
		if (Perfil.getTema().equals("claro")) {
			contentPane.setBackground(new Color(220, 220, 220));
			txtHora.setBackground(Color.LIGHT_GRAY);
			txtHora.setForeground(Color.BLACK);
			status.setForeground(Color.black);
			status.setBackground(Color.LIGHT_GRAY);

			dom.setBackground(new Color(200, 200, 200));
			dom.setForeground(Color.BLACK);
			seg.setBackground(new Color(200, 200, 200));
			seg.setForeground(Color.BLACK);
			ter.setBackground(new Color(200, 200, 200));
			ter.setForeground(Color.BLACK);
			qua.setBackground(new Color(200, 200, 200));
			qua.setForeground(Color.BLACK);
			qui.setBackground(new Color(200, 200, 200));
			qui.setForeground(Color.BLACK);
			sex.setBackground(new Color(200, 200, 200));
			sex.setForeground(Color.BLACK);
			sab.setBackground(new Color(200, 200, 200));
			sab.setForeground(Color.BLACK);

			btnSom.setForeground(Color.black);
			btnSom.setBackground(Color.LIGHT_GRAY);
			btnSom.setBorder(new LineBorder(Color.BLACK));

			lblSom.setForeground(Color.black);
			lblSom.setBackground(Color.LIGHT_GRAY);
			lblSom.setBorder(new LineBorder(Color.BLACK));

			lblVoltar.setForeground(Color.BLACK);
			lblSalvar.setForeground(Color.BLACK);

		} else if (Perfil.getTema().equals("escuro")) {
			contentPane.setBackground(new Color(0, 6, 45));
			txtHora.setBackground(Color.GRAY);
			txtHora.setForeground(Color.WHITE);
			status.setForeground(Color.WHITE);
			status.setBackground(new Color(0, 6, 30));

			dom.setBackground(new Color(0, 6, 180));
			dom.setForeground(Color.WHITE);
			seg.setBackground(new Color(0, 6, 180));
			seg.setForeground(Color.WHITE);
			ter.setBackground(new Color(0, 6, 180));
			ter.setForeground(Color.WHITE);
			qua.setBackground(new Color(0, 6, 180));
			qua.setForeground(Color.WHITE);
			qui.setBackground(new Color(0, 6, 180));
			qui.setForeground(Color.WHITE);
			sex.setBackground(new Color(0, 6, 180));
			sex.setForeground(Color.WHITE);
			sab.setBackground(new Color(0, 6, 180));
			sab.setForeground(Color.WHITE);

			lblSom.setForeground(Color.white);
			lblSom.setBackground(Color.GRAY);
			lblSom.setBorder(new LineBorder(Color.white));

			btnSom.setForeground(Color.WHITE);
			btnSom.setBackground(Color.GRAY);
			btnSom.setBorder(new LineBorder(Color.white));

			lblVoltar.setForeground(Color.WHITE);
			lblSalvar.setForeground(Color.WHITE);
		}
	}

	public TelaAdicionarAlarme(int index, ArrayList<String> alarmes) throws MySQLSyntaxErrorException,
			MySQLNonTransientConnectionException, CommunicationsException, SQLException, ParseException {
		this.index = index;
		this.alarmes = alarmes;

		setTitle("Salvar alarme");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);

		MaskFormatter hora = new MaskFormatter("##:##");
		hora.setPlaceholderCharacter('0');

		txtHora = new JFormattedTextField(hora);
		txtHora.setText(new SimpleDateFormat("HH:mm").format(new Date(System.currentTimeMillis())));
		txtHora.setFont(new Font("Dialog", Font.BOLD, 30));
		txtHora.setHorizontalAlignment(SwingConstants.CENTER);
		txtHora.setBounds(165, 20, 120, 50);
		contentPane.add(txtHora);

		status = new JTextField(allStatus[i]);
		status.setFont(new Font("Dialog", Font.PLAIN, 14));
		status.setEditable(false);
		status.setHorizontalAlignment(SwingConstants.CENTER);
		status.setBounds(125, 90, 200, 25);
		status.setBorder(new LineBorder(null));
		contentPane.add(status);

		dom = new JCheckBox("D");
		dom.setBounds(60, 140, 40, 30);
		contentPane.add(dom);

		seg = new JCheckBox("S");
		seg.setBounds(110, 140, 40, 30);
		contentPane.add(seg);

		ter = new JCheckBox("T");
		ter.setBounds(160, 140, 40, 30);
		contentPane.add(ter);

		qua = new JCheckBox("Q");
		qua.setBounds(210, 140, 40, 30);
		contentPane.add(qua);

		qui = new JCheckBox("Q");
		qui.setBounds(260, 140, 40, 30);
		contentPane.add(qui);

		sex = new JCheckBox("S");
		sex.setBounds(310, 140, 40, 30);
		contentPane.add(sex);

		sab = new JCheckBox("S");
		sab.setBounds(360, 140, 40, 30);
		contentPane.add(sab);

		btnSom = new JButton("Música");
		btnSom.setBounds(100, 200, 150, 30);
		btnSom.setFont(new Font("Dialog", Font.PLAIN, 20));
		contentPane.add(btnSom);

		lblSom = new JLabel(som);
		lblSom.setBounds(300, 200, 40, 30);
		lblSom.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblSom.setOpaque(true);
		lblSom.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblSom);

		if (this.index >= 0) {

			String[] a = Alarme.getAlarme(alarmes.get(index));
			lblSom.setText(a[0]);
			txtHora.setText(a[1]);
			Status = a[2];
			dias = a[3];
			
			setStatus();
			setDias();
			
			
		}

		// Labels de ações
		lblSalvar = new JLabel("Salvar");
		lblSalvar.setBounds(10, 250, 60, 20);
		lblSalvar.setHorizontalAlignment(SwingConstants.LEFT);
		lblSalvar.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPane.add(lblSalvar);

		lblVoltar = new JLabel("Voltar");
		lblVoltar.setBounds(360, 250, 80, 20);
		lblVoltar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVoltar.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPane.add(lblVoltar);

		alterarTema();

		// EVENTOS

		txtHora.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				verificarHora();
				switch (e.getKeyCode()) {
				case (KeyEvent.VK_DOWN):
					status.requestFocus();
					break;
				case (KeyEvent.VK_F1):
					salvar();
					break;
				case (KeyEvent.VK_F2):
					dispose();
					try {
						new TelaAlarmes().setVisible(true);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					break;
				}

			}
		});

		status.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case (KeyEvent.VK_LEFT):
					if (i == 0) {
						i = 2;
					} else {
						i--;
					}
					status.setText(allStatus[i]);
					alterarStatus();
					if (i == 1)
						setCheckBoxesEditable(false);
					else
						setCheckBoxesEditable(true);

					break;
				case (KeyEvent.VK_RIGHT):
					if (i == 2) {
						i = 0;
					} else {
						i++;
					}
					status.setText(allStatus[i]);
					alterarStatus();
					if (i == 1)
						setCheckBoxesEditable(false);
					else
						setCheckBoxesEditable(true);

					break;
				case (KeyEvent.VK_UP):
					txtHora.requestFocus();
					break;
				case (KeyEvent.VK_DOWN):
					if (dom.isEnabled())
						dom.requestFocus();
					else
						btnSom.requestFocus();
					break;
				case (KeyEvent.VK_F1):
					salvar();
					break;
				case (KeyEvent.VK_F2):
					dispose();
					try {
						new TelaAlarmes().setVisible(true);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					break;
				}
			}
		});

		status.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {
					status.setForeground(Color.black);
					status.setBackground(Color.LIGHT_GRAY);
				} else {
					status.setForeground(Color.WHITE);
					status.setBackground(new Color(0, 6, 30));
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {
					status.setForeground(Color.black);
					status.setBackground(Color.WHITE);
				} else {
					status.setForeground(Color.WHITE);
					status.setBackground(new Color(0, 6, 180));
				}

			}
		});

		dom.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					seg.requestFocus();
					break;
				case KeyEvent.VK_LEFT:
					sab.requestFocus();
					break;
				case KeyEvent.VK_UP:
					status.requestFocus();
					break;
				case KeyEvent.VK_DOWN:
					btnSom.requestFocus();
					break;
				case (KeyEvent.VK_F1):
					salvar();
					break;
				case (KeyEvent.VK_F2):
					dispose();
					try {
						new TelaAlarmes().setVisible(true);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					break;
				}
			}
		});

		seg.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					ter.requestFocus();
					break;
				case KeyEvent.VK_LEFT:
					dom.requestFocus();
					break;
				case KeyEvent.VK_UP:
					status.requestFocus();
					break;
				case KeyEvent.VK_DOWN:
					btnSom.requestFocus();
					break;
				case (KeyEvent.VK_F1):
					salvar();
					break;
				case (KeyEvent.VK_F2):
					dispose();
					try {
						new TelaAlarmes().setVisible(true);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					break;
				}
			}
		});

		ter.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					qua.requestFocus();
					break;
				case KeyEvent.VK_LEFT:
					seg.requestFocus();
					break;
				case KeyEvent.VK_UP:
					status.requestFocus();
					break;
				case KeyEvent.VK_DOWN:
					btnSom.requestFocus();
					break;
				case (KeyEvent.VK_F1):
					salvar();
					break;
				case (KeyEvent.VK_F2):
					dispose();
					try {
						new TelaAlarmes().setVisible(true);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					break;
				}
			}
		});

		qua.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					qui.requestFocus();
					break;
				case KeyEvent.VK_LEFT:
					ter.requestFocus();
					break;
				case KeyEvent.VK_UP:
					status.requestFocus();
					break;
				case KeyEvent.VK_DOWN:
					btnSom.requestFocus();
					break;
				case (KeyEvent.VK_F1):
					salvar();
					break;
				case (KeyEvent.VK_F2):
					dispose();
					try {
						new TelaAlarmes().setVisible(true);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					break;
				}
			}
		});

		qui.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					sex.requestFocus();
					break;
				case KeyEvent.VK_LEFT:
					qua.requestFocus();
					break;
				case KeyEvent.VK_UP:
					status.requestFocus();
					break;
				case KeyEvent.VK_DOWN:
					btnSom.requestFocus();
					break;
				case (KeyEvent.VK_F1):
					salvar();
					break;
				case (KeyEvent.VK_F2):
					dispose();
					try {
						new TelaAlarmes().setVisible(true);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					break;
				}
			}
		});

		sex.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					sab.requestFocus();
					break;
				case KeyEvent.VK_LEFT:
					qui.requestFocus();
					break;
				case KeyEvent.VK_UP:
					status.requestFocus();
					break;
				case KeyEvent.VK_DOWN:
					btnSom.requestFocus();
					break;
				case (KeyEvent.VK_F1):
					salvar();
					break;
				case (KeyEvent.VK_F2):
					dispose();
					try {
						new TelaAlarmes().setVisible(true);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					break;
				}
			}
		});

		sab.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					dom.requestFocus();
					break;
				case KeyEvent.VK_LEFT:
					sex.requestFocus();
					break;
				case KeyEvent.VK_UP:
					status.requestFocus();
					break;
				case KeyEvent.VK_DOWN:
					btnSom.requestFocus();
					break;
				case (KeyEvent.VK_F1):
					salvar();
					break;
				case (KeyEvent.VK_F2):
					dispose();
					try {
						new TelaAlarmes().setVisible(true);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					break;
				}
			}
		});
		
		btnSom.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new TelaEscolherSom().setVisible(true);
			}
		});
		
		btnSom.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					if (dom.isEnabled())
						dom.requestFocus();
					else
						status.requestFocus();
					break;
				case (KeyEvent.VK_F1):
					salvar();
					break;
				case (KeyEvent.VK_F2):
					dispose();
					try {
						new TelaAlarmes().setVisible(true);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					break;
				}
			}
		});

		btnSom.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {
					btnSom.setForeground(Color.black);
					btnSom.setBackground(Color.LIGHT_GRAY);
				} else {
					btnSom.setForeground(Color.WHITE);
					btnSom.setBackground(Color.GRAY);
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (Perfil.getTema().equals("claro")) {
					btnSom.setForeground(Color.black);
					btnSom.setBackground(Color.WHITE);
				} else {
					btnSom.setForeground(Color.WHITE);
					btnSom.setBackground(new Color(0, 6, 180));
				}

			}
		});

	}

	/**************************************/
	// SALVANDO INFORMACOES (NOTA)
	
	private void salvar() {
		if (coletarInfo()) {
			JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
		} else {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao salvar.");
		}
		dispose();
		try {
			new TelaAlarmes().setVisible(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private boolean coletarInfo() {
		
		String[] valores = new String[5];
		valores[0] = lblSom.getText();
		valores[1] = txtHora.getText();
		valores[2] = Status;
		valores[3] = getDias();
		valores[4] = ""+Perfil.getCodigo();

		return salvarAlarme(valores);
	}

	private boolean salvarAlarme(String[] valores) {
		if (this.index >= 0)
			return Alarme.salvarAlarme(valores, alarmes.get(this.index));
		else
			return Alarme.salvarAlarme(valores, "null");
	}

	// FIM SALVAR INFO

	private void verificarHora() {
		int h = Integer.parseInt(txtHora.getText().substring(0, 2));
		int m = Integer.parseInt(txtHora.getText().substring(3));

		if (m > 59) {
			txtHora.setText(txtHora.getText().substring(0, 2) + "59");
		}
		if (h > 24) {
			txtHora.setText("00" + txtHora.getText().substring(3));
		}
	}
	
	private void setDias() {
		if (dias.charAt(0) == 'N')
			dom.setSelected(false);
		else
			dom.setSelected(true);
		
		if (dias.charAt(1) == 'N')
			seg.setSelected(false);
		else
			seg.setSelected(true);
		
		if (dias.charAt(2) == 'N')
			ter.setSelected(false);
		else
			ter.setSelected(true);
		
		if (dias.charAt(3) == 'N')
			qua.setSelected(false);
		else
			qua.setSelected(true);
		
		if (dias.charAt(4) == 'N')
			qui.setSelected(false);
		else
			qui.setSelected(true);
		
		if (dias.charAt(5) == 'N')
			sex.setSelected(false);
		else
			sex.setSelected(true);
		
		if (dias.charAt(6) == 'N')
			sab.setSelected(false);
		else
			sab.setSelected(true);
	}
	
	public static void setSom(String s) {
		som = s;
		lblSom.setText(som);
	}
	
	private void alterarStatus() {
		if (i == 0)
			Status = "A";
		else if (i == 1)
			Status = "B";
		else if (i == 2)
			Status = "C";
	}
	
	private void setStatus() {
		if (Status.equals("A")) {
			status.setText(allStatus[0]);
			i = 0;
		}
		else if (Status.equals("B")) {
			status.setText(allStatus[1]);
			i = 1;
			setCheckBoxesEditable(false);
		}
		else if (Status.equals("C")) {
			status.setText(allStatus[2]);
			i = 2;
		}
	}
	
	private String getDias() {
		dias = "";
		
		ArrayList<JCheckBox> al = new ArrayList<>();
		al.add(dom);
		al.add(seg);
		al.add(ter);
		al.add(qua);
		al.add(qui);
		al.add(sex);
		al.add(sab);
		
		for (JCheckBox box : al) {
			if (box.isSelected())
				dias += "S";
			else
				dias += "N";
		}
		
		return dias;
		
	}
	
	private void setCheckBoxesEditable(boolean e) {
		dom.setEnabled(e);
		seg.setEnabled(e);
		ter.setEnabled(e);
		qua.setEnabled(e);
		qui.setEnabled(e);
		sex.setEnabled(e);
		sab.setEnabled(e);
	}

}
