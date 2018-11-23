package objetos;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

public class Alarme {
	private String hora;
	private String som;
	private String status;
	private String dias;
	
	private static Clip line;
	
	private static boolean lineAtivo = false;

	public Alarme(String hora, String som, String status, String dias) {
		super();
		this.hora = hora;
		this.som = som;
		this.status = status;
		this.dias = dias;
	}

	public static String[] getAlarme(String index) throws SQLException {
		String[] colunas = { "som", "hora", "status", "dias" };
		DataBase.conectar();
		ResultSet query = DataBase.lerTabela("alarmes", colunas, "cod_alarme", index);

		String[] n = new String[4];
		n[0] = "A";
		n[1] = "";
		n[2] = "A";
		n[3] = "NNNNNNN";

		if (query.next()) {
			n[0] = query.getString(1);
			n[1] = query.getString(2);
			n[2] = query.getString(3);
			n[3] = query.getString(4);
		}

		return n;
	}

	public static boolean salvarAlarme(String[] valores, String cod_alarme) {
		try {
			DataBase.conectar();
			if (!cod_alarme.equals("null")) {
				String[] colunas = { "som", "hora", "status", "dias", "cod_usuario" };
				DataBase.atualizarTabela("alarmes", colunas, valores, "cod_alarme", cod_alarme);
			} else {
				DataBase.gravarDados("alarmes (som, hora, status, dias, cod_usuario)", valores);
			}
			
			Perfil.preencherArrayList();

			DataBase.fechar();
			return true;
		} catch (SQLException sql) {
			// se ocorrer algum erro no cadastro, vai aparecer um
			// JOptionPane informando que
			// deu erro. Descomente esse comando pra ver o erro no Console e saber em qual
			// linha foi. Geralmente é algo na sintaxe do comando SQL acima.
			sql.printStackTrace();

			return false;
		}
	}

	public static void apagarAlarme(String cod_alarmes) {
		try {
			DataBase.conectar();

			DataBase.excluirDados("alarmes", "cod_alarme", cod_alarmes);

			DataBase.fechar();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void apagarTudo() {
		try {
			DataBase.conectar();

			DataBase.enviarComando("delete from alarmes where cod_alarme > 0 and cod_usuario = " + Perfil.getCodigo());

			DataBase.fechar();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void tocarMusica(String musica) {
		try {
			String path = String.format("/home/dahan/eclipse-workspace/DADU/%s.wav", musica);
			AudioInputStream som = AudioSystem.getAudioInputStream(new File(path));

			TargetDataLine.Info dataLine = new DataLine.Info(Clip.class, som.getFormat());

			if (!AudioSystem.isLineSupported(dataLine)) {
				JOptionPane.showMessageDialog(null, "Erro");
			} else {

				line = (Clip) AudioSystem.getLine(dataLine);

				line.open(som);
				
				lineAtivo = true;
				
				line.start();
				line.loop(5);

			}
		} catch (LineUnavailableException lue) {
			// lue.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro");
		} catch (UnsupportedAudioFileException e) {
			// e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro");
		} catch (IOException e) {
			// e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro");
		}
	}
	
	public static void pararMusica() {
		line.stop();
		line.close();
		lineAtivo = false;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getSom() {
		return som;
	}

	public void setSom(String som) {
		this.som = som;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDias() {
		return dias;
	}

	public void setDias(String dias) {
		this.dias = dias;
	}

	public static boolean isLineAtivo() {
		return lineAtivo;
	}

}
