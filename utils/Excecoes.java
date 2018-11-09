package br.gov.pb.receita.atfbdd.utils;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;

import es.indra.testes.akira.page.PageObject;

/**
 * Testes automatizados s�o muito suscet�veis � diversas exce��es e alguns erros durante a execu��o.
 * Essa classe tem como objetivo mapear e tratar os mais diversos tipos de exce��o encontrados
 * durante os testes utilizando um �nico metodo; destarte, evitando a repeti��o de c�digo e
 * tornando o framework de teste mais coeso.
 * 
 * @author victor.teixeira@receita.pb.gov.br
 * @author vhsilvat@indracompany.com
 *
 */
public class Excecoes extends PageObject {

	String codigoExcecao = "";

	public static final String ELEMENTO_NAO_ENCONTRADO = "Elemento, bot�o ou campo n�o encontrados na p�gina.";
	public static final String ELEMENTO_INVISIVEL = "Elemento, bot�o ou campo inalcan��veis ou invis�veis.";
	public static final String FRAME_NAO_ENCONTRADO = "iFrame n�o encontrado na p�gina";
	public static final String NAO_EXISTEM_OCORRENCIAS = "N�o existem ocorr�ncias para a consulta, ";
	public static final String DADOS_INCONSISTENTES = "Inconsistencia durante a verifica��o com os dados informados.";
	public static final String ERRO_DURANTE_EXECUCAO = "Erro durante a execu��o do teste, ";
	public static final String CAMPO_OBRIGATORIO = "Campo de preenchimento obrigat�rio, ";
	public static final String ACESSO_NAO_AUTORIZADO = "Acesso � funcionalidade n�o autorizado para este usu�rio, ";
	public static final String REGISTRO_DUPLICADO = "Registro duplicado, ";
	public static final String REGISTRO_JA_CADASTRADO = "Registro j� cadastrado, ";
	public static final String ERRO_ACESSO_SISTEMA = "Ocorreu um erro de acesso no sistema, "; // 1026

	/**
	 * Este m�todo captura a URL em caso de redirecionamento ou em casos de
	 * falha durante a localiza��o de algum elemento de p�gina, e ent�o retorna,
	 * caso haja, o c�digo do erro que o sistema ATF lan�a.
	 */
	public void getCodigoUrl() {

		String url = getDriver().getCurrentUrl();
		String value = url;

		if (value.length() < 73) {

			codigoExcecao = null;

		} else {

			String codigo = value.substring(74);
			codigoExcecao = codigo;
		}
	}

	/**
	 * Tratamento de exce��o gen�rica, com base no c�digo de erro do ATF.
	 * Retorna a mensagem contida no c�digo, juntamente com o stacktrace.
	 * 
	 * @param e
	 * @throws Exception
	 */
	public void retornarCodigoErro(Throwable e) throws Exception {

		getCodigoUrl();

		if (codigoExcecao != "" && codigoExcecao.contains("codigo=267")) {
			throw new Exception(NAO_EXISTEM_OCORRENCIAS + codigoExcecao, e);

		} else if (codigoExcecao != "" && codigoExcecao.contains("codigo=1026")) {
			throw new Exception(ERRO_ACESSO_SISTEMA + codigoExcecao, e);

		} else if (codigoExcecao != "" && codigoExcecao.contains("codigo=384")) {
			throw new Exception(REGISTRO_DUPLICADO + codigoExcecao, e);

		} else if (codigoExcecao != "" && codigoExcecao.contains("codigo=9")) {
			throw new Exception(REGISTRO_JA_CADASTRADO + codigoExcecao, e);

		} else if (codigoExcecao != "" && codigoExcecao.contains("codigo=27")) {
			throw new Exception(CAMPO_OBRIGATORIO + codigoExcecao, e);

		} else if (codigoExcecao != "" && codigoExcecao.contains("codigo=46")) {
			throw new Exception(ACESSO_NAO_AUTORIZADO + codigoExcecao, e);

		} else if (codigoExcecao != "" && codigoExcecao.contains("codigo")) {
			throw new Exception(ERRO_DURANTE_EXECUCAO + codigoExcecao, e);

		} else if (codigoExcecao == null) {
			throw new Exception(ELEMENTO_NAO_ENCONTRADO, e);
		}
	}

	/**
	 * Tratamento de erro ao tentar localizar um elemento de p�gina. Retorna uma
	 * mensagem informando a falha na localiza��o do elemento, juntamente com o
	 * stacktrace.
	 * 
	 * @param e
	 * @throws NoSuchElementException
	 */
	public void retornarElementoNaoEncontrado(Throwable e) throws NoSuchElementException {

		getCodigoUrl();

		if (codigoExcecao != "" && codigoExcecao.contains("codigo=267")) {
			throw new NoSuchElementException(NAO_EXISTEM_OCORRENCIAS + codigoExcecao, e);

		} else if (codigoExcecao != "" && codigoExcecao.contains("codigo=1026")) {
			throw new NoSuchElementException(ERRO_ACESSO_SISTEMA + codigoExcecao, e);

		} else if (codigoExcecao != "" && codigoExcecao.contains("codigo=384")) {
			throw new NoSuchElementException(REGISTRO_DUPLICADO + codigoExcecao, e);

		} else if (codigoExcecao != "" && codigoExcecao.contains("codigo=9")) {
			throw new NoSuchElementException(REGISTRO_JA_CADASTRADO + codigoExcecao, e);

		} else if (codigoExcecao != "" && codigoExcecao.contains("codigo=27")) {
			throw new NoSuchElementException(CAMPO_OBRIGATORIO + codigoExcecao, e);

		} else if (codigoExcecao != "" && codigoExcecao.contains("codigo=46")) {
			throw new NoSuchElementException(ACESSO_NAO_AUTORIZADO + codigoExcecao, e);

		} else if (codigoExcecao != "" && codigoExcecao.contains("codigo")) {
			throw new NoSuchElementException(ERRO_DURANTE_EXECUCAO + codigoExcecao, e);

		} else if (codigoExcecao == null) {
			throw new NoSuchElementException(ELEMENTO_NAO_ENCONTRADO, e);
		}
	}

	/**
	 * Tratamento de assertion errors do JUnit. Retorna uma mensagem informando
	 * a inconsistencia de dados, juntamente com o stacktrace.
	 * 
	 * @param e
	 * @throws AssertionError
	 */
	public void retornarInconsistencia(Throwable e) throws AssertionError {

		throw new AssertionError(DADOS_INCONSISTENTES, e);
	}

	/**
	 * Tratamento de erro ao tentar localizar um iframe na p�gina. Retorna uma
	 * mensagem informando a falha na localiza��o do frame, juntamente com o
	 * stacktrace.
	 * 
	 * @param e
	 * @throws NoSuchFrameException
	 */
	public void retornarFrameNaoEncontrato(Throwable e) throws NoSuchFrameException {

		throw new NoSuchFrameException(FRAME_NAO_ENCONTRADO, e);
	}

	/**
	 * Tratamento de erro ao tentar localizar um elemento invis�vel ou ainda n�o
	 * carregado. Retorna uma mensagem informando a invisibilidade do elemento,
	 * juntamente com o stacktrace.
	 * 
	 * @param e
	 * @throws ElementNotVisibleException
	 */
	public void retornarElementoInvisivel(Throwable e) throws ElementNotVisibleException {

		throw new ElementNotVisibleException(ELEMENTO_INVISIVEL, e);
	}

	/**
	 * Com base na subclasse de exce��o capturada, este m�todo retorna a exce��o
	 * espec�fica devidamente tratada, com uma mensagem informando o erro e o
	 * stacktrace da exce��o. Subclasses diretamente ligadas a superclasse Throwable
	 * s�o as classes Error e Exception.
	 * 
	 * @param e
	 * @throws Throwable
	 */
	public void tratarExcecoes(Throwable e) throws Throwable {

		String value = e.toString();

		if (value.contains("AssertionError")) {
			retornarInconsistencia(e);

		} else if (value.contains("ElementNotVisibleException")) {
			retornarElementoInvisivel(e);

		} else if (value.contains("NoSuchFrameException")) {
			retornarFrameNaoEncontrato(e);

		} else if (value.contains("NoSuchElementException")) {

			retornarElementoNaoEncontrado(e);

		} else {

			retornarCodigoErro(e);
		}
	}

}