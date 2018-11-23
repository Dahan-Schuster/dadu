package objetos;

import java.sql.SQLException;

public class Evento {
	
	private String data;
	private String titulo;
	private String horaInicio;
	private String horaFim;
	private String informacoes;
	
	
	public Evento(String data,String titulo, String horaInicio, String horaFim, String informacoes) {
		super();
		this.data = data;
		this.titulo=titulo;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.informacoes = informacoes;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getInformacoes() {
		return informacoes;
	}


	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}


	public String getHoraInicio() {
		return horaInicio;
	}


	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}


	public String getHoraFim() {
		return horaFim;
	}


	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}

	public static boolean salvarEvento(String[] valores) {
		try {
			DataBase.conectar();
			DataBase.gravarDados("eventos (titulo, data, horaInicio, horaFim, informacoes, cod_usuario )",
					valores);
			DataBase.fechar();
			return true;
		} catch (SQLException sql) {
			sql.printStackTrace(); // se ocorrer algum erro no cadastro, vai aparecer um
			// JOptionPane informando que
			// deu erro. Descomente esse comando pra ver o erro no Console e saber em qual
			// linha foi. Geralmente Ã© algo na sintaxe do comando SQL acima.
			return false;
		}
	}
	public static void excluirEvento(String cod_evento) {
		try {
			DataBase.conectar();
			
			DataBase.excluirDados("eventos","cod_evento", cod_evento);
			
			DataBase.fechar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void excluirTodosEventos(){
		try {
			DataBase.conectar();
			
			DataBase.enviarComando("delete from eventos where cod_evento > 0 and cod_usuario = "+Perfil.getCodigo());
			
			DataBase.fechar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static boolean atualizarEvento(String[]valores,String codigo) {
		String colunas[]=new String[5];
		colunas[0]="titulo"; colunas[1]="data";colunas[2]="horaInicio";colunas[3]="horaFim";colunas[4]="informacoes";
		
		try {
			DataBase.conectar();
			DataBase.atualizarTabela("eventos", colunas, valores, "cod_evento", codigo);
			DataBase.fechar();
			return true;
		} catch (SQLException sql) {
			// sql.printStackTrace(); // se ocorrer algum erro no cadastro, vai aparecer um
			// JOptionPane informando que
			// deu erro. Descomente esse comando pra ver o erro no Console e saber em qual
			// linha foi. Geralmente Ã© algo na sintaxe do comando SQL acima.
			return false;
		}
		
	}

	
 
}