package br.com.tecnisys.core.base.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Null;

/**
 * The persistent class for the permissao database table.
 * 
 */
@Entity
@Table(name = "permissao", schema = "core_base2")
@NamedQuery(name="Permissao.findAll", query="SELECT p FROM Permissao p")
public class Permissao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String descricao;
	
	private String url;
	
	@ManyToOne
	@Null
	@JoinColumn(name="permissao_pai_id")
	private Permissao permissaoPai;

	public Permissao() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Permissao getPermissaoPai() {
		return permissaoPai;
	}

	public void setPermissaoPai(Permissao permissaoPai) {
		this.permissaoPai = permissaoPai;
	}

}