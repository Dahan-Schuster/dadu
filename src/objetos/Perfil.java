package objetos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

public class Perfil {
	private static int codigo;
	private static String usuario;
	private static String senha;
	private static String nome;
	private static String sobrenome;
	private static String email1;
	private static String email2;
	private static String tel1;
	private static String tel2;
	private static String endereco;
	private static String profissao;
	private static String empresa;

	private static String tema = "escuro";
	private static String wp = "C";

	private static ArrayList<String[]> alarmes = new ArrayList<String[]>();

	// CONSTRUTORES

	/**
	 * Inicializa um novo perfil com os atributos obrigatórios na tabela USUARIOS
	 * (usuario, senha e nome)
	 * 
	 * @param usuario varchar(20)
	 * @param senha   varchar(12)
	 * @param nome    varchar(20)
	 */
	public Perfil(int cod, String user, String password, String name, String sobrenome1) {
		codigo = cod;
		usuario = user;
		senha = password;
		nome = name;
		sobrenome = sobrenome1;
		email1 = "Não informado";
		email2 = "Não informado";
		tel1 = "Não informado";
		tel2 = "Não informado";
		endereco = "Não informado";
		profissao = "Não informado";
		empresa = "Não informado";
	}

	/**
	 * Inicializa um novo perfil com os parâmetros recebidos
	 * 
	 * @param cod       integer primary key auto_increment
	 * @param usuario   varchar(20)
	 * @param senha     varchar(12)
	 * @param nome      varchar(20)
	 * @param sobrenome varchar(20)
	 * @param apelido   varchar(8)
	 * @param email1    varchar(255) -> número máximo oficial de caracteres num
	 *                  email
	 * @param email2    varchar(255)
	 * @param tel1      varchar(11) -> formato brasileiro
	 * @param tel2      varchar(11)
	 * @param endereco  varchar(100)
	 * @param profissao varchar(20)
	 * @param empresa   varchar(20)
	 */
	public Perfil(int cod, String user, String password, String name, String sobrenome1, String email11, String email21,
			String tel11, String tel21, String endereco1, String profissao1, String empresa1) {
		super();
		codigo = cod;
		usuario = user;
		senha = password;
		nome = name;
		sobrenome = sobrenome1;
		email1 = email11;
		email2 = email21;
		tel1 = tel11;
		tel2 = tel21;
		endereco = endereco1;
		profissao = profissao1;
		empresa = empresa1;
	}

	// MÉTODO PARA LOGIN
	public static boolean entrar(String user, String password) throws SQLException {

		boolean loged = false;

		// Pesquisa por um usuário com o username recebido
		DataBase.conectar();
		ResultSet query = DataBase.lerTabela("usuarios", "usuario", user);

		// Verifica entre eles
		while (query.next()) {
			if (query.getString(3).equals(password)) {

				new Perfil(query.getInt(1), query.getString(2), query.getString(3), query.getString(4),
						query.getString(5), query.getString(6), query.getString(7), query.getString(8),
						query.getString(9), query.getString(10), query.getString(11), query.getString(12));

				tema = Tema.getTema();
				wp = Tema.getWallpaper();

				preencherArrayList();

				loged = true;
				break;
			}
		}

		query.close();
		DataBase.fechar();

		return loged;
	}

	// MÉTODOS REFERENTES AO BANCO DE DADOS -> S.E.E.
	// (Salvar, Editar e Excluir)

	/**
	 * Grava dados na tabela USUARIOS
	 * 
	 * @param valores referentes às colunas usuario, senha, ..., empresa.
	 * @return TRUE se os registros forem salvos com sucesso ou FALSE caso ocorra
	 *         algum erro desconhecido
	 */
	public static boolean salvarUsuario(String[] valores) {
		try {
			DataBase.conectar();
			DataBase.gravarDados(
					"usuarios(usuario, senha, nome, sobrenome, email1, email2, tel1, tel2, endereco, profissao, empresa)",
					valores);
			DataBase.fechar();
			return true;
		} catch (SQLException sql) {
			// sql.printStackTrace(); // se ocorrer algum erro no cadastro, vai aparecer um
			// JOptionPane informando que
			// deu erro. Descomente esse comando pra ver o erro no Console e saber em qual
			// linha foi. Geralmente é algo na sintaxe do comando SQL acima.
			return false;
		}
	}

	/**
	 * Edita registros na tabela USUARIOS
	 * 
	 * @param colunas em que serão feitas as alterações
	 * @param valores novos
	 * @param codigo  identificador de usuário
	 * @return TRUE se os registros forem alterados com sucesso ou FALSE caso ocorra
	 *         algum erro desconhecido
	 */
	public static boolean editarPerfil(String[] colunas, String[] valores, String codigo) {
		try {
			DataBase.conectar();
			DataBase.atualizarTabela("usuarios", colunas, valores, "codigo", codigo);
			DataBase.fechar();
			return true;
		} catch (SQLException sql) {
			return false;
		}
	}

	/**
	 * Exclui registros da tabela USUARIOS
	 * 
	 * @param codigo identificador de usuário
	 * @return TRUE se os registros forem excluídos com sucesso ou FALSE caso ocorra
	 *         algum erro desconhecido
	 */
	public static boolean excluirConta(String codigo) {
		try {
			// Excluindo registros de outras tabelas
			DataBase.conectar();
			Jogo.excluirRegistro(codigo);
			Tema.excluirRegistro(codigo);
			dataHora.excluirRegistro(codigo);

			// Apagando conta
			DataBase.excluirDados("usuarios", "codigo", codigo);
			DataBase.fechar();
			return true;
		} catch (SQLException sql) {
			return false;
		}
	}

	/**
	 * Edita o perfil
	 * 
	 * @param valores
	 * @param codigo
	 * @return
	 */
	public static boolean atualizarPerfil(String[] valores, String codigo) {
		String colunas[] = new String[11];
		colunas[0] = "usuario";
		colunas[1] = "senha";
		colunas[2] = "nome";
		colunas[3] = "sobrenome";
		colunas[4] = "email1";
		colunas[5] = "email2";
		colunas[6] = "tel1";
		colunas[7] = "tel2";
		colunas[8] = "endereco";
		colunas[9] = "profissao";
		colunas[10] = "empresa";

		try {
			DataBase.conectar();
			DataBase.atualizarTabela("usuarios", colunas, valores, "codigo", codigo);
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

	public static void atualizarTema() throws MySQLSyntaxErrorException, MySQLNonTransientConnectionException,
			CommunicationsException, SQLException {
		tema = Tema.getTema();
	}

	public static void atualizarWallpaper() throws MySQLSyntaxErrorException, MySQLNonTransientConnectionException,
			CommunicationsException, SQLException {
		wp = Tema.getWallpaper();
	}

	public static void preencherArrayList() {
		try {
			DataBase.conectar();

			String[] campos = { "som", "hora", "status", "dias", "cod_alarme"};
			ResultSet query = DataBase.lerTabela("alarmes", campos, "cod_usuario", "" + codigo);
			
			String[] a = new String[5];
			
			alarmes.clear();
			
			while (query.next()) {
				a = new String[5];
				a[0] = query.getString(1);
				a[1] = query.getString(2);
				a[2] = query.getString(3);
				a[3] = query.getString(4);
				a[4] = query.getString(5);
				
				alarmes.add(a);
								
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// GETTERS E SETTER

	public static int getCodigo() {
		return codigo;
	}

	public static void setCodigo(int codigo) {
		Perfil.codigo = codigo;
	}

	public static String getUsuario() {
		return usuario;
	}

	public static void setUsuario(String usuario) {
		Perfil.usuario = usuario;
	}

	public static String getSenha() {
		return senha;
	}

	public static void setSenha(String senha) {
		Perfil.senha = senha;
	}

	public static String getNome() {
		return nome;
	}

	public static void setNome(String nome) {
		Perfil.nome = nome;
	}

	public static String getSobrenome() {
		return sobrenome;
	}

	public static void setSobrenome(String sobrenome) {
		Perfil.sobrenome = sobrenome;
	}

	public static String getEmail1() {
		return email1;
	}

	public static void setEmail1(String email1) {
		Perfil.email1 = email1;
	}

	public static String getEmail2() {
		return email2;
	}

	public static void setEmail2(String email2) {
		Perfil.email2 = email2;
	}

	public static String getTel1() {
		return tel1;
	}

	public static void setTel1(String tel1) {
		Perfil.tel1 = tel1;
	}

	public static String getTel2() {
		return tel2;
	}

	public static void setTel2(String tel2) {
		Perfil.tel2 = tel2;
	}

	public static String getEndereco() {
		return endereco;
	}

	public static void setEndereco(String endereco) {
		Perfil.endereco = endereco;
	}

	public static String getProfissao() {
		return profissao;
	}

	public static void setProfissao(String profissao) {
		Perfil.profissao = profissao;
	}

	public static String getEmpresa() {
		return empresa;
	}

	public static void setEmpresa(String empresa) {
		Perfil.empresa = empresa;
	}

	public static String getTema() {
		return tema;
	}

	public static String getWallpaper() {
		return wp;
	}
	
	public static ArrayList<String[]> getAlarmes() {
		return alarmes;
	}

}
