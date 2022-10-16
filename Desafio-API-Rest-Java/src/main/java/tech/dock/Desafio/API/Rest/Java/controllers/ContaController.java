package tech.dock.Desafio.API.Rest.Java.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tech.dock.Desafio.API.Rest.Java.domain.Conta;
import tech.dock.Desafio.API.Rest.Java.dto.ContaSaldoDTO;
import tech.dock.Desafio.API.Rest.Java.dto.ContaTransacoesDTO;
import tech.dock.Desafio.API.Rest.Java.services.ContaService;

@RestController
@RequestMapping(value = "/contas")
public class ContaController {
	
	@Autowired
	private ContaService contaService;
	
	@PostMapping(value = "/criar")
	public ResponseEntity<Void> inserir(@RequestBody Conta obj){
		obj = contaService.criarConta(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idConta}").buildAndExpand(obj.getIdConta()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{idConta}/depositar")
	public ResponseEntity<Conta> deposito(@PathVariable Long idConta, @RequestBody Conta novoValor){
		novoValor = contaService.depositaNaConta(idConta, novoValor);
		return ResponseEntity.ok().body(novoValor);
	}
	
	@GetMapping(value = "/{idConta}/saldo")
	public ResponseEntity<ContaSaldoDTO> consultaSaldo(@PathVariable Long idConta){
		ContaSaldoDTO contaSaldoDTO = contaService.retornaSaldo(idConta);
		return ResponseEntity.ok().body(contaSaldoDTO);
	}
	
	@PutMapping(value = "/{idConta}/sacar")
	public ResponseEntity<Conta> sacar(@PathVariable Long idConta, @RequestBody Conta novoValor){
		novoValor = contaService.saqueNaConta(idConta, novoValor);
		return ResponseEntity.ok().body(novoValor);
	}
	
	@GetMapping(value = "/{idConta}/transacoes")
	public ResponseEntity<ContaTransacoesDTO> retornaTransacoes(@PathVariable Long idConta){
		Conta conta = contaService.retornaConta(idConta);
		ContaTransacoesDTO contaTransacoesDTO = new ContaTransacoesDTO(conta);
		return ResponseEntity.ok().body(contaTransacoesDTO);
	}
	
	@PutMapping(value = "/{idConta}/bloquear")
	public ResponseEntity<Conta> bloquear(@PathVariable Long idConta){
		Conta novoValor = contaService.bloqueiaConta(idConta);
		return ResponseEntity.ok().body(novoValor);
	}
}
