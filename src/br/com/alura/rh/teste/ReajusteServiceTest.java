package br.com.alura.rh.teste;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.alura.rh.ValidacaoException;
import br.com.alura.rh.model.Cargo;
import br.com.alura.rh.model.Funcionario;
import br.com.alura.rh.service.PeriodicidadeEntreReajustes;
import br.com.alura.rh.service.ReajusteService;
import br.com.alura.rh.service.ValidacaoPercentualReajuste;
import br.com.alura.rh.service.ValidacaoReajuste;

public class ReajusteServiceTest {

	@Test()
	public void deveriaImpedirReajusteSalarialComPeriodoInferiorA6Meses() {
		ValidacaoReajuste validacaoReajuste = new PeriodicidadeEntreReajustes();
		List<ValidacaoReajuste> validacoes = new ArrayList<ValidacaoReajuste>();
		validacoes.add(validacaoReajuste);
		ReajusteService reajusteService=new ReajusteService(validacoes);
		Funcionario funcionario = new Funcionario("Pádua", "02804613305", Cargo.ANALISTA, new BigDecimal("1000"));
		funcionario.setDataUltimoReajuste(LocalDate.now());
		BigDecimal reajuste = new BigDecimal("100");

		ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
			reajusteService.reajustarSalariodoFuncionario(funcionario, reajuste);
		});

		assertEquals("O intevalo entre reajustes deve ser de no minimo 6 meses", exception.getMessage());

	}

	@Test()
	public void deveriaPermitirReajusteSalarialComPeriodoSuperiorA6Meses() {
		ValidacaoReajuste validacaoReajuste = new PeriodicidadeEntreReajustes();
		List<ValidacaoReajuste> validacoes = new ArrayList<ValidacaoReajuste>();
		validacoes.add(validacaoReajuste);
		ReajusteService reajusteService=new ReajusteService(validacoes);
		Funcionario funcionario = new Funcionario("Pádua", "02804613305", Cargo.ANALISTA, new BigDecimal("1000"));
		funcionario.setDataUltimoReajuste(LocalDate.of(2020, 01, 01));
		BigDecimal reajuste = new BigDecimal("100");
		reajusteService.reajustarSalariodoFuncionario(funcionario, reajuste);
		assertEquals(new BigDecimal("1100"), funcionario.getSalario());

	}

	@Test()
	public void deveriaImpedirReajusteSalarialSuperiorA40Porcento() {
		ValidacaoReajuste validacaoPercentualReajuste = new ValidacaoPercentualReajuste();
		List<ValidacaoReajuste> validacoes = new ArrayList<ValidacaoReajuste>();
		validacoes.add(validacaoPercentualReajuste);
		ReajusteService reajusteService=new ReajusteService(validacoes);
		Funcionario funcionario = new Funcionario("Pádua", "02804613305", Cargo.ANALISTA, new BigDecimal("1000"));
		funcionario.setDataUltimoReajuste(LocalDate.now());
		BigDecimal reajuste = new BigDecimal("1000");

		ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
			reajusteService.reajustarSalariodoFuncionario(funcionario, reajuste);
		});

		assertEquals("O Reajuste nao pode ser superior a 40% do salario!", exception.getMessage());

	}
}
