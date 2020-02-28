package br.com.tecnisys.core.base.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the Perfil_has_Permissao database table.
 * 
 */
@Entity
@NamedQuery(name="PerfilHasPermissao.findAll", query="SELECT p FROM Perfil p")
public class PerfilHasPermissao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int status;

	//bi-directional many-to-one association to Perfil
	@ManyToOne
	@JoinColumn(name = "perfil_id")
	private Perfil perfil_id;

	@ManyToOne
	@JoinColumn(name = "permissao_id")
	private Permissao permissao_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Perfil getPerfil_id() {
		return perfil_id;
	}

	public void setPerfil_id(Perfil perfil_id) {
		this.perfil_id = perfil_id;
	}

	public Permissao getPermissao_id() {
		return permissao_id;
	}

	public void setPermissao_id(Permissao permissao_id) {
		this.permissao_id = permissao_id;
	}

	
	
}
