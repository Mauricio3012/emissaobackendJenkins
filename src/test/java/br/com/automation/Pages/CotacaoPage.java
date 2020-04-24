package br.com.automation.Pages;

import static io.restassured.RestAssured.given;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import br.com.automation.Utils.GerarCpfCnpj;

import cucumber.api.Scenario;
import io.restassured.RestAssured;

@SuppressWarnings("deprecation")
public class CotacaoPage {
	private static final Scenario String = null;
	private static WebDriver navegador;
	public static String nrLocalizer;
	public static String nrToken;
	public String cenarioCucumber;

	private static EmissaoPage emissaoPage = new EmissaoPage();
	
	
	static io.restassured.specification.RequestSpecification requestspecs = given();

	@Test
	public void emitirCotacao() throws Exception {

		System.setProperty("webdriver.chrome.driver", "c:\\temp\\driver\\chromedriver.exe");
		navegador = new ChromeDriver();
		navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized");
//		navegador = (WebDriver) new ChromeOptions();

		// Navegando para a página
		navegador.get("https://cotacao.dev-cld.brasilseg.com.br/");

		navegador.findElement(By.xpath("//h4[text()='Residencial']")).click();
		Thread.sleep(1000);
		navegador.findElement(By.xpath("//button[@id='btn-proceed']")).click();
		Thread.sleep(1000);
		navegador.findElement(By.xpath("//div[text()='BB Seguro Residencial']")).click();
		Thread.sleep(1000);
		navegador.findElement(By.xpath("//button[@id='button-positive']")).click();
		Thread.sleep(1000);
		navegador.findElement(By.xpath("//h4[text()='Plano Básico']//../../div/div")).click();
		Thread.sleep(1000);
		navegador.findElement(By.xpath("//h3[text()='8 anos']")).click();
		Thread.sleep(1000);
		navegador.findElement(By.xpath("//button[text()='Selecionar']")).click();
		Thread.sleep(1000);
		navegador.findElement(By.xpath("//button[@id='button-positive']")).click();

		Thread.sleep(2000);
		capturarLocalizer();
		capturarToken();
		Thread.sleep(2000);

		navegador.quit();
	}

	@Test
	public void emitirCotacaoBeneficiario(String cenario) throws Exception {

		System.setProperty("webdriver.chrome.driver", "c:\\temp\\driver\\chromedriver.exe");
		navegador = new ChromeDriver();
		navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized");
//		navegador = (WebDriver) new ChromeOptions();

		
		// Navegando para a página
		navegador.get("https://cotacao.dev-cld.brasilseg.com.br/");

		navegador.findElement(By.xpath("//h4[text()='Residencial']")).click();
		Thread.sleep(1000);
		navegador.findElement(By.xpath("//button[@id='btn-proceed']")).click();
		Thread.sleep(1000);
		navegador.findElement(By.xpath("//div[text()='BB Seguro Residencial']")).click();
		Thread.sleep(1000);
		navegador.findElement(By.xpath("//button[@id='button-positive']")).click();
		Thread.sleep(1000);
		navegador.findElement(By.xpath("//h4[text()='Plano Básico']//../../div/div")).click();
		Thread.sleep(1000);
		navegador.findElement(By.xpath("//h3[text()='8 anos']")).click();
		Thread.sleep(1000);
		navegador.findElement(By.xpath("//button[text()='Selecionar']")).click();
		Thread.sleep(1000);
		navegador.findElement(By.xpath("//button[@id='button-positive']")).click();

		Thread.sleep(2000);
		capturarLocalizer();
		capturarToken();
		Thread.sleep(2000);

		String cpfFinal = geraCPF();
		
		navegador.findElement(By.id("searchCustomerData")).sendKeys(cpfFinal);
		Thread.sleep(1000);
		navegador.findElement(By.id("button-positive")).click();
		Thread.sleep(1500);
		navegador.findElement(By.id("txNomeCliente")).sendKeys("Mauricio Oliveira");
		Thread.sleep(1000);
		navegador.findElement(By.id("txNomeCliente")).sendKeys(Keys.TAB);
		Thread.sleep(1000);
		navegador.findElement(By.xpath("//input[@class='bb-input-icon react-datepicker-ignore-onclickoutside']")).sendKeys("30121989");
		Thread.sleep(1000);
		navegador.findElement(By.id("button-positive")).click();
		Thread.sleep(1500);
		navegador.findElement(By.xpath("//button[@id='add']")).click();
		Thread.sleep(1000);	
		navegador.findElement(By.id("txCep")).sendKeys("04521000");
		Thread.sleep(1000);
		
//		//Problema na API DE CEP
//		navegador.findElement(By.id("txLogradouro")).sendKeys("Avenida Adolfo Pinheiro");
//		Thread.sleep(1000);
//		navegador.findElement(By.id("txBairro")).sendKeys("Santo Amaro");
//		Thread.sleep(1000);
//		navegador.findElement(By.id("txMunicipio")).sendKeys("Sao Paulo");
//		Thread.sleep(1000);
//		navegador.findElement(By.id("nmUf")).click();
//		Thread.sleep(1000);
//		navegador.findElement(By.xpath("//span[text()='SP']")).click();
//		Thread.sleep(1000);
//		navegador.findElement(By.xpath("//div[text()='Selecionar país']")).click();
//		Thread.sleep(1000);
//		navegador.findElement(By.xpath("//span[text()='Brasil']")).click();
//		Thread.sleep(1000);
			
		navegador.findElement(By.id("txNumero")).click();
		Thread.sleep(1000);
		navegador.findElement(By.id("txNumero")).sendKeys("100");
		Thread.sleep(1000);
		navegador.findElement(By.xpath("//div[@id='address-type']")).click();
		Thread.sleep(1000);	
		navegador.findElement(By.xpath("//span[text()='Residencial']")).click();
		Thread.sleep(1000);		
		navegador.findElement(By.xpath("//button[text()='Salvar']")).click();
		Thread.sleep(2000);
		navegador.findElement(By.id("proceed")).click();
		Thread.sleep(1000);
		navegador.findElement(By.xpath("//label[text()='Novo']")).click();
		Thread.sleep(1000);
		navegador.findElement(By.id("new-contact-selector-email")).click();
		Thread.sleep(1000);
		navegador.findElement(By.id("txEmail")).sendKeys("mauricio@gmail.com");
		Thread.sleep(1000);
		navegador.findElement(By.xpath("//div[@id='tpEmail']")).click();
		Thread.sleep(1000);
		navegador.findElement(By.xpath("//span[text()='Pessoal']")).click();
		Thread.sleep(1000);
		navegador.findElement(By.xpath("//button[text()='Salvar']")).click();
		Thread.sleep(1000);
		navegador.findElement(By.id("new-contact-selector-phone")).click();
		Thread.sleep(1000);
		navegador.findElement(By.id("txNmContato")).sendKeys("Mauricio");
		Thread.sleep(1000);
		navegador.findElement(By.id("txDdd")).sendKeys("11");
		Thread.sleep(1000);
		navegador.findElement(By.id("txTelefone")).sendKeys("953803043");
		Thread.sleep(1000);
		navegador.findElement(By.xpath("//div[@id='tpTelefone']")).click();
		Thread.sleep(1000);
		navegador.findElement(By.xpath("//span[text()='Celular']")).click();
		Thread.sleep(1000);
		navegador.findElement(By.xpath("//button[text()='Salvar']")).click();
		Thread.sleep(2000);
		navegador.findElement(By.id("button-proceed")).click();
		Thread.sleep(2000);
		navegador.findElement(By.xpath("//label[text()='Novo']")).click();
		Thread.sleep(1000);
		navegador.findElement(By.id("obCEP")).sendKeys("04521000");
		Thread.sleep(1000);
		navegador.findElement(By.id("obLogradouro")).sendKeys("Avenida Adolfo Pinheiro");
		Thread.sleep(1000);
		navegador.findElement(By.id("obNumero")).sendKeys("1000");
		Thread.sleep(1000);
		navegador.findElement(By.id("obBairro")).sendKeys("Santo Amaro");
		Thread.sleep(1000);
		navegador.findElement(By.id("obMunicipio")).sendKeys("São Paulo");
		Thread.sleep(1000);
		navegador.findElement(By.id("obUf")).sendKeys("SP");
		Thread.sleep(1000);
		navegador.findElement(By.id("obPais")).sendKeys("Brasil");
		Thread.sleep(1000);
		navegador.findElement(By.xpath("//div[text()='O imóvel é próximo de favela?']")).click();
		Thread.sleep(1000);
		navegador.findElement(By.xpath("//span[text()='Sim']")).click();
		Thread.sleep(2000);
		navegador.findElement(By.xpath("//button[text()='Salvar']")).click();
		Thread.sleep(2000);
		navegador.quit();
	}

	public String geraCPF() {
		GerarCpfCnpj gerador = new GerarCpfCnpj();
		String cpf = gerador.cpf(true);
		System.out.println(cpf);
		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");
		return cpf;
	}
	
	public static void capturarLocalizer() throws Exception {

		JavascriptExecutor js = (JavascriptExecutor) navegador;
		String value = (String) js.executeScript("return localStorage.getItem('localizer')");

		nrLocalizer = value;
//		data.setData("vOut_Numero_Localizador_Cotacao", value);
		System.out.println("O localizer da cotação é " + value);
	}

	public static void capturarToken() throws Exception {

		JavascriptExecutor js = (JavascriptExecutor) navegador;
		String valueToken = (String) js.executeScript("return localStorage.getItem('authorization')");

		nrToken = valueToken;
//		data.setData("vOut_Numero_Localizador_Cotacao", value);
		System.out.println("O token da cotação é " + valueToken);

	}

	public static void capturarLocalizadorObjetoSegurado() throws Exception {

		RestAssured.baseURI = "https://apigateway.dev-cld.brasilseg.com.br/api/emissao/cotacao";
	
		
		emissaoPage.enviaGETBeneficiario("Incluir Beneficiário");
		emissaoPage.capturaIdentificadorObjetoSegurado();

	}


}
