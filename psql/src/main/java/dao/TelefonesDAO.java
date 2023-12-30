package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;

import conexaojdbc.SingleConnection;
import model.Telefones;

/**
 * Telefones dos usuários
 * 
 * @author Diego Mendes Rodrigues
 * @version 1.0
 */
public class TelefonesDAO {
	private Connection connection;
	private String erro;

	/**
	 * Construtor que realiza a conexão com o banco de dados
	 */
	public TelefonesDAO() {
		connection = SingleConnection.getConnection();
	}

	/**
	 * Salvar um telefone de usuário no banco de dados
	 * 
	 * @param Telefones sendo as informações do telefone
	 * @return boolean sendo true no caso de sucesso, e false caso contrário
	 */
	public boolean salvar(Telefones telefone) {
		String sql = "insert into telefones (tipo,telefone,usuariosid) values (?, ?, ?)";
		try {
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, telefone.getTipo());
			insert.setString(2, telefone.getTelefone());
			insert.setLong(3, telefone.getUsuarioid());

			int retorno = insert.executeUpdate();
			connection.commit();

			if (retorno > 0) {
				return true;
			} else {
				return false;
			}
		} catch (PSQLException epsql) {
			epsql.printStackTrace();
			this.erro = epsql.getMessage();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			this.erro = e.getMessage();
			return false;
		}
	}

	/**
	 * Alterar telefone no banco de dados
	 * 
	 * @param Telefones sendo as informações dp telefone
	 * @return boolean sendo true no caso de sucesso, e false caso contrário
	 */
	public boolean alterar(Telefones telefone) {
		String sql = "update telefone set tipo = ? , telefone = ? , usuariosid = ? where id = ?";
		try {
			PreparedStatement update = connection.prepareStatement(sql);
			update.setString(1, telefone.getTipo());
			update.setString(2, telefone.getTelefone());
			update.setLong(3, telefone.getUsuarioid());
			update.setLong(4, telefone.getId());
			int retorno = update.executeUpdate();
			connection.commit(); /* Salvar no banco de dados */

			if (retorno > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			try {
				connection.rollback(); /* Reverter a operação no BD */
			} catch (SQLException esql) {
				esql.printStackTrace();
				this.erro = esql.getMessage();
				return false;
			}
			e.printStackTrace();
			this.erro = e.getMessage();
			return false;
		}
	}

	/**
	 * Excluir um telefone do banco de dados
	 * 
	 * @param Long id sendo o id do telefone
	 * @return boolean sendo true no caso de sucesso, e false caso contrário
	 */
	public boolean excluir(Long id) {
		String sql = "delete from telefone where id = ?";
		try {
			PreparedStatement delete = connection.prepareStatement(sql);
			delete.setLong(1, id);
			int retorno = delete.executeUpdate();
			connection.commit(); /* Salvar no banco de dados */

			if (retorno > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			try {
				connection.rollback(); /* Reverter a operação no BD */
			} catch (SQLException esql) {
				esql.printStackTrace();
				this.erro = esql.getMessage();
				return false;
			}
			e.printStackTrace();
			this.erro = e.getMessage();
			return false;
		}
	}

	/**
	 * Listar todos os telefones do banco de dados
	 * 
	 * @return List<Telefones> sendo a lista de telefones
	 */
	public List<Telefones> listar() {
		List<Telefones> lista = new ArrayList<Telefones>();

		String sql = "select * from telefones order by id";
		try {
			PreparedStatement select = connection.prepareStatement(sql);
			ResultSet resultado = select.executeQuery();

			while (resultado.next()) {
				Telefones telefone = new Telefones();
				telefone.setId(resultado.getLong("id"));
				telefone.setTipo(resultado.getString("tipo"));
				telefone.setTelefone(resultado.getString("telefone"));
				telefone.setUsuarioid(resultado.getLong("usuariosid"));
				lista.add(telefone);
			}
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			this.erro = e.getMessage();
			return lista;
		}
	}

	/**
	 * Lista um telefone do banco de dados
	 * 
	 * @param Long sendo o id do telefone
	 * @return Telefones sendo o telefone
	 */
	public Telefones buscar(Long id) {
		Telefones telefone = new Telefones();

		String sql = "select * from telefones where id = " + id;
		try {
			PreparedStatement select = connection.prepareStatement(sql);
			ResultSet resultado = select.executeQuery();

			while (resultado.next()) {
				telefone.setId(resultado.getLong("id"));
				telefone.setTipo(resultado.getString("tipo"));
				telefone.setTelefone(resultado.getString("telefone"));
				telefone.setUsuarioid(resultado.getLong("usuariosid"));
			}
			return telefone;
		} catch (Exception e) {
			e.printStackTrace();
			this.erro = e.getMessage();
			return telefone;
		}
	}

	public String getErro() {
		return erro;
	}
}
