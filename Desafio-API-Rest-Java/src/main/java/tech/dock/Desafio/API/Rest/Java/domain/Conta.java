package tech.dock.Desafio.API.Rest.Java.domain;

import java.time.LocalDate;

public class Conta {
	
	private Long idConta;
	private Double saldo;
	private Double limiteSaqueDiario;
	private Boolean flagAtivo;
	private LocalDate dataCriacao;
	
	public Conta(Long idConta, Double saldo, Double limiteSaqueDiario, Boolean flagAtivo, LocalDate dataCriacao) {
		this.idConta = idConta;
		this.saldo = saldo;
		this.limiteSaqueDiario = limiteSaqueDiario;
		this.flagAtivo = flagAtivo;
		this.dataCriacao = dataCriacao;
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
}
