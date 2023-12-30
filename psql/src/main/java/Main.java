import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import configuracoes.TipoTelefone;
import dao.TelefonesDAO;
import dao.UsuariosDAO;
import model.Telefones;
import model.UsuarioTelefone;
import model.Usuarios;

/**
 * Utilizar o JDBC (Java Database Connectivity) com o banco de dados PostgreSQL
 * 13.10 Utilizou-se a plataforma ElephantSQL - PostgreSQL as a Service como a
 * provedora do banco de dados Foram criadas 2 tabelas: - Usuarios: id (UNIQUE),
 * nome, email, login e senha - Telefones: id (PK), tipo, telefone e usuariosid
 * . O campo usuariosid é uma FK para a tabela Usuarios, com ON DELETE CASCADE .
 * No campo tipo, pode-se apenas preencher 'Celular', 'Fixo', 'Comercial' ou
 * 'Fax'
 * 
 * @author Diego Mendes Rodrigues
 * @version 1.0
 */
public class Main {

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
//		incluirUsuario();
//		alterarUsuario();
		listarUsuarios();

//		incluirTelefone();
		listarTelefones();

		/* Listagens utilizando INNER JOIN */
		listarUsuariosComTelefones();
		listarUmUsuario();
	}

	/**
	 * Incluir um usuário na tabela Usuarios
	 */
	public static void incluirUsuario() {
		System.out.println("# Cadastrar um usuário");

		UsuariosDAO dao = new UsuariosDAO();
		Usuarios usuario = new Usuarios();

//		usuario.setNome("Diego Rodrigues");
//		usuario.setEmail("drodrigues@fake.io");
//		usuario.setLogin("drodrigues");
//		String senha = dao.gerarSenhaHash("senha2023");
//		usuario.setSenha(senha);

		usuario.setNome("Bruna Mendes");
		usuario.setEmail("bmendes@fake.io");
		usuario.setLogin("bmendes");
		String senha = dao.gerarSenhaHash("sapoFada");
		usuario.setSenha(senha);

		if (dao.salvar(usuario)) {
			System.out.println("Usuário incluído com sucesso");
		} else {
			System.out.println("Falha ao incluir o usuário");
		}
	}

	/**
	 * Alterar um usuário na tabela Usuarios
	 */
	public static void alterarUsuario() {
		System.out.println("# Alterar um usuário");

		UsuariosDAO dao = new UsuariosDAO();
		Usuarios usuario = new Usuarios();

		System.out.println("# Cadastrar um usuário");

		usuario.setId(2L);
		usuario.setNome("Bruna Mendes");
		usuario.setEmail("bmendes@fake.io");
		usuario.setLogin("bmendes");
		String senha = dao.gerarSenhaHash("sapoF@da88");
		usuario.setSenha(senha);

		if (dao.alterar(usuario)) {
			System.out.println("Usuário alterado com sucesso");
		} else {
			System.out.println("Falha ao alterado o usuário");
		}
	}

	/**
	 * Listar todos os usuários da tabela Usuarios
	 */
	public static void listarUsuarios() {
		System.out.println("# Listar os usuários");

		UsuariosDAO dao = new UsuariosDAO();
		List<Usuarios> listaUsuarios = new ArrayList<Usuarios>();

		listaUsuarios = dao.listar();

		for (Usuarios usuario : listaUsuarios) {
			System.out.println(usuario.toString());
		}
	}

	/**
	 * Incluir um telefone na tabela Telefones
	 */
	public static void incluirTelefone() {
		System.out.println("# Cadastrar um telefone");

		TelefonesDAO dao = new TelefonesDAO();
		Telefones telefone = new Telefones();

//		telefone.setTipo(TipoTelefone.CELULAR);
//		telefone.setTelefone("(11) 98355-4235");
//		telefone.setUsuarioid(1L);

//		telefone.setTipo(TipoTelefone.COMERCIAL);
//		telefone.setTelefone("(12) 98657-8745");
//		telefone.setUsuarioid(1L);

		telefone.setTipo(TipoTelefone.CELULAR);
		telefone.setTelefone("(19) 98789-3456");
		telefone.setUsuarioid(2L);

		if (dao.salvar(telefone)) {
			System.out.println("Telefone incluído com sucesso");
		} else {
			System.out.println("Falha ao incluir o telefone");
		}
	}

	/**
	 * Listar todos os telefones da tabela Telefones
	 */
	public static void listarTelefones() {
		System.out.println("# Listar os telefones");

		TelefonesDAO dao = new TelefonesDAO();
		List<Telefones> listaTelefones = new ArrayList<Telefones>();

		listaTelefones = dao.listar();

		for (Telefones telefone : listaTelefones) {
			System.out.println(telefone.toString());
		}
	}

	/**
	 * Listar todos os usuários (tabela Usuários) com os telefones (tabela
	 * Telefones) utilizando INNER JOIN
	 */
	public static void listarUsuariosComTelefones() {
		System.out.println("# Listar os usuários com telefones");

		UsuariosDAO dao = new UsuariosDAO();
		List<UsuarioTelefone> listaUsuariosTelefones = new ArrayList<UsuarioTelefone>();

		listaUsuariosTelefones = dao.listarComTelefones();

		for (UsuarioTelefone usuario : listaUsuariosTelefones) {
			System.out.println(usuario.toString());
		}
	}

	/**
	 * Listar um usuário (tabela Usuários) com seu primeiro telefone (tabela
	 * Telefones) utilizando INNER JOIN
	 */
	public static void listarUmUsuario() {
		System.out.println("# Listar um usuário");
		UsuariosDAO dao = new UsuariosDAO();

		UsuarioTelefone usuario = dao.buscarComTelefone(1L);
		System.out.println(usuario.toString());
	}
}
