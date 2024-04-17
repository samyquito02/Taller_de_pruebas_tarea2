package tallerselenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Tarea2 {
	@Test
	public void caso01() {
		System.setProperty("webdriver.edge.driver", "C:\\Users\\jfran\\msedgedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("https://www.buscalibre.com.co/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement inputBusqueda = driver.findElement(By.name("q"));
        String textoBuscar = "xyz123";
        inputBusqueda.sendKeys(textoBuscar);
        WebElement botonBuscar = driver.findElement(By.id("botonBuscarHeader"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", botonBuscar);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement mensajeNoEncontrado = driver.findElement(By.cssSelector("#noEncontrado p"));
        String textoNoEncontrado = mensajeNoEncontrado.getText();
        String textoEsperado = "Lo sentimos, pero no encontramos lo que buscas: " + textoBuscar;
        assertEquals(textoEsperado, textoNoEncontrado, "El mensaje de no encontrado no coincide");
        driver.quit();
	}
	
	@Test
	public void caso02() {
		System.setProperty("webdriver.edge.driver", "C:\\Users\\jfran\\msedgedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("https://www.buscalibre.com.co/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement inputBusqueda = driver.findElement(By.name("q"));
        String textoBuscar = "Patrick Rothfuss";
        inputBusqueda.sendKeys(textoBuscar);
        WebElement botonBuscar = driver.findElement(By.id("botonBuscarHeader"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", botonBuscar);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        List<WebElement> elementosLibros = driver.findElements(By.cssSelector(".productos .box-producto"));
        boolean libroEncontrado = false;
        for (WebElement elementoLibro : elementosLibros) {
            WebElement autorElemento = elementoLibro.findElement(By.cssSelector(".autor"));
            String autorTexto = autorElemento.getText();
            if (autorTexto.contains("Patrick Rothfuss")) {
                libroEncontrado = true;
                break;
            }
        }
        
        assertTrue(libroEncontrado, "No se encontró ningún libro escrito por Patrick Rothfuss");
        driver.quit();
	}
}
