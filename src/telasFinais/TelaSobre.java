package telasFinais;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import objetos.Perfil;
import telasMenu.MenuConfiguracoes;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextPane;

public class TelaSobre extends JFrame  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JLabel lblNome;
	private JLabel lblDataDePublicao;
	private JLabel lblDadudispositivoDe;
	private JLabel data;
	private JTextPane txtpnODadudispositivo;
	private JLabel lblOpes;
	private JPanel panel;
	private JLabel lblDadu;
	private JLabel lblNomeAutores;
	private JLabel lblAutores;
	private JLabel versao;
	private JLabel lblVerso;
	private JLabel lblInformacoes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaSobre frame = new TelaSobre();
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
			lblNome.setForeground(Color.black);
			lblDataDePublicao.setForeground(Color.black);
			lblDadudispositivoDe.setForeground(Color.black);
			data.setForeground(Color.black);
			txtpnODadudispositivo.setBackground(Color.white);
			txtpnODadudispositivo.setForeground(Color.black);
			lblOpes.setForeground(Color.black);
			panel.setBackground(new Color(220,220,220,220));
			lblDadu.setForeground(Color.black);
			lblNomeAutores.setForeground(Color.black);
			lblAutores.setForeground(Color.black);
			versao.setForeground(Color.black);
			lblVerso.setForeground(Color.black);
			lblInformacoes.setForeground(Color.black);
			


		} else if (Perfil.getTema().equals("escuro")) {
			contentPane.setBackground(new Color(0, 6, 45));
			lblNome.setForeground(Color.white);
			lblDataDePublicao.setForeground(Color.white);
			lblDadudispositivoDe.setForeground(Color.white);
			data.setForeground(Color.white);
			txtpnODadudispositivo.setBackground(Color.GRAY);
			txtpnODadudispositivo.setForeground(Color.white);
			lblOpes.setForeground(Color.white);
			panel.setBackground(new Color(105,105,105,105));
			lblDadu.setForeground(Color.white);
			lblNomeAutores.setForeground(Color.white);
			lblAutores.setForeground(Color.white);
			versao.setForeground(Color.white);
			lblVerso.setForeground(Color.white);
			lblInformacoes.setForeground(Color.white);

		}
	}

	
	/**
	 * Create the frame.
	 */
	public TelaSobre() {
		setResizable(false);
		requestFocus();
		setTitle("Sobre D.A.D.U");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 309);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNome = new JLabel("Nome do  dispositivo: ");
		lblNome.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNome.setBounds(19, 40, 160, 14);
		contentPane.add(lblNome);
		
		lblDataDePublicao = new JLabel("Data de publica\u00E7\u00E3o: ");
		lblDataDePublicao.setFont(new Font("Dialog", Font.BOLD, 13));
		lblDataDePublicao.setBounds(136, 101, 160, 14);
		contentPane.add(lblDataDePublicao);
		
		lblDadudispositivoDe = new JLabel("D.A.DU (Dispositivo de Ajuda Di\u00E1ria ao Usu\u00E1rio)");
		lblDadudispositivoDe.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblDadudispositivoDe.setBounds(80, 58, 340, 14);
		contentPane.add(lblDadudispositivoDe);
		
		data = new JLabel("22/11/2018");
		data.setFont(new Font("Dialog", Font.PLAIN, 13));
		data.setBounds(290, 101, 76, 14);
		contentPane.add(data);
		
		txtpnODadudispositivo = new JTextPane();
		txtpnODadudispositivo.setForeground(Color.BLACK);
		txtpnODadudispositivo.setBackground(Color.WHITE);
		txtpnODadudispositivo.setEditable(false);
		txtpnODadudispositivo.setText("O D.A.D.U (Dispositivo de Ajuda Di\u00E1ria ao Usu\u00E1rio) \u00E9 um dispositivo que traz v\u00E1rias funcionalidades, afim de ser usada para de ajudar em diversas \u00E1reas no cotidiano do usu\u00E1rio atrav\u00E9s de ferramentas que facilitem tarefas do dia-a-dia.");
		txtpnODadudispositivo.setBounds(20, 140, 403, 101);
		contentPane.add(txtpnODadudispositivo);
		txtpnODadudispositivo.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {

				case (KeyEvent.VK_F2):
					dispose();
				MenuConfiguracoes.main(null);
					break;
				}
		}});
		
		lblOpes = new JLabel("Voltar");
		lblOpes.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblOpes.setBounds(378, 255, 56, 14);
		contentPane.add(lblOpes);
		
		panel = new JPanel();
		panel.setBackground(new Color(220, 220, 220));
		panel.setBounds(10, 11, 424, 243);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblDadu = new JLabel("D.A.D.U");
		lblDadu.setBounds(182, 5, 80, 21);
		panel.add(lblDadu);
		lblDadu.setFont(new Font("Dialog", Font.BOLD, 16));
		
		lblNomeAutores = new JLabel("Dahan Schuscer & Ingrid Pauline");
		lblNomeAutores.setBounds(79, 66, 232, 14);
		panel.add(lblNomeAutores);
		lblNomeAutores.setFont(new Font("Dialog", Font.PLAIN, 13));
		
		lblAutores = new JLabel("Autores: ");
		lblAutores.setBounds(10, 66, 65, 14);
		panel.add(lblAutores);
		lblAutores.setFont(new Font("Dialog", Font.BOLD, 12));
		
		versao = new JLabel("1.0");
		versao.setBounds(67, 91, 46, 14);
		panel.add(versao);
		versao.setFont(new Font("Dialog", Font.PLAIN, 13));
		
		lblVerso = new JLabel("Vers\u00E3o:");
		lblVerso.setBounds(10, 91, 52, 14);
		panel.add(lblVerso);
		lblVerso.setFont(new Font("Dialog", Font.BOLD, 12));
		
		lblInformacoes = new JLabel("Informa\u00E7\u00F5es:");
		lblInformacoes.setBounds(10, 110, 105, 18);
		panel.add(lblInformacoes);
		lblInformacoes.setFont(new Font("Dialog", Font.BOLD, 13));
		
		alterarTema();
		
	}
	}