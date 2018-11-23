package telasMain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import objetos.DataBase;
import objetos.Jogo;
import objetos.Perfil;
import telasMenu.MenuEntretenimento;

public class TelaPontuacoes extends JFrame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panelNome, panelScore;
	private ResultSet query;
	
	private List nomes, scores;
	private JLabel lblReset, lblVoltar, playerNome, playerScore;

	// vertex digital arts

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPontuacoes frame = new TelaPontuacoes();
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
			panelNome.setBackground(new Color(200, 200, 200));
			panelScore.setBackground(new Color(200, 200, 200));
			
			lblReset.setForeground(Color.BLACK);
			lblVoltar.setForeground(Color.BLACK);
			playerNome.setForeground(Color.BLACK);
			playerScore.setForeground(Color.BLACK);
			
			nomes.setBackground(Color.LIGHT_GRAY);
			nomes.setForeground(Color.black);

			scores.setBackground(Color.LIGHT_GRAY);
			scores.setForeground(Color.black);
			
			

		} else if (Perfil.getTema().equals("escuro")) {
			contentPane.setBackground(new Color(0, 6, 45));
			panelNome.setBackground(new Color(0, 6, 60));
			panelScore.setBackground(new Color(0, 6, 60));
			
			lblReset.setForeground(Color.WHITE);
			lblVoltar.setForeground(Color.WHITE);
			playerNome.setForeground(Color.WHITE);
			playerScore.setForeground(Color.WHITE);
			
			nomes.setBackground(Color.GRAY);
			nomes.setForeground(Color.WHITE);

			scores.setBackground(Color.GRAY);
			scores.setForeground(Color.WHITE);
		}
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public TelaPontuacoes() throws SQLException {
		addKeyListener(this);
		setResizable(false);
		setTitle("Pontuações");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblReset = new JLabel("Reset score");
		lblReset.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblReset.setBounds(15, 250, 90, 14);
		contentPane.add(lblReset);

		lblVoltar = new JLabel("Voltar");
		lblVoltar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblVoltar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVoltar.setBounds(390, 250, 46, 14);
		contentPane.add(lblVoltar);

		nomes = new List();
		scores = new List();

		// Preechendo as listas
		DataBase.conectar();
		query = DataBase.enviarComandoPesquisa(
				"select usuarios.codigo, usuarios.nome, usuarios.sobrenome, pontuacao from spaceGamePontuacoes inner join usuarios on spaceGamePontuacoes.cod_usuario = usuarios.codigo  order by pontuacao desc");

		String score = "null";
		while (query.next()) {
			nomes.add(query.getString(2) + " " + query.getString(3));
			scores.add(query.getString(4));

			if (query.getString(1).equals("" + Perfil.getCodigo()))
				score = query.getString(4);
		}

		DataBase.fechar();
		// Fim preencher listas

		nomes.setFont(new Font("Dialog", Font.PLAIN, 14));
		nomes.setFocusable(false);
		nomes.setBounds(15, 10, 290, 170);
		contentPane.add(nomes);

		scores.setFont(new Font("Dialog", Font.PLAIN, 14));
		scores.setFocusable(false);
		scores.setBounds(310, 10, 100, 170);
		contentPane.add(scores);

		panelNome = new JPanel();
		panelNome.setBounds(15, 190, 290, 40);
		panelNome.setLayout(null);
		panelNome.setBorder(new LineBorder(Color.BLACK));
		contentPane.add(panelNome);

		playerNome = new JLabel(Perfil.getNome() + " " + Perfil.getSobrenome()); // Perfil.getNome() + " " +
																					// Perfil.getSobrenome()
		playerNome.setFont(new Font("Dialog", Font.PLAIN, 14));
		playerNome.setBounds(5, 5, 290, 25);
		panelNome.add(playerNome);

		panelScore = new JPanel();
		panelScore.setBounds(310, 190, 100, 40);
		panelScore.setLayout(null);
		panelScore.setBorder(new LineBorder(Color.BLACK));
		contentPane.add(panelScore);

		playerScore = new JLabel(score);
		playerScore.setFont(new Font("Dialog", Font.PLAIN, 14));
		playerScore.setBounds(5, 5, 100, 25);
		panelScore.add(playerScore);

		
		alterarTema();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case (KeyEvent.VK_F1):
			String[] escolha = { "Sim", "Não" };
			if (JOptionPane.showOptionDialog(this, "Deseja realmente zerar sua pontuação?", "Confirme",
					JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, escolha, escolha[1]) == 0) {
				dispose();
				try {
					Jogo.resetPontuacao();
					new TelaPontuacoes().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			break;
		case (KeyEvent.VK_F2):
			dispose();
			new MenuEntretenimento().setVisible(true);
			break;
		case (KeyEvent.VK_BACK_SPACE):
			try {
				dispose();
				new TelaInicio().setVisible(true);
			} catch (IOException io) {
				io.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
