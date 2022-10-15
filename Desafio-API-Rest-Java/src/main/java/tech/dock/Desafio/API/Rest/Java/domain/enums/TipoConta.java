package tech.dock.Desafio.API.Rest.Java.domain.enums;

public enum TipoConta {
	
	CONTA_POUPANCA(1, "Conta poupança"),
	CONTA_SALARIO(2, "Conta salário"),
	CONTA_CORRENTE(2, "Pessoa jurídica");
	
	private Integer codigo;
	private String descricao;
	
	private TipoConta(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoConta valueOf(Integer codigo) {
		
		if(codigo == null) {
			return null;
		}
		
		for(TipoConta valor: TipoConta.values()) {
			if(valor.getCodigo() == codigo) {
				return valor;
			}
		}
		
		throw new IllegalArgumentException("Código do TipoConta inválido");
	}
}
