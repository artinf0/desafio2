package tech.dock.Desafio.API.Rest.Java.dto;

import java.util.ArrayList;
import java.util.List;

import tech.dock.Desafio.API.Rest.Java.domain.Conta;
import tech.dock.Desafio.API.Rest.Java.domain.Transacao;

public class ContaTransacoesDTO {

	private List<Transacao> transacoes = new ArrayList<>();
	
	public ContaTransacoesDTO(){
		
	}
	
	public ContaTransacoesDTO(Conta conta) {
		this.transacoes = conta.getTransacoes();
	}

	public List<Transacao> getTransacoes() {
		return transacoes;
	}
}
