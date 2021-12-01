package br.com.alura.rh.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Funcionario {

	@NonNull
	private String nome;
	@NonNull
	private String cpf;
	@NonNull
	private Cargo cargo;
	@NonNull
	private BigDecimal salario;
	private LocalDate dataUltimoReajuste;

	public void atualizarSalario(BigDecimal novoSalario) {
		this.salario = novoSalario;
		this.dataUltimoReajuste = LocalDate.now();
	}

	public void promover(Cargo novoCargo) {
		this.cargo = novoCargo;

	}
}
