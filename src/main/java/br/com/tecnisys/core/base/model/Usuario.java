package br.com.tecnisys.core.base.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name="usuario", schema="core_base2")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;

	private String email;
	
	private String senha;
	
	private String CPF;
	
	private String login;

	private String imagem;
	
	private Timestamp criado;

	private byte status;

	//bi-directional many-to-one association to Perfil
	@ManyToOne
	@JoinColumn(name = "perfil_id")
	private Perfil perfil;

	//bi-directional many-to-one association to HistoricoAcesso
	@OneToMany(mappedBy="usuario")
	private List<HistoricoAcesso> historicoAcessos;

	public Usuario() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getCriado() {
		return this.criado;
	}

	public void setCriado(Timestamp criado) {
		this.criado = criado;
	}
	
	

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<HistoricoAcesso> getHistoricoAcessos() {
		return this.historicoAcessos;
	}

	public void setHistoricoAcessos(List<HistoricoAcesso> historicoAcessos) {
		this.historicoAcessos = historicoAcessos;
	}

	public HistoricoAcesso addHistoricoAcesso(HistoricoAcesso historicoAcesso) {
		getHistoricoAcessos().add(historicoAcesso);
		historicoAcesso.setUsuario(this);

		return historicoAcesso;
	}

	public HistoricoAcesso removeHistoricoAcesso(HistoricoAcesso historicoAcesso) {
		getHistoricoAcessos().remove(historicoAcesso);
		historicoAcesso.setUsuario(null);

		return historicoAcesso;
	}

	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	
	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}