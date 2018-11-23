package telasMain;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import objetos.Perfil;
import telasCadastro.TelaAdicionarEvento;
import telasMenu.MenuFerramentas;

import javax.swing.SwingConstants;
import javax.swing.JPopupMenu;

public class TelaCalendario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JTextField lblD1,lblD2,lblD3,lblD4,lblD5,lblD6,lblD7,lblD8,lblD9,lblD10,lblD11,lblD12,lblD13,lblD14,lblD15,lblD16,lblD17,lblD18,lblD19,lblD20,lblD21,lblD22,lblD23,lblD24,lblD25,lblD26;
    public static JTextField lblD27,lblD28,lblD29,lblD30,lblD31,lblD32,lblD33,lblD34,lblD35,lblD36,lblD37,lblD38,lblD39,lblD40,lblD41,lblD42;

	 int anoFixo=0,mesFixo=0,diaFixo=0; // variaveis do ano, dia e mes atual
	 int anoAltera=0,mesAltera=0,diaAltera=0;//variaveis que o usuario seleciona
	 int valorSelecao=0;
	private JComboBox<Integer> comboBoxAno;
	private JComboBox<String> comboBoxMes;
	private JLabel lblOpes;
	private JPopupMenu popupMenu;
	private JLabel lblVoltar;
	private JPanel panelDom;
	private JLabel lblDomigo;
	private JPanel panelSeg;
	private JLabel lblSegunda;
	private JPanel panelTer;
	private JLabel lblTerca;
	private JPanel panelQua;
	private JLabel lblQuarta;
	private JPanel panelQui;
	private JLabel lblQuinta;
	private JPanel panelSex;
	private JLabel lblSexta;
	private JPanel panelSab;
	private JLabel lblSabado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCalendario frame = new TelaCalendario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    public void alterarTema(){
    	if (Perfil.getTema().equals("claro")) {
			contentPane.setBackground(new Color(220, 220, 220));
			panelDom.setBackground(new Color(128,0,0));
			lblDomigo.setForeground(Color.white);
			panelSeg.setBackground(new Color(128,0,0));
			lblSegunda.setForeground(Color.white);
			panelTer.setBackground(new Color(128,0,0));
			lblTerca.setForeground(Color.white);
			panelQua.setBackground(new Color(128,0,0));
			lblQuarta.setForeground(Color.white);
			panelQui.setBackground(new Color(128,0,0));
			lblQuinta.setForeground(Color.white);
			panelSex.setBackground(new Color(128,0,0));
			lblSexta.setForeground(Color.white);
			panelSab.setBackground(new Color(128,0,0));
			lblSabado.setForeground(Color.white);
			lblOpes.setForeground(Color.black);
			lblVoltar.setForeground(Color.black);
			


		} else if (Perfil.getTema().equals("escuro")) {
			contentPane.setBackground(new Color(0, 6, 45));
			panelDom.setBackground(SystemColor.activeCaption);
			lblDomigo.setForeground(Color.white);
			panelSeg.setBackground(SystemColor.activeCaption);
			lblSegunda.setForeground(Color.white);
			panelTer.setBackground(SystemColor.activeCaption);
			lblTerca.setForeground(Color.white);
			panelQua.setBackground(SystemColor.activeCaption);
			lblQuarta.setForeground(Color.white);
			panelQui.setBackground(SystemColor.activeCaption);
			lblQuinta.setForeground(Color.white);
			panelSex.setBackground(SystemColor.activeCaption);
			lblSexta.setForeground(Color.white);
			panelSab.setBackground(SystemColor.activeCaption);
			lblOpes.setForeground(Color.white);
			lblVoltar.setForeground(Color.white);
			

		}
    	
    }
	/**
	 * Create the frame.
	 */
	public TelaCalendario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Calendário");
		setResizable(false);
		setBounds(100, 100, 438, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		

		comboBoxAno = new JComboBox<Integer>();
		comboBoxAno.setBounds(319, 25, 104, 20);
		comboBoxAno.setBackground(Color.WHITE);
		
	    comboBoxAno.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
		try{
		calendario();
		}catch(Exception e1){
			JOptionPane.showMessageDialog(null, "Informe um ano válido", "Erro", JOptionPane.INFORMATION_MESSAGE);
		}
		}});
		contentPane.add(comboBoxAno);
		
		
		comboBoxMes = new JComboBox<String>();
		comboBoxMes.setBounds(10, 25, 140, 20);
		comboBoxMes.setBackground(Color.WHITE);
		comboBoxMes.addItem("JANEIRO");
		comboBoxMes.addItem("FEVEREIRO");
		comboBoxMes.addItem("MARÇO");
		comboBoxMes.addItem("ABRIL");
		comboBoxMes.addItem("MAIO");
		comboBoxMes.addItem("JUNHO");
		comboBoxMes.addItem("JULHO");
		comboBoxMes.addItem("AGOSTO");
		comboBoxMes.addItem("SETEMBRO");
		comboBoxMes.addItem("OUTUBRO");
		comboBoxMes.addItem("NOVEMBRO");
		comboBoxMes.addItem("DEZEMBRO");
		
        comboBoxMes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				diaAltera=diaFixo;
				calendario();
			}});
		contentPane.add(comboBoxMes);
		
		panelDom = new JPanel();
		panelDom.setBounds(10, 56, 60, 29);
		panelDom.setBackground(SystemColor.activeCaption);
		contentPane.add(panelDom);
		panelDom.setLayout(null);
		
		lblDomigo = new JLabel("Dom");
		lblDomigo.setBounds(6, 5, 48, 19);
		lblDomigo.setForeground(Color.WHITE);
		panelDom.add(lblDomigo);
		lblDomigo.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		panelSeg = new JPanel();
		panelSeg.setBounds(72, 56, 67, 29);
		panelSeg.setBackground(SystemColor.activeCaption);
		contentPane.add(panelSeg);
		
		lblSegunda = new JLabel("Seg");
		lblSegunda.setForeground(Color.WHITE);
		panelSeg.add(lblSegunda);
		lblSegunda.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		panelTer = new JPanel();
		panelTer.setBounds(141, 56, 52, 29);
		panelTer.setBackground(SystemColor.activeCaption);
		contentPane.add(panelTer);
		panelTer.setLayout(null);
		
		lblTerca = new JLabel("Ter");
		lblTerca.setBounds(7, 5, 37, 19);
		lblTerca.setForeground(Color.WHITE);
		panelTer.add(lblTerca);
		lblTerca.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		panelQua = new JPanel();
		panelQua.setBounds(195, 56, 52, 29);
		panelQua.setBackground(SystemColor.activeCaption);
		contentPane.add(panelQua);
		panelQua.setLayout(null);
		
		lblQuarta = new JLabel("Qua");
		lblQuarta.setBounds(4, 5, 44, 19);
		lblQuarta.setForeground(Color.WHITE);
		panelQua.add(lblQuarta);
		lblQuarta.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		panelQui = new JPanel();
		panelQui.setBounds(249, 56, 57, 29);
		panelQui.setBackground(SystemColor.activeCaption);
		contentPane.add(panelQui);
		panelQui.setLayout(null);
		
		lblQuinta = new JLabel("Qui");
		lblQuinta.setBounds(7, 5, 42, 19);
		lblQuinta.setForeground(Color.WHITE);
		panelQui.add(lblQuinta);
		lblQuinta.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		panelSex = new JPanel();
		panelSex.setBounds(308, 56, 52, 29);
		panelSex.setBackground(SystemColor.activeCaption);
		contentPane.add(panelSex);
		panelSex.setLayout(null);
		
		lblSexta = new JLabel("Sex");
		lblSexta.setBounds(8, 5, 35, 19);
		lblSexta.setForeground(Color.WHITE);
		panelSex.add(lblSexta);
		lblSexta.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		panelSab = new JPanel();
		panelSab.setBounds(362, 56, 61, 29);
		panelSab.setBackground(SystemColor.activeCaption);
		contentPane.add(panelSab);
		panelSab.setLayout(null);
		
		lblSabado = new JLabel("Sab");
		lblSabado.setBounds(6, 5, 49, 19);
		lblSabado.setForeground(Color.WHITE);
		panelSab.add(lblSabado);
		lblSabado.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(10, 83, 413, 137);
		contentPane.add(panel_6);
		panel_6.setLayout(null);
		
		lblD1 = new JTextField("0");
		lblD1.setEditable(false);
		lblD1.setBounds(0, 0, 60, 20);
		lblD1.setHorizontalAlignment(SwingConstants.CENTER);
		lblD1.setForeground(new Color(25, 25, 112));
		lblD1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_6.add(lblD1);
		
		lblD2 = new JTextField("0");
		lblD2.setEditable(false);
		lblD2.setBounds(61, 0, 68, 20);
		lblD2.setHorizontalAlignment(SwingConstants.CENTER);
		lblD2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_6.add(lblD2);
		
		lblD3 = new JTextField("0");
		lblD3.setEditable(false);
		lblD3.setBounds(130, 0, 53, 20);
		lblD3.setHorizontalAlignment(SwingConstants.CENTER);
		lblD3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_6.add(lblD3);
		
		lblD4 = new JTextField("0");
		lblD4.setEditable(false);
		lblD4.setBounds(185, 0, 52, 20);
		lblD4.setHorizontalAlignment(SwingConstants.CENTER);
		lblD4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_6.add(lblD4);
		
		lblD5 = new JTextField("0");
		lblD5.setEditable(false);
		lblD5.setBounds(238, 0, 58, 20);
		lblD5.setHorizontalAlignment(SwingConstants.CENTER);
		lblD5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_6.add(lblD5);
		
		lblD6 = new JTextField("0");
		lblD6.setEditable(false);
		lblD6.setBounds(298, 0, 52, 20);
		lblD6.setHorizontalAlignment(SwingConstants.CENTER);
		lblD6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_6.add(lblD6);
		
		lblD7 = new JTextField("0");
		lblD7.setEditable(false);
		lblD7.setBounds(351, 0, 62, 20);
		lblD7.setHorizontalAlignment(SwingConstants.CENTER);
		lblD7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_6.add(lblD7);
		
		lblD8 = new JTextField("0");
		lblD8.setEditable(false);
		lblD8.setHorizontalAlignment(SwingConstants.CENTER);
		lblD8.setForeground(new Color(25, 25, 112));
		lblD8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD8.setBounds(0, 22, 60, 20);
		panel_6.add(lblD8);
		
		lblD9 = new JTextField("0");
		lblD9.setEditable(false);
		lblD9.setHorizontalAlignment(SwingConstants.CENTER);
		lblD9.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD9.setBounds(61, 22, 68, 20);
		panel_6.add(lblD9);
		
		lblD10 = new JTextField("0");
		lblD10.setEditable(false);
		lblD10.setHorizontalAlignment(SwingConstants.CENTER);
		lblD10.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD10.setBounds(130, 22, 53, 20);
		panel_6.add(lblD10);
		
		lblD11 = new JTextField("0");
		lblD11.setEditable(false);
		lblD11.setHorizontalAlignment(SwingConstants.CENTER);
		lblD11.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD11.setBounds(185, 22, 52, 20);
		panel_6.add(lblD11);
		
		lblD12 = new JTextField("0");
		lblD12.setEditable(false);
		lblD12.setHorizontalAlignment(SwingConstants.CENTER);
		lblD12.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD12.setBounds(238, 22, 58, 20);
		panel_6.add(lblD12);
		
		lblD13 = new JTextField("0");
		lblD13.setEditable(false);
		lblD13.setHorizontalAlignment(SwingConstants.CENTER);
		lblD13.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD13.setBounds(298, 22, 52, 20);
		panel_6.add(lblD13);
		
		lblD14 = new JTextField("0");
		lblD14.setEditable(false);
		lblD14.setHorizontalAlignment(SwingConstants.CENTER);
		lblD14.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD14.setBounds(351, 22, 62, 20);
		panel_6.add(lblD14);
		
		lblD15 = new JTextField("0");
		lblD15.setEditable(false);
		lblD15.setHorizontalAlignment(SwingConstants.CENTER);
		lblD15.setForeground(new Color(25, 25, 112));
		lblD15.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD15.setBounds(0, 45, 60, 20);
		panel_6.add(lblD15);
		
		lblD16 = new JTextField("0");
		lblD16.setEditable(false);
		lblD16.setHorizontalAlignment(SwingConstants.CENTER);
		lblD16.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD16.setBounds(61, 45, 68, 20);
		panel_6.add(lblD16);
		
		lblD17 = new JTextField("0");
		lblD17.setEditable(false);
		lblD17.setHorizontalAlignment(SwingConstants.CENTER);
		lblD17.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD17.setBounds(130, 45, 53, 20);
		panel_6.add(lblD17);
		
		lblD18 = new JTextField("0");
		lblD18.setEditable(false);
		lblD18.setHorizontalAlignment(SwingConstants.CENTER);
		lblD18.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD18.setBounds(185, 45, 52, 20);
		panel_6.add(lblD18);
		
		lblD19 = new JTextField("0");
		lblD19.setEditable(false);
		lblD19.setHorizontalAlignment(SwingConstants.CENTER);
		lblD19.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD19.setBounds(238, 45, 58, 20);
		panel_6.add(lblD19);
		
		lblD20 = new JTextField("0");
		lblD20.setEditable(false);
		lblD20.setHorizontalAlignment(SwingConstants.CENTER);
		lblD20.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD20.setBounds(298, 45, 52, 20);
		panel_6.add(lblD20);
		
		lblD21 = new JTextField("0");
		lblD21.setEditable(false);
		lblD21.setHorizontalAlignment(SwingConstants.CENTER);
		lblD21.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD21.setBounds(351, 45, 62, 20);
		panel_6.add(lblD21);
		
		lblD22 = new JTextField("0");
		lblD22.setEditable(false);
		lblD22.setHorizontalAlignment(SwingConstants.CENTER);
		lblD22.setForeground(new Color(25, 25, 112));
		lblD22.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD22.setBounds(0, 68, 60, 20);
		panel_6.add(lblD22);
		
		lblD23 = new JTextField("0");
		lblD23.setEditable(false);
		lblD23.setHorizontalAlignment(SwingConstants.CENTER);
		lblD23.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD23.setBounds(61, 68, 68, 20);
		panel_6.add(lblD23);
		
		lblD24 = new JTextField("0");
		lblD24.setEditable(false);
		lblD24.setHorizontalAlignment(SwingConstants.CENTER);
		lblD24.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD24.setBounds(130, 68, 53, 20);
		panel_6.add(lblD24);
		
		lblD25 = new JTextField("0");
		lblD25.setEditable(false);
		lblD25.setHorizontalAlignment(SwingConstants.CENTER);
		lblD25.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD25.setBounds(185, 68, 52, 20);
		panel_6.add(lblD25);
		
		lblD26 = new JTextField("0");
		lblD26.setEditable(false);
		lblD26.setHorizontalAlignment(SwingConstants.CENTER);
		lblD26.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD26.setBounds(238, 68, 58, 20);
		panel_6.add(lblD26);
		
		lblD27 = new JTextField("0");
		lblD27.setEditable(false);
		lblD27.setHorizontalAlignment(SwingConstants.CENTER);
		lblD27.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD27.setBounds(298, 68, 52, 20);
		panel_6.add(lblD27);
		
		lblD28 = new JTextField("0");
		lblD28.setEditable(false);
		lblD28.setHorizontalAlignment(SwingConstants.CENTER);
		lblD28.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD28.setBounds(351, 68, 62, 20);
		panel_6.add(lblD28);
		
		lblD29 = new JTextField("0");
		lblD29.setEditable(false);
		lblD29.setHorizontalAlignment(SwingConstants.CENTER);
		lblD29.setForeground(new Color(25, 25, 112));
		lblD29.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD29.setBounds(0, 93, 60, 20);
		panel_6.add(lblD29);
		
		lblD30 = new JTextField("0");
		lblD30.setEditable(false);
		lblD30.setHorizontalAlignment(SwingConstants.CENTER);
		lblD30.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD30.setBounds(61, 93, 68, 20);
		panel_6.add(lblD30);
		
		lblD31 = new JTextField("0");
		lblD31.setEditable(false);
		lblD31.setHorizontalAlignment(SwingConstants.CENTER);
		lblD31.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD31.setBounds(130, 93, 53, 20);
		panel_6.add(lblD31);
		
		lblD32 = new JTextField("0");
		lblD32.setEditable(false);
		lblD32.setHorizontalAlignment(SwingConstants.CENTER);
		lblD32.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD32.setBounds(185, 93, 52, 20);
		panel_6.add(lblD32);
		
		lblD33 = new JTextField("0");
		lblD33.setEditable(false);
		lblD33.setHorizontalAlignment(SwingConstants.CENTER);
		lblD33.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD33.setBounds(238, 93, 58, 20);
		panel_6.add(lblD33);
		
		lblD34 = new JTextField("0");
		lblD34.setEditable(false);
		lblD34.setHorizontalAlignment(SwingConstants.CENTER);
		lblD34.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD34.setBounds(298, 93, 52, 20);
		panel_6.add(lblD34);
		
		lblD35 = new JTextField("0");
		lblD35.setEditable(false);
		lblD35.setHorizontalAlignment(SwingConstants.CENTER);
		lblD35.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD35.setBounds(351, 93, 62, 20);
		panel_6.add(lblD35);
		
		lblD36 = new JTextField("");
		lblD36.setEditable(false);
		lblD36.setHorizontalAlignment(SwingConstants.CENTER);
		lblD36.setForeground(new Color(25, 25, 112));
		lblD36.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD36.setBounds(0, 116, 60, 20);
		panel_6.add(lblD36);
		
		lblD37 = new JTextField("");
		lblD37.setHorizontalAlignment(SwingConstants.CENTER);
		lblD37.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD37.setEditable(false);
		lblD37.setBounds(61, 116, 68, 20);
		panel_6.add(lblD37);
		
		lblD38 = new JTextField("");
		lblD38.setHorizontalAlignment(SwingConstants.CENTER);
		lblD38.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD38.setEditable(false);
		lblD38.setBounds(130, 116, 53, 20);
		panel_6.add(lblD38);
		
		lblD39 = new JTextField("");
		lblD39.setHorizontalAlignment(SwingConstants.CENTER);
		lblD39.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD39.setEditable(false);
		lblD39.setBounds(185, 116, 52, 20);
		panel_6.add(lblD39);
		
		lblD40 = new JTextField("");
		lblD40.setHorizontalAlignment(SwingConstants.CENTER);
		lblD40.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD40.setEditable(false);
		lblD40.setBounds(238, 116, 58, 20);
		panel_6.add(lblD40);
		
		lblD41 = new JTextField("");
		lblD41.setHorizontalAlignment(SwingConstants.CENTER);
		lblD41.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD41.setEditable(false);
		lblD41.setBounds(298, 116, 52, 20);
		panel_6.add(lblD41);
		
		lblD42 = new JTextField("");
		lblD42.setHorizontalAlignment(SwingConstants.CENTER);
		lblD42.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblD42.setEditable(false);
		lblD42.setBounds(351, 116, 62, 20);
		panel_6.add(lblD42);

		
		lblVoltar = new JLabel("Voltar");
		lblVoltar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblVoltar.setBounds(377, 236, 46, 14);
		contentPane.add(lblVoltar);
		
		lblOpes = new JLabel("Op\u00E7\u00F5es");
		lblOpes.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblOpes.setBounds(10, 238, 60, 14);
		contentPane.add(lblOpes);
		
		popupMenu = new JPopupMenu();
		JMenuItem AdicionarEvento= new JMenuItem("Adicionar Evento");
		popupMenu.add(AdicionarEvento);
		AdicionarEvento.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					dispose();
					new TelaAdicionarEvento().setVisible(true);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
			}
		});
		JMenuItem VerEvento= new JMenuItem("Lista de Eventos");
		popupMenu.add(VerEvento);
		VerEvento.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new TelaEventos(1).setVisible(true);
				
			}
		});
	
		
		comboBoxMes.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {

					switch (e.getKeyCode()) {
					
					case KeyEvent.VK_RIGHT:
						
						comboBoxAno.requestFocus();
						break;
					case KeyEvent.VK_F1:
						popupMenu.show(contentPane,0,215);
						break;
					case KeyEvent.VK_F2:
						dispose();
					new MenuFerramentas().setVisible(true);
					break;
					}
				}});
	
		comboBoxAno.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {
				
				case KeyEvent.VK_LEFT:
					
					comboBoxMes.requestFocus();
					break;
				case KeyEvent.VK_F1:
					popupMenu.show(contentPane,0,220);
					break;
				case KeyEvent.VK_F2:
					dispose();
				new MenuFerramentas().setVisible(true);
				break;
			}}});

		
		
		iniciarCalendario();
		calendario();
		alterarTema();
	}
	
	public void iniciarCalendario(){
		SimpleDateFormat Ano=new SimpleDateFormat("yyyy");
		SimpleDateFormat Mes=new SimpleDateFormat("MM");
		SimpleDateFormat Dia=new SimpleDateFormat("dd");
		
		//Recebendo A data atual
		anoFixo = Integer.parseInt(Ano.format(new Date()));
		mesFixo = Integer.parseInt(Mes.format(new Date()));
		diaFixo = Integer.parseInt(Dia.format(new Date()));
		
		//Adicionando varios anos ao ComboBox
		int anoMax=anoFixo+50;
		for(int i=1967 ;i<anoMax;i++){
			comboBoxAno.addItem(i);
		}
		//Deixando os comboBox selecionados no ano e mes atual
	    comboBoxAno.setSelectedItem(anoFixo);
	    comboBoxMes.setSelectedIndex(mesFixo-1);
		
	}
	public void calendario(){
	    lblD1.setText(""); lblD2.setText(""); lblD3.setText(""); lblD4.setText(""); lblD5.setText(""); lblD6.setText(""); lblD7.setText(""); lblD8.setText(""); lblD9.setText(""); lblD10.setText(""); lblD11.setText(""); lblD12.setText("");
	    lblD13.setText(""); lblD14.setText(""); lblD15.setText(""); lblD16.setText(""); lblD17.setText(""); lblD18.setText(""); lblD19.setText(""); lblD20.setText(""); lblD21.setText(""); lblD22.setText("");  lblD23.setText(""); lblD24.setText("");
	    lblD25.setText(""); lblD26.setText(""); lblD27.setText(""); lblD28.setText(""); lblD29.setText(""); lblD30.setText(""); lblD31.setText(""); lblD32.setText(""); lblD33.setText(""); lblD34.setText("");lblD35.setText("");
	
		mesAltera=comboBoxMes.getSelectedIndex()+1;
		anoAltera=Integer.parseInt(comboBoxAno.getSelectedItem().toString());
		
		int totalDeDias=0;
		if(mesAltera==1||mesAltera==3||mesAltera==5||mesAltera==7||mesAltera==8||mesAltera==10||mesAltera==12){
			totalDeDias=31;
		}if(mesAltera==4||mesAltera==6||mesAltera==9||mesAltera==11){
			totalDeDias=30;
		}if(mesAltera==2){
			if((anoAltera%4)==0){
				totalDeDias=29;
			}else{
				totalDeDias=28;
			}
		}
	   //Descobrindo qual dia da semana cai o 1 dia do mes selecionado
	   Calendar calendario=Calendar.getInstance();
	   calendario.set(anoAltera,(mesAltera-1),1);//o 1 significa que quer saber o primeiro dia
	   int dia=0;
	   int Semana=calendario.get(Calendar.DAY_OF_WEEK);
	   if(Semana==Calendar.SUNDAY){
		   dia=1;
	   } if(Semana==Calendar.MONDAY){
		   dia=2;
	   } if(Semana==Calendar.TUESDAY){
		   dia=3;
	   } if(Semana==Calendar.WEDNESDAY){
		   dia=4;
	   } if(Semana==Calendar.THURSDAY){
		   dia=5;
	   } if(Semana==Calendar.FRIDAY){
		   dia=6;
	   }
	   if(Semana==Calendar.SATURDAY){
		   dia=7;
	   }
	  metodosGrandesCalendario.setando1Dia(dia,totalDeDias,diaFixo,mesAltera,mesFixo,anoAltera,anoFixo);
	   
	}
	
	

	
}