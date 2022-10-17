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

/**
 * Classe que fará operações com a conta na camada de serviço.
 * @author gabrielribeirojb
 *
 */

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
	
	/**
	 * Retorna uma referência de uma conta no banco de dados.
	 * @param idConta
	 * @return
	 */
	public Conta retornaConta(Long idConta){
		return contaRepository.getReferenceById(idConta);
	}
	
	/**
	 * Retorna uma conta do banco de dados.
	 * @param idConta
	 * @return
	 */
	public Conta retornaContaPorId(Long idConta) {
		Optional<Conta> obj = contaRepository.findById(idConta);
		return obj.orElseThrow(() -> new ResourceNotFoundException(idConta));
	}
	
	@Transactional
	public Conta depositaNaConta(Long idConta, Conta novoValor) {
		try {
			Conta entidade = retornaConta(idConta);
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
		try {
			Conta entidade = retornaConta(idConta);
			
			updateSaque(entidade, novoValor);
			Transacao novaTransacao = new Transacao(null, -novoValor.getSaldo(), LocalDate.now(), entidade);
			entidade.getTransacoes().add(novaTransacao);
			return contaRepository.save(entidade);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(idConta);
		}
	}
	
	public Conta bloqueiaConta(Long idConta) {
		Conta entidade = retornaContaPorId(idConta);
		
		updateFlagAtivo(entidade);
		return contaRepository.save(entidade);
	}
	
	
	/**
	 * Método auxiliar que determina um novo valor no saldo quando há um depósito, 
	 * fazendo a comparação entre o saldo atual e o novo saldo.
	 * @param entidade
	 * @param novoValor
	 */
	private void updateDeposito(Conta entidade, Conta novoValor) {
		Double saldoAtual = entidade.getSaldo();
		entidade.setSaldo(saldoAtual += novoValor.getSaldo());
	}
	
	/**
	 * Método auxiliar que determina um novo valor no saldo quando há um saque, 
	 * fazendo a comparação entre o saldo atual e o novo saldo.
	 * @param entidade
	 * @param novoValor
	 */
	private void updateSaque(Conta entidade, Conta novoValor) {
		Double saldoAtual = entidade.getSaldo();
		Double valorSacado = -novoValor.getSaldo();
		entidade.setSaldo(saldoAtual += valorSacado);
	}
	
	/**
	 * Método auxiliar que determina um novo valor ao campo flagAtivo de uma Conta quando ela
	 * for bloqueada.
	 * @param entidade
	 * @param novoValor
	 */
	private void updateFlagAtivo(Conta entidade) {
		entidade.setFlagAtivo(false);
	}
}
