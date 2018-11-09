package br.gov.pb.receita.atfbdd.runner;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * Classe responsável por executar os testes descritos nas features 
 * do Cucumber. A execução só é possível por meio da anotação "CucumberOptions",
 * onde:
 * <ul>
 * <li>@feature = caminho do projeto onde estão descritas as features, com os scripts de teste.
 * <li>@glue = caminho do projeto onde estão os packages com os codigos para a execução dos scripts.
 * <li>@plugin = nesse contexto é utilizado o plugin "pretty", que prove
 * um output verboso no console de quantos scenarios e steps foram executados,
 * e seus possíveis erros durante a execução.
 * <li>@tags = tags de identificacao das features
 * </ul>
 * <p>
 * Cada feature possui uma gama de tags identificadoras. A combinação 
 * delas permite a execução de testes mais amplos
 * e abrangentes, ou testes mais afunilados e precisos. Tomando como exemplo
 * a feature "ConsultarCargo", temos as seguintes tags:
 * <ul>
 * <li>@RecursosHumanos = executa todos os testes do módulo de Recursos Humanos do ATF.
 * <li>@TabelasBasicas = executa todos os testes das funcionalidades de
 * Tabelas Básicas, dentro do módulo de Recursos Humanos.
 * <li>@Consultar = executa todos os testes das funcionalidades de "Consultar".
 * <li>@Cargo = executa todos os testes das funcionalidades de cargo (incluir, consultar, alterar e excluir)
 * <li>@ConsultarCargo = executa especificamente o teste de funcionalidade "Consultar Cargo".
 * <li>@FluxoBasico = executa o fluxo básico do caso de uso da funcionalidade em questão.
 * <li>@FluxoAlternativo = executa os fluxos alternativos da funcionalidade em questão.
 * <li>@A2 = cada fluxo alternativo diferente é tageado como "A1, A2, A3" e assim por diante.
 * </ul>
 * <p>
 * Para realizar as combinações, é preciso separar as tags por vírgulas e aspas.
 * Aglomerar todas as tags dentro de uma só aspas representa um cenário OR, ao passo que
 * separar as tags por aspas representa um cenário AND. É possivel ainda excluir determinadas
 * features ou scenarios utilizando o símbolo "~". Seguem exemplos:
 * <ul>
 * <li>tags = { "@ConsultarCargo", "@E1" } (executa apenas o fluxo de exceção marcados como E1
 * da funcionalidade consultar cargo.)
 * <li>tags = { "@Cargo", "FluxoExcecao" } (executa todos os fluxos de exceção da funcionalidade
 * tageada como "cargo" [incluir, consultar, alterar, incluir]).
 * <li>tags = { "@Cargo", "@E1", "~@Alterar" } (executa os fluxos de exceção, exceto o
 * fluxo de "alterar".)
 * funcionalidade.
 * <li>tags = { "@TabelasBasicas", "~@Escolaridade" } (executa todos os testes 
 * do módulo Tabelas Básicas, exceto a funcionalidade "Escolaridade".)
 * <li>tags = { "@RecursosHumanos" } (executa TODOS os testes de TODAS as funcionalidades
 * do módulo de recursos humanos.
 * </ul>
 * <p>
 * @author victor.teixeira@receita.pb.gov.br
 * @author vhsilvat@indracompany.com
 */
@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features/",
		glue = "br.gov.pb.receita.atfbdd.steps",
		plugin = {"pretty", "json:target/cucumber.json"},
		tags = {""})
public class CucumberRunner {
	
	@BeforeClass
	public static void setup() {
		
//		DriverFactory factory = new DriverFactory();
//		factory.iniciar();
		
		System.setProperty("webdriver.gecko.driver", "C:\\ambiente\\ferramentas\\drivers\\geckodriver.exe");
	}

}