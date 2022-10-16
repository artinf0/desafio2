package tech.dock.Desafio.API.Rest.Java.dto;

import tech.dock.Desafio.API.Rest.Java.domain.Conta;

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
