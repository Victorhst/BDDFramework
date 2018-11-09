package br.gov.pb.receita.atfbdd.runner;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * Classe respons�vel por executar os testes descritos nas features 
 * do Cucumber. A execu��o s� � poss�vel por meio da anota��o "CucumberOptions",
 * onde:
 * <ul>
 * <li>@feature = caminho do projeto onde est�o descritas as features, com os scripts de teste.
 * <li>@glue = caminho do projeto onde est�o os packages com os codigos para a execu��o dos scripts.
 * <li>@plugin = nesse contexto � utilizado o plugin "pretty", que prove
 * um output verboso no console de quantos scenarios e steps foram executados,
 * e seus poss�veis erros durante a execu��o.
 * <li>@tags = tags de identificacao das features
 * </ul>
 * <p>
 * Cada feature possui uma gama de tags identificadoras. A combina��o 
 * delas permite a execu��o de testes mais amplos
 * e abrangentes, ou testes mais afunilados e precisos. Tomando como exemplo
 * a feature "ConsultarCargo", temos as seguintes tags:
 * <ul>
 * <li>@RecursosHumanos = executa todos os testes do m�dulo de Recursos Humanos do ATF.
 * <li>@TabelasBasicas = executa todos os testes das funcionalidades de
 * Tabelas B�sicas, dentro do m�dulo de Recursos Humanos.
 * <li>@Consultar = executa todos os testes das funcionalidades de "Consultar".
 * <li>@Cargo = executa todos os testes das funcionalidades de cargo (incluir, consultar, alterar e excluir)
 * <li>@ConsultarCargo = executa especificamente o teste de funcionalidade "Consultar Cargo".
 * <li>@FluxoBasico = executa o fluxo b�sico do caso de uso da funcionalidade em quest�o.
 * <li>@FluxoAlternativo = executa os fluxos alternativos da funcionalidade em quest�o.
 * <li>@A2 = cada fluxo alternativo diferente � tageado como "A1, A2, A3" e assim por diante.
 * </ul>
 * <p>
 * Para realizar as combina��es, � preciso separar as tags por v�rgulas e aspas.
 * Aglomerar todas as tags dentro de uma s� aspas representa um cen�rio OR, ao passo que
 * separar as tags por aspas representa um cen�rio AND. � possivel ainda excluir determinadas
 * features ou scenarios utilizando o s�mbolo "~". Seguem exemplos:
 * <ul>
 * <li>tags = { "@ConsultarCargo", "@E1" } (executa apenas o fluxo de exce��o marcados como E1
 * da funcionalidade consultar cargo.)
 * <li>tags = { "@Cargo", "FluxoExcecao" } (executa todos os fluxos de exce��o da funcionalidade
 * tageada como "cargo" [incluir, consultar, alterar, incluir]).
 * <li>tags = { "@Cargo", "@E1", "~@Alterar" } (executa os fluxos de exce��o, exceto o
 * fluxo de "alterar".)
 * funcionalidade.
 * <li>tags = { "@TabelasBasicas", "~@Escolaridade" } (executa todos os testes 
 * do m�dulo Tabelas B�sicas, exceto a funcionalidade "Escolaridade".)
 * <li>tags = { "@RecursosHumanos" } (executa TODOS os testes de TODAS as funcionalidades
 * do m�dulo de recursos humanos.
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