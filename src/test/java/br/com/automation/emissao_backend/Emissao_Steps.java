package br.com.automation.emissao_backend;

import static org.junit.Assert.assertTrue;

import br.com.automation.Pages.CotacaoPage;
import br.com.automation.Pages.EmissaoPage;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.bs.I;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import io.qameta.allure.Step;

public class Emissao_Steps {

	private EmissaoPage telaInicialPage = new EmissaoPage();
	private CotacaoPage cotacaoPage = new CotacaoPage();

	public String cenarioCucumber = "";

	@Before
	public void beforeHook(Scenario scenario) throws Exception {
		addScenario(scenario.getName());
	}

	public void addScenario(String scenario) throws Exception {
		System.out.println(scenario);
		cenarioCucumber = scenario.toString();

	}

	@Step("Carrego a URL")
	@Dado("carrego a URL de \"(.*?)\"$")
	public void carregar_URL_Consulta_Contrato(String url) {
		telaInicialPage.carregaAPI(url);
	}

	@Step("Emito uma proposta pela Cotação")
	@Dado("que emiti uma proposta pela Cotação")
	public void emitir_Proposta_Cotacao() throws Exception {
		cotacaoPage.emitirCotacao();
	}

//	@Step("Emito uma proposta pela Cotação para um cenário de Beneficiário")
//	@Dado("que emiti uma proposta pela Cotação para um cenário de \"(.*?)\"$")
//	public void emitir_Proposta_Cotacao_Beneficiario(String cenario) throws Exception {
//		cotacaoPage.emitirCotacaoBeneficiario(cenario);
//		cotacaoPage.capturarLocalizadorObjetoSegurado();
//	}

	@Step("O Token é gerado")
	@Dado("que o Token é gerado")
	public void gerarToken() throws Exception {
		telaInicialPage.carregaAPI("Gerar Token");
		telaInicialPage.enviaPOSTArquivoJson("Gerar Token");
		
		if(EmissaoPage.responseCode==407) {
		 
		for ( int i= 0; i<3; i++ )
		{
			telaInicialPage.enviaPOSTArquivoJson("Gerar Token");	
			
			if (EmissaoPage.responseCode==200) {
				break;
			}
			
		}
		}	
		
		telaInicialPage.pegarToken();
	}

	@Step("Envio o comando POST para Copiar Endereço")
	@Quando("envio o comando POST para Copiar Endereço")
	public void envia_Post_Copiar_Endereco() throws InterruptedException {
		telaInicialPage.enviaPOSTCopiarEndereco(cenarioCucumber);
		
		if(EmissaoPage.responseCode==407) {
		for ( int i= 0; i> 3; i++ )
		{
			telaInicialPage.enviaPOSTCopiarEndereco(cenarioCucumber);	
			
			if (EmissaoPage.responseCode==200) {
				break;
			}
			
		}	}
	}

//	@Step("Envio o comando POST para Beneficiário")
//	@Quando("envio o comando POST para \"([^\"]*)\"$")
//	public void envia_Post_Beneficiario(String tipoBeneficiario) throws Exception {
//		telaInicialPage.enviaPOSTBeneficiario(tipoBeneficiario, cenarioCucumber);
//		
//		if(EmissaoPage.responseCode==407) {
//		for ( int i= 0; i> 3; i++ )
//		{
//			telaInicialPage.enviaPOSTBeneficiario(tipoBeneficiario, cenarioCucumber);	
//			
//			if (EmissaoPage.responseCode==200) {
//				break;
//			}
//		}
//		}	
//	}

	@Step("Envio o comando POST")
	@Quando("envio o comando POST")
	public void envia_Post() throws InterruptedException {
		telaInicialPage.enviaPOST(cenarioCucumber);
		
		if(EmissaoPage.responseCode==407) {
		for ( int i= 0; i> 3; i++ )
		{
			telaInicialPage.enviaPOST(cenarioCucumber);	
			
			if (EmissaoPage.responseCode==200) {
				break;
			}
			
		}	}
	}

	@Step("Envio o comando POST para Produto")
	@Quando("envio o comando POST para produto")
	public void envia_PostProduto() throws InterruptedException {
		telaInicialPage.enviaPOSTArquivoJson(cenarioCucumber);
		
		if(EmissaoPage.responseCode==407) {
		for ( int i= 0; i> 3; i++ )
		{
			telaInicialPage.enviaPOSTArquivoJson(cenarioCucumber);	
			
			if (EmissaoPage.responseCode==200) {
				break;
			}
			
		}	
	}}
	
	@Step("Envio o comando POST para o Fluxo de Cotação")
	@Quando("envio o comando POST para o fluxo de cotação")
	public void envia_PostCotação() throws InterruptedException {
		telaInicialPage.enviaPOSTArquivoJson(telaInicialPage.nomeAPI);
		
		if(EmissaoPage.responseCode==407) {
		for ( int i= 0; i> 3; i++ )
		{
			telaInicialPage.enviaPOSTArquivoJson(telaInicialPage.nomeAPI);	
			
			if (EmissaoPage.responseCode==200) {
				break;
			}
			
		}	
	}}

	@Entao("verifico o retorno")
	public void verificoRetorno() throws InterruptedException {
		telaInicialPage.verificoRetorno();
	}

	@Step("Envio o comando GET")
	@Quando("envio o comando GET")
	public void envia_Get() throws InterruptedException {
		telaInicialPage.enviaGET(cenarioCucumber);
		
		if(EmissaoPage.responseCode==407) {
		for ( int i= 0; i> 3; i++ )
		{
			telaInicialPage.enviaGET(cenarioCucumber);	
			
			if (EmissaoPage.responseCode==200) {
				break;
			}
			
		}	}
	}

	@Step("Retorno a periodicidade")
	@Quando("retorno a periodicidade")
	public void retorno_Periodicidade() throws InterruptedException {
		telaInicialPage.carregaAPI("Retornar a Periodicidade");
		telaInicialPage.enviaGET(cenarioCucumber);
		
		if(EmissaoPage.responseCode==407) {
		for ( int i= 0; i> 3; i++ )
		{
			telaInicialPage.enviaGET(cenarioCucumber);	
			
			if (EmissaoPage.responseCode==200) {
				break;
			}
			
		}	}
	}

	@Step("Retorno a vigência")
	@Quando("retorno a vigência")
	public void retorno_Vigencia() throws InterruptedException {
		telaInicialPage.carregaAPI("Retornar a Vigência");
		telaInicialPage.enviaGET(cenarioCucumber);
		
		if(EmissaoPage.responseCode==407) {
		for ( int i= 0; i> 3; i++ )
		{
			telaInicialPage.enviaGET(cenarioCucumber);
			
			if (EmissaoPage.responseCode==200) {
				break;
			}
			
		}	}
	}
	
	@Step("Confirmo a contratação")
	@Quando("confirmo a contratação")
	public void confirmo_Contratacao() throws Throwable {
	    telaInicialPage.carregaAPI("Grava Contrato");
	    telaInicialPage.enviaPOSTArquivoJson("Grava Contrato");
	   
	    
		if(EmissaoPage.responseCode==407) {
			for ( int i= 0; i> 3; i++ )
			{
				telaInicialPage.enviaPOSTArquivoJson("Grava Contrato");
				
				if (EmissaoPage.responseCode==200) {
					break;
				}
				
			}	}
		
		 o_status_code_é_tempo_resposta(200,10000);
	}
	
	
	@Step("Realizo o Fluxo de Cotação")
	@Quando("realizo o fluxo de cotação")
	public void realizo_fluxo_cotacao() throws Throwable {

		
	    telaInicialPage.carregaAPI("Iniciar Cotação");
	    telaInicialPage.enviaGET("Iniciar Cotação");
	    
	    
		if(EmissaoPage.responseCode==407) {
			for ( int i= 0; i> 3; i++ )
			{
				telaInicialPage.enviaGET("Iniciar Cotação");
				
				if (EmissaoPage.responseCode==200) {
					break;
				}
				
			}	}
		
		o_status_code_é_tempo_resposta(200,5000);

	    telaInicialPage.carregaAPI("Consulta GrupoRamo");
	    telaInicialPage.enviaGET("Consulta GrupoRamo");
	   
	    
		if(EmissaoPage.responseCode==407) {
			for ( int i= 0; i> 3; i++ )
			{
				telaInicialPage.enviaGET("Consulta GrupoRamo");
				
				if (EmissaoPage.responseCode==200) {
					break;
				}
				
			}	}
		
		 o_status_code_é_tempo_resposta(200,5000);
		
	    telaInicialPage.carregaAPI("Consulta Oferta");
	    telaInicialPage.enviaGET("Consulta Oferta");
	    
	    
		if(EmissaoPage.responseCode==407) {
			for ( int i= 0; i> 3; i++ )
			{
				telaInicialPage.enviaGET("Consulta Oferta");
				
				if (EmissaoPage.responseCode==200) {
					break;
				}
				
			}	}
		
		o_status_code_é_tempo_resposta(200,5000);
	    
	    telaInicialPage.carregaAPI("Consulta Plano");
	    telaInicialPage.enviaGET("Consulta Plano");
	   
	    
		if(EmissaoPage.responseCode==407) {
			for ( int i= 0; i> 3; i++ )
			{
				 telaInicialPage.enviaGET("Consulta Plano");
				
				if (EmissaoPage.responseCode==200) {
					break;
				}
				
			}	}
		
		 o_status_code_é_tempo_resposta(200,5000);
	    
	    telaInicialPage.carregaAPI("Grava Plano");
	    telaInicialPage.enviaPOSTArquivoJson("Grava Plano");
	    
	    
	    if(EmissaoPage.responseCode==407) {
			for ( int i= 0; i> 3; i++ )
			{
				telaInicialPage.enviaPOSTArquivoJson("Grava Plano");
				
				if (EmissaoPage.responseCode==200) {
					break;
				}
				
			}	}
	    
	    o_status_code_é_tempo_resposta(200,5000);
	    
	    telaInicialPage.carregaAPI("Grava Vigência");
	    telaInicialPage.enviaPOSTArquivoJson("Grava Vigência");
	    
	    
	    if(EmissaoPage.responseCode==407) {
			for ( int i= 0; i> 3; i++ )
			{
				telaInicialPage.enviaPOSTArquivoJson("Grava Vigência");
				
				if (EmissaoPage.responseCode==200) {
					break;
				}
				
			}	}
	    
	    o_status_code_é_tempo_resposta(200,5000);
	    
	    telaInicialPage.carregaAPI("Consulta Pessoa");
	    telaInicialPage.enviaGET("Consulta Pessoa");
	    
	    
	    if(EmissaoPage.responseCode==407) {
			for ( int i= 0; i> 3; i++ )
			{
				telaInicialPage.enviaGET("Consulta Pessoa");
				
				if (EmissaoPage.responseCode==200) {
					break;
				}
				
			}	}
	    
	    o_status_code_é_tempo_resposta(200,5000);
	    
	    telaInicialPage.carregaAPI("Grava Pessoa");
	    telaInicialPage.enviaPOSTArquivoJson("Grava Pessoa");
	    
	    
	    if(EmissaoPage.responseCode==407) {
			for ( int i= 0; i> 3; i++ )
			{
				 telaInicialPage.enviaPOSTArquivoJson("Grava Pessoa");
				
				if (EmissaoPage.responseCode==200) {
					break;
				}
				
			}	}
	    
	    o_status_code_é_tempo_resposta(200,5000);
	    
	    telaInicialPage.carregaAPI("Consulta CEP");
	    telaInicialPage.enviaGET("Consulta CEP");
	    
	    
	    if(EmissaoPage.responseCode==407) {
			for ( int i= 0; i> 3; i++ )
			{
				telaInicialPage.enviaGET("Consulta CEP");
				
				if (EmissaoPage.responseCode==200) {
					break;
				}
				
			}	}
	    
	    o_status_code_é_tempo_resposta(200,5000);
	    
	    telaInicialPage.carregaAPI("Grava Endereço");
	    telaInicialPage.enviaPOSTArquivoJson("Grava Endereço");
	   
	    
	    if(EmissaoPage.responseCode==407) {
			for ( int i= 0; i> 3; i++ )
			{
				telaInicialPage.enviaPOSTArquivoJson("Grava Endereço");
				
				if (EmissaoPage.responseCode==200) {
					break;
				}
				
			}	}
	    
	    o_status_code_é_tempo_resposta(200,5000);
	    
	    telaInicialPage.carregaAPI("Grava Email");
	    telaInicialPage.enviaPOSTArquivoJson("Grava Email");
	    
	    
	    if(EmissaoPage.responseCode==407) {
			for ( int i= 0; i> 3; i++ )
			{
				telaInicialPage.enviaPOSTArquivoJson("Grava Email");
				
				if (EmissaoPage.responseCode==200) {
					break;
				}
				
			}	}
	    
	    o_status_code_é_tempo_resposta(200,5000);
	    
	    telaInicialPage.carregaAPI("Grava Telefone");
	    telaInicialPage.enviaPOSTArquivoJson("Grava Telefone");
	    
	    
	    if(EmissaoPage.responseCode==407) {
			for ( int i= 0; i> 3; i++ )
			{
				telaInicialPage.enviaPOSTArquivoJson("Grava Telefone");
				
				if (EmissaoPage.responseCode==200) {
					break;
				}
				
			}	}
	    
	    o_status_code_é_tempo_resposta(200,5000);
	    
	    telaInicialPage.carregaAPI("Grava Objeto Segurado");
	    telaInicialPage.enviaPOSTArquivoJson("Grava Objeto Segurado");
	    
	    
	    if(EmissaoPage.responseCode==407) {
			for ( int i= 0; i> 3; i++ )
			{
				telaInicialPage.enviaPOSTArquivoJson("Grava Objeto Segurado");
				
				if (EmissaoPage.responseCode==200) {
					break;
				}
				
			}	}
	    
	    o_status_code_é_tempo_resposta(200,10000);
	}

	@Step("O status de retorno é 200 e o tempo de resposta é menor ou igual a 10000 milisegundos")
	@Entao("^o status de retorno é (\\d+) e o tempo de resposta é menor ou igual a (\\d+) milisegundos$")
	public void o_status_code_é_tempo_resposta(int statusCode, int timeLimit) throws Throwable {
		telaInicialPage.validaResponseCode(statusCode);
		assertTrue(telaInicialPage.validaTempoDeResposta(timeLimit));
	}
	
	@Step("Valido se as informações apresentadas estão corretas")
	@Entao("valido se as informações apresentadas estão corretas")
	public void valido_informacoes_configurador() throws Throwable {
		assertTrue(telaInicialPage.validaRetornoConfigurador());
	}
	
	@Step("Valido se as informações apresentadas estão corretas com o Front-End")
	@Entao("valido se as informações apresentadas estão corretas com o Front-End")
	public void valido_informacoes_configurador_front() throws Throwable {
		assertTrue(telaInicialPage.validaRetornoConfiguradorFront());
	}
	
	@Step("Valido se as vigências são retornadas")
	@Entao("as vigências são retornadas")
	public void as_vigencias_sao_retornadas() throws Throwable {
		assertTrue(telaInicialPage.validaRetornoVigencia());
	}

	@Step("Valido se as informações apresentadas estão corretas")
	@Entao("as periodicidades são retornadas")
	public void as_periodicidades_sao_retornadas() throws Throwable {
		assertTrue(telaInicialPage.validaRetornoPeriodicidade());
	}

	@Step("Histórico do cliente é retornado")
	@Entao("o histórico do cliente é retornado")
	public void o_historico_cliente_retornado() throws Throwable {
		assertTrue(telaInicialPage.historicoClienteRetornado());
	}

	
	@Entao("verifico se o beneficiario foi \"([^\"]*)\" corretamente")
	public void verifico_beneficiario_corretamente(String operacao) throws Throwable {
		telaInicialPage.validaResponseCode(200);
		assertTrue(telaInicialPage.validaTempoDeResposta(5000));

		telaInicialPage.carregaAPI("Consultar Cotação");
		telaInicialPage.enviaGET(cenarioCucumber);
		
		if(EmissaoPage.responseCode==407) {
		for ( int i= 0; i> 3; i++ )
		{
			telaInicialPage.enviaGET(cenarioCucumber);
			
			if (EmissaoPage.responseCode==200) {
				break;
			}
			
		}	

		assertTrue(telaInicialPage.validaBeneficiario(operacao));
	}}

	@Step("Verifico na Consulta de Cotação se o endereço foi copiado corretamente\"")
	@Entao("verifico na Consulta de Cotação se o endereço foi copiado corretamente")
	public void verifico_consulta_cotacao_copiado() throws Throwable {
		telaInicialPage.carregaAPI("Consultar Cotação");
		telaInicialPage.enviaGET(cenarioCucumber);
		
		if(EmissaoPage.responseCode==407) {
		for ( int i= 0; i> 3; i++ )
		{
			telaInicialPage.enviaGET(cenarioCucumber);
			
			if (EmissaoPage.responseCode==200) {
				break;
			}
		}
		}	
		assertTrue(telaInicialPage.validaEnderecoCopiado());
	}

	@Step("O  endereço é filtrado corretamente")
	@Entao("o endereço é filtrado corretamente")
	public void endereço_filtrado_corretamente() throws Throwable {
		assertTrue(telaInicialPage.validaRegistroVigencia());
	}

	@Step("Verifico se a periodicidade é registrada corretamente")
	@Entao("verifico se a periodicidade é registrada corretamente")
	public void verifico_periodicidade_registrada_corretamente() throws Throwable {
		// registra periodicidade
		telaInicialPage.carregaAPI("Registrar a Periodicidade");
		telaInicialPage.enviaPOST(cenarioCucumber);
		
		if(EmissaoPage.responseCode==407) {
		for ( int i= 0; i> 3; i++ )
		{
			telaInicialPage.enviaPOST(cenarioCucumber);
			
			if (EmissaoPage.responseCode==200) {
				break;
			}
			
		}	}
		
	
		// retorna periodicidade
		telaInicialPage.carregaAPI("Retornar a Periodicidade Novamente");
		telaInicialPage.enviaGET(cenarioCucumber);
		
		if(EmissaoPage.responseCode==407) {
		for ( int i= 0; i> 3; i++ )
		{
			telaInicialPage.enviaGET(cenarioCucumber);
			
			if (EmissaoPage.responseCode==200) {
				break;
			}}
			
		}	
		assertTrue(telaInicialPage.validaRegistroPeriodicidade());
	}
	
	@Step("Verifico se a  vigência é registrada corretamente")
	@Entao("verifico se a vigência é registrada corretamente")
	public void verifico_vigencia_registrada_corretamente() throws Throwable {
		// registra vigência
		telaInicialPage.carregaAPI("Registrar a Vigência");
		telaInicialPage.enviaPOST(cenarioCucumber);
		
		if(EmissaoPage.responseCode==407) {
		for ( int i= 0; i> 3; i++ )
		{
			telaInicialPage.enviaPOST(cenarioCucumber);
			
			if (EmissaoPage.responseCode==200) {
				break;
			}
		}
		}	
		// retorna periodicidade
		telaInicialPage.carregaAPI("Retornar a Vigência Novamente");
		telaInicialPage.enviaGET(cenarioCucumber);
		if(EmissaoPage.responseCode==407) {
		for ( int i= 0; i> 3; i++ )
		{
			telaInicialPage.enviaGET(cenarioCucumber);
			
			if (EmissaoPage.responseCode==200) {
				break;
			}
		}
		}	
		assertTrue(telaInicialPage.validaRegistroVigencia());
	}
	
	
	@Step("A proposta é gerada corretamente")
	@Entao("a proposta é gerada corretamente")
	public void proposta_gerada_corretamente() throws Throwable {
		assertTrue(telaInicialPage.validaPropostaBackend());
	}

}
