/**
 * Testes da classe SingleConnection
 * 
 * @author Diego Mendes Rodrigues
 * @version 1.0
 */
package conexaojdbc;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;

import org.junit.Test;

/**
 * Verificar se é possível autenticar no PostgreSQL
 */
public class SingleConnectionTest {
	@Test
	public void conexaoTest() {
		Connection connection = SingleConnection.getConnection();
		if (connection != null) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}
}
