package tech.dock.Desafio.API.Rest.Java.dto;

import tech.dock.Desafio.API.Rest.Java.domain.Conta;

/**
 * Classe no modelo Data Transfer Object que servirá para retornar o campo saldo de uma Conta
 * @author gabrielribeirojb
 *
 */

public class ContaSaldoDTO {
	
	private Double saldo;
	
	public ContaSaldoDTO() {
		
	}
	
	public ContaSaldoDTO(Conta conta) {
		this.saldo = conta.getSaldo();
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
}
