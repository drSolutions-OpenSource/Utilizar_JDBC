package conexaojdbc;

import java.sql.Connection;
import java.sql.DriverManager;

import configuracoes.ConfiguracoesPSQL;

/**
 * Realizar a conexão com o banco de dados
 * 
 * @author Diego Mendes Rodrigues
 * @version 1.0
 */
public class SingleConnection {
	private static Connection connection = null;

	static {
		conectar();
	}

	public SingleConnection() {
		conectar();
	}

	private static void conectar() {
		try {
			if (connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(ConfiguracoesPSQL.url, ConfiguracoesPSQL.username,
						ConfiguracoesPSQL.password);
				connection.setAutoCommit(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return connection;
	}
}
