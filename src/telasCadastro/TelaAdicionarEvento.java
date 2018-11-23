package telasCadastro;

import java.awt.EventQueue;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import objetos.Evento;
import objetos.Perfil;
import telasMain.TelaEventos;

import javax.swing.border.LineBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;


public class TelaAdicionarEvento extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane, panel;
	private JTextField txtTitulo;
	private JTextField txtData;
	private JTextField txtHoraInicio;
	private JTextField txtHoraFim;
	private JTextField txtObs;
	private JLabel lblTtulo, lblData, lblHoranicio, lblHoraDeTermino, lblVoltar, lblSalvar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAdicionarEvento frame = new TelaAdicionarEvento();
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
			panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Observações", TitledBorder.LEADING, TitledBorder.TOP, null, Color.black));
			panel.setBackground(new Color(200, 200, 200));
			panel.setForeground(Color.black);
			lblTtulo.setForeground(Color.black);
			lblData.setForeground(Color.black);
			lblHoranicio.setForeground(Color.black);
			lblHoraDeTermino.setForeground(Color.black);
			lblVoltar.setForeground(Color.black);
			lblSalvar.setForeground(Color.black);
			
		} else if (Perfil.getTema().equals("escuro")) {
			contentPane.setBackground(new Color(0, 6, 45));
			panel.setBorder(new TitledBorder(new LineBorder(Color.WHITE), "Observações", TitledBorder.LEADING, TitledBorder.TOP, null, Color.white));
			panel.setBackground(new Color(0, 6, 60));
			panel.setForeground(Color.white);
			lblTtulo.setForeground(Color.white);
			lblData.setForeground(Color.white);
			lblHoranicio.setForeground(Color.white);
			lblHoraDeTermino.setForeground(Color.white);
			lblVoltar.setForeground(Color.white);
			lblSalvar.setForeground(Color.white);

		}
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public TelaAdicionarEvento() throws ParseException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Adicionar Evento");
		setResizable(false);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTtulo = new JLabel("Título:");
		lblTtulo.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblTtulo.setBounds(10, 22, 46, 14);
		contentPane.add(lblTtulo);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(65, 20, 178, 20);
		contentPane.add(txtTitulo);
		txtTitulo.setColumns(10);
		
		lblData = new JLabel("Data:");
		lblData.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblData.setBounds(260, 22, 50, 14);
		contentPane.add(lblData);		
		
		MaskFormatter maskData = new MaskFormatter("##/##/####");
		maskData.setPlaceholderCharacter('X');

		
		txtData = new JFormattedTextField(maskData);
		txtData.setBounds(309, 20, 112, 20);
		contentPane.add(txtData);
		txtData.setColumns(10);
		
		lblHoranicio = new JLabel("Hora de início:");
		lblHoranicio.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblHoranicio.setBounds(10, 72, 110, 14);
		contentPane.add(lblHoranicio);
		
		
		MaskFormatter maskHora = new MaskFormatter("##:##");
		maskHora.setPlaceholderCharacter('X');
		
		txtHoraInicio = new JFormattedTextField(maskHora);
		txtHoraInicio.setBounds(150, 70, 80, 20);
		contentPane.add(txtHoraInicio);
		txtHoraInicio.setColumns(10);
		
		lblHoraDeTermino = new JLabel("Hora de Término:");
		lblHoraDeTermino.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblHoraDeTermino.setBounds(10, 112, 130, 14);
		contentPane.add(lblHoraDeTermino);
		
		txtHoraFim = new JFormattedTextField(maskHora);
		txtHoraFim.setBounds(150, 110, 80, 20);
		contentPane.add(txtHoraFim);
		txtHoraFim.setColumns(10);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 150, 424, 70);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtObs = new JTextField();
		txtObs.setBounds(10, 32, 404, 20);
		panel.add(txtObs);
		txtObs.setColumns(10);
		
		
		lblSalvar = new JLabel("Salvar");
		lblSalvar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblSalvar.setBounds(10, 240, 46, 14);
		contentPane.add(lblSalvar);
		
		lblVoltar = new JLabel("Voltar");
		lblVoltar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblVoltar.setBounds(388, 240, 46, 14);
		contentPane.add(lblVoltar);
		
		alterarTema();
		
		txtTitulo.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					txtData.requestFocus();
					break;
				case KeyEvent.VK_DOWN:
					txtHoraInicio.requestFocus();
					break;
				case KeyEvent.VK_F1:
					try {
						coletarInformacoes();
					} catch (SQLException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				break;
				case KeyEvent.VK_F2:
					dispose();
					new TelaEventos(0).setVisible(true);
					break;
				}
			}});
		
		txtData.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					txtTitulo.requestFocus();
					break;
				case KeyEvent.VK_DOWN:
					txtHoraInicio.requestFocus();
					break;
				case KeyEvent.VK_F1:
					try {
						coletarInformacoes();
					} catch (SQLException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				break;
				case KeyEvent.VK_F2:
					dispose();
					new TelaEventos(0).setVisible(true);
					break;
				}
			}});
		txtHoraInicio.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					txtHoraFim.requestFocus();
					break;
				case KeyEvent.VK_DOWN:
					txtHoraFim.requestFocus();
					break;
				case KeyEvent.VK_F1:
					try {
						coletarInformacoes();
					} catch (SQLException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				break;
				case KeyEvent.VK_UP:
					txtTitulo.requestFocus();
					break;
				case KeyEvent.VK_F2:
					dispose();
					new TelaEventos(0).setVisible(true);
					break;
				}
			}});
		txtHoraFim.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					txtHoraInicio.requestFocus();
					break;
				case KeyEvent.VK_DOWN:
					txtObs.requestFocus();
					break;
				case KeyEvent.VK_F1:
					try {
						coletarInformacoes();
					} catch (SQLException | IOException e1) {
						e1.printStackTrace();
					}
				break;
				case KeyEvent.VK_UP:
					txtHoraInicio.requestFocus();
					break;
				case KeyEvent.VK_F2:
					dispose();
					new TelaEventos(0).setVisible(true);
					break;
				}
			}});
		
		txtObs.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {
				case KeyEvent.VK_F1:
					try {
						coletarInformacoes();
					} catch (SQLException | IOException e1) {
						e1.printStackTrace();
					}
				break;
				case KeyEvent.VK_UP:
					txtHoraInicio.requestFocus();
					break;
				case KeyEvent.VK_F2:
					dispose();
					new TelaEventos(0).setVisible(true);
					break;
				}
			}});
		

	}
	private void coletarInformacoes() throws SQLException, IOException {
		String[] valores = new String[6];

		if (txtTitulo.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"É obrigatório que seja preenchido o campo: Titulo ", "Erro",
					JOptionPane.INFORMATION_MESSAGE);

		} else {

			valores[0] = txtTitulo.getText();
			if (txtData.getText().contains("X")) {

				valores[1] = "";
			} else {

				valores[1] = formatarData(txtData.getText());
			}
			if (txtHoraInicio.getText().contains("X")) {

				valores[2] = " ";
			} else {

				valores[2] = txtHoraInicio.getText();
			}


			if (txtHoraFim.getText().contains("X")) {

				valores[3] = " ";
			} else {

				valores[3] = txtHoraFim.getText();
			}
			if (txtObs.getText().trim().isEmpty()) {

				valores[4] = " ";
			} else {

				valores[4] = txtObs.getText();
			}

	
			valores[5] = "" + Perfil.getCodigo();

			if (Evento.salvarEvento(valores)) {
				JOptionPane.showMessageDialog(null, "Evento Adicionado com sucesso!", "Evento Adicionado",
						JOptionPane.INFORMATION_MESSAGE);

			} else {
				JOptionPane.showMessageDialog(null, "Erro ao Adicionar!", "Erro", JOptionPane.INFORMATION_MESSAGE);

			}
		}

	}
	
	
	private String formatarData(String data) {
		String dataFormatada = "";
		
		String[] dataPartes = data.split("/");
		
		dataFormatada = String.format("%s-%s-%s", dataPartes[2], dataPartes[1], dataPartes[0]);
		
		return dataFormatada;
	}
}