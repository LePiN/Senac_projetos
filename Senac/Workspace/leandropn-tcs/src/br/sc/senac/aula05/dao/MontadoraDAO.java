package br.sc.senac.aula05.dao;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.sc.senac.aula05.entity.Montadora;

public class MontadoraDAO {

	private Connection conexao;
	
	public MontadoraDAO () {
		
		conexao = (Connection) ConnectionFactory.getInstance().obterConexao();
	}
	
	public int inserir(Montadora m) {
		
		int chave = -1;
		
		String SQL = "INSERT INTO MONTADORA (nome, pais) VALUES (?,?)";
		
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) this.getConexao().prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, m.getNome());
			ps.setString(2, m.getPais());
			
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return chave;
	}

	public Connection getConexao() {
		return conexao;
	}

	public void setConexao(Connection conexao) {
		this.conexao = conexao;
	}
}
