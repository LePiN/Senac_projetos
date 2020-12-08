package br.sc.senac.lista04.dao;

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
	
	private static ConnectionFactory instance;
	
	public static ConnectionFactory getInstance() {
		
		if (instance == null) {
			instance = new ConnectionFactory();
		}
		
		return instance;
	}

	public Connection obterConexao() {
		
		//Configurações da conexão
		
		String nomeEsquema = "DW_LISTA_06";
		String enderecoBanco = "jdbc:mysql://localhost/" + nomeEsquema;
		String usuario = "root";
		String senha = "";
		String driverJDBC = "com.mysql.jdbc.Driver";

		try {
			Class.forName(driverJDBC);
			Connection conexao = DriverManager.getConnection(enderecoBanco, usuario, senha);
			System.out.println("Conexão aberta");
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
	
	private void CriarBancoDados(Connection conn){
		
		String sqlEndereco ="CREATE TABLE IF NOT EXISTS Endereco(\n"
		+ "idEndereco INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\n"
		+ "rua VARCHAR(60) NOT NULL,\n"
		+ "numero INT,\n"
		+ "bairro VARCHAR(30) NOT NULL,\n"
		+ "dtRemocao DATETIME)";
		
		String sqlPessoa = "CREATE TABLE IF NOT EXISTS Pessoa(\n"
		+ "idPessoa INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\n "
		+ "idEndereco INT NOT NULL,\n "
		+ "nome VARCHAR(30) NOT NULL,\n "
		+ "sobrenome VARCHAR(60),\n "
		+ "cpf VARCHAR(11),\n "
		+ "dtNascimento DATETIME,\n"
		+ "CONSTRAINT fk_endereco \n"
		+ "FOREIGN KEY(idEndereco) REFERENCES Endereco(idEndereco)\n"
		+")";
	}
}