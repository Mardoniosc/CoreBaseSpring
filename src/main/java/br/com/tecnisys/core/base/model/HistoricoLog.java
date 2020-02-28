package br.com.tecnisys.core.base.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the historico_log database table.
 * 
 */
@Entity
@Table(name = "historico_log")
@NamedQuery(name = "HistoricoLog.findAll", query = "SELECT h FROM HistoricoLog h")
public class HistoricoLog implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "data_log")
  private Timestamp dataLog;

  @Column(name = "tabela")
  private String tabela;

  @Column(name = "operacao")
  private String operacao;

  // bi-directional many-to-one association to Usuario
  @ManyToOne
  @JoinColumn(name = "usuario_id")
  private Usuario usuario;

  public HistoricoLog() {
  }

  public String getTabela() {
    return tabela;
  }

  public void setTabela(String tabela) {
    this.tabela = tabela;
  }

  public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getDataLog() {
		return this.dataLog;
	}

	public void setDataLog(Timestamp dataLog) {
		this.dataLog = dataLog;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

  public String getOperacao() {
    return operacao;
  }

  public void setOperacao(String operacao) {
    this.operacao = operacao;
  }
}