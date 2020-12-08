package br.sc.senac.perisistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.sc.senac.entidades.Pessoa;

public class PessoaDAO {
	
	private Connection conexao;
	
	public Connection getConexao() {
		return conexao;
	}

	public void setConexao(Connection conexao) {
		this.conexao = conexao;
	}
	
	public PessoaDAO () {
		conexao = ConnectionFactory.getInstance().obterConexao();
	}
	
public int salvarPessoa (Pessoa p) {
		
	int status = -1;
					
		String sql = "INSERT INTO Pessoa(nome, sobrenome, cpf, idEndereco, dtNascimento) VALUES(?, ?, ?, ?, ?)";
		try {
			
			PreparedStatement ps = this.getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, p.getNome());
			ps.setString(2, p.getSobrenome());
			ps.setString(3, p.getCpf());
			ps.setInt(4, p.getEndereco().getIdEndereco() );
			ps.setTimestamp(5, p.getDtNascimento());
			
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				status= rs.getInt(1);
			}
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		return status;
	}
	
	public boolean atualizarPessoa(Pessoa p){
		
		boolean status = false;
		
		String sql = "UPDATE Pessoa SET nome=?, sobrenome=?, cpf=?, idEndereco=? WHERE idPessoa=?";
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, p.getNome());
			ps.setString(2, p.getSobrenome());
			ps.setString(3, p.getCpf());
			ps.setInt(4, p.getEndereco().getIdEndereco());
			ps.setInt(5, p.getIdPessoa());
			int valorRetorno = ps.executeUpdate();
			if(valorRetorno ==1) {
				status=true;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return status;
		
	}
	public boolean removerPessoa(int idPessoa) {
		
		boolean status = false;
		
		String sql = "DELETE FROM Pessoa WHERE idPessoa=?";
		
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, idPessoa);
			int valorRetorno = ps.executeUpdate();
			if(valorRetorno==1) {
				status=true;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return status;
	}
	
	public Pessoa obterPessoa(int idPessoa) {
		
		Pessoa resultado = new Pessoa();
		String sql = "SELECT * FROM Pessoa WHERE idPessoa=?";
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, idPessoa);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				resultado = montarPessoa(rs);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return resultado;
	}
	
	public ArrayList<Pessoa> obterPessoas(){
		ArrayList<Pessoa> resultado = new ArrayList<Pessoa>();
		String sql = "SELECT * FROM Pessoa";
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Pessoa p = montarPessoa(rs);
				resultado.add(p);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return resultado;
	}
	
	public Pessoa obterPessoaCPF(String cpf) {
		
		Pessoa resultado = new Pessoa();
		
		String sql = "SELECT * FROM Pessoa WHERE cpf=?";
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, cpf);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				resultado = montarPessoa(rs);
			}
			System.out.println(resultado.toString());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return resultado;		
	}
	
	private Pessoa montarPessoa(ResultSet rs) {
		
		Pessoa retorno = new Pessoa();
		EnderecoDAO end = new EnderecoDAO();
		try {
			retorno.setIdPessoa(rs.getInt("idPessoa"));
			retorno.setNome(rs.getString("nome"));
			retorno.setSobrenome(rs.getString("sobrenome"));
			retorno.setCpf(rs.getString("cpf"));
			retorno.setEndereco(end.obterEndereco(rs.getInt("idPessoa")));
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	//public static void main(String[] args) {
		//PessoaDAO pdao = new PessoaDAO();
		//System.out.println(pdao.obterPessoas());
	//}
}
