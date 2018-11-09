package br.gov.pb.receita.atfbdd.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Classe utilitária em conjunto com as properties.
 * Define as URLs do ATF e configurações do testlink. 
 * 
 * @author victor.teixeira@receita.pb.gov.br
 * @author vhsilvat@indracompany.com
 */
public class CommonProperties {
	
	public static final String CONFIG_FILE = "src/test/resources/automacao.properties";

	public static final String URL_INTEGRACAO;
	public static final String URL_HOMOLOGACAO;
	
	public static final String URL_TESTLINK;
	public static final String DEVKEY;
	public static final String PROJETO;
	public static final String BUILD;
	public static final String PLANO_TESTE;

	static {
		
		URL_INTEGRACAO = get("base.url.des");
		URL_HOMOLOGACAO = get("base.url.hom");
		
		URL_TESTLINK = get("url.testlink");
		DEVKEY = get("devkey");
		PROJETO = get("projeto");
		BUILD = get("build");
		PLANO_TESTE = get("plano");
	}
	
	/**
	 * Método para pegar o valor de alguma propriedade no arquivo de properties.
	 * O caminho e o nome do arquivo pode ser trocados.
	 */
	private static String get(String name) {
		
		Properties properties = new Properties();
		String value = null;
		
		try {
		
			FileInputStream file = new FileInputStream(CONFIG_FILE);
			properties.load(file);
		    value = properties.getProperty(name);
		    
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return value;
	}

}
