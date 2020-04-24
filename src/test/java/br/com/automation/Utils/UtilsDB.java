package br.com.automation.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.google.common.collect.Lists;

public class UtilsDB  {

	public static List<List<String>> listaDeValores;
	public static String Lista;
	public static int TamanhoLista;
	

	public void conectaDB(String Cenario, String numeroProposta) {

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		String connectionUrl;
		
		if(Cenario.equals("Momento")||Cenario.equals("Tipo de Seguro")||Cenario.equals("Referência")||Cenario.equals("Operação")||Cenario.equals("Ação")||Cenario.equals("Motivo de recusa")||Cenario.equals("Classe da regra")) {
			connectionUrl = "jdbc:sqlserver://brsegd.cctdpwuhuznc.sa-east-1.rds.amazonaws.com;databaseName=subscricao;user=usr_app_subscricao;password=kdyh6JG27S";	
		}else {
			connectionUrl = "jdbc:sqlserver://brsegd.cctdpwuhuznc.sa-east-1.rds.amazonaws.com;databaseName=apolice;user=usr_app_apolice;password=u5r_@pp_ap0lic&";
		}
			
		
		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
			System.out.println("Conectou com sucesso no Banco de Dados");
	
				String proposta = numeroProposta;
				consultaDB(Cenario, proposta);

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Houve um problema na conexão com o Banco de Dados");
		}
	}

	public void consultaDB(String nomeConsulta, String proposta) throws SQLException {

		String connectionUrl;
		
		if(nomeConsulta.equals("Momento")||nomeConsulta.equals("Tipo de Seguro")||nomeConsulta.equals("Referência")||nomeConsulta.equals("Operação")||nomeConsulta.equals("Ação")||nomeConsulta.equals("Motivo de recusa")||nomeConsulta.equals("Classe da regra")){
			connectionUrl = "jdbc:sqlserver://brsegd.cctdpwuhuznc.sa-east-1.rds.amazonaws.com;databaseName=subscricao;user=usr_app_subscricao;password=kdyh6JG27S";	
		}else {
			connectionUrl = "jdbc:sqlserver://brsegd.cctdpwuhuznc.sa-east-1.rds.amazonaws.com;databaseName=apolice;user=usr_app_apolice;password=u5r_@pp_ap0lic&";
		}
		
		
		Connection con = DriverManager.getConnection(connectionUrl);
		Statement stmt = con.createStatement();

		switch (nomeConsulta) {
		case "ValidaDataProposta":
			String SQL = "SELECT CONVERT(VARCHAR(10),dt_inclusao) as DATA from proposta where nr_proposta = " + proposta ;
			ResultSet rs = stmt.executeQuery(SQL);

			listaDeValores = Lists.newArrayList();

			while (rs.next()) {
				System.out.println(rs.getString("DATA"));
				listaDeValores.add(Lists.newArrayList(rs.getString("DATA")));
			}

			break;
				
		case "Momento":
			String SQL2 = "select nm_momento from momento";
			ResultSet rs2 = stmt.executeQuery(SQL2);

			listaDeValores = Lists.newArrayList();

			while (rs2.next()) {
				System.out.println(rs2.getString("nm_momento"));
				listaDeValores.add(Lists.newArrayList(rs2.getString("nm_momento")));
			}
			break;
			
		case "Tipo de Seguro":
			String SQL3 = "select nm_tipo_seguro from tipo_seguro";
			ResultSet rs3 = stmt.executeQuery(SQL3);

			listaDeValores = Lists.newArrayList();

			while (rs3.next()) {
				System.out.println(rs3.getString("nm_tipo_seguro"));
				listaDeValores.add(Lists.newArrayList(rs3.getString("nm_tipo_seguro")));
			}
			break;
			
		case "Referência":
			String SQL4 = "select nm_referencia from referencia";
			ResultSet rs4 = stmt.executeQuery(SQL4);

			listaDeValores = Lists.newArrayList();

			while (rs4.next()) {
				System.out.println(rs4.getString("nm_referencia"));
				listaDeValores.add(Lists.newArrayList(rs4.getString("nm_referencia")));
			}
			break;
			
		case "Operação":
			String SQL5 = "select nm_operacao from operacao";
			ResultSet rs5 = stmt.executeQuery(SQL5);

			listaDeValores = Lists.newArrayList();

			while (rs5.next()) {
				System.out.println(rs5.getString("nm_operacao"));
				listaDeValores.add(Lists.newArrayList(rs5.getString("nm_operacao")));
			}
			break;
			
		case "Ação":
			String SQL6 = "select nm_acao from acao";
			ResultSet rs6 = stmt.executeQuery(SQL6);

			listaDeValores = Lists.newArrayList();

			while (rs6.next()) {
				System.out.println(rs6.getString("nm_acao"));
				listaDeValores.add(Lists.newArrayList(rs6.getString("nm_acao")));
			}
			break;
			
		case "Motivo de recusa":
			String SQL7 = "select nm_motivo_recusa from motivo_recusa";
			ResultSet rs7 = stmt.executeQuery(SQL7);

			listaDeValores = Lists.newArrayList();

			while (rs7.next()) {
				System.out.println(rs7.getString("nm_motivo_recusa"));
				listaDeValores.add(Lists.newArrayList(rs7.getString("nm_motivo_recusa")));
			}
			break;
			
		case "Classe da regra":
			String SQL8 = "select nm_classe_regra from classe_regra";
			ResultSet rs8 = stmt.executeQuery(SQL8);

			listaDeValores = Lists.newArrayList();

			while (rs8.next()) {
				System.out.println(rs8.getString("nm_classe_regra"));
				listaDeValores.add(Lists.newArrayList(rs8.getString("nm_classe_regra")));
			}
			break;
		
		default:
			String SQL_default = "select * from proposta_subscricao";
			ResultSet rs_default = stmt.executeQuery(SQL_default);

			listaDeValores = Lists.newArrayList();

			while (rs_default.next()) {
				System.out.println(rs_default.getString("id_proposta_subscricao"));
				listaDeValores.add(Lists.newArrayList(rs_default.getString("id_proposta_subscricao")));
			}

		}

		TamanhoLista = listaDeValores.size();

		Lista = listaDeValores.toString();

		System.out.println(Lista);
		
//		Utils.printResponseBancoDados(Lista, nomeConsulta, connectionUrl);

	}

	public void fechaConexaoDB() {

	}

}
