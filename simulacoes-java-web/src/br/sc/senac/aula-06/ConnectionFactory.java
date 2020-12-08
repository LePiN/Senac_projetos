package br.sc.senac.aula06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável por criar e destruir conexÃµes com bancos de dados
 * 
 * O banco escolhido foi o MySQL, assim é necessário utilizar o respectivo
 * driver JDBC
 * 
 * @author Vilmar C. Pereira Júnior
 * 
 *         Disciplina de Desenvolvimento Web Senac
 *
 */
public class ConnectionFactory {

	public Connection obterConexao() {
		
		//Configurações da conexão
		
		String nomeEsquema = "exemplosbd";
		String enderecoBanco = "jdbc:mysql://localhost/" + nomeEsquema;
		String usuario = "root";
		String senha = "1234";
		String driverJDBC = "com.mysql.jdbc.Driver";

		try {
			Class.forName(driverJDBC);
			Connection conexao = DriverManager.getConnection(enderecoBanco, usuario, senha);
			System.out.println("Conex�o aberta");
			return conexao;
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Erro ao obter conexão com o banco: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public static void fecharConexao(Connection con) {
		try {
			con.close();
			System.out.println("Conex�o fechada");
		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException(e);
		}
	}
}