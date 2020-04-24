package br.com.automation.Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.common.io.Files;

import br.com.automation.Pages.CotacaoPage;
import br.com.automation.Pages.EmissaoPage;
import io.qameta.allure.Attachment;
import io.restassured.RestAssured;

public class Utils{
	
	public static String fileTestResult;
	public static String data;
	public static String hora;
	
	private EmissaoPage emissaoPage = new EmissaoPage();
	
	@Attachment(type = "text/plain")
	public static String printResponse(String metodo, String body, String cenario, String message, int responseCodeFinal, long time) {
		PrintWriter out;
		System.out.println("Pasta final dos arquivos: " + fileTestResult);
		geraHorarioExecucao();
		String cen = cenario;
		String output = fileTestResult +  cen + ".txt";
		try {
			out = new PrintWriter(new FileWriter(output, true), true);
			out.write("\r\n");
			out.write("\r\n");
			out.write(cen);
			out.write("\r\n");
			out.write("\r\n");
			out.write("URL utilizada é: " + RestAssured.baseURI);
			out.write("\r\n");
			out.write("\r\n");
			out.write("Requisição " + metodo);
			out.write("\r\n");
			out.write("\r\n");	
			if(metodo.equals("POST")) {
			out.write("O body utilizado é: ");
			out.write("\r\n");
			out.write("\r\n");
			out.write(body);
			out.write("\r\n");
			out.write("\r\n");
			}
			out.write("Status code: " + responseCodeFinal);
			out.write("\r\n");
			out.write("Response time: " + time);
			out.write("\r\n");
			out.write("\r\n");
			out.write("O Response é: ");
			out.write("\r\n");
			out.write("\r\n");
			out.write(message);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}
	
	public static void geraHorarioExecucao() {
		Date dataHoraAtual = new Date();
		data = new SimpleDateFormat("ddMMyyyy").format(dataHoraAtual);
		hora = new SimpleDateFormat("HHmmss").format(dataHoraAtual);
		System.out.println("A data é " + data + " e o horário é " + hora);
	
	}
	
	
}
