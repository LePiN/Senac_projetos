package br.sc.senac.aula10.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.sc.senac.aula10.entity.Endereco;
import br.sc.senac.dao.ConnectionFactory;

public class EnderecoDAO {

	private Connection conexao;

	public static void main(String[] a) {
		// Apenas para testes!
		// EnderecoDAO dao = new EnderecoDAO();

		// int idPessoa = 4;
		// dao.remover(idPessoa);

	}

	public EnderecoDAO() {
		this.getConexao();
	}

	private void getConexao() {
		this.conexao = new ConnectionFactory().obterConexao();
	}

	public int incluir(Endereco endereco) {
		this.getConexao();
		int idInserido = 0;
		String sql = "INSERT INTO Endereco (rua, numero, bairro) VALUES (?, ?, ?)";

		try {
			// prepared statement para insercao
			PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			// seta os valores
			stmt.setString(1, endereco.getRua());
			stmt.setInt(2, endereco.getNumero());
			stmt.setString(3, endereco.getBairro());

			// executa
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();

			if (rs.next()) {
				idInserido = rs.getInt(1);
			}

			if (idInserido > 0) {
				System.out.println("Endere�o inserido com sucesso");
			} else {
				System.out.println("Erro ao inserir endere�o");
			}

			stmt.close();

			return idInserido;
		} catch (SQLException e) {
			System.out.println("Erro ao inserir endere�o: " + e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionFactory.fecharConexao(this.conexao);
		}
	}

	public boolean atualizar(Endereco endereco) {
		this.getConexao();
		boolean atualizadoSucesso = false;
		String sql = "UPDATE Endereco SET rua = ?, numero = ?, bairro = ? WHERE id = ?";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, endereco.getRua());
			stmt.setInt(2, endereco.getNumero());
			stmt.setString(3, endereco.getBairro());
			stmt.setInt(4, endereco.getId());
			int ok = stmt.executeUpdate();

			if (ok == 1) {
				System.out.println("Endere�o atualizado com sucesso");
				atualizadoSucesso = true;
			} else {
				System.out.println("Erro ao atualizar endere�o");
			}

			stmt.close();

			return atualizadoSucesso;
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar endere�o: " + e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionFactory.fecharConexao(this.conexao);
		}
	}

	public boolean remover(int idPessoa) {
		Endereco endereco = this.obter(idPessoa);

		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.remover(idPessoa);

		this.getConexao();

		boolean removidoSucesso = false;
		String sql = "DELETE FROM Endereco WHERE id = ?)";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, endereco.getId());

			int codigoRetorno = stmt.executeUpdate();
			if (codigoRetorno == 1) {
				System.out.println("Endere�o removido com sucesso");
				removidoSucesso = true;
			} else {
				System.out.println("Erro ao remover endere�o.");
			}
			stmt.close();

		} catch (SQLException e) {
			System.out.println("Erro ao remover endere�o: " + e.getMessage());
		}
		return removidoSucesso;
	}

	public Endereco obter(int idPessoa) {
		this.getConexao();
		Endereco endereco = null;
		String sql = "SELECT e.* FROM Endereco e INNER JOIN Pessoa p ON e.id = p.endereco_id WHERE p.id = ?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idPessoa);
			stmt.setMaxRows(1);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				endereco = this.obterEnderecoResultSet(rs);
			}
			stmt.close();
			return endereco;
		} catch (SQLException e) {
			System.out.println("Erro ao buscar endereço: " + e.getMessage());
			return null;
		} finally {
			ConnectionFactory.fecharConexao(this.conexao);
		}
	}

	private Endereco obterEnderecoResultSet(ResultSet rs) {
		Endereco endereco = null;
		try {
			endereco = new Endereco();
			endereco.setId(rs.getInt("id"));
			endereco.setRua(rs.getString("rua"));
			endereco.setNumero(rs.getInt("numero"));
			endereco.setBairro(rs.getString("bairro"));

			System.out.println(endereco.toString());
		} catch (SQLException e) {
			System.out.println("Erro ao obter endere�o");
		}
		return endereco;
	}

	public List<Endereco> listar() {
		this.getConexao();
		List<Endereco> enderecos = new ArrayList<Endereco>();
		String sql = "SELECT * FROM Endereco";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Endereco endereco = this.obterEnderecoResultSet(rs);
				enderecos.add(endereco);
			}
			stmt.close();
			return enderecos;
		} catch (SQLException e) {
			System.out.println("Erro ao buscar endere�os: " + e.getMessage());
			return enderecos;
		} finally {
			ConnectionFactory.fecharConexao(this.conexao);
		}
	}
}