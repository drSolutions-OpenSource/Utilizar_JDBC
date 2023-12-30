package model;

import java.util.Objects;

/**
 * Usuários com telefones (INNER JOIN)
 * 
 * @author Diego Mendes Rodrigues
 * @version 1.0
 */
public class UsuarioTelefone {
	String nome;
	String email;
	String login;
	String senha;
	String tipo;
	String telefone;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "UsuarioTelefone [nome=" + nome + ", email=" + email + ", login=" + login + ", senha=" + senha
				+ ", tipo=" + tipo + ", telefone=" + telefone + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, login, nome, senha, telefone, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioTelefone other = (UsuarioTelefone) obj;
		return Objects.equals(email, other.email) && Objects.equals(login, other.login)
				&& Objects.equals(nome, other.nome) && Objects.equals(senha, other.senha)
				&& Objects.equals(telefone, other.telefone) && Objects.equals(tipo, other.tipo);
	}
}
