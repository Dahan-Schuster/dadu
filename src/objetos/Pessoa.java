package objetos;

import java.sql.SQLException;

public class Pessoa {

	@SuppressWarnings("unused")
	private int cod;
	private String nome;
	private String sobrenome;
	private String apelido;
	private String grupo;
	private String email1;
	private String email2;
	private String tel1;
	private String tel2;

	public Pessoa(int cod, String nome, String sobrenome, String apelido, String grupo, String email1, String email2,
			String tel1, String tel2) {
		super();
		this.cod = cod;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.apelido = "Não informado";

		this.grupo = "Não informado";
		this.email1 = "Não informado";
		this.email2 = "Não informado";
		this.tel1 = tel1;
		this.tel2 = "Não informado";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getTel1() {
		return tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getTel2() {
		return tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	// ArrayList de contatos
	public void ContatosSalvos() {

	}

	public static boolean salvarContato(String[] valores) {
		try {
			DataBase.conectar();
			DataBase.gravarDados("contatos(nome, sobrenome, apelido, grupo, email1, email2, tel1, tel2, cod_usuario )",
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
	public static boolean atualizarContato(String[]valores,String codigo) {
		String colunas[]=new String[8];
		colunas[0]="nome"; colunas[1]="sobrenome";colunas[2]="apelido";colunas[3]="grupo";colunas[4]="email1";
		colunas[5]="email2";colunas[6]="tel1";colunas[7]="tel2";
		
		try {
			DataBase.conectar();
			DataBase.atualizarTabela("contatos", colunas, valores, "cod_contato", codigo);
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
	
	
	public static void excluirContato(String cod_contato) {
		try {
			DataBase.conectar();
			
			DataBase.excluirDados("contatos","cod_contato", cod_contato);
			
			DataBase.fechar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void excluirTodosContatos(){
		try {
			DataBase.conectar();
			
			DataBase.enviarComando("delete from contatos where cod_contato > 0 and cod_usuario = "+Perfil.getCodigo());
			
			DataBase.fechar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	}
	