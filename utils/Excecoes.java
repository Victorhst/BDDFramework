package br.gov.pb.receita.atfbdd.utils;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;

import es.indra.testes.akira.page.PageObject;

/**
 * Testes automatizados são muito suscetíveis à diversas exceções e alguns erros durante a execução.
 * Essa classe tem como objetivo mapear e tratar os mais diversos tipos de exceção encontrados
 * durante os testes utilizando um único metodo; destarte, evitando a repetição de código e
 * tornando o framework de teste mais coeso.
 * 
 * @author ...
 * @author ...
 *
 */
public class Excecoes extends PageObject {

	String codigoExcecao = "";

	public static final String ELEMENTO_NAO_ENCONTRADO = "Elemento, botão ou campo não encontrados na página.";
	public static final String ELEMENTO_INVISIVEL = "Elemento, botão ou campo inalcançáveis ou invisíveis.";
	public static final String FRAME_NAO_ENCONTRADO = "iFrame não encontrado na página";
	public static final String NAO_EXISTEM_OCORRENCIAS = "Não existem ocorrências para a consulta, ";
	public static final String DADOS_INCONSISTENTES = "Inconsistencia durante a verificação com os dados informados.";
	public static final String ERRO_DURANTE_EXECUCAO = "Erro durante a execução do teste, ";
	public static final String CAMPO_OBRIGATORIO = "Campo de preenchimento obrigatório, ";
	public static final String ACESSO_NAO_AUTORIZADO = "Acesso à funcionalidade não autorizado para este usuário, ";
	public static final String REGISTRO_DUPLICADO = "Registro duplicado, ";
	public static final String REGISTRO_JA_CADASTRADO = "Registro já cadastrado, ";
	public static final String ERRO_ACESSO_SISTEMA = "Ocorreu um erro de acesso no sistema, "; // 1026

	/**
	 * Este método captura a URL em caso de redirecionamento ou em casos de
	 * falha durante a localização de algum elemento de página, e então retorna,
	 * caso haja, o código do erro que o sistema ATF lança.
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
	 * Tratamento de exceção genérica, com base no código de erro do ATF.
	 * Retorna a mensagem contida no código, juntamente com o stacktrace.
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
	 * Tratamento de erro ao tentar localizar um elemento de página. Retorna uma
	 * mensagem informando a falha na localização do elemento, juntamente com o
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
	 * Tratamento de erro ao tentar localizar um iframe na página. Retorna uma
	 * mensagem informando a falha na localização do frame, juntamente com o
	 * stacktrace.
	 * 
	 * @param e
	 * @throws NoSuchFrameException
	 */
	public void retornarFrameNaoEncontrato(Throwable e) throws NoSuchFrameException {

		throw new NoSuchFrameException(FRAME_NAO_ENCONTRADO, e);
	}

	/**
	 * Tratamento de erro ao tentar localizar um elemento invisível ou ainda não
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
	 * Com base na subclasse de exceção capturada, este método retorna a exceção
	 * específica devidamente tratada, com uma mensagem informando o erro e o
	 * stacktrace da exceção. Subclasses diretamente ligadas a superclasse Throwable
	 * são as classes Error e Exception.
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
