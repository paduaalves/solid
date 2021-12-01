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
import br.com.alura.rh.service.promocao.PromocaoService;

public class PromocaoServiceTest {

	@Test()
	public void deveriaPromoverParaAnalista() {
		PromocaoService promocaoService = new PromocaoService();
		Funcionario funcionario = new Funcionario("Pádua", "02804613305", Cargo.ASSISTENTE, new BigDecimal("1000"));
		promocaoService.promover(funcionario, true);
		assertEquals(Cargo.ANALISTA, funcionario.getCargo());
	}
	@Test()
	public void deveriaPromoverParaEspecialista() {
		PromocaoService promocaoService = new PromocaoService();
		Funcionario funcionario = new Funcionario("Pádua", "02804613305", Cargo.ANALISTA, new BigDecimal("1000"));
		promocaoService.promover(funcionario, true);
		assertEquals(Cargo.ESPECIALISTA, funcionario.getCargo());
	}
	@Test()
	public void deveriaPromoverParaGerente() {
		PromocaoService promocaoService = new PromocaoService();
		Funcionario funcionario = new Funcionario("Pádua", "02804613305", Cargo.ESPECIALISTA, new BigDecimal("1000"));
		promocaoService.promover(funcionario, true);
		assertEquals(Cargo.GERENTE, funcionario.getCargo());
	}
	
	@Test()
	public void deveriaImpedirPromocaoComMetaNaoBatida() {
		PromocaoService promocaoService = new PromocaoService();
		Funcionario funcionario = new Funcionario("Pádua", "02804613305", Cargo.ESPECIALISTA, new BigDecimal("1000"));
		ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
			promocaoService.promover(funcionario, false);;
		});
		assertEquals("Funcionário não bateu a meta.", exception.getMessage());
	}

	@Test()
	public void deveriaImpedirPromocaoParaOCargoGerente() {
		PromocaoService promocaoService = new PromocaoService();
		Funcionario funcionario = new Funcionario("Pádua", "02804613305", Cargo.GERENTE, new BigDecimal("1000"));
		ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
			promocaoService.promover(funcionario, false);;
		});
		assertEquals("Gerentes não podem ser promovidos.", exception.getMessage());
	}
}
