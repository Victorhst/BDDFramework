package br.gov.pb.receita.atfbdd.utils;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import es.indra.testes.akira.page.PageObject;

/**
 * Classe com m�todos que se repetem constantemente durante a implementa��o das classes Page.
 * Possui tamb�m m�todos que auxiliam as implementa��es tornando-as menos verbosas
 * e mais intuitivas.
 * <p>
 * @author victor.teixeira@receita.pb.gov.br
 * @author vhsilvat@indracompany.com
 *
 */
public class Utils extends PageObject {
	
	public Utils() {
		
	}
	
	/**
	 * O geckodriver n�o suporta os Actions Handlers.
	 * Este m�todo, portanto, alterna o handler da janela atual para uma nova janela
	 * que eventualmente seja aberta durante os testes.
	 * 
	 * @param tituloJanela t�tulo da nova janela, identificado durante
	 * as configura��es dos cen�rios de teste.
	 * <p>
	 * @author victor.teixeira@receita.pb.gov.br
	 * @author vhsilvat@indracompany.com
	 */
	public void alternarEntreDuasJanela(String tituloJanela) {
		
		String newWindow = tituloJanela;
		String mainWindow = getDriver().getWindowHandle();
		Set<String> allWindows = getDriver().getWindowHandles();
		
		for (String actualWindow : allWindows) {
			
			if (getDriver().switchTo().window(actualWindow).getTitle().equals(newWindow)) {
				
				break;
			} else {
				
				getDriver().switchTo().window(mainWindow);
			}
		}
	}
	
	/**
	 * O geckodriver n�o suporta os Actions Handlers.
	 * Este m�todo, portanto, alterna o handler da janela atual para uma nova janela
	 * que eventualmente seja aberta durante os testes.
	 * 
	 * Este m�todo n�o utiliza o nome da janela para alterna��o.
	 * <p>
	 * @author victor.teixeira@receita.pb.gov.br
	 * @author vhsilvat@indracompany.com
	 */
	@SuppressWarnings("unused")
	public void alternarJanelasAutomaticamente() {
		
		String mainWindow = getDriver().getWindowHandle();
		Set<String> allWindows = getDriver().getWindowHandles();
		
		for (String currentWindow : allWindows) {
			
			getDriver().switchTo().window(currentWindow);
		}
	}
	
	/**
	 * M�todo para capturar os popups de erro durante a execu��o dos testes.
	 * 
	 * @param mensagemEsperada compara a mensagem de erro esperada com a mensagem 
	 * de alerta capturada pelo m�todo.
	 * <p>
	 * @author victor.teixeira@receita.pb.gov.br
	 * @author vhsilvat@indracompany.com
	 */ 
	public void capturarPopup(String mensagemEsperada) {
		
		String alerta = getDriver().switchTo().alert().getText();
		assertEquals(mensagemEsperada, alerta);
	}
	
	public void capturarMensagemErro() throws Exception {
		
		WebElement value = getDriver().findElement(By.xpath("/html/body/div/h2"));
		value.getText();
		
		System.out.println("ERRO DURANTE A EXECU��O:");
		System.out.println("------------------------");
		System.out.println(value);
	}

}
