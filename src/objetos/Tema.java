package objetos;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

public class Tema {
	
	private static ResultSet query;
	
	public static void abrirTema() throws MySQLSyntaxErrorException, MySQLNonTransientConnectionException, CommunicationsException, SQLException {
		DataBase.conectar();
		
		String[] valores = {""+Perfil.getCodigo(), "claro", "A"};
		DataBase.gravarDados("configuracoesTemas", valores);
		DataBase.fechar();
	}
	
	public static void salvarTema(String valor) throws MySQLSyntaxErrorException, MySQLNonTransientConnectionException, CommunicationsException, SQLException {
		DataBase.conectar();
		
		String[] valores = {valor};
		String[] colunas = {"tema"};
		DataBase.atualizarTabela("configuracoesTemas", colunas, valores, "cod_usuario", ""+Perfil.getCodigo());
		
		Perfil.atualizarTema();
		
		DataBase.fechar();
	}
	
	public static void salvarWallpaper(String valor) throws MySQLSyntaxErrorException, MySQLNonTransientConnectionException, CommunicationsException, SQLException {
		DataBase.conectar();
		
		String[] valores = {valor};
		String[] colunas = {"wallpaper"};
		DataBase.atualizarTabela("configuracoesTemas", colunas, valores, "cod_usuario", ""+Perfil.getCodigo());
		
		Perfil.atualizarWallpaper();
		
		DataBase.fechar();
	}
	
	public static String getTema() throws MySQLSyntaxErrorException, MySQLNonTransientConnectionException, CommunicationsException, SQLException {
		DataBase.conectar();
		
		String[] campos = {"tema"};
		
		query = DataBase.lerTabela("configuracoesTemas", campos, "cod_usuario", ""+Perfil.getCodigo());
		
		if(query.next())
			return query.getString(1); // claro ou escuro
		else
			return "N"; // Null
	}
	
	public static String getWallpaper() throws MySQLSyntaxErrorException, MySQLNonTransientConnectionException, CommunicationsException, SQLException {
		DataBase.conectar();
		
		String[] campos = {"wallpaper"};
		
		query = DataBase.lerTabela("configuracoesTemas", campos, "cod_usuario", ""+Perfil.getCodigo());
		
		if(query.next())
			return query.getString(1); // A, B, C, D ou E
		else
			return "N"; // Null
	}
	

	public static boolean excluirRegistro(String codigo) {
		try {
			DataBase.conectar();
			DataBase.excluirDados("configuracoesTemas", "cod_usuario", codigo);
			DataBase.fechar();
			return true;
		} catch (SQLException sql) {
			return false;
		}
	}
}
