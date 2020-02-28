package br.com.tecnisys.core.base.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the historico_acesso database table.
 * 
 */
@Entity
@NamedQuery(name="HistoricoAcesso.findAll", query="SELECT h FROM HistoricoAcesso h")
public class HistoricoAcesso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name="data_acesso")
	private Timestamp dataAcesso;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;

	public HistoricoAcesso() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getDataAcesso() {
		return this.dataAcesso;
	}

	public void setDataAcesso(Timestamp dataAcesso) {
		this.dataAcesso = dataAcesso;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}