package model;

import java.util.Objects;

/**
 * Telefones
 * 
 * @author Diego Mendes Rodrigues
 * @version 1.0
 */
public class Telefones {
	private Long id;
	private String tipo;
	private String telefone;
	private Long usuarioid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUsuarioid() {
		return usuarioid;
	}

	public void setUsuarioid(Long usuarioid) {
		this.usuarioid = usuarioid;
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

	@Override
	public String toString() {
		return "Telefones [id=" + id + ", tipo=" + tipo + ", telefone=" + telefone + ", usuarioid=" + usuarioid + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, telefone, tipo, usuarioid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Telefones other = (Telefones) obj;
		return Objects.equals(id, other.id) && Objects.equals(telefone, other.telefone)
				&& Objects.equals(tipo, other.tipo) && Objects.equals(usuarioid, other.usuarioid);
	}
}
