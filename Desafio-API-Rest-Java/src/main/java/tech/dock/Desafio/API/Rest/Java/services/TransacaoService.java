package tech.dock.Desafio.API.Rest.Java.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tech.dock.Desafio.API.Rest.Java.domain.Transacao;
import tech.dock.Desafio.API.Rest.Java.repositories.TransacaoRepository;
@Service
public class TransacaoService {
	
	@Autowired
	private TransacaoRepository transacaoRepository;
	
	public Page<Transacao> findTransacoes(String dataMin, String dataMax, Pageable pageable) {
		LocalDate hoje = LocalDate.now();
		
		LocalDate min = dataMin.equalsIgnoreCase("") ? hoje.minusDays(30): LocalDate.parse(dataMin);
		LocalDate max = dataMin.equalsIgnoreCase("") ? hoje: LocalDate.parse(dataMax);
		
		return transacaoRepository.findTransacoesPaginado(min, max, pageable);

	}
	
}
