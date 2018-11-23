/**
 * Classe para comunicação com o banco de dados. As explicações dos métodos estão logo acima da declaração.
 * Altere o valor do atributo user para o usuário do seu banco de dados, assim como o atributo password.
 * 
 * IMPORTANTE: Antes de utilizar cada método, é necessário CONECTAR (método Conectar) com o banco.
 * Após utilizar o método, FECHE a conexão com o método Fechar.
 * 
 * Ex.:
 * 
 * DataBase.conectar();
 * 
 * String[] valores = {valor1, valor2, ..., valorN};
 * DataBase.gravarDados(tabela, valores);
 * 
 * DataBase.fechar();
 * 
 * IMPORTANTE 2: Todos os métodos são estáticos, ou seja, não é necessário criar um objeto DataBase
 * 
 * Ex.: DataBase database = new DataBase(); ← ISSO NÃO É NECESSÁRIO
 * 
 * Utilize os métodos usando DataBase.método();
 * 
 * @author Dahan Schuster e Ingrid Pauline
 */

package objetos;

import java.sql.Connection;
import java.sql.Statement;

import com.mysql.jdbc.CommunicationsException;
import com.mysql.jdbc.exceptions.MySQLNonTransientConnectionException;
import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBase {
	private static Connection conexao;
	private static String url = "jdbc:mysql://localhost/dadu?autoReconnect=true&useSSL=false";
	private static String user = "root";
	private static String password = "";

	public DataBase() {
		url = "jdbc:mysql://localhost/dadu?autoReconnect=true&useSSL=false";
		user = "root";
		password = "";
	}
	
	// GETTER E SETTER
	public static Connection getConexao() {
		return conexao;
	}
	
	public static void setConexao(Connection conex) {
		conexao = conex;
	}

	/*
	 * ********************************** MÉTODOS PRINCIPAIS
	 ********************************************/

	/**
	 * Realiza a conex�o com o banco de dados, pode lan�ar diferentes exce��es SQL
	 * 
	 * @throws CommunicationsException              server inativo
	 * @throws MySQLSyntaxErrorException            banco de dados inexistente
	 * @throws MySQLNonTransientConnectionException localhost incorreto
	 * @throws SQLException                         usu�rio ou senha incorretos,
	 *                                              localhost sem indica��o
	 *                                              ("localhost:")
	 * @return Uma flag representado o resultado. flag = 0 se a conexão foi bem sucedida, flag > 0 em outros casos.                                             
	 */
	public static int conectar() throws com.mysql.jdbc.exceptions.jdbc4.CommunicationsException,
			com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException,
			com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException, SQLException {
	
		try {

			// Tentando estabelecer conexão com o banco de dados
			conexao = DriverManager.getConnection(url, user, password);
			return 0;
			// flag = 0;

		} catch (com.mysql.jdbc.exceptions.jdbc4.CommunicationsException a) {

			return 1;
			// flag = 1;

		} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException b) {

			return 2;
			// flag = 2;

		} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException c) {

			return 3;
			// flag = 3;
		} catch (SQLException d) {
			return 4;
			// flag = 4;
		}
	}

	/**
	 * Fecha a conexão com o banco de dados.
	 */
	public static void fechar() {
		if (conexao != null) {
			try {
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**Faz isso aí
	 * 
	 * @param tabela
	 * @param colunaChave
	 * @param valor
	 * @param colunaChave2
	 * @param valor2
	 * @return
	 * @throws SQLException
	 */
	public static ResultSet lerTabela(String tabela, String colunaChave, String valor,String colunaChave2, String valor2) throws SQLException {
		Statement statement = conexao.createStatement();

		String sql = String.format("select * from %s where (%s = '%s' AND %s ='%s') ", tabela, colunaChave, valor,colunaChave2, valor2);
		ResultSet query = statement.executeQuery(sql);

		return query;
	}

	/**
	 * Executa um comando SQL personalizado.
	 * 
	 * @param comando
	 * @throws SQLException
	 */
	public static ResultSet enviarComandoPesquisa(String comando) throws SQLException {
		Statement statement = conexao.createStatement();
		statement.execute(comando);
		ResultSet query = statement.getResultSet();
		return query;
	}
	
	/**
	 * Executa um comando SQL personalizado.
	 * 
	 * @param comando
	 * @throws SQLException
	 */
	public static void enviarComando(String comando) throws SQLException {
		Statement statement = conexao.createStatement();
		statement.execute(comando);
		statement.close();
	}

	/**
	 * Grava dados em uma tabela no banco de dados: INSERT INTO tabela VALUES
	 * (valores);
	 * 
	 * @param tabela  a tabela indicada
	 * @param valores os valores a serem inseridos, em formato String
	 * @throws SQLException quando a sintaxe SQL est� incorreta
	 */
	public static void gravarDados(String tabela, String[] valores) throws SQLException {
		String sql;
		Statement statement = conexao.createStatement();

		sql = String.format("insert into %s values (", tabela);

		for (String valor : valores) {
			sql += "'" + valor + "',";
		}

		sql = sql.substring(0, sql.length() - 1) + ")";

		statement.executeUpdate(sql);
		statement.close();
	}

	/**
	 * Exclui dados de uma tabela no banco de dados: DELETE FROM tabela WHERE
	 * colunaChave = valor;
	 * 
	 * @param tabela      a tabela indicada
	 * @param colunaChave a coluna usada para a condi��o
	 * @param valor       o valor de compara��o entre a conluna chave
	 * @throws SQLException quando a sintaxe SQL est� incorreta
	 */
	public static void excluirDados(String tabela, String colunaChave, String valor) throws SQLException {

		String sql;
		Statement statement = conexao.createStatement();

		sql = String.format("delete from %s where %s = '%s'", tabela, colunaChave, valor);

		statement.executeUpdate(sql);
		statement.close();
	}	
	
	/**
	 * Atualiza dados na tabela indicada: UPDATE tabela SET coluna1 = valor1,
	 * coluna2 = valor2, ..., colunaN = valorN WHERE colunaChave = valor;
	 * 
	 * Por favor, verifique sempre se os �NDICES do vetor COLUNAS correspondem aos
	 * do vetor VALORES Ex.: SE colunas[0] = "nome" ENTÃO valores[0] = "joão" ETC.
	 * 
	 * @param tabela      a tabela indicada
	 * @param colunas     as colunas que ter�o seus registros atualizados
	 * @param valores     os novos valores
	 * @param colunaChave a coluna usada para a condi��o
	 * @param valor       o valor de compara��o entre a conluna chave
	 * @throws SQLException quando a sintaxe SQL est� incorreta
	 */
	public static void atualizarTabela(String tabela, String[] colunas, String[] valores, String colunaChave,
			String valor) throws SQLException {
		Statement statement = conexao.createStatement();

		String sql = String.format("update %s set ", tabela);

		for (int i = 0; i < valores.length; i++) {
			sql += String.format("%s = '%s', ", colunas[i], valores[i]);
		}
		sql = String.format("%s where %s = '%s'", sql.substring(0, sql.length() - 2), colunaChave, valor);

		statement.executeUpdate(sql);
		statement.close();
	}

	/**
	 * Retorna todos os registros da tabela indicada: SELECT * FROM tabela;
	 * 
	 * @param tabela a tabela indicada
	 * @return query um objeto ResultSet com o resultado da pesquisa
	 * @throws SQLException quando a tabela n�o existe
	 */
	public static ResultSet lerTabela(String tabela) throws SQLException {
		Statement statement = conexao.createStatement();

		String sql = String.format("select * from %s", tabela);
		ResultSet query = statement.executeQuery(sql);

		return query;
	}

	/**
	 * Retorna todos os registros da tabela indicada ordenados por uma tabela
	 * específica: SELECT * FROM tabela ORDER BY orderBy ASC | DESC;
	 * 
	 * @param tabela  a tabela indicada
	 * @param orderBy a tabela base para o ORDER BY
	 * @param asc     indica se a organização é em ordem crescente ou decrescente.
	 *                TRUE -> asc ; FALSE -> desc
	 * @return query um objeto ResultSet com o resultado da pesquisa
	 * @throws SQLException quando a tabela n�o existe
	 */
	public static ResultSet lerTabela(String tabela, String orderBy, boolean asc) throws SQLException {
		Statement statement = conexao.createStatement();

		String sql;
		if (asc) {
			sql = String.format("select * from %s order by %s asc", tabela, orderBy);
		} else {
			sql = String.format("select * from %s order by %s desc", tabela, orderBy);
		}
		ResultSet query = statement.executeQuery(sql);

		return query;
	}

	/**
	 * Retorna tudo sobre registros espec�ficos da tabela indicada: SELECT * FROM
	 * tabela WHERE colunaChave = valor;
	 * 
	 * @param tabela      a tabela indicada
	 * @param colunaChave a coluna usada para a condi��o
	 * @param valor       o valor de compara��o entre a conluna chave
	 * @return query um objeto ResultSet com o resultado da pesquisa
	 * @throws SQLException quando a tabela n�o existe
	 */
	public static ResultSet lerTabela(String tabela, String colunaChave, String valor) throws SQLException {
		Statement statement = conexao.createStatement();

		String sql = String.format("select * from %s where %s = '%s'", tabela, colunaChave, valor);
		ResultSet query = statement.executeQuery(sql);

		return query;
	}

	/**
	 * Retorna tudo sobre registros espec�ficos da tabela indicada ordenados por uma
	 * tabela específica: SELECT * FROM tabela WHERE colunaChave = valor ORDER BY
	 * orderBy ASC | DESC;
	 * 
	 * @param tabela      a tabela indicada
	 * @param colunaChave a coluna usada para a condi��o
	 * @param valor       o valor de compara��o entre a conluna chave
	 * @param orderBy     a tabela base para o ORDER BY
	 * @param asc         indica se a organização é em ordem crescente ou
	 *                    decrescente. TRUE -> asc ; FALSE -> desc
	 * @return query um objeto ResultSet com o resultado da pesquisa
	 * @throws SQLException quando a tabela n�o existe
	 */
	public static ResultSet lerTabela(String tabela, String colunaChave, String valor, String orderBy, boolean asc)
			throws SQLException {
		Statement statement = conexao.createStatement();

		String sql;
		if (asc) {
			sql = String.format("select * from %s where %s = '%s' order by %s asc", tabela, colunaChave, valor,
					orderBy);
		} else {
			sql = String.format("select * from %s where %s = '%s' order by %s desc", tabela, colunaChave, valor,
					orderBy);
		}
		ResultSet query = statement.executeQuery(sql);

		return query;
	}

	/**
	 * Retorna as colunas especificadas de todos os registros da tabela indicada:
	 * SELECT campo1, campo2, ..., campoN FROM tabela;
	 * 
	 * @param tabela a tabela indicada
	 * @param campos as colunas a serem listadas
	 * @return query um objeto ResultSet com o resultado da pesquisa
	 * @throws SQLException quando a tabela n�o existe
	 */
	public static ResultSet lerTabela(String tabela, String[] campos) throws SQLException {
		Statement statement = conexao.createStatement();

		String sql = "select ";

		for (String coluna : campos) {
			sql += coluna + ", ";
		}
		sql = String.format("%s from %s", sql.substring(0, sql.length() - 2), tabela);

		ResultSet query = statement.executeQuery(sql);

		return query;
	}

	/**
	 * Retorna as colunas especificadas de todos os registros da tabela indicada
	 * ordenados por uma tabela específica: SELECT campo1, campo2, ..., campoN FROM
	 * tabela ORDER BY orderBy ASC | DESC;
	 * 
	 * @param tabela  a tabela indicada
	 * @param campos  as colunas a serem listadas
	 * @param orderBy a tabela base para o ORDER BY
	 * @param asc     indica se a organização é em ordem crescente ou decrescente.
	 *                TRUE -> asc ; FALSE -> desc
	 * @return query um objeto ResultSet com o resultado da pesquisa
	 * @throws SQLException quando a tabela n�o existe
	 */
	public static ResultSet lerTabela(String tabela, String[] campos, String orderBy, boolean asc) throws SQLException {
		Statement statement = conexao.createStatement();

		String sql = "select ";

		for (String coluna : campos) {
			sql += coluna + ", ";
		}

		if (asc) {
			sql = String.format("%s from %s order by %s asc", sql.substring(0, sql.length() - 2), tabela, orderBy);
		} else {
			sql = String.format("%s from %s order by %s desc", sql.substring(0, sql.length() - 2), tabela, orderBy);
		}

		ResultSet query = statement.executeQuery(sql);

		return query;
	}

	/**
	 * Retorna as colunas especificadas de registros espec�ficos na tabela indicada:
	 * SELECT campos FROM tabela WHERE colunaChave = valor;
	 * 
	 * @param tabela      a tabela indicada
	 * @param campos      as colunas a serem listadas
	 * @param colunaChave a coluna usada para a condi��o
	 * @param valor       o valor de compara��o entre a conluna chave
	 * @return query um objeto ResultSet com o resultado da pesquisa
	 * @throws SQLException quando a sintaxe SQL est� incorreta
	 */
	public static ResultSet lerTabela(String tabela, String[] campos, String colunaChave, String valor)
			throws SQLException {
		Statement statement = conexao.createStatement();

		String sql = "select ";

		for (String coluna : campos) {
			sql += coluna + ", ";
		}
		sql = String.format("%s from %s where %s = '%s'", sql.substring(0, sql.length() - 2), tabela, colunaChave,
				valor);

		ResultSet query = statement.executeQuery(sql);

		return query;
	}

	/**
	 * Retorna as colunas especificadas de registros espec�ficos na tabela indicada
	 * ordenados por uma tabela específica: SELECT campo1, campo2, ..., campoN FROM
	 * tabela WHERE colunaChave = valor ORDER BY orderBy ASC | DESC;
	 * 
	 * @param tabela      a tabela indicada
	 * @param campos      as colunas a serem listadas
	 * @param colunaChave a coluna usada para a condi��o
	 * @param valor       o valor de compara��o entre a conluna chave
	 * @param orderBy     a tabela base para o ORDER BY
	 * @param asc         indica se a organização é em ordem crescente ou
	 *                    decrescente. true -> ASC ; false -> DESC
	 * @return query um objeto ResultSet com o resultado da pesquisa
	 * @throws SQLException quando a sintaxe SQL est� incorreta
	 */
	public static ResultSet lerTabela(String tabela, String[] campos, String colunaChave, String valor, String orderBy,
			boolean asc) throws SQLException {
		Statement statement = conexao.createStatement();

		String sql = "select ";

		for (String coluna : campos) {
			sql += coluna + ", ";
		}

		if (asc) {
			sql = String.format("%s from %s where %s = '%s' order by %s asc", sql.substring(0, sql.length() - 2),
					tabela, colunaChave, valor, orderBy);
		} else {
			sql = String.format("%s from %s where %s = '%s' order by %s desc", sql.substring(0, sql.length() - 2),
					tabela, colunaChave, valor, orderBy);
		}

		ResultSet query = statement.executeQuery(sql);

		return query;
	}


	/*
	 * ********************************* MÉTODOS PERIGOSOS
	 *******************************************/

	/**
	 * Usado para editar informa��es de usuario, senha e do banco de dados a ser
	 * utilizado.
	 */
	public static void alterarInfoSQL(String banco, String novoUsuario, String novaSenha) {
		url = "jdbc:mysql://localhost:3306/" + banco + "?autoReconnect=true&useSSL=false";
		user = novoUsuario;
		password = novaSenha;

	}

	/*
	 * // Verifica se um registro existe numa tabela, usando como pesquisa um valor
	 * n�o inteiro private boolean registrado(String tabela, String colunaChave,
	 * String valor) throws SQLException { Statement statement =
	 * conexao.createStatement();
	 * 
	 * String sql = String.format("select * from %s where %s = '%s'", tabela,
	 * colunaChave, valor); ResultSet query = statement.executeQuery(sql);
	 * 
	 * if (query.next()) { return true; } else { return false; } }
	 * 
	 * // Verifica se um registro existe numa tabela, usando como pesquisa um valor
	 * inteiro private boolean registrado(String tabela, String colunaChave, int
	 * valor) throws SQLException { Statement statement = conexao.createStatement();
	 * 
	 * String sql = String.format("select * from %s where %s = %s", tabela,
	 * colunaChave, valor); ResultSet query = statement.executeQuery(sql);
	 * 
	 * if (query.next()) { return true; } else { return false; } }
	 */
}






