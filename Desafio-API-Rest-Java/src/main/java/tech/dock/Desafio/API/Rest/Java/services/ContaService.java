package tech.dock.Desafio.API.Rest.Java.services;

import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.dock.Desafio.API.Rest.Java.domain.Conta;
import tech.dock.Desafio.API.Rest.Java.domain.Transacao;
import tech.dock.Desafio.API.Rest.Java.dto.ContaSaldoDTO;
import tech.dock.Desafio.API.Rest.Java.repositories.ContaRepository;
import tech.dock.Desafio.API.Rest.Java.services.exceptions.ResourceNotFoundException;

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
	
	public Conta retornaContaPorId(Long idConta) {
		Optional<Conta> obj = contaRepository.findById(idConta);
		return obj.orElseThrow(() -> new ResourceNotFoundException(idConta));
	}
	
	@Transactional
	public Conta depositaNaConta(Long idConta, Conta novoValor) {
		try {
			Conta entidade = retornaContaPorId(idConta);
			updateDeposito(entidade, novoValor);
			Transacao novaTransacao = new Transacao(null, novoValor.getSaldo(), LocalDate.now(), entidade);
			entidade.getTransacoes().add(novaTransacao);
			
			return contaRepository.save(entidade);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(idConta);
		}
	}
	
	public ContaSaldoDTO retornaSaldo(Long idConta) {
		Conta conta = retornaContaPorId(idConta);
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
		Double valorSacado = -novoValor.getSaldo();
		entidade.setSaldo(saldoAtual += valorSacado);
	}
	
	private void updateFlagAtivo(Conta entidade) {
		entidade.setFlagAtivo(false);
	}
}
