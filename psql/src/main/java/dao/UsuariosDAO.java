package dao;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import conexaojdbc.SingleConnection;
import model.UsuarioTelefone;
import model.Usuarios;

/**
 * Usuários
 * 
 * @author Diego Mendes Rodrigues
 * @version 1.0
 */
public class UsuariosDAO {
	private Connection connection;

	/**
	 * Construtor que realiza a conexão com o banco de dados
	 */
	public UsuariosDAO() {
		connection = SingleConnection.getConnection();
	}

	/**
	 * Salvar um novo usuário no banco de dados
	 * 
	 * @param Usuarios sendo as informações do usuário
	 * @return boolean sendo true no caso de sucesso, e false caso contrário
	 */
	public boolean salvar(Usuarios usuario) {
		String sql = "insert into usuarios (nome,email,login,senha) values (?, ?, ?, ?)";
		try {
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, usuario.getNome());
			insert.setString(2, usuario.getEmail());
			insert.setString(3, usuario.getLogin());
			insert.setString(4, usuario.getSenha());
			int retorno = insert.executeUpdate();
			connection.commit(); /* Salvar no banco de dados */

			if (retorno > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			try {
				connection.rollback(); /* Reverter a operação no BD */
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Alterar um usuário no banco de dados
	 * 
	 * @param Usuarios sendo as informações do usuário
	 * @return boolean sendo true no caso de sucesso, e false caso contrário
	 */
	public boolean alterar(Usuarios usuario) {
		String sql = "update usuarios set nome = ? , email = ? , login = ? , senha = ? where id = ?";
		try {
			PreparedStatement update = connection.prepareStatement(sql);
			update.setString(1, usuario.getNome());
			update.setString(2, usuario.getEmail());
			update.setString(3, usuario.getLogin());
			update.setString(4, usuario.getSenha());
			update.setLong(5, usuario.getId());
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
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Excluir um usuário do banco de dados
	 * 
	 * @param Long id sendo o id do usuário
	 * @return boolean sendo true no caso de sucesso, e false caso contrário
	 */
	public boolean excluir(Long id) {
		String sql = "delete from usuarios where id = ?";
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
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Listar todos os usuários do banco de dados
	 * 
	 * @return List<Usuarios> sendo a lista de usuários
	 */
	public List<Usuarios> listar() {
		List<Usuarios> lista = new ArrayList<Usuarios>();

		String sql = "select * from usuarios order by id";
		try {
			PreparedStatement select = connection.prepareStatement(sql);
			ResultSet resultado = select.executeQuery();

			while (resultado.next()) {
				Usuarios usuario = new Usuarios();
				usuario.setId(resultado.getLong("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setEmail(resultado.getString("email"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
				lista.add(usuario);
			}
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return lista;
		}
	}

	/**
	 * Listar todos os usuários do banco de dados com seus telefones
	 * 
	 * @return List<UsuarioTelefone> sendo a lista de usuários
	 */
	public List<UsuarioTelefone> listarComTelefones() {
		List<UsuarioTelefone> lista = new ArrayList<UsuarioTelefone>();

		String sql = "select usuario.nome, usuario.email, usuario.login, usuario.senha, telefone.tipo, telefone.telefone"
				+ "	from telefones as telefone" + "	inner join usuarios as usuario"
				+ "	on telefone.usuariosid = usuario.id  " + "	order by telefone.id";
		try {
			PreparedStatement select = connection.prepareStatement(sql);
			ResultSet resultado = select.executeQuery();

			while (resultado.next()) {
				UsuarioTelefone usuarioTelefone = new UsuarioTelefone();
				usuarioTelefone.setNome(resultado.getString("nome"));
				usuarioTelefone.setEmail(resultado.getString("email"));
				usuarioTelefone.setLogin(resultado.getString("login"));
				usuarioTelefone.setSenha(resultado.getString("senha"));
				usuarioTelefone.setTipo(resultado.getString("tipo"));
				usuarioTelefone.setTelefone(resultado.getString("telefone"));
				lista.add(usuarioTelefone);
			}
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return lista;
		}
	}

	/**
	 * Listar um usuário do banco de dados
	 * 
	 * @param Long sendo o id do usuário
	 * @return Usuarios sendo o usuário
	 */
	public Usuarios buscar(Long id) {
		Usuarios retorno = new Usuarios();
		String sql = "select * from usuarios where id = ?";

		try {
			PreparedStatement select = connection.prepareStatement(sql);
			select.setLong(1, id);
			ResultSet resultado = select.executeQuery();

			while (resultado.next()) {
				retorno.setId(resultado.getLong("id"));
				retorno.setNome(resultado.getString("nome"));
				retorno.setEmail(resultado.getString("email"));
				retorno.setLogin(resultado.getString("login"));
				retorno.setSenha(resultado.getString("senha"));
			}
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			return retorno;
		}
	}

	/**
	 * Listar um usuário do banco de dados com seu telefone
	 * 
	 * @param Long sendo o id do usuário
	 * @return UsuarioTelefone sendo o usuário
	 */
	public UsuarioTelefone buscarComTelefone(Long id) {
		UsuarioTelefone usuario = new UsuarioTelefone();

		String sql = "select usuario.nome, usuario.email, usuario.login, usuario.senha, telefone.tipo, telefone.telefone"
				+ "	from telefones as telefone" + "	inner join usuarios as usuario"
				+ "	on telefone.usuariosid = usuario.id  " + "	where usuario.id = " + id;
		try {
			PreparedStatement select = connection.prepareStatement(sql);
			ResultSet resultado = select.executeQuery();

			while (resultado.next()) {
				usuario.setNome(resultado.getString("nome"));
				usuario.setEmail(resultado.getString("email"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
				usuario.setTipo(resultado.getString("tipo"));
				usuario.setTelefone(resultado.getString("telefone"));
			}
			return usuario;
		} catch (Exception e) {
			e.printStackTrace();
			return usuario;
		}
	}

	/**
	 * Gerar um hash de senha
	 * 
	 * @param senha String sendo a senha original
	 * @return String sendo a senha com hash
	 */
	public String gerarSenhaHash(String senha) {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		KeySpec spec = new PBEKeySpec(senha.toCharArray(), salt, 65536, 128);
		SecretKeyFactory f;
		byte[] hash = null;
		try {
			f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			hash = f.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		Base64.Encoder enc = Base64.getEncoder();
		return enc.encodeToString(hash);
	}
}
