package tech.dock.Desafio.API.Rest.Java.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe que representa a entidade Transacao.
 * @author gabrielribeirojb
 *
 */

@Entity
@Table(name = "transacoes")
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTransacao;
	private Double valor;
	private LocalDate dataTransacao;
	
	@ManyToOne
	@JoinColumn(name = "idConta")
	@JsonIgnore
	private Conta conta;
	
	public Transacao() {
		
	}
	
	public Transacao(Long idTransacao, Double valor, LocalDate dataTransacao, Conta conta) {
		super();
		this.idTransacao = idTransacao;
		this.valor = valor;
		this.dataTransacao = dataTransacao;
		this.conta = conta;
	}

	public Long getIdTransacao() {
		return idTransacao;
	}

	public void setIdTransacao(Long idTransacao) {
		this.idTransacao = idTransacao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public LocalDate getDataTransacao() {
		return dataTransacao;
	}

	public void setDataTransacao(LocalDate dataTransacao) {
		this.dataTransacao = dataTransacao;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	public String toString() {
		return "ID: " + idTransacao+
		", VALOR: " + valor
		+", DataTransacao: " + dataTransacao
		+", IDCONTA: "+ conta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTransacao == null) ? 0 : idTransacao.hashCode());
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
		Transacao other = (Transacao) obj;
		if (idTransacao == null) {
			if (other.idTransacao != null)
				return false;
		} else if (!idTransacao.equals(other.idTransacao))
			return false;
		return true;
	}
}
