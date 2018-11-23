package telasCadastro;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

import objetos.Documento;
import objetos.Nota;
import objetos.Perfil;
import telasMain.TelaInicio;
import telasMain.TelaNotas;

public class TelaAdicionarNota extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea bloco;
	private JLabel lblOpcoes, lblVoltar, lblEdit, lblData, lblHora, liMirtes;
	private JScrollPane scroll;
	private String cor = "C";
	private JPopupMenu popup, popCor;
	private JMenuItem menuAdd, menuApagar, menuFechar, menuCor, menuVoltar;
	private JMenuItem verm, lar, ama, verd, azul, roxo;
	private ArrayList<String> notas = new ArrayList<>();
	private int index;

	public static void main(String[] args) {
		TelaAdicionarNota frame;
		try {
			frame = new TelaAdicionarNota(-1, new ArrayList<String>());
			frame.setVisible(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void alterarTema() {
		if (Perfil.getTema().equals("claro")) {
			popup.setBackground(new Color(200, 200, 200));
			popup.setForeground(Color.black);
			popCor.setBackground(new Color(200, 200, 200));
			popCor.setForeground(Color.black);
			
			menuAdd.setBackground(new Color(200, 200, 200));
			menuAdd.setForeground(Color.black);
			menuApagar.setBackground(new Color(200, 200, 200));
			menuApagar.setForeground(Color.black);
			menuFechar.setBackground(new Color(200, 200, 200));
			menuFechar.setForeground(Color.black);
			menuCor.setBackground(new Color(200, 200, 200));
			menuCor.setForeground(Color.black);
			
			verm.setBackground(new Color(200, 200, 200));
			verm.setForeground(Color.black);
			lar.setBackground(new Color(200, 200, 200));
			lar.setForeground(Color.black);
			ama.setBackground(new Color(200, 200, 200));
			ama.setForeground(Color.black);
			verd.setBackground(new Color(200, 200, 200));
			verd.setForeground(Color.black);
			azul.setBackground(new Color(200, 200, 200));
			azul.setForeground(Color.black);
			roxo.setBackground(new Color(200, 200, 200));
			roxo.setForeground(Color.black);
			menuVoltar.setBackground(new Color(200, 200, 200));
			menuVoltar.setForeground(Color.black);

		} else if(Perfil.getTema().equals("escuro")) {
			popup.setBackground(new Color(0, 6, 60));
			popup.setForeground(Color.white);
			popCor.setBackground(new Color(0, 6, 60));
			popCor.setForeground(Color.white);
			
			menuAdd.setBackground(new Color(0, 6, 60));
			menuAdd.setForeground(Color.white);
			menuApagar.setBackground(new Color(0, 6, 60));
			menuApagar.setForeground(Color.white);
			menuFechar.setBackground(new Color(0, 6, 60));
			menuFechar.setForeground(Color.white);
			menuCor.setBackground(new Color(0, 6, 60));
			menuCor.setForeground(Color.white);
			
			verm.setBackground(new Color(0, 6, 60));
			verm.setForeground(Color.white);
			lar.setBackground(new Color(0, 6, 60));
			lar.setForeground(Color.white);
			ama.setBackground(new Color(0, 6, 60));
			ama.setForeground(Color.white);
			verd.setBackground(new Color(0, 6, 60));
			verd.setForeground(Color.white);
			azul.setBackground(new Color(0, 6, 60));
			azul.setForeground(Color.white);
			roxo.setBackground(new Color(0, 6, 60));
			roxo.setForeground(Color.white);
			menuVoltar.setBackground(new Color(0, 6, 60));
			menuVoltar.setForeground(Color.white);
			
		}
	}
	

	public TelaAdicionarNota(int index, ArrayList<String> notas) throws MySQLSyntaxErrorException,
			MySQLNonTransientConnectionException, CommunicationsException, SQLException {
		this.index = index;
		this.notas = notas;

		setTitle("Salvar nota");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);


		lblEdit = new JLabel("Última edição:");
		lblEdit.setBounds(15, 215, 90, 20);
		lblEdit.setFont(new Font("arial", Font.PLAIN, 12));
		lblEdit.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(lblEdit);

		lblHora = new JLabel();
		lblHora.setBounds(110, 215, 45, 20);
		lblHora.setFont(new Font("arial", Font.PLAIN, 12));
		lblHora.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(lblHora);

		lblData = new JLabel();
		lblData.setBounds(155, 215, 90, 20);
		lblData.setFont(new Font("arial", Font.PLAIN, 12));
		lblData.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(lblData);

		bloco = new JTextArea();
		scroll = new JScrollPane(bloco, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		scroll.setBorder(new LineBorder(null));
		scroll.requestFocus();
		scroll.setBounds(15, 15, 420, 200);

		bloco.setLineWrap(true);
		bloco.setFont(new Font("Arial", Font.PLAIN, 16));
		bloco.setDocument(new Documento("", 255));

		contentPane.add(scroll);

		if (this.index >= 0) {
			
			String[] n = Nota.getNota(notas.get(index));
			bloco.setText(n[0]); // texto
			lblHora.setText(n[1]); // hora
			lblData.setText(n[2]); // data
			cor = n[3]; // cor
		}
		
		
		
		alterarCor(cor);

		// Label de limite de caracteres
		liMirtes = new JLabel("0/255");
		liMirtes.setBounds(365, 215, 70, 20);
		liMirtes.setFont(new Font("Arial", Font.PLAIN, 12));
		liMirtes.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(liMirtes);

		// Labels de ações
		lblOpcoes = new JLabel("Opções");
		lblOpcoes.setBounds(10, 250, 60, 20);
		lblOpcoes.setHorizontalAlignment(SwingConstants.LEFT);
		lblOpcoes.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPane.add(lblOpcoes);

		popCor = new JPopupMenu();
		popup = new JPopupMenu();
		
		verm = new JMenuItem("Vermelho");
		lar = new JMenuItem("Laranja");
		ama = new JMenuItem("Amarelo");
		verd = new JMenuItem("Verde");
		azul = new JMenuItem("Azul");
		roxo = new JMenuItem("Roxo");
		menuVoltar = new JMenuItem("Voltar");

		popCor.add(verm);
		popCor.add(lar);
		popCor.add(ama);
		popCor.add(verd);
		popCor.add(azul);
		popCor.add(roxo);
		popCor.add(menuVoltar);

		verm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				alterarCor("A");
			}
		});
		lar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				alterarCor("B");
			}
		});
		ama.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				alterarCor("C");
			}
		});
		verd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				alterarCor("D");
			}
		});
		azul.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				alterarCor("E");
			}
		});
		roxo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				alterarCor("F");
			}
		});
		menuVoltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				popup.show(contentPane, 0, 189);
			}
		});


		menuAdd = new JMenuItem("Adicionar");
		popup.add(menuAdd);
		menuAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (coletarInfo())
					JOptionPane.showMessageDialog(null, "Salvo com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "Erro", null, JOptionPane.ERROR_MESSAGE);

				try {
					dispose();
					new TelaNotas().setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

		menuApagar = new JMenuItem("Apagar tudo");
		popup.add(menuApagar);
		menuApagar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				bloco.setText("");
				liMirtes.setText("0/255");
			}
		});

		menuFechar = new JMenuItem("Sair");
		popup.add(menuFechar);
		menuFechar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					new TelaInicio().setVisible(true);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		menuCor = new JMenuItem("Cor");
		popup.add(menuCor);
		menuCor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				popCor.show(contentPane, 0, 132);
			}
		});

		lblVoltar = new JLabel("Voltar");
		lblVoltar.setBounds(360, 250, 80, 20);
		lblVoltar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVoltar.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPane.add(lblVoltar);
		
		alterarTema();

		// EVENTOS
		bloco.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_F1:
					popup.show(contentPane, 0, 189);
					break;
				case KeyEvent.VK_F2:
					try {
						dispose();
						new TelaNotas().setVisible(true);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					break;
				}

			}

			@Override
			public void keyTyped(KeyEvent e) {
				liMirtes.setText(bloco.getText().length() + "/255");
				if (bloco.getText().length() > 230)
					liMirtes.setForeground(new Color(255, 20, 20));
				else
					liMirtes.setForeground(Color.BLACK);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				liMirtes.setText(bloco.getText().length() + "/255");
				if (bloco.getText().length() > 230)
					liMirtes.setForeground(new Color(255, 20, 20));
				else
					liMirtes.setForeground(Color.BLACK);

			}
		});
		
	}

	/**************************************/
	// SALVANDO INFORMACOES (NOTA)
	
	private boolean coletarInfo() {
		
		String[] valores = new String[5];
		Date date = new Date(System.currentTimeMillis());
		valores[0] = new SimpleDateFormat("yyyy-MM-dd").format(date);
		valores[1] = new SimpleDateFormat("HH:mm").format(date);
		valores[2] = bloco.getText();
		valores[3] = "" + Perfil.getCodigo();
		valores[4] = cor;

		return salvarNota(valores);
	}

	private boolean salvarNota(String[] valores) {
		if (this.index >= 0)
			return Nota.salvarNota(valores, notas.get(this.index));
		else
			return Nota.salvarNota(valores, "null");
	}
	
	// FIM SALVAR INFO

	/** 
	 * Recebe um novo valor para o atributo Cor, atribui esse valor ao atributo e altera o plano de fundo
	 * 
	 * @param c : a nova cor
	 */
	private void alterarCor(String c){
		
		// altera o valor do atributo
		cor = c;
		
		// atualiza o plano de fundo
		if (cor.equals("A"))
			contentPane.setBackground(new Color(255, 130, 130)); // -> vermelho claro
		else if (cor.equals("B"))
			contentPane.setBackground(new Color(255, 178, 102)); // -> laranja claro
		else if (cor.equals("C"))
			contentPane.setBackground(new Color(255, 255, 102)); // -> amarelo
		else if (cor.equals("D"))
			contentPane.setBackground(new Color(178, 255, 102)); // -> verde claro
		else if (cor.equals("E"))
			contentPane.setBackground(new Color(153, 255, 204)); // -> ciano
		else if (cor.equals("F"))
			contentPane.setBackground(new Color(229, 150, 255)); // -> lilas
	}
}
