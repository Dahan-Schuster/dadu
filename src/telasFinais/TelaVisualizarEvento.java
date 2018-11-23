package telasFinais;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import objetos.DataBase;
import objetos.Evento;
import objetos.Perfil;
import objetos.ValorIncorretoException;
import telasMain.TelaCalendario;
import telasMain.TelaEventos;

public class TelaVisualizarEvento extends JFrame {

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
	private ResultSet informacoes;
	private JPopupMenu popupMenu;
	private JLabel lblOpcoes;
	private JLabel lblTtulo, lblData, lblHoranicio, lblHoraDeTermino, lblVoltar;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaVisualizarEvento frame = new TelaVisualizarEvento();
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
			lblOpcoes.setForeground(Color.black);
			lblVoltar.setForeground(Color.black);
			
		} else if (Perfil.getTema().equals("escuro")) {
			contentPane.setBackground(new Color(0, 6, 45));
			panel.setBorder(new TitledBorder(new LineBorder(Color.WHITE), "Observações", TitledBorder.LEADING, TitledBorder.TOP, null, Color.white));
			panel.setBackground(new Color(0, 6, 60));
			panel.setForeground(Color.white);
			lblTtulo.setForeground(Color.white);
			lblData.setForeground(Color.white);
			lblHoranicio.setForeground(Color.white);
			lblHoraDeTermino.setForeground(Color.white);
			lblOpcoes.setForeground(Color.white);
			lblVoltar.setForeground(Color.white);

		}
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public TelaVisualizarEvento() throws ParseException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Adicionar Evento");
		setResizable(false);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTtulo = new JLabel("Título:");
		lblTtulo.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblTtulo.setBounds(10, 22, 46, 14);
		contentPane.add(lblTtulo);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(65, 20, 178, 20);
		txtTitulo.setEditable(false);
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
		txtData.setEditable(false);
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
		txtHoraInicio.setEditable(false);
		contentPane.add(txtHoraInicio);
		txtHoraInicio.setColumns(10);
		
		lblHoraDeTermino = new JLabel("Hora de Término:");
		lblHoraDeTermino.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblHoraDeTermino.setBounds(10, 112, 130, 14);
		contentPane.add(lblHoraDeTermino);
		
		txtHoraFim = new JFormattedTextField(maskHora);
		txtHoraFim.setBounds(150, 110, 80, 20);
		txtHoraFim.setEditable(false);
		contentPane.add(txtHoraFim);
		txtHoraFim.setColumns(10);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 150, 424, 70);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtObs = new JTextField();
		txtObs.setBounds(10, 32, 404, 20);
		txtObs.setEditable(false);
		panel.add(txtObs);
		txtObs.setColumns(10);
		
		
		lblVoltar = new JLabel("Voltar");
		lblVoltar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblVoltar.setBounds(388, 240, 46, 14);
		contentPane.add(lblVoltar);
		
		lblOpcoes = new JLabel("Op\u00E7\u00F5es");
		lblOpcoes.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblOpcoes.setBounds(10, 240, 57, 14);
		contentPane.add(lblOpcoes);
		
		alterarTema();
		
		preencherTextFields();
		
		popupMenu = new JPopupMenu();
		JMenuItem menuAdd = new JMenuItem("Alterar Dados");
		popupMenu.add(menuAdd);
		menuAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				txtTitulo.setEditable(true);
				txtHoraFim.setEditable(true);
				txtHoraInicio.setEditable(true);
				txtData.setEditable(true);
				txtObs.setEditable(true);
				lblOpcoes.setText("Salvar");;

				txtTitulo.requestFocus();

			}

		});

		JMenuItem menuDelete = new JMenuItem("Excluir");
		popupMenu.add(menuDelete);
		menuDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				Evento.excluirEvento(TelaEventos.codigo);
				new TelaEventos(0).setVisible(true);

			}
		});
		
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
					eventoF1();
				break;
				case KeyEvent.VK_F2:
					voltar();
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
					eventoF1();
				break;
				case KeyEvent.VK_F2:
					voltar();
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

					eventoF1();
				break;
				case KeyEvent.VK_UP:
					txtTitulo.requestFocus();
					break;
				case KeyEvent.VK_F2:
					voltar();
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

					eventoF1();
				break;
				case KeyEvent.VK_UP:
					txtHoraInicio.requestFocus();
					break;
				case KeyEvent.VK_F2:
					voltar();
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
					new TelaCalendario().setVisible(true);
					break;
				}
			}});
		
	}
	
	private void coletarInformacoes() throws SQLException, IOException {
		String[] valores = new String[5];

		if (txtTitulo.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"É obrigatório que seja preenchido o campo: Titulo ", "Erro",
					JOptionPane.INFORMATION_MESSAGE);

		} else {

			valores[0] = txtTitulo.getText();
			if (txtData.getText().contains("X")) {

				valores[1] = "";
			} else {

				try {
					valores[1] = formatarData(txtData.getText(), 0);
				} catch (ValorIncorretoException e) {
					e.printStackTrace();
				}
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

	

			if (Evento.atualizarEvento(valores, TelaEventos.codigo)) {
				JOptionPane.showMessageDialog(null, "Evento Alterado com sucesso!", "Evento Alterado",
						JOptionPane.INFORMATION_MESSAGE);

			} else {
				JOptionPane.showMessageDialog(null, "Erro ao Alterar informaÃ§Ãµes!", "Erro",
						JOptionPane.INFORMATION_MESSAGE);

			}
		}

	}

	private void preencherTextFields() {
		try {
			DataBase.conectar();
			informacoes = DataBase.lerTabela("eventos", "cod_evento", "" + TelaEventos.codigo, "cod_usuario",
					"" + Perfil.getCodigo());

			while (informacoes.next()) {

				txtTitulo.setText(informacoes.getString(2));
				txtData.setText(formatarData(informacoes.getString(3), 1));
				txtHoraInicio.setText(informacoes.getString(4));
				txtHoraFim.setText(informacoes.getString(5));
				txtObs.setText(informacoes.getString(6));
			}
			DataBase.fechar();
		} catch (SQLException | ValorIncorretoException sql) {
			sql.printStackTrace();
		}
	}
	private void eventoF1() {
		if (txtTitulo.isEditable()) {
			try {
				coletarInformacoes();
			} catch (SQLException | IOException e1) {
				e1.printStackTrace();
			}
		} else {

			popupMenu.show(contentPane, 0, 215);
		}
	}
	private void voltar() {
		dispose();
		new TelaEventos(0).setVisible(true);
	}
	
	private String formatarData(String data, int tipo) throws ValorIncorretoException {
		String dataFormatada = "";
		
		String[] partes;
		
		if (tipo == 0) { // enviar para o banco
			partes = data.split("/");
			dataFormatada = String.format("%s-%s-%s", partes[2], partes[1], partes[0]); // yyyy-MM-dd
		} else if (tipo == 1) { // receber do banco
			partes = data.split("-");
			dataFormatada = String.format("%s/%s/%s", partes[2], partes[1], partes[0]); // dd/MM/yyyy
		} else { // flag incorreta
			throw new ValorIncorretoException("formatarData(String data, int tipo");
		}
		
		return dataFormatada;
	}

}