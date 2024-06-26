package br.com.automation.Pages;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import br.com.automation.Utils.GerarCpfCnpj;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import br.com.automation.Utils.Utils;
import br.com.automation.Utils.UtilsDB;
import cucumber.api.Scenario;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public class EmissaoPage {

	public static String operacao = null;
	public static Object[] arrElement;
	public static Object[] arrElement2;
	public static Map<String, Object> payLoadGeral = new HashMap<String, Object>();
	public static Map<String, Object> payLoadGeral2 = new HashMap<String, Object>();
	public static Map<String, Object> payLoadGeral4 = new HashMap<String, Object>();
	public static Map<String, List<Map<String, Object>>> payLoadGeral5 = new HashMap<String, List<Map<String, Object>>>();
	public static Map<String, List<Map<String, Object>>> payLoadGeral3 = new HashMap<String, List<Map<String, Object>>>();
	static String cenarioFixo;
	public static Response restAssuredResponse;
	public static String guardaRequest;
	public String jsonFinalTest;
	public static String json;
	public static Map<String, Object> valorBody;
	public static Map<String, List<Map<String, Object>>> valorBody2;
	io.restassured.specification.RequestSpecification requestspecs = given();
	public static int responseCode = -1;
	public static String pegaResponse = null;
	public static String url = null;
	public static String nomeAPI = "";
	public static String url_VALOR = "";
	public static String identificadorObjetoSegurado = "";
	public static String idBeneficiario = "";
	public static String tpBeneficiario = "";
	public static String scenarioNameReport = "";
	public static String idGrupoRamo = "";
	public static String idChassi = "";
	public static String nrChassi = "";
	public static String cenarioGlobal = "";
	public static String jsonBodyPost = "";
	public static String idCoberturaIncendio = "";
	public static String idCoberturaVendaval = "";
	public static String tokenCapturado = "";
	public static String localizadorCotacaoCapturado = "";
	public static String identificadorOferta = "";
	public static String identificadorChassi = "";
	public static String identificadorPeriodicidade = "";	
	public static String identificadorPlano= "";
	public static String identificadorPlanoPagamento = "";
	public static String identificadorPacoteAssistencia = "";
	public static String identificadorParcela = "";
	public static String nmIdSessao = "";
	public static String nmIdSessaoHML = "eyKicGciNhKIUzH0OiJ9/dxJqdFjhOiIyLEEwMU@xMTU1OBIsIlmidCI7LUU4OUPyNzM{LhwiaYO{Ijoh`IR0cIL7Ly9uc3JpbFTtZGV3MVNsZB4hcmF{`VxzZVbtY29uMlJyIhvhY29ocll0b{qocm90bIMiOmrhTkFQY1NPUmKGTlRKT0RBIm1rInV{[YJuYV0mIjohUjFPX1OQUlJGUmRJU0SCIn0/^p5vWCgg55kQQgpYUjB6bReTo[80UspxSmRUj[vC1`v";
	public static String nmIdTransacao = "";
	public static String numeroProposta = "";
	public static String listinha5;
	public static String dadoCapturado;
	public static String dadoTratado5;
	private static WebDriver navegador;
	public static String valorCriticaMomento;
	public static String dadoFront;

	private CotacaoPage cotacaoPage = new CotacaoPage();
	private UtilsDB utilsdb = new UtilsDB();
	Faker faker = new Faker();

	public void carregaAPI(String url) {
		nomeAPI = url;
//		RestAssured.baseURI = "";

		if (url.contains("HML")) {
			url_VALOR = "https://apigateway.hml-cld.brasilseg.com.br/";
		} else {
			url_VALOR = "https://apigateway.dev-cld.brasilseg.com.br/";
		}

		String urlValidada = url.replace(" HML", "");

		switch (urlValidada) {
		case "Gerar Token":
			RestAssured.baseURI = url_VALOR + "login";
			requestspecs.contentType("application/json");
			break;

		case "Consulta por Proponente":
			RestAssured.baseURI = url_VALOR + "api/emissao/consulta/proponente";
			requestspecs.param("nmCpfCnpj", "38995063831");
			break;

		case "Consulta por Contrato":
			RestAssured.baseURI = url_VALOR + "api/emissao/consulta/contrato";
			requestspecs.param("nrProposta", "2019101000000003001");
			requestspecs.param("nrVersaoContrato", "1");
			requestspecs.param("nrContrato", "2019101000000003");
			break;

		case "Consulta por Proposta":
			RestAssured.baseURI = url_VALOR + "api/emissao/consulta/proposta";
			requestspecs.param("nrProposta", "2019101000000003001");
			break;

		case "Consulta por Apólice":
			RestAssured.baseURI = url_VALOR + "api/emissao/consulta/apolice";
			requestspecs.param("nrApolice", "20191222000000007");
			break;

		// Vigência
		case "Retornar a Vigência":
			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao/vigencia/" + cotacaoPage.nrLocalizer + "/10000";
			requestspecs.header("Authorization", cotacaoPage.nrToken);
			break;

		case "Retornar a Vigência Novamente":
			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao/vigencia/" + cotacaoPage.nrLocalizer + "/10000";
			break;

		case "Registrar a Vigência":
			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao/vigencia";
			requestspecs.contentType("application/json");
			break;

		// Periodicidade
		case "Retornar a Periodicidade":
			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao/periodicidade/" + cotacaoPage.nrLocalizer + "/10000";
			requestspecs.header("Authorization", cotacaoPage.nrToken);
			break;

		case "Retornar a Periodicidade Novamente":
			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao/periodicidade/" + cotacaoPage.nrLocalizer + "/10000";
			break;

		case "Registrar a Periodicidade":
			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao/periodicidade";
			requestspecs.contentType("application/json");
			break;

		// Histórico do Cliente
		case "Retornar a Consulta de Historico":
			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao/historico/38995063831";
			requestspecs.header("Authorization", cotacaoPage.nrToken);
			break;

		// Beneficiário
		case "Incluir Beneficiário":
			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao/beneficiario";
			break;

		// Proponente
		case "Incluir Proponente":
			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao/proponente";
			break;

		// Consultar Cotação
		case "Consultar Cotação":
			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao";
//			if (!(CotacaoPage.Cenario.equals("Beneficiário"))) {
			requestspecs.param("nrLocalizador", cotacaoPage.nrLocalizer);
//			}
			break;

		// Copiar Endereço
		case "Copiar Endereço":
			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao/objetosegurado/endereco";
			requestspecs.header("Authorization", cotacaoPage.nrToken);
			requestspecs.contentType("application/json");
			break;

		// Filtrar Endereço
		case "Filtrar Endereço":
			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao/objetosegurado/endereco";
			requestspecs.param("nrLocalizador", cotacaoPage.nrLocalizer);
			requestspecs.header("Authorization", cotacaoPage.nrToken);
			requestspecs.contentType("application/json");
			break;

		// Emitir Cotação
		case "Emitir Cotação":
			RestAssured.baseURI = url_VALOR + "api/emissao/contrato/gerar_contrato";
			requestspecs.param("nrApolice", "");
			break;

		// Grupo Ramo
		case "Grupo Ramo":
			RestAssured.baseURI = url_VALOR + "api/produto/grupoRamo";
			requestspecs.contentType("application/json");
			break;

		// Chassi
		case "Chassi":
			RestAssured.baseURI = url_VALOR + "api/produto/chassi";
			requestspecs.contentType("application/json");
			break;

		// Cobertura Incêndio
		case "Cobertura Incêndio":
			RestAssured.baseURI = url_VALOR + "api/produto/cobertura";
			requestspecs.contentType("application/json");
			break;

		case "Cobertural Vendeval":
			RestAssured.baseURI = url_VALOR + "api/produto/cobertura";
			requestspecs.contentType("application/json");
			break;

		case "Cadastro Chassi Incêndio":
			RestAssured.baseURI = url_VALOR + "api/produto/chassiCobertura";
			requestspecs.contentType("application/json");
			break;

		case "Cadastro Chassi Vendaval":
			RestAssured.baseURI = url_VALOR + "api/produto/chassiCobertura";
			requestspecs.contentType("application/json");
			break;

		// Fluxo Cotação
		case "Iniciar Cotação":
			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao";
			requestspecs.header("nmIdSessao", nmIdSessao);
			requestspecs.header("nmIdTransacao", nmIdTransacao);
			break;

		case "Iniciar Cotação com Proponente":
			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao";
			if (url.contains("HML")) {
				requestspecs.header("nmIdSessao", nmIdSessaoHML);
			} else {
				requestspecs.header("nmIdSessao", nmIdSessao);
			}
			break;

		case "Consulta GrupoRamo":
			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao/grupo-ramo";
//			requestspecs.header("Authorization", tokenCapturado);
			break;

		case "Consulta Oferta":
			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao/oferta";
//			requestspecs.header("Authorization", tokenCapturado);
			break;

		case "Selecionar Oferta":
			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao/oferta";
			break;

		case "Consulta Plano":
			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao/plano/" + localizadorCotacaoCapturado + "/20000/1";
//			requestspecs.header("Authorization", tokenCapturado);
			break;

		case "Grava Plano":
			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao/plano";
//			requestspecs.header("Authorization", tokenCapturado);
			break;

		case "Grava Vigência":
			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao/vigencia";
//			requestspecs.header("Authorization", tokenCapturado);
			break;

		case "Consulta Pessoa":
			String cpfFinal = geraCPF();

			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao/pessoa/" + localizadorCotacaoCapturado + "/"
					+ cpfFinal;
//			requestspecs.header("Authorization", tokenCapturado);
			break;

		case "Grava Pessoa":
			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao/pessoa";
//			requestspecs.header("Authorization", tokenCapturado);
			break;

		case "Consulta CEP":
			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao/cep/04521000";
//			requestspecs.header("Authorization", tokenCapturado);
			break;

		case "Grava Endereço":
			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao/endereco";
//			requestspecs.header("Authorization", tokenCapturado);
			break;

		case "Grava Email":
			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao/email";
//			requestspecs.header("Authorization", tokenCapturado);
			break;

		case "Grava Telefone":
			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao/telefone";
//			requestspecs.header("Authorization", tokenCapturado);
			break;

		case "Incluir Objeto Segurado":
			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao/objetosegurado";
			break;

		case "Grava Periodicidade de Contratação":
			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao/periodicidade";
			break;

		case "Grava Contrato":
			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao/contrato";
			break;

		case "Selecionar Plano":
			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao/plano";
			break;

		case "Registrar De acordo":
			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao/deacordo";
			break;
			
		case "Selecionar Plano de Pagamento":
			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao/planopagamento";
			break;

		case "Selecionar Assistência":
			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao/assistencia";
			break;

		case "Gerar PDF":
			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao/pdf/" + localizadorCotacaoCapturado;
//			requestspecs.header("Authorization", tokenCapturado);
			requestspecs.contentType("application/pdf");
			break;
			
		case "Selecionar Parcelas":
			RestAssured.baseURI = url_VALOR + "api/emissao/cotacao/planopagamento/parcelamento";
			break;		

		// Configurador de regras

		case "Momento":
			RestAssured.baseURI = url_VALOR + "api/emissao/regra/momento";
			requestspecs.header("nmIdSessao", nmIdSessao);
			break;

		case "Tipo de Seguro":
			RestAssured.baseURI = url_VALOR + "api/emissao/regra/tipoSeguro";
			requestspecs.header("nmIdSessao", nmIdSessao);
			break;

		case "Referência":
			RestAssured.baseURI = url_VALOR + "api/emissao/regra/referencia";
			requestspecs.header("nmIdSessao", nmIdSessao);
			break;

		case "Operação":
			RestAssured.baseURI = url_VALOR + "api/emissao/regra/operacao";
			requestspecs.header("nmIdSessao", nmIdSessao);
			break;

		case "Ação":
			RestAssured.baseURI = url_VALOR + "api/emissao/regra/acao";
			requestspecs.header("nmIdSessao", nmIdSessao);
			break;

		case "Motivo de recusa":
			RestAssured.baseURI = url_VALOR + "api/emissao/regra/motivoRecusa";
			requestspecs.header("nmIdSessao", nmIdSessao);
			break;

		case "Classe da regra":
			RestAssured.baseURI = url_VALOR + "api/emissao/regra/classeRegra";
			requestspecs.header("nmIdSessao", nmIdSessao);
			break;
		
		case "Postman":
			RestAssured.baseURI = "https://postman-echo.com/get";
			//requestspecs.header("nmIdSessao", nmIdSessao);
			break;
			
		case "Postman Post":
			RestAssured.baseURI = "https://postman-echo.com/post";
			//requestspecs.header("nmIdSessao", nmIdSessao);
			break;

	}


	}

	public String geraCPF() {
		GerarCpfCnpj gerador = new GerarCpfCnpj();
		String cpf = gerador.cpf(true);
		System.out.println(cpf);
		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");
		return cpf;
	}

	public void enviaPOST(String cenario) {
		try {

			valorBody = montaBody();
//			valorBody = "" ;
			System.out.println(valorBody.toString());

			Gson g = new Gson();
			json = g.toJson(valorBody);
			System.out.println("LOG: O request será: " + json);
			guardaRequest = json;
			restAssuredResponse = requestspecs.body(json).post(RestAssured.baseURI);
			System.out.println("LOG: Response retornado é: " + restAssuredResponse.asString());
			jsonFinalTest = restAssuredResponse.asString();

			String metodo = "POST";

			responseCode = restAssuredResponse.getStatusCode();

			Utils.printResponse(metodo, json, cenario, restAssuredResponse.prettyPrint(), responseCode,
					restAssuredResponse.getTime());

		} catch (Exception e) {
			if (e.getMessage().contains("PROBLEMA NA SIMULAÇÃO!")) {
				e.printStackTrace();
				Assert.fail("Provavelmente o serviço está fora do ar.");
			} else {
				e.printStackTrace();
				Assert.fail("Aconteceu um erro inesperado ao invocar este serviço. \n Response:"
						+ restAssuredResponse.asString() + "\n" + " Response: " + restAssuredResponse.prettyPrint());
			}
		}
	}

	public void enviaPOSTArquivoJson(String cenario) {
		try {

			String cenarioValidado = cenario.replace(" HML", "");

			File starting = new File(System.getProperty("user.dir"));

			switch (cenarioValidado) {
			// Gerar Token
			case "Gerar Token":
				jsonBodyPost = generateStringFromResource(
						starting + "\\src\\test\\java\\br\\com\\automation\\Resources\\GerarToken.json");

//			restAssuredResponse = requestspecs.body(jsonBodyPost).post(RestAssured.baseURI);
				break;

			// Grupo Ramo
			case "Cadastro dos Grupos Ramo":
				jsonBodyPost = generateStringFromResource(
						starting + "\\src\\test\\java\\br\\com\\automation\\Resources\\GrupoRamo.json");
				break;

			// Chassi
			case "Chassi":
				jsonBodyPost = generateStringFromResource(
						starting + "\\src\\test\\java\\br\\com\\automation\\Resources\\Chassi.json");
				break;

			// Cobertura Incêndio
			case "Cobertura Incêndio":
				jsonBodyPost = generateStringFromResource(
						starting + "\\src\\test\\java\\br\\com\\automation\\Resources\\CoberturaIncendio.json");
				break;

			case "Cobertural Vendeval":
				jsonBodyPost = generateStringFromResource(
						starting + "\\src\\test\\java\\br\\com\\automation\\Resources\\CoberturaVendaval.json");
				break;

			case "Cadastro Chassi Incêndio":
				jsonBodyPost = generateStringFromResource(
						starting + "\\src\\test\\java\\br\\com\\automation\\Resources\\ChassiCoberturaIncendio.json");
				break;

			case "Cadastro Chassi Vendaval":
				jsonBodyPost = generateStringFromResource(
						starting + "\\src\\test\\java\\br\\com\\automation\\Resources\\ChassiCoberturaVendaval.json");
				break;

			case "Grava Plano":
				String jsonArquivoAlterado = generateStringFromResource(
						starting + "\\src\\test\\java\\br\\com\\automation\\Resources\\GravaPlano.json");

				jsonBodyPost = jsonArquivoAlterado
						.replace("{\r\n" + "	\"nrLocalizador\": \"7772cde6-e144-485e-9f38-e917a7c874ef\",",
								"{\r\n" + "	\"nrLocalizador\": \"" + localizadorCotacaoCapturado + "\",")
						.trim();

				break;

			case "Grava Vigência":
				String jsonArquivoAlteradoVigencia = generateStringFromResource(
						starting + "\\src\\test\\java\\br\\com\\automation\\Resources\\GravaVigencia.json");
				jsonBodyPost = jsonArquivoAlteradoVigencia
						.replace("{\r\n" + "	\"localizadorCotacao\": \"7772cde6-e144-485e-9f38-e917a7c874ef\",",
								"{\r\n" + "	\"localizadorCotacao\": \"" + localizadorCotacaoCapturado + "\",")
						.trim();

				break;

			case "Grava Pessoa":
				String jsonArquivoAlteradoPessoa = generateStringFromResource(
						starting + "\\src\\test\\java\\br\\com\\automation\\Resources\\GravaPessoa.json");
				jsonBodyPost = jsonArquivoAlteradoPessoa
						.replace("{\r\n" + "	\"nrLocalizador\": \"5db92dd2-0557-496b-b2d5-624523b0c888\",",
								"{\r\n" + "	\"nrLocalizador\": \"" + localizadorCotacaoCapturado + "\",")
						.trim();

				break;

			case "Grava Endereço":
				String jsonArquivoAlteradoEndereco = generateStringFromResource(
						starting + "\\src\\test\\java\\br\\com\\automation\\Resources\\GravaEndereço.json");
				jsonBodyPost = jsonArquivoAlteradoEndereco
						.replace("{\r\n" + "	\"nrLocalizador\": \"5db92dd2-0557-496b-b2d5-624523b0c888\",",
								"{\r\n" + "	\"nrLocalizador\": \"" + localizadorCotacaoCapturado + "\",")
						.trim();

				break;

			case "Grava Email":
				String jsonArquivoAlteradoEmail = generateStringFromResource(
						starting + "\\src\\test\\java\\br\\com\\automation\\Resources\\GravaEmail.json");
				jsonBodyPost = jsonArquivoAlteradoEmail
						.replace("{\r\n" + "	\"nrLocalizador\": \"5db92dd2-0557-496b-b2d5-624523b0c888\",",
								"{\r\n" + "	\"nrLocalizador\": \"" + localizadorCotacaoCapturado + "\",")
						.trim();

				break;

			case "Grava Telefone":
				String jsonArquivoAlteradoTelefone = generateStringFromResource(
						starting + "\\src\\test\\java\\br\\com\\automation\\Resources\\GravaTelefone.json");
				jsonBodyPost = jsonArquivoAlteradoTelefone
						.replace("{\r\n" + "	\"nrLocalizador\": \"5db92dd2-0557-496b-b2d5-624523b0c888\",",
								"{\r\n" + "	\"nrLocalizador\": \"" + localizadorCotacaoCapturado + "\",")
						.trim();
				break;

			case "Grava Objeto Segurado":
				String jsonArquivoAlteradoObjetoSegurado = generateStringFromResource(
						starting + "\\src\\test\\java\\br\\com\\automation\\Resources\\GravaObjetoSegurado.json");
				jsonBodyPost = jsonArquivoAlteradoObjetoSegurado.replace("{\r\n" + "	\"nmOperacao\": \"i\",\r\n"
						+ "	\"nrLocalizador\": \"5db92dd2-0557-496b-b2d5-624523b0c888\","

						, "{\r\n" + "	\"nmOperacao\": \"i\",\r\n" + "	\"nrLocalizador\": \""
								+ localizadorCotacaoCapturado + "\",")
						.trim();
				break;

			case "Grava Contrato":
					String jsonBodyPostGravaContrato = generateStringFromResource(
							starting + "\\src\\test\\java\\br\\com\\automation\\Resources\\GravaContrato.json");
					jsonBodyPost = jsonBodyPostGravaContrato
							.replace("{\r\n" + 
									"    \"localizadorCotacao\": \"${localizadorCotacao}\"",
									"{\r\n" + "	\"localizadorCotacao\": \"" + localizadorCotacaoCapturado + "\"")
							.trim();
				
				break;

			case "Iniciar Cotação com Proponente":			
				String cpfAleatorio = geraCPF();
				String nomeAleatorio = faker.name();
				nomeAleatorio.replace("Mrs. ", "");
				
				String jsonBodyPostIniciarCotacao = generateStringFromResource(
						starting + "\\src\\test\\java\\br\\com\\automation\\Resources\\IniciarCotacaoComProponente.json");
				jsonBodyPost = jsonBodyPostIniciarCotacao
						.replace("  \"nmCpfCnpj\": \"35852188069\",\r\n" + 
								"    \"nmNome\": \"Damien Lillard\",",
								"  \"nmCpfCnpj\": \"" +  cpfAleatorio +  "\",\r\n" + 
								"    \"nmNome\": \"" + nomeAleatorio +   "\",")
						.trim();			
				break;

			case "Selecionar Oferta":
					String jsonBodyPostSelecionarOferta = generateStringFromResource(
							starting + "\\src\\test\\java\\br\\com\\automation\\Resources\\SelecionarOferta.json");
					String jsonBodyPostAnterior = jsonBodyPostSelecionarOferta
							.replace("{\r\n" + "  \"localizadorCotacao\": \"5ef1ab41-c9bd-4b65-8adb-5d0c68298c79\"",
									"{\r\n" + "	\"localizadorCotacao\": \"" + localizadorCotacaoCapturado + "\"")
							.trim();

					jsonBodyPost = jsonBodyPostAnterior.replace("  \"nmIdentificadorOferta\": \"\"",
							"  \"nmIdentificadorOferta\": \"" + identificadorOferta + "\"").trim();
				break;

			case "Incluir Objeto Segurado":
					String jsonBodyPostGravaObjetoSegurado = generateStringFromResource(starting
							+ "\\src\\test\\java\\br\\com\\automation\\Resources\\IncluiObjetoSegurado.json");
					jsonBodyPost = jsonBodyPostGravaObjetoSegurado
							.replace(
									"{\r\n" + "    \"nmIdentificadorChassi\": \"{{nmIdentificadorChassi}}\",\r\n"
											+ "    \"localizadorCotacao\": \"{{localizador}}\",",
									"{\r\n" + "	\"nmIdentificadorChassi\": \"" + identificadorChassi + "\",\r\n"
											+ "	\"localizadorCotacao\": \"" + localizadorCotacaoCapturado + "\",")
							.trim();

				break;

			case "Incluir Beneficiário":
					String jsonBodyPostIncluirBeneficiario = generateStringFromResource(
							starting + "\\src\\test\\java\\br\\com\\automation\\Resources\\IncluirBeneficiario.json");
					jsonBodyPost = jsonBodyPostIncluirBeneficiario.replace(
							"{\r\n" + "	\"localizadorCotacao\": \"{{localizador}}\",\r\n"
									+ "	\"nmIdentificadorObjetoSegurado\": \"{{nmIdentificadorObjetoSegurado}}\",",
							"{\r\n" + "	\"localizadorCotacao\": \"" + localizadorCotacaoCapturado + "\",\r\n"
									+ "	\"nmIdentificadorObjetoSegurado\": \"" + identificadorObjetoSegurado + "\",")
							.trim();
				break;
				
			case "Incluir Proponente":			
					String jsonBodyPostIncluiProponente = generateStringFromResource(
							starting + "\\src\\test\\java\\br\\com\\automation\\Resources\\IncluirProponente.json");
					jsonBodyPost = jsonBodyPostIncluiProponente
							.replace("{\r\n" + 
									"	\"localizadorCotacao\": \"{{localizador}}\",",
									"{\r\n" + "	\"localizadorCotacao\": \"" + localizadorCotacaoCapturado + "\",")
							.trim();
				break;

			case "Grava Periodicidade de Contratação":
					String jsonBodyPostGravaPeriodicidade = generateStringFromResource(
							starting + "\\src\\test\\java\\br\\com\\automation\\Resources\\InformaPeriodicidade.json");
					jsonBodyPost = jsonBodyPostGravaPeriodicidade
							.replace("{\r\n" + 
									"  \"localizadorCotacao\": \"${localizadorCotacao}\",\r\n" + 
									"  \"nmIdentificadorChassi\": \"${identificadorChassi}\",\r\n" + 
									"  \"qtPeriodicidade\": 1,\r\n" + 
									"  \"nmIdentificadorPeriodicidade\": \"${identificadorPeriodicidade}\"\r\n" + 
									"}",
									"{\r\n" + "	\"localizadorCotacao\": \"" + localizadorCotacaoCapturado + "\","
											+ "\"nmIdentificadorChassi\": \"" + identificadorChassi + "\","
											+ "\"qtPeriodicidade\": \"" + 1 + "\","
											+ "\"nmIdentificadorPeriodicidade\": \"" + identificadorPeriodicidade + "\"}")
							.trim();
				break;

			case "Selecionar Plano":
					String jsonBodyPostGravaPlano = generateStringFromResource(
							starting + "\\src\\test\\java\\br\\com\\automation\\Resources\\SelecionaPlano.json");
					jsonBodyPost = jsonBodyPostGravaPlano
							.replace("{\r\n" + 
									"  \"localizadorCotacao\": \"{{localizador}}\",\r\n" + 
									"  \"nmIdentificadorPlano\": \"{{nmIdentificadorPlano}}\"\r\n" + 
									"}",
									"{\r\n" + 
									"  \"localizadorCotacao\": \"" + localizadorCotacaoCapturado + "\",\r\n" + 
									"  \"nmIdentificadorPlano\": \"" + identificadorPlano + "\"\r\n" + 
									"}")
							.trim();
				break;

			case "Registrar De acordo":
				String jsonBodyPostRegistraDeAcordo = generateStringFromResource(
						starting + "\\src\\test\\java\\br\\com\\automation\\Resources\\RegistraDeAcordo.json");
				jsonBodyPost = jsonBodyPostRegistraDeAcordo
						.replace("{\r\n" + "  \"localizadorCotacao\": \"c90b49cf-ee1a-4214-9c45-8ac2dedee5fb\"",
								"{\r\n" + "	\"localizadorCotacao\": \"" + localizadorCotacaoCapturado + "\"")
						.trim();
				break;
								
			case "Selecionar Plano de Pagamento":
					String jsonBodyPostSelecionaPlanoPagamento = generateStringFromResource(
							starting + "\\src\\test\\java\\br\\com\\automation\\Resources\\SelecionaPlanoPagamento.json");
					jsonBodyPost = jsonBodyPostSelecionaPlanoPagamento
							.replace("{\r\n" + 
									"	\"localizadorCotacao\": \"{{localizador}}\",\r\n" + 
									"	\"nmIdentificadorPlanoPagamento\": \"{{nmIdentificadorPlanoPagamento}}\"\r\n" + 
									"}",
									"{\r\n" + 
									"  \"localizadorCotacao\": \"" + localizadorCotacaoCapturado + "\",\r\n" + 
									"  \"nmIdentificadorPlanoPagamento\": \"" + identificadorPlanoPagamento + "\"\r\n" + 
									"}")
							.trim();
			break;

			case "Selecionar Assistência":
					String jsonBodyPostSelecionaAssistencia = generateStringFromResource(
							starting + "\\src\\test\\java\\br\\com\\automation\\Resources\\SelecionaAssistencia.json");
					jsonBodyPost = jsonBodyPostSelecionaAssistencia
							.replace("{\r\n" + 
									"  \"localizadorCotacao\": \"${localizadorCotacao}\",\r\n" + 
									"  \"nmIdentificadorPacoteAssistencia\": \"${identificadorPacoteAssistencia}\"\r\n" + 
									"}",
									"{\r\n" + 
									"  \"localizadorCotacao\": \"" + localizadorCotacaoCapturado + "\",\r\n" + 
									"  \"nmIdentificadorPacoteAssistencia\": \"" + identificadorPacoteAssistencia + "\"\r\n" + 
									"}")
							.trim();
				break;
				
			case "Selecionar Parcelas":
					String jsonBodyPostSelecionaParcelas = generateStringFromResource(
							starting + "\\src\\test\\java\\br\\com\\automation\\Resources\\SelecionaParcelas.json");
					jsonBodyPost = jsonBodyPostSelecionaParcelas
							.replace("{\r\n" + 
									"  \"localizadorCotacao\": \"${localizadorCotacao}\",\r\n" + 
									"  \"nmIdentificadorParcela\": \"${identificadorParcelas}\"\r\n" + 
									"}",
									"{\r\n" + 
									"  \"localizadorCotacao\": \"" + localizadorCotacaoCapturado + "\",\r\n" + 
									"  \"nmIdentificadorParcela\": \"" + identificadorParcela+ "\"\r\n" + 
									"}")
							.trim();	
			break;
			
			case "Postman Post":
				String jsonBodyPostPostman = generateStringFromResource(
						starting + "\\src\\test\\java\\br\\com\\automation\\Resources\\PostmanPost.json");
				jsonBodyPost = jsonBodyPostPostman
						.trim();	
		break;
			}

			restAssuredResponse = requestspecs.body(jsonBodyPost).post(RestAssured.baseURI);
			System.out.println("LOG: Response retornado é: " + restAssuredResponse.asString());
			jsonFinalTest = restAssuredResponse.asString();

			if (nomeAPI.contains("Iniciar Cotação com Proponente")) {
				capturaLocalizadorCotacao();
				capturaIdentificadorOferta();
			}

			if (nomeAPI.contains("Selecionar Oferta")) {
				capturaIdentificadorChassi();
				capturaIdentificadorPeriodicidade();
				capturaIdentificadorPlano();
				capturaIdentificadorPlanoPagamento();
			}

			if (nomeAPI.contains("Incluir Objeto Segurado")) {
				capturaIdentificadorObjetoSegurado();
				capturaIdentificadorPacoteAssistencia();
			}
			
			if (nomeAPI.contains("Selecionar Assistência")) {
				capturaIdentificadorParcela();
			}
			
			String metodo = "POST";

			cenarioGlobal = cenario;

			responseCode = restAssuredResponse.getStatusCode();

			Utils.printResponse(metodo, jsonBodyPost, cenario, restAssuredResponse.prettyPrint(), responseCode,
					restAssuredResponse.getTime());

		} catch (Exception e) {
			if (e.getMessage().contains("PROBLEMA NA SIMULAÇÃO!")) {
				e.printStackTrace();
				Assert.fail("Provavelmente o serviço está fora do ar.");
			} else {
				e.printStackTrace();
				Assert.fail("Aconteceu um erro inesperado ao invocar este serviço. \n Response:"
						+ restAssuredResponse.asString() + "\n" + " Response: " + restAssuredResponse.prettyPrint());
			}
		}
	}

	public void enviaPOSTCopiarEndereco(String cenario) {
		try {

			valorBody2 = montaBody2();
//			valorBody = "" ;
			System.out.println(valorBody2.toString());

			Gson g = new Gson();
			json = g.toJson(valorBody2);
			System.out.println("LOG: O request será: " + json);

			String alteradoCopiarEndereço = json.replace("[{", "{").trim();
			String alteradoCopiarEndereço2 = alteradoCopiarEndereço.replace("}]}", "}}").trim();
			String jsonFinalCopiarEndereço = alteradoCopiarEndereço2
					.replace("{\"endereco\":", "{\r\n" + "  \"nrLocalizador\": \"" + cotacaoPage.nrLocalizer + "\",\r\n"
							+ "  \"nrChassi\": 10000,\r\n" + "  \"endereco\":")
					.trim();

			System.out.println("LOG: O request final será: " + jsonFinalCopiarEndereço);

			guardaRequest = jsonFinalCopiarEndereço;
			restAssuredResponse = requestspecs.body(jsonFinalCopiarEndereço).post(RestAssured.baseURI);
			System.out.println("LOG: Response retornado é: " + restAssuredResponse.asString());
			jsonFinalTest = restAssuredResponse.asString();

			String metodo = "POST";

			responseCode = restAssuredResponse.getStatusCode();

			Utils.printResponse(metodo, json, cenario, restAssuredResponse.prettyPrint(), responseCode,
					restAssuredResponse.getTime());

		} catch (Exception e) {
			if (e.getMessage().contains("PROBLEMA NA SIMULAÇÃO!")) {
				e.printStackTrace();
				Assert.fail("Provavelmente o serviço está fora do ar.");
			} else {
				e.printStackTrace();
				Assert.fail("Aconteceu um erro inesperado ao invocar este serviço. \n Response:"
						+ restAssuredResponse.asString() + "\n" + " Response: " + restAssuredResponse.prettyPrint());
			}
		}
	}

//	public void enviaPOSTBeneficiario(String tipoBeneficiario, String cenario) {
//		try {
//
//			tpBeneficiario = tipoBeneficiario;
//			valorBody2 = montaBody2();
//			System.out.println(valorBody2.toString());
//
//			Gson g = new Gson();
//			json = g.toJson(valorBody2);
//			System.out.println("LOG: O request será: " + json);
//			String operacao = "";
//			String identificadorBeneficiario = "";
//
//			switch (tipoBeneficiario) {
//			case "Incluir":
//				operacao = "I";
//				identificadorBeneficiario = "";
//				break;
//			case "Alterar":
//				operacao = "A";
//				identificadorBeneficiario = idBeneficiario;
//				break;
//
//			case "Excluir":
//				operacao = "E";
//				identificadorBeneficiario = idBeneficiario;
//				break;
//			}
//
//			String alterado = json.replace("[{", "{").trim();
//			String alterado2 = alterado.replace("}]}", "}}").trim();
//			String alterado3 = alterado2.replace("{\"beneficiario\":",
//					" \"nrLocalizador\": \"" + cotacaoPage.nrLocalizer + "\",\r\n" + " \"nmOperacao\": \"" + operacao
//							+ "\",\r\n" + " \"nrChassi\": 10000,\r\n" + " \"nmIdentificadorObjetoSegurado\": \""
//							+ identificadorObjetoSegurado + "\",\r\n" + " \"nmIdentificador\": \""
//							+ identificadorBeneficiario + "\",\r\n" + " \"beneficiario\" :")
//					.trim();
//			String jsonFinal = alterado3.replace("\"nrLocalizador\":", "{\"nrLocalizador\":").trim();
//
//			System.out.println("LOG: O request final será: " + jsonFinal);
//
//			// Body vindo do arquivo
////			File starting = new File(System.getProperty("user.dir"));
////			String jsonBody = generateStringFromResource(starting + "\\src\\test\\java\\br\\com\\automation\\Resources\\IncluirBeneficiario.json");
//
//			guardaRequest = jsonFinal;
//			restAssuredResponse = requestspecs.body(jsonFinal).post(RestAssured.baseURI);
//			System.out.println("LOG: Response retornado é: " + restAssuredResponse.asString());
//			jsonFinalTest = restAssuredResponse.asString();
//
//			String metodo = "POST";
//
//			responseCode = restAssuredResponse.getStatusCode();
//
//			Utils.printResponse(metodo, jsonFinal, cenario, restAssuredResponse.prettyPrint(), responseCode,
//					restAssuredResponse.getTime());
//
//		} catch (Exception e) {
//			if (e.getMessage().contains("PROBLEMA NA SIMULAÇÃO!")) {
//				e.printStackTrace();
//				Assert.fail("Provavelmente o serviço está fora do ar.");
//			} else {
//				e.printStackTrace();
//				Assert.fail("Aconteceu um erro inesperado ao invocar este serviço. \n Response:"
//						+ restAssuredResponse.asString() + "\n" + " Response: " + restAssuredResponse.prettyPrint());
//			}
//		}
//	}

//	public Response enviaGETBeneficiario(String cenario) {
//		try {
//			requestspecs.header("Authorization", cotacaoPage.nrToken);
//			requestspecs.param("nrLocalizador", cotacaoPage.nrLocalizer);
//			System.out.println("A URL utilizada será: " + RestAssured.baseURI);
//			restAssuredResponse = requestspecs.get(RestAssured.baseURI);
//			pegaResponse = restAssuredResponse.toString();
//			System.out.println("Response retornado é: " + restAssuredResponse.asString());
//			// zerar header
//
////			String jsonFinal = "";
////			
////			String metodo = "GET";
//
//			responseCode = restAssuredResponse.getStatusCode();
////			
////			Utils.printResponse(metodo, jsonFinal, cenario, restAssuredResponse.prettyPrint(), responseCode,
////					restAssuredResponse.getTime());
//
//			return restAssuredResponse;
//		} catch (Exception e) {
//			if (e.getMessage().contains("Não foi possivel realizar a conexão")) {
//				Assert.fail("Provavelmente o serviço está fora do ar.");
//				return null;
//			} else {
//				Assert.fail("Aconteceu um erro inesperado ao invocar este serviço. Response:"
//						+ restAssuredResponse.asString());
//				e.printStackTrace();
//			}
//		}
//		return null;
//	}

	public Response enviaGET(String cenario) {
		try {
//			if(url.contains("Beneficiário")) {
//				requestspecs.header("Authorization", cotacaoPage.nrToken);
//				requestspecs.param("nrLocalizador", cotacaoPage.nrLocalizer);
//			}
//			

			System.out.println("A URL utilizada será: " + RestAssured.baseURI);
			restAssuredResponse = requestspecs.get(RestAssured.baseURI);
			pegaResponse = restAssuredResponse.toString();
			System.out.println("Response retornado é: " + restAssuredResponse.asString());

			String jsonFinal = "";

			String metodo = "GET";

			responseCode = restAssuredResponse.getStatusCode();

			Utils.printResponse(metodo, jsonFinal, cenario, restAssuredResponse.prettyPrint(), responseCode,
					restAssuredResponse.getTime());

			if (nomeAPI.equals("Iniciar Cotação")) {
				capturaLocalizadorCotacao();
			}

			return restAssuredResponse;
		} catch (Exception e) {
			if (e.getMessage().contains("Não foi possivel realizar a conexão")) {
				Assert.fail("Provavelmente o serviço está fora do ar.");
				return null;
			} else {
				Assert.fail("Aconteceu um erro inesperado ao invocar este serviço. Response:"
						+ restAssuredResponse.asString());
				e.printStackTrace();
			}
		}
		return null;
	}

	public boolean validaRetornoVigencia() {
		try {
			// valida Response
			restAssuredResponse.then().assertThat().body("message", equalTo("Vigencia localizada com sucesso"));
			restAssuredResponse.then().assertThat().body("resource", equalTo("Vigência"));
			restAssuredResponse.then().assertThat().body("status", equalTo(200));
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean validaRetornoConfigurador() {
		try {

			// captura momentos

			switch (nomeAPI) {

			case "Momento":
				dadoCapturado = restAssuredResponse.jsonPath().getString("dados.nmMomento");
				break;

			case "Tipo de Seguro":
				dadoCapturado = restAssuredResponse.jsonPath().getString("dados.nmTipoSeguro");
				break;

			case "Referência":
				dadoCapturado = restAssuredResponse.jsonPath().getString("dados.nmReferencia");
				break;

			case "Operação":
				dadoCapturado = restAssuredResponse.jsonPath().getString("dados.nmOperacao");
				break;

			case "Ação":
				dadoCapturado = restAssuredResponse.jsonPath().getString("dados.nmAcao");
				break;

			case "Motivo de recusa":
				dadoCapturado = restAssuredResponse.jsonPath().getString("dados.nmMotivoRecusa");
				break;

			case "Classe da regra":
				dadoCapturado = restAssuredResponse.jsonPath().getString("dados.nmClasseRegra");
				break;
			}
			// valida retorno com informações do bd

			System.out.println(dadoCapturado);
			utilsdb.conectaDB(nomeAPI, "");
			trataDadoCapturado();
			trataDadoBanco();

			if (dadoTratado5.equals(listinha5)) {
				System.out.println(
						"Retorno do JSON validado com os dados do banco de dados de Subscricao corretamente OK.");
				return true;
			}

			return false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean validaRetornoConfiguradorFront() {
		try {

			// captura momentos

			switch (nomeAPI) {

			case "Momento":
				dadoCapturado = restAssuredResponse.jsonPath().getString("dados.nmMomento");
				break;

			case "Tipo de Seguro":
				dadoCapturado = restAssuredResponse.jsonPath().getString("dados.nmTipoSeguro");
				break;

			case "Referência":
				dadoCapturado = restAssuredResponse.jsonPath().getString("dados.nmReferencia");
				break;

			case "Operação":
				dadoCapturado = restAssuredResponse.jsonPath().getString("dados.nmOperacao");
				break;

			case "Ação":
				dadoCapturado = restAssuredResponse.jsonPath().getString("dados.nmAcao");
				break;

			case "Motivo de recusa":
				dadoCapturado = restAssuredResponse.jsonPath().getString("dados.nmMotivoRecusa");
				break;

			case "Classe da regra":
				dadoCapturado = restAssuredResponse.jsonPath().getString("dados.nmClasseRegra");
				break;
			}
			// valida retorno com informações do bd

			System.out.println(dadoCapturado);

			trataDadoCapturado();
			capturaDadosFront();
			trataDadosFront();

			if (dadoTratado5.equals(dadoFront)) {
				System.out.println("Retorno do JSON validado com os dados do Front-End corretamente OK.");
				return true;
			}

			return false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void verificoRetorno() {
		try {

			if (responseCode == 201) {

				switch (nomeAPI) {
				// Grupo Ramo
				case "Grupo Ramo":
					idGrupoRamo = restAssuredResponse.jsonPath().getString("idGrupoRamo");
					System.out.println(idGrupoRamo);
					break;
				// Chassi
				case "Chassi":
					idChassi = restAssuredResponse.jsonPath().getString("dados.id");
					System.out.println(idChassi);
					break;

				// Cobertura Incêndio
				case "Cobertura Incêndio":
					idCoberturaIncendio = restAssuredResponse.jsonPath().getString("dados.id");
					System.out.println(idCoberturaIncendio);
					break;

				case "Cobertural Vendeval":
					idCoberturaVendaval = restAssuredResponse.jsonPath().getString("dados.id");
					System.out.println(idCoberturaVendaval);
					break;

				case "Cadastro Chassi Incêndio":
					System.out.println("O idGrupoRamo é:" + idGrupoRamo);
					System.out.println("O idChassi é:" + idChassi);
					System.out.println("O idCoberturaIncendio é:" + idCoberturaIncendio);
					System.out.println("O idCoberturaVendaval é:" + idCoberturaVendaval);

					break;

				case "Cadastro Chassi Vendaval":

					break;

				}

			} else {

				switch (nomeAPI) {
				// Grupo Ramo
				case "Grupo Ramo":
					capturaIdGrupoRamo();
					break;

				// Chassi
				case "Chassi":
					capturaIdChassi();
					break;

				// Cobertura Incêndio
				case "Cobertura Incêndio":
					capturaIdCoberturaIncendio();
					break;

				case "Cobertural Vendeval":

					break;

				case "Cadastro Chassi Incêndio":

					break;

				case "Cadastro Chassi Vendaval":

					break;

				}

			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void capturaIdGrupoRamo() {
		try {

			enviaGET("Cadastro dos Grupos Ramo");
			idGrupoRamo = restAssuredResponse.jsonPath()
					.getString("dados.content.find { it.nrGrupoRamo == '0114' }.id");

			System.out.println("O idGrupoRamo é :" + idGrupoRamo);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void capturaIdChassi() {
		try {

			enviaGET("Cadastro do Chassi");
			idChassi = restAssuredResponse.jsonPath().getString("dados.content.find { it.nrGrupoRamo == '0114' }.id");
			nrChassi = restAssuredResponse.jsonPath()
					.getString("dados.content.find { it.nrGrupoRamo == '0114' }.nrChassi");

			System.out.println("O idChassi é :" + idChassi);
			System.out.println("O nrChassi é :" + nrChassi);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void capturaIdCoberturaIncendio() {
		try {

			enviaGET("Cobertura - Incêndio, Idt, Raio e Explosão de QQ Natureza");
			idCoberturaIncendio = restAssuredResponse.jsonPath()
					.getString("dados.content.find { it.nrGrupoRamo == '0114' }.id");

			System.out.println("O id da Cobertura de Incêndio é :" + idCoberturaIncendio);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void capturaIdCoberturaVendaval() {
		try {

			enviaGET("Cobertura - Vendaval, Furacão, Ciclone, Tornado, Granizo e Fumaça");
			idCoberturaVendaval = restAssuredResponse.jsonPath()
					.getString("dados.content.find { it.nrGrupoRamo == '0114' }.id");

			System.out.println("O id da Cobertura de Incêndio é :" + idCoberturaVendaval);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public boolean validaRetornoPeriodicidade() {
		try {
			// valida Response
			restAssuredResponse.then().assertThat().body("message", equalTo("Periodicidade localizada com sucesso"));
			restAssuredResponse.then().assertThat().body("resource", equalTo("Periodicidade"));
			restAssuredResponse.then().assertThat().body("status", equalTo(200));
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean historicoClienteRetornado() {
		try {
			// valida Response
			restAssuredResponse.then().assertThat().body("message",
					equalTo("Historico do cliente recuperado com sucesso"));
			restAssuredResponse.then().assertThat().body("resource", equalTo("/cliente"));
			restAssuredResponse.then().assertThat().body("status", equalTo(200));
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean capturaIdentificadorObjetoSegurado() {
		try {
			// valida Response

			identificadorObjetoSegurado = restAssuredResponse.jsonPath()
					.getString("dados.nmIdentificadorObjetoSegurado");
			System.out.println(identificadorObjetoSegurado);

//			restAssuredResponse.getBody();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean validaRegistroVigencia() {
		try {
			// valida Response
			restAssuredResponse.then().assertThat().body("dados.inSelecionado", contains(true));
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean validaRegistroPeriodicidade() {
		try {
			// valida Response
			restAssuredResponse.then().assertThat().body("dados.inSelecionado", contains(false, true));
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean validaBeneficiario(String operacao) {
		try {

			switch (operacao) {

			case "incluido":

				// valida Response
				idBeneficiario = restAssuredResponse.jsonPath().getString(
						"dados.oferta.lsPlano[0].lsChassi[0].lsObjetoSegurado[0].lsBeneficiario[0].nmIdentificador");
				System.out.println(idBeneficiario);

				restAssuredResponse.then().assertThat().body(
						"dados.oferta.lsPlano[0].lsChassi[0].lsObjetoSegurado[0].lsBeneficiario[0].inProprietario",
						equalTo(true));
				restAssuredResponse.then().assertThat().body(
						"dados.oferta.lsPlano[0].lsChassi[0].lsObjetoSegurado[0].lsBeneficiario[0].nmCpfCnpj",
						equalTo("38995063831"));
				restAssuredResponse.then().assertThat().body(
						"dados.oferta.lsPlano[0].lsChassi[0].lsObjetoSegurado[0].lsBeneficiario[0].nmNome",
						equalTo("Mauricio"));
				return true;

			case "alterado":

				restAssuredResponse.then().assertThat().body(
						"dados.oferta.lsPlano[0].lsChassi[0].lsObjetoSegurado[0].lsBeneficiario[0].inProprietario",
						equalTo(true));
				restAssuredResponse.then().assertThat().body(
						"dados.oferta.lsPlano[0].lsChassi[0].lsObjetoSegurado[0].lsBeneficiario[0].nmCpfCnpj",
						equalTo("38995063831"));
				restAssuredResponse.then().assertThat().body(
						"dados.oferta.lsPlano[0].lsChassi[0].lsObjetoSegurado[0].lsBeneficiario[0].nmNome",
						equalTo("Ronaldo"));
				return true;

			case "excluido":

				restAssuredResponse.then().assertThat().body(
						"dados.oferta.lsPlano[0].lsChassi[0].lsObjetoSegurado[0].lsBeneficiario[0].inProprietario",
						equalTo(null));
				restAssuredResponse.then().assertThat().body(
						"dados.oferta.lsPlano[0].lsChassi[0].lsObjetoSegurado[0].lsBeneficiario[0].nmCpfCnpj",
						equalTo(null));
				restAssuredResponse.then().assertThat().body(
						"dados.oferta.lsPlano[0].lsChassi[0].lsObjetoSegurado[0].lsBeneficiario[0].nmNome",
						equalTo(null));
				return true;
			}

			return false;
			// dados.oferta.lsPlano.lsObjetoSegurado.objeto.obBairro.nmResposta

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean validaEnderecoCopiado() {
		try {

			// valida Response
			restAssuredResponse.then().assertThat().body(
					"dados.oferta.lsPlano[0].lsChassi[0].lsObjetoSegurado[0].objeto.obBairro.nmResposta",
					equalTo("Chácara Santo Antônio"));
			restAssuredResponse.then().assertThat().body(
					"dados.oferta.lsPlano[0].lsChassi[0].lsObjetoSegurado[0].objeto.obCEP.nmResposta",
					equalTo("04521000"));
			restAssuredResponse.then().assertThat().body(
					"dados.oferta.lsPlano[0].lsChassi[0].lsObjetoSegurado[0].objeto.obComplemento.nmResposta",
					equalTo("teste"));
			restAssuredResponse.then().assertThat().body(
					"dados.oferta.lsPlano[0].lsChassi[0].lsObjetoSegurado[0].objeto.obLogradouro.nmResposta",
					equalTo("Rua Alexandre Dumas"));
			restAssuredResponse.then().assertThat().body(
					"dados.oferta.lsPlano[0].lsChassi[0].lsObjetoSegurado[0].objeto.obMunicipio.nmResposta",
					equalTo("São Paulo"));
			restAssuredResponse.then().assertThat().body(
					"dados.oferta.lsPlano[0].lsChassi[0].lsObjetoSegurado[0].objeto.obNumero.nmResposta",
					equalTo("1671"));
			restAssuredResponse.then().assertThat().body(
					"dados.oferta.lsPlano[0].lsChassi[0].lsObjetoSegurado[0].objeto.obPais.nmResposta",
					equalTo("Brasil"));
			restAssuredResponse.then().assertThat().body(
					"dados.oferta.lsPlano[0].lsChassi[0].lsObjetoSegurado[0].objeto.obProximoFavela.nmResposta",
					equalTo("null"));
			restAssuredResponse.then().assertThat().body(
					"dados.oferta.lsPlano[0].lsChassi[0].lsObjetoSegurado[0].objeto.obUf.nmResposta", equalTo("SP"));
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean validaEnderecoFiltrado() {
		try {

			// valida Response
			restAssuredResponse.then().assertThat().body(
					"data.oferta.lsPlano[0].lsChassi[0].lsObjetoSegurado[0].objeto.obBairro.nmResposta",
					equalTo("Chácara Santo Antônio"));

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Map<String, Object> montaBody() {

		switch (nomeAPI) {

		case "Consulta por Proponente":

			arrElement = new Object[] { "", "38995063831", "15151515" };

			Object[] arrFinal = (arrElement);

			payLoadGeral.put("cpf", (arrFinal[1]));
			payLoadGeral.put("senha", (arrFinal[2]));
			break;

		case "Registrar a Vigência":

			arrElement = new Object[] { "", cotacaoPage.nrLocalizer, 10000, 8 };

			Object[] arrFinal2 = (arrElement);

			System.out.println("Tamanho do array final: " + arrFinal2.length);
			System.out.println("Tamanho do array final: " + arrElement.toString());

			assertTrue(arrFinal2.length > 0);

			for (int g = 0; g < arrFinal2.length; g++) {
				System.out.println("LOG: " + g + " _ " + arrFinal2[g]);
			}

			payLoadGeral.put("localizadorCotacao", (arrFinal2[1]));
			payLoadGeral.put("nrChassi", (arrFinal2[2]));
			payLoadGeral.put("nrVigencia", (arrFinal2[3]));
			break;

		case "Registrar a Periodicidade":

			arrElement = new Object[] { "", cotacaoPage.nrLocalizer, 10000, 2 };

			Object[] arrFinal3 = (arrElement);

			System.out.println("Tamanho do array final: " + arrFinal3.length);
			System.out.println("Tamanho do array final: " + arrElement.toString());

			assertTrue(arrFinal3.length > 0);

			for (int g = 0; g < arrFinal3.length; g++) {
				System.out.println("LOG: " + g + " _ " + arrFinal3[g]);
			}

			payLoadGeral.put("localizadorCotacao", (arrFinal3[1]));
			payLoadGeral.put("nrChassi", (arrFinal3[2]));
			payLoadGeral.put("nrPeriodicidade", (arrFinal3[3]));
			break;

		}

		return payLoadGeral;
	}

	public Map<String, List<Map<String, Object>>> montaBody2() {

		switch (nomeAPI) {

		case "Copiar Endereço":
			// Primeiro array dentro de endereco
			arrElement2 = new Object[] { "", "Rua Alexandre Dumas", "1671", "teste", "Chácara Santo Antônio",
					"São Paulo", "SP", "Brasil", "04521000" };
			Object[] arrFinal5 = (arrElement2);

			System.out.println("Tamanho do array final: " + arrFinal5.length);
			System.out.println("Tamanho do array final: " + arrElement2.toString());

//				for (int g = 0; g < arrFinal5.length; g++) {
//					System.out.println("LOG: " + g + " _ " + arrFinal5[g]);
			payLoadGeral4.put("nmLogradouro", (arrFinal5[1]));
			payLoadGeral4.put("nmNumero", (arrFinal5[2]));
			payLoadGeral4.put("nmComplemento", (arrFinal5[3]));
			payLoadGeral4.put("nmBairro", (arrFinal5[4]));
			payLoadGeral4.put("nmMunicipio", (arrFinal5[5]));
			payLoadGeral4.put("nmUf", (arrFinal5[6]));
			payLoadGeral4.put("nmPais", (arrFinal5[7]));
			payLoadGeral4.put("nmCep", (arrFinal5[8]));
//					break;
//				}

			// Array Final

			arrElement = new Object[] { "", cotacaoPage.nrLocalizer, 10000, payLoadGeral2 };

			Object[] arrFinal4 = (arrElement);

			System.out.println("Tamanho do array final: " + arrFinal4.length);
			System.out.println("Tamanho do array final: " + arrElement.toString());

			assertTrue(arrFinal4.length > 0);

			for (int g = 0; g < arrFinal4.length; g++) {
				System.out.println("LOG: " + g + " _ " + arrFinal4[g]);
			}
//				payLoadGeral3.put("nrLocalizador", (arrFinal4[1]));
//				payLoadGeral3.put("nrChassi", (arrFinal4[2]));
			payLoadGeral5.put("endereco", Arrays.asList(payLoadGeral4));

			return payLoadGeral5;
//			break;

		case "Beneficiário":

			String nomeBeneficiario = "";

			switch (tpBeneficiario) {
			case "Incluir":
				nomeBeneficiario = "Mauricio";
				break;
			case "Alterar":
				nomeBeneficiario = "Ronaldo";
				break;
			case "Excluir":
				nomeBeneficiario = "Mauricio";
				break;
			}

			arrElement = new Object[] { "", "38995063831", nomeBeneficiario, true };
			Object[] arrFinal6 = (arrElement);

			payLoadGeral2.put("nmCpfCnpj", (arrFinal6[1]));
			payLoadGeral2.put("nmNome", (arrFinal6[2]));
			payLoadGeral2.put("inProprietario", (arrFinal6[3]));

			arrElement2 = new Object[] { "", cotacaoPage.nrLocalizer, "I", 10000, identificadorObjetoSegurado, "",
					payLoadGeral2 };

			Object[] arrFinal7 = (arrElement2);

//				System.out.println("Tamanho do array final: " + arrFinal6.length);
//				System.out.println("Tamanho do array final: " + arrElement.toString());
//
//				assertTrue(arrFinal6.length > 0);

			for (int g = 0; g < arrFinal7.length; g++) {
				System.out.println("LOG: " + g + " _ " + arrFinal7[g]);
			}

			payLoadGeral3.put("beneficiario", Arrays.asList(payLoadGeral2));

			return payLoadGeral3;
//			break;

		}
		return null;

//		if(nomeAPI.equals("Beneficiário")) {
//			return payLoadGeral3;
//		}else {
//			return payLoadGeral5; 
//		}	

	}

	public void validaResponseCode(int expectedHTTPResponseCode) {
		pegaResponse = restAssuredResponse.toString();
		System.out.println(pegaResponse);

		responseCode = restAssuredResponse.getStatusCode();
		if (responseCode != expectedHTTPResponseCode) {
			Assert.fail("StatusCode esperado : " + expectedHTTPResponseCode + " e o StatusCode recebido : "
					+ responseCode + " não são o mesmo. Erro ao executar a API de: " + nomeAPI + " .Response: "
					+ restAssuredResponse.prettyPrint());
		}

		System.out.println(
				"StatusCode esperado : " + expectedHTTPResponseCode + " e o StatusCode recebido : " + responseCode);

//		System.out.println("Erro ao executar a API de: " + nomeAPI);
	}

	public boolean validaTempoDeResposta(int timeLimit) {
		System.out.println("Tempo de resposta esperado : " + timeLimit + " e o Tempo de resposta recebido : "
				+ restAssuredResponse.getTime());
		if (restAssuredResponse.getTime() <= timeLimit) {
			return true;
		} else
			System.out.println("");
		return false;
	}

	public String generateStringFromResource(String path) throws IOException {

		return new String(Files.readAllBytes(Paths.get(path)));

	}

	public static void pegarToken() throws Exception {
//		nmIdSessao = restAssuredResponse.jsonPath().getString("nmIdSessao");
		// Adaptado para Token Fixo
		nmIdSessao = "eyKicGciNhKIUzH0OiJ9/dxJqdFjhOiIyLEEwMU@xMTU1OBIsIlmidCI7LUU5MUL3OTMxORwiaYO{Ijoh`IR0cIL7Ly9uc3JpbFTtZGV3MVNsZB4hcmF{`VxzZVbtY29uMlJyIhvhY29ocll0b{qocm90bIMiOmrhQUNGT0NPX1KBIl0rHoVzZYKtYW1mHkoiQTOGU1NQY1JCIo1/UXzSX[WmiCKPkdi{kbc3iDp,8wZ,eFZyJ[O,oXt0[gw";
		System.out.println(nmIdSessao);
	}

	public static void capturaLocalizadorCotacao() throws Exception {
		localizadorCotacaoCapturado = restAssuredResponse.jsonPath().getString("dados.cotacao.localizadorCotacao");
		System.out.println(localizadorCotacaoCapturado);
	}

	public static void capturaIdentificadorOferta() throws Exception {
		identificadorOferta = restAssuredResponse.jsonPath()
				.getString("dados.cotacao.lsOferta[0].nmIdentificadorOferta");
		System.out.println(identificadorOferta);
	}

	public static void capturaIdentificadorChassi() throws Exception {
		identificadorChassi = restAssuredResponse.jsonPath()
				.getString("dados.cotacao.lsOferta[0].lsChassi[0].nmIdentificadorChassi");
		System.out.println(identificadorChassi);
	}
	
	public static void capturaIdentificadorPeriodicidade() throws Exception {
		identificadorPeriodicidade = restAssuredResponse.jsonPath()
				.getString("dados.cotacao.lsOferta[0].lsChassi[0].periodicidadeContratacao.nmIdentificadorPeriodicidadeContratacao");
		System.out.println(identificadorPeriodicidade);
	}
	
	public static void capturaIdentificadorPlano() throws Exception {
		identificadorPlano = restAssuredResponse.jsonPath()
				.getString("dados.cotacao.lsOferta[0].lsPlano[0].nmIdentificadorPlano");
		System.out.println(identificadorPlano);
	}
	
	public static void capturaIdentificadorPlanoPagamento() throws Exception {
		identificadorPlanoPagamento = restAssuredResponse.jsonPath()
				.getString("dados.cotacao.lsOferta[0].lsPlanoPagamento[0].nmIdentificadorPlanoPagamento");
		System.out.println(identificadorPlanoPagamento);
	}
	
	public static void capturaIdentificadorPacoteAssistencia() throws Exception {
		identificadorPacoteAssistencia = restAssuredResponse.jsonPath()
				.getString("dados.cotacao.lsOferta[0].lsChassi[0].lsPacoteAssistencia[0].nmIdentificadorPlanoPacoteAssistencia");
		System.out.println(identificadorPacoteAssistencia);
	}
	
	public static void capturaIdentificadorParcela() throws Exception {
		identificadorParcela = restAssuredResponse.jsonPath()
				.getString("dados.cotacao.lsOferta[0].lsPlano[0].lsParcela[0].nmIdentificadorParcela");
		System.out.println(identificadorParcela);
	}
	
	public boolean validaPropostaBackend() {
		try {
			// valida Response
			restAssuredResponse.then().assertThat().body("message", equalTo("Cotação contrada com sucesso"));
			restAssuredResponse.then().assertThat().body("status", equalTo(200));

			numeroProposta = restAssuredResponse.jsonPath().getString("dados.propostas[0].nrProposta");
			System.out.println("O número da proposta capturado é  " + numeroProposta);

			// Valida Data da Proposta

			utilsdb.conectaDB("ValidaDataProposta", numeroProposta);
			String resultadosql = utilsdb.listaDeValores.toString();
			String resultadosql1 = resultadosql.replace("[[", " ");
			String resultadosql2 = resultadosql1.replace("]]", " ").trim();

			System.out.println(resultadosql2);

//			String resultado = "2020-02-20";

			String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());

			System.out.println("A data atual é " + timeStamp);

			if (resultadosql2.equals(timeStamp)) {
				System.out.println("A proposta gravada no banco de dados corretamente.");
				return true;
			} else {
				System.out.println("A proposta não foi gravada no banco de dados corretamente.");
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public void trataDadoBanco() throws Exception {

		String listinha = UtilsDB.Lista;
		String listinha1 = listinha.replace("[", "").trim();
		String listinha2 = listinha1.replace("], ", "\n").trim();
		String listinha3 = listinha2.replace(",", "").trim();
		String listinha4 = listinha3.replace("]]", "");
		listinha5 = listinha4;
		System.out.println(listinha5);
	}

	public void trataDadoCapturado() throws Exception {

		String dadoTratado = dadoCapturado;
		String dadoTratado1 = dadoTratado.replace("[", "").trim();
		String dadoTratado2 = dadoTratado1.replace("], ", "").trim();
		String dadoTratado3 = dadoTratado2.replace(", ", "\n").trim();
		String dadoTratado4 = dadoTratado3.replace("]", "");
		dadoTratado5 = dadoTratado4;
		System.out.println(dadoTratado5);
	}

	public void trataDadosFront() throws Exception {

		if (nomeAPI.contentEquals("Tipo de Seguro")) {
			String dadoFrontTratadoTipoSeguro = valorCriticaMomento;
			String dadoFrontTratadoTipoSeguro1 = dadoFrontTratadoTipoSeguro.replace("Digite ou selecione...", "")
					.trim();
			dadoFront = dadoFrontTratadoTipoSeguro1;
		} else {
			String dadoFrontTratado = valorCriticaMomento;
			String dadoFrontTratado1 = dadoFrontTratado.replace("Selecione...", "").trim();
			dadoFront = dadoFrontTratado1;
		}

//		switch (nomeAPI) {
//
//		case "Momento":
//			String dadoFrontTratado = valorCriticaMomento;
//			String dadoFrontTratado1 = dadoFrontTratado.replace("Selecione...", "").trim();
//			dadoFront = dadoFrontTratado1;
//			break;
//
//		case "Tipo de Seguro":
//			String dadoFrontTratadoTipoSeguro = valorCriticaMomento;
//			String dadoFrontTratadoTipoSeguro1 = dadoFrontTratadoTipoSeguro.replace("Digite ou selecione...", "").trim();
//			dadoFront = dadoFrontTratadoTipoSeguro1;
//			break;
//			
//		case "Ação":
//			String dadoFrontAcao = valorCriticaMomento;
//			String dadoFrontAcao1 = dadoFrontAcao.replace("Selecione...", "").trim();
//			dadoFront = dadoFrontAcao1;
//			break;
//
//		case "Motivo de recusa":
//
//			break;
//
//		case "Classe da regra":
//
//			break;
//		}

		System.out.println(dadoFront);
	}

	public void capturaDadosFront() throws Exception {

		System.setProperty("webdriver.chrome.driver", "c:\\temp\\driver\\chromedriver.exe");
		navegador = new ChromeDriver();
		navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized");

		// Navegando para a página
		navegador.get("https://regra.dev-cld.brasilseg.com.br/");

		Thread.sleep(3000);

		navegador.findElement(By.xpath("//span[text()='Nova regra']")).click();

		Thread.sleep(4000);

		// Switch

		switch (nomeAPI) {

		case "Momento":
			WebElement comboMomento = navegador.findElement(By.id("critica-momento"));

			comboMomento.click();
			Thread.sleep(2000);

			valorCriticaMomento = navegador.findElement(By.xpath("//ul[@role='listbox']")).getText();
			break;

		case "Tipo de Seguro":
			WebElement campoTipoSeguro = navegador.findElement(By.id("critica-tipo-seguro"));

			campoTipoSeguro.click();
			Thread.sleep(2000);
			valorCriticaMomento = navegador.findElement(By.xpath("//div[@role='presentation']")).getText();

//			valorCriticaMomento = navegador.findElement(By.xpath("//ul[@role='listbox']")).getText();	

			break;

		case "Ação":
			WebElement comboAcao = navegador.findElement(By.id("critica-acao"));

			comboAcao.click();
			Thread.sleep(2000);

			valorCriticaMomento = navegador.findElement(By.xpath("//ul[@role='listbox']")).getText();
			break;

		case "Motivo de recusa":
			WebElement comboMotivoRecusa = navegador.findElement(By.id("critica-motivo-recusa"));

			comboMotivoRecusa.click();
			Thread.sleep(2000);

			valorCriticaMomento = navegador.findElement(By.xpath("//ul[@role='listbox']")).getText();
			break;

		case "Classe da regra":
			WebElement comboClasseRegra = navegador.findElement(By.id("critica-classe-critica"));

			comboClasseRegra.click();
			Thread.sleep(2000);

			valorCriticaMomento = navegador.findElement(By.xpath("//ul[@role='listbox']")).getText();

			break;
		}

		navegador.quit();

		System.out.println(valorCriticaMomento);

		Thread.sleep(1000);
	}

}
