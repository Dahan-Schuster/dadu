package telasFinais;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.towel.swing.img.JImagePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import objetos.BufferedImageLoader;
import objetos.Calculadora;
import objetos.SintaxeIncorretaException;
import telasMenu.MenuFerramentas;

public class TelaCalculadora extends JFrame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	// private static final long serialVersionUID = 3915434782005199322L;
	private JImagePanel contentPane;
	private JTextField txtMain;
	private JLabel lblConta;
	private JLabel mais;
	private JLabel menos;
	private JLabel div;
	private JLabel mult;
	private JLabel igual;
	private JLabel voltar;
	private JLabel avancado;
	private JPopupMenu popAvancado;

	/**
	 * Launch the application.
	 * 
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		TelaCalculadora frame = new TelaCalculadora();
		frame.setVisible(true);
	}

	// CONSTRUTOR

	public TelaCalculadora() throws IOException {
		addKeyListener(this);
		requestFocus();
		setSize(450, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Calculadora");
		setLocationRelativeTo(null);
		contentPane = new JImagePanel(loadImage("/calc-wp.png"));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		txtMain = new JTextField();
		txtMain.setBounds(75, 15, 300, 60);
		txtMain.setForeground(new Color(100, 200, 255));
		txtMain.setFont(new Font("Dialog", Font.PLAIN, 45));
		txtMain.setFocusable(false);
		contentPane.add(txtMain);

		lblConta = new JLabel();
		lblConta.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblConta.setBounds(75, 75, 299, 60);
		lblConta.setBorder(new LineBorder(null));
		lblConta.setFont(new Font("Dialog", Font.PLAIN, 45));
		lblConta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConta.setBackground(new Color(240, 240, 240));
		lblConta.setOpaque(true);
		contentPane.add(lblConta);

		mais = new JLabel("+");
		mais.setFont(new Font("Dialog", Font.PLAIN, 30));
		mais.setBounds(205, 130, 50, 50);
		mais.setHorizontalAlignment(SwingConstants.CENTER);
		mais.setBackground(Color.white);

		mais.setOpaque(true);
		contentPane.add(mais);

		menos = new JLabel("-");
		menos.setFont(new Font("Dialog", Font.PLAIN, 40));
		menos.setBounds(206, 220, 50, 50);
		menos.setHorizontalAlignment(SwingConstants.CENTER);
		menos.setBackground(Color.white);
		menos.setOpaque(true);
		contentPane.add(menos);

		div = new JLabel("÷");
		div.setFont(new Font("Dialog", Font.PLAIN, 30));
		div.setBounds(155, 180, 50, 40);
		div.setHorizontalAlignment(SwingConstants.CENTER);
		div.setBackground(Color.white);
		div.setOpaque(true);
		contentPane.add(div);

		mult = new JLabel("×");
		mult.setFont(new Font("Dialog", Font.PLAIN, 30));
		mult.setBounds(250, 180, 50, 40);
		mult.setHorizontalAlignment(SwingConstants.CENTER);
		mult.setBackground(Color.white);
		mult.setOpaque(true);
		contentPane.add(mult);

		igual = new JLabel("=");
		igual.setFont(new Font("Dialog", Font.PLAIN, 30));
		igual.setBounds(205, 180, 50, 40);
		igual.setHorizontalAlignment(SwingConstants.CENTER);
		igual.setBackground(Color.white);
		igual.setOpaque(true);
		contentPane.add(igual);

		voltar = new JLabel("Voltar");
		voltar.setOpaque(true);
		voltar.setBackground(Color.white);
		voltar.setBounds(380, 250, 60, 20);
		voltar.setHorizontalAlignment(SwingConstants.RIGHT);
		voltar.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPane.add(voltar);

		avancado = new JLabel("Avançado");
		avancado.setOpaque(true);
		avancado.setBackground(Color.WHITE);
		avancado.setBounds(10, 250, 80, 20);
		avancado.setHorizontalAlignment(SwingConstants.LEFT);
		avancado.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPane.add(avancado);

		popAvancado = new JPopupMenu();

		Font fonteMenus = new Font("Dialog", Font.PLAIN, 20);

		JMenuItem sin = new JMenuItem("sin");
		JMenuItem cos = new JMenuItem("cos");
		JMenuItem tan = new JMenuItem("tan");
		JMenuItem sqrt = new JMenuItem("Raiz Q");
		JMenuItem quad = new JMenuItem("x^2");
		JMenuItem cubo = new JMenuItem("x^3");
		// JMenuItem pot = new JMenuItem("x^y");

		sin.setFont(fonteMenus);
		cos.setFont(fonteMenus);
		tan.setFont(fonteMenus);
		sqrt.setFont(fonteMenus);
		quad.setFont(fonteMenus);
		cubo.setFont(fonteMenus);

		popAvancado.add(sin);
		popAvancado.add(cos);
		popAvancado.add(tan);
		popAvancado.add(sqrt);
		popAvancado.add(quad);
		popAvancado.add(cubo);

		// EVENTOS

		lblConta.addPropertyChangeListener(new PropertyChangeListener() {
			Double d;

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				try {
					d = (Double) Double.parseDouble(lblConta.getText());
				} catch (NumberFormatException e) {
					d = null;
				}

				if (evt.getPropertyName().equalsIgnoreCase("Text")) {

					if (lblConta.getText().length() > 10 && lblConta.getText().length() < 16) {
						lblConta.setFont(new Font("Dialog", Font.PLAIN, 30));

					} else if (lblConta.getText().length() > 15) {
						if (d == null) {
							lblConta.setFont(new Font("Dialog", Font.PLAIN, 15));
						} else {
							lblConta.setFont(new Font("Dialog", Font.PLAIN, 20));
						}

					} else
						lblConta.setFont(new Font("Dialog", Font.PLAIN, 45));
				}

			}
		});

		sin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				calculoAvancado(sin);
			}
		});

		cos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				calculoAvancado(cos);
			}
		});

		tan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				calculoAvancado(tan);
			}
		});

		sqrt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				calculoAvancado(sqrt);
			}
		});

		quad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				calculoAvancado(quad);
			}
		});

		cubo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				calculoAvancado(cubo);
			}
		});

		// FIM EVENTOS

	}

	// FIM CONSTRUTOR

	// M�TODOS KEYLISTENER

	@Override
	public void keyTyped(KeyEvent e) {
		atualizarFonte();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Coleta o c�digo da tecla pressionada e envia ao m�todo verificarTecla
		verificarTecla(e, txtMain.getText());
		atualizarFonte();

	}

	@Override
	public void keyReleased(KeyEvent e) {
		atualizarFonte();
	}

	/*
	 * recebe um inteiro representando o c�digo da tecla pressionada testa se a
	 * tecla � uma das de controle da tela (setas e espac�o)
	 */

	private void verificarTecla(KeyEvent e, String conta) {
		String resultado = "0";
		int keyCode = e.getKeyCode();
		if (!e.isShiftDown() && !e.isAltDown() && !e.isAltGraphDown() && !e.isControlDown()) {
			switch (keyCode) {
			case KeyEvent.VK_SPACE:
				try {
					if (!txtMain.getText().isEmpty())
						resultado = Calculadora.organizarCalculos(conta);
					lblConta.setText("" + formatar(resultado));
				} catch (SintaxeIncorretaException s) {
					lblConta.setText(s.getMessage());
				}
				break;
			case KeyEvent.VK_UP:
				txtMain.setText(txtMain.getText() + "+");
				break;
			case KeyEvent.VK_DOWN:
				txtMain.setText(txtMain.getText() + "-");
				break;
			case KeyEvent.VK_LEFT:
				txtMain.setText(txtMain.getText() + "÷");
				break;
			case KeyEvent.VK_RIGHT:
				txtMain.setText(txtMain.getText() + "×");
				break;
			case KeyEvent.VK_BACK_SPACE:
				if (!txtMain.getText().isEmpty())
					txtMain.setText(txtMain.getText().substring(0, txtMain.getText().length() - 1));
				break;
			case KeyEvent.VK_PERIOD:
				txtMain.setText(txtMain.getText() + ".");
				break;
			case KeyEvent.VK_9:
			case KeyEvent.VK_NUMPAD9:
			case KeyEvent.VK_8:
			case KeyEvent.VK_NUMPAD8:
			case KeyEvent.VK_7:
			case KeyEvent.VK_NUMPAD7:
			case KeyEvent.VK_6:
			case KeyEvent.VK_NUMPAD6:
			case KeyEvent.VK_5:
			case KeyEvent.VK_NUMPAD5:
			case KeyEvent.VK_4:
			case KeyEvent.VK_NUMPAD4:
			case KeyEvent.VK_3:
			case KeyEvent.VK_NUMPAD3:
			case KeyEvent.VK_2:
			case KeyEvent.VK_NUMPAD2:
			case KeyEvent.VK_1:
			case KeyEvent.VK_NUMPAD1:
			case KeyEvent.VK_0:
			case KeyEvent.VK_NUMPAD0:
				if (txtMain.getText().length() < 30)
					txtMain.setText(txtMain.getText() + e.getKeyChar());
				break;
			case KeyEvent.VK_F1:
				popAvancado.show(contentPane, 0, 90);
				break;
			case KeyEvent.VK_F2:
				dispose();
				new MenuFerramentas().setVisible(true);
				break;
			}
		}
	}

	private void atualizarFonte() {
		if (txtMain.getText().length() > 10 && txtMain.getText().length() < 16)
			txtMain.setFont(new Font("Dialog", Font.PLAIN, 30));
		else if (txtMain.getText().length() > 15)
			txtMain.setFont(new Font("Dialog", Font.PLAIN, 20));
		else
			txtMain.setFont(new Font("Dialog", Font.PLAIN, 45));
	}

	// FIM M�TODOS KEYLISTENER

	private static Object formatar(String res) {
		double f = Double.parseDouble(res);
		int i = (int) f;

		if (i < f || i > f)
			return f;
		else
			return i;
	}

	private void calculoAvancado(JMenuItem calculo) {
		String resultado = "0";
		try {
			if (!txtMain.getText().isEmpty())
				resultado = Calculadora.organizarCalculos(txtMain.getText(), calculo.getText());
			lblConta.setText("" + formatar(resultado));
		} catch (SintaxeIncorretaException s) {
			lblConta.setText(s.getMessage());
		}
	}

	// Construtor do JImagePanel
	private static BufferedImage loadImage(String file) {
		return new BufferedImageLoader().loadImage(file);
	}
}
