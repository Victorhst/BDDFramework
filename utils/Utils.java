package br.gov.pb.receita.atfbdd.utils;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import es.indra.testes.akira.page.PageObject;

/**
 * Classe com métodos que se repetem constantemente durante a implementação das classes Page.
 * Possui também métodos que auxiliam as implementações tornando-as menos verbosas
 * e mais intuitivas.
 * 
 * @author ...
 * @author ...
 *
 */
public class Utils extends PageObject {
	
	public Utils() {
		
	}
	
	/**
	 * O geckodriver não suporta os Actions Handlers.
	 * Este método, portanto, alterna o handler da janela atual para uma nova janela
	 * que eventualmente seja aberta durante os testes.
	 * 
	 * @param tituloJanela título da nova janela, identificado durante
	 * as configurações dos cenários de teste.
	 * <p>
	 * @author ...
	 * @author ...
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
	 * O geckodriver não suporta os Actions Handlers.
	 * Este método, portanto, alterna o handler da janela atual para uma nova janela
	 * que eventualmente seja aberta durante os testes.
	 * 
	 * Este método não utiliza o nome da janela para alternação.
	 * <p>
	 * @author ...
	 * @author ...
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
	 * Método para capturar os popups de erro durante a execução dos testes.
	 * 
	 * @param mensagemEsperada compara a mensagem de erro esperada com a mensagem 
	 * de alerta capturada pelo método.
	 * <p>
	 * @author ...
	 * @author ...
	 */ 
	public void capturarPopup(String mensagemEsperada) {
		
		String alerta = getDriver().switchTo().alert().getText();
		assertEquals(mensagemEsperada, alerta);
	}
	
	public void capturarMensagemErro() throws Exception {
		
		WebElement value = getDriver().findElement(By.xpath("/html/body/div/h2"));
		value.getText();
		
		System.out.println("ERRO DURANTE A EXECUÇÃO:");
		System.out.println("------------------------");
		System.out.println(value);
	}

}
