package tech.dock.Desafio.API.Rest.Java.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import tech.dock.Desafio.API.Rest.Java.domain.enums.TipoConta;

@Entity
@Table(name = "contas")
public class Conta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idConta;
	
	@OneToOne
	@JoinColumn(name = "idPessoa", referencedColumnName = "idPessoa")
	private Pessoa pessoa;
	
	@OneToMany(mappedBy = "conta")
	private List<Transacao> transacoes = new ArrayList<>();
	
	private Double saldo;
	private Double limiteSaqueDiario;
	private Boolean flagAtivo;
	private LocalDate dataCriacao;
	private Integer tipoConta;
	
	public Conta() {
		
	}
	
	public Conta(Long idConta, Double saldo, Double limiteSaqueDiario, Boolean flagAtivo, LocalDate dataCriacao, Pessoa pessoa, TipoConta tipoConta) {
		super();
		this.idConta = idConta;
		this.saldo = saldo;
		this.limiteSaqueDiario = limiteSaqueDiario;
		this.flagAtivo = flagAtivo;
		this.dataCriacao = dataCriacao;
		this.pessoa = pessoa;
		this.tipoConta = (tipoConta == null) ? null : tipoConta.getCodigo();
	}

	public Long getIdConta() {
		return idConta;
	}

	public void setIdConta(Long idConta) {
		this.idConta = idConta;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Double getLimiteSaqueDiario() {
		return limiteSaqueDiario;
	}

	public void setLimiteSaqueDiario(Double limiteSaqueDiario) {
		this.limiteSaqueDiario = limiteSaqueDiario;
	}

	public Boolean getFlagAtivo() {
		return flagAtivo;
	}

	public void setFlagAtivo(Boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public List<Transacao> getTransacoes() {
		return transacoes;
	}
	
	public TipoConta getTipoConta() {
		return TipoConta.valueOf(tipoConta);
	}
	
	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta.getCodigo();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idConta == null) ? 0 : idConta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		if (idConta == null) {
			if (other.idConta != null)
				return false;
		} else if (!idConta.equals(other.idConta))
			return false;
		return true;
	}
}
