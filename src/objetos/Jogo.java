package objetos;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

import spaceGame.Game;

public class Jogo {

	private static ResultSet query;

	public static void abrirJogo() {
		Game.main(null); // chama o método principal do jogo, que irá inicializá-lo
	}
	
	public static void abrirPontuacao(int Score) throws MySQLSyntaxErrorException, MySQLNonTransientConnectionException,
			CommunicationsException, SQLException {
		DataBase.conectar();

		String[] dados = new String[2];
		dados[0] = "" + Perfil.getCodigo();
		dados[1] = "" + Score;
		DataBase.gravarDados("spaceGamePontuacoes", dados);

		DataBase.fechar();
	}

	public static void salvarPontuacao(int Score) throws MySQLSyntaxErrorException,
			MySQLNonTransientConnectionException, CommunicationsException, SQLException {
		DataBase.conectar();

		query = DataBase.lerTabela("spaceGamePontuacoes", "cod_usuario", "" + Perfil.getCodigo());

		query.next();
		int scoreAtual = query.getInt(2);

		if (Score > scoreAtual) { // atualiza o novo score apenas se for maior que o antigo

			String[] colunas = new String[1];
			colunas[0] = "pontuacao";

			String[] valores = new String[1];
			valores[0] = "" + Score;

			DataBase.atualizarTabela("spaceGamePontuacoes", colunas, valores, "cod_usuario", "" + Perfil.getCodigo());
		}
		DataBase.fechar();
	}

	public static void resetPontuacao() throws MySQLSyntaxErrorException, MySQLNonTransientConnectionException,
			CommunicationsException, SQLException {
		DataBase.conectar();

		DataBase.enviarComando(
				"UPDATE spaceGamePontuacoes SET pontuacao = 0 WHERE cod_usuario = " + Perfil.getCodigo());

		DataBase.fechar();
	}
	
	public static boolean excluirRegistro(String codigo) {
		try {
			DataBase.conectar();
			DataBase.excluirDados("spaceGamePontuacoes", "cod_usuario", codigo);
			DataBase.fechar();
			return true;
		} catch (SQLException sql) {
			return false;
		}
	}

}
