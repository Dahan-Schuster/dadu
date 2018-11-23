package objetos;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Nota {

	private String data;
	private String hora;
	private String texto;

	public Nota(String data, String hora, String texto) {
		super();
		this.data = data;
		this.hora = hora;
		this.texto = texto;
	}
	
	public static String[] getNota(String index) throws SQLException {
		String[] colunas = { "texto", "hora", "data", "cor" };
		DataBase.conectar();
		ResultSet query = DataBase.lerTabela("notas", colunas, "cod_nota", index);
		
		String[] n = new String[4];
		n[0] = "";
		n[1] = "";
		n[2] = "";
		n[3] = "C";
		
		if (query.next()) {
			 n[0] = query.getString(1);
			 n[1] = query.getString(2);
			 n[2] = query.getString(3);
			 n[3] = query.getString(4);
		}
		
		return n;
	}

	public static boolean salvarNota(String[] valores, String cod_nota) {
		try {
			DataBase.conectar();
			if (!cod_nota.equals("null")) {
				String[] colunas = {"data", "hora", "texto", "cod_usuario", "cor"};
				DataBase.atualizarTabela("notas", colunas, valores, "cod_nota", cod_nota);
			} else {
				DataBase.gravarDados("notas (data, hora, texto, cod_usuario, cor)",
						valores);
			}
			
			DataBase.fechar();
			return true;
		} catch (SQLException sql) {
			// se ocorrer algum erro no cadastro, vai aparecer um
			// JOptionPane informando que
			// deu erro. Descomente esse comando pra ver o erro no Console e saber em qual
			// linha foi. Geralmente é algo na sintaxe do comando SQL acima.
			//sql.printStackTrace(); 
			
			return false;
		}
	}
	
	public static void apagarNota(String cod_nota) {
		try {
			DataBase.conectar();
			
			DataBase.excluirDados("notas", "cod_nota", cod_nota);
			
			DataBase.fechar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void apagarTudo() {
		try {
			DataBase.conectar();
			
			DataBase.enviarComando("delete from notas where cod_nota > 0 and cod_usuario = "+Perfil.getCodigo());
			
			DataBase.fechar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}
