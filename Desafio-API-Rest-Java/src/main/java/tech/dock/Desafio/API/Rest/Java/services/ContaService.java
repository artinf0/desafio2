package tech.dock.Desafio.API.Rest.Java.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.dock.Desafio.API.Rest.Java.domain.Conta;
import tech.dock.Desafio.API.Rest.Java.domain.enums.TipoConta;
import tech.dock.Desafio.API.Rest.Java.dto.ContaSaldoDTO;
import tech.dock.Desafio.API.Rest.Java.repositories.ContaRepository;

@Service
public class ContaService {
	
	@Autowired
	private ContaRepository contaRepository;
	
	public Conta criarConta(Conta obj) {
		obj.setIdConta(null);
		LocalDate hoje = LocalDate.now();
		obj.setDataCriacao(hoje);
		obj.setSaldo(0.0);
		obj.setFlagAtivo(true);
		
		obj = contaRepository.save(obj);
		return obj;
	}
	
	public Conta retornaConta(Long idConta){
		return contaRepository.getReferenceById(idConta);
	}
	
	public Conta depositaNaConta(Long idConta, Conta novoValor) {
		Conta entidade = retornaConta(idConta);
		updateDeposito(entidade, novoValor);
		return contaRepository.save(entidade);
	}
	
	public ContaSaldoDTO retornaSaldo(Long idConta) {
		Conta conta = contaRepository.getReferenceById(idConta);
		ContaSaldoDTO contaSaldoDTO = new ContaSaldoDTO(conta);
		return contaSaldoDTO;
	}
	
	public Conta saqueNaConta(Long idConta, Conta novoValor) {
		Conta entidade = retornaConta(idConta);
		
		updateSaque(entidade, novoValor);
		return contaRepository.save(entidade);
	}
	
	public Conta bloqueiaConta(Long idConta) {
		Conta entidade = retornaConta(idConta);
		
		updateFlagAtivo(entidade);
		return contaRepository.save(entidade);
	}

	private void updateDeposito(Conta entidade, Conta novoValor) {
		Double saldoAtual = entidade.getSaldo();
		entidade.setSaldo(saldoAtual += novoValor.getSaldo());
	}
	
	private void updateSaque(Conta entidade, Conta novoValor) {
		Double saldoAtual = entidade.getSaldo();
		TipoConta tipoConta = entidade.getTipoConta();
		if(entidade.getSaldo() + novoValor.getSaldo() < 0.0 && (tipoConta == TipoConta.CONTA_SALARIO || tipoConta == TipoConta.CONTA_POUPANCA)) {
			throw new IllegalArgumentException("Não se pode sacar mais do que o saldo atual para esse tipo de conta");
		} else {
			entidade.setSaldo(saldoAtual -= novoValor.getSaldo());
		}
	}
	
	private void updateFlagAtivo(Conta entidade) {
		entidade.setFlagAtivo(false);
	}
}
