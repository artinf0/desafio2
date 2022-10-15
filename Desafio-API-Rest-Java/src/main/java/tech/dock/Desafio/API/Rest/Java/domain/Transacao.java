package tech.dock.Desafio.API.Rest.Java.domain;

import java.time.LocalDate;

public class Transacao {

	private Long idTransacao;
	private Double valor;
	private LocalDate dataTransacao;
	
	public Transacao(Long idTransacao, Double valor, LocalDate dataTransacao) {
		this.idTransacao = idTransacao;
		this.valor = valor;
		this.dataTransacao = dataTransacao;
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
}
