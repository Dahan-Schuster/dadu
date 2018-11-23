package objetos;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

public class dataHora {

	
	public static void abrirConfiguracoes() throws SQLException {
		DataBase.conectar();
		
		String[] dados = new String[3];
		dados[0] = "" + Perfil.getCodigo();
		dados[1] = "A"; // formato de data
		dados[2] = "A"; // formato de hora
		
		DataBase.gravarDados("configuracoesDataHora", dados);
		
		DataBase.fechar();
	}
	
	public static void salvarConfiguracao(String fData, String fHora) throws MySQLSyntaxErrorException, MySQLNonTransientConnectionException, CommunicationsException, SQLException {
		
		DataBase.conectar();
		
		String[] colunas = {"formatoData", "formatoHora"};
		String[] valores = {fData, fHora};
		
		DataBase.atualizarTabela("configuracoesDataHora", colunas, valores, "cod_usuario", ""+Perfil.getCodigo());
	
		DataBase.fechar();
	}
	
	public static String formatoData() throws SQLException {
		DataBase.conectar();
		String[] campo = {"formatoData"};
		ResultSet query = DataBase.lerTabela("configuracoesDataHora", campo, "cod_usuario", ""+Perfil.getCodigo()); 
		
		query.next();
		
		return query.getString(1);
	}
	
	public static boolean formatoHora() throws SQLException {
		DataBase.conectar();
		String[] campo = {"formatoHora"};
		ResultSet query = DataBase.lerTabela("configuracoesDataHora", campo, "cod_usuario", ""+Perfil.getCodigo());
		
		query.next();
		
		if (query.getString(1).equals("A"))
			return true;
		else
			return false;
	}
	

	public static boolean excluirRegistro(String codigo) {
		try {
			DataBase.conectar();
			DataBase.excluirDados("configuracoesDataHora", "cod_usuario", codigo);
			DataBase.fechar();
			return true;
		} catch (SQLException sql) {
			return false;
		}
	}
	
}
