package br.sc.senac.aula10.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.sc.senac.aula10.entity.Endereco;
import br.sc.senac.aula10.entity.Pessoa;
import br.sc.senac.dao.ConnectionFactory;

public class PessoaDAO {

	private Connection conexao;

	public PessoaDAO() {
		this.getConexao();
	}

	private void getConexao() {
		this.conexao = new ConnectionFactory().obterConexao();
	}

	public int incluir(Pessoa pessoa) {
		this.getConexao();
		int idInserido = 0;
		int idEndereco = 0;
		String sql = "INSERT INTO Pessoa (nome, sobrenome, endereco_id) VALUES (?, ?, ?)";

		if (pessoa.getEndereco() != null) {
			Endereco endereco = pessoa.getEndereco();
			EnderecoDAO enderecoDAO = new EnderecoDAO();

			idEndereco = enderecoDAO.incluir(endereco);
			if (idEndereco == 0) {
				return 0;
			}
		}

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// seta os valores
			stmt.setString(1, pessoa.getNome());
			stmt.setString(2, pessoa.getSobrenome());
			stmt.setInt(3, idEndereco);
			// executa
			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();

			if (rs.next()) {
				idInserido = rs.getInt(1);
			}

			if (idInserido > 0) {
				System.out.println("Pessoa inserida com sucesso");
			} else {
				System.out.println("Erro ao inserir pessoa");
			}

			stmt.close();

			return idInserido;
		} catch (SQLException e) {
			System.out.println("Erro ao inserir pessoa: " + e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionFactory.fecharConexao(this.conexao);
		}
	}

	public boolean atualizar(Pessoa pessoa) {
		this.getConexao();
		boolean atualizadoSucesso = false;
		String sql = "UPDATE Pessoa SET nome = ?, sobrenome = ?, endereco_id = ? WHERE id = ?";

		Endereco endereco = pessoa.getEndereco();
		EnderecoDAO enderecoDAO = new EnderecoDAO();

		if (enderecoDAO.atualizar(endereco) == false) {
			return false;
		}

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, pessoa.getNome());
			stmt.setString(2, pessoa.getSobrenome());
			stmt.setInt(3, pessoa.getEndereco().getId());
			stmt.setInt(4, pessoa.getId());

			// executa
			int ok = stmt.executeUpdate();

			if (ok == 1) {
				System.out.println("Pessoa atualizada com sucesso");
				atualizadoSucesso = true;
			} else {
				System.out.println("Erro ao atualizar Pessoa");
			}

			stmt.close();

			return atualizadoSucesso;
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar Pessoa. " + e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionFactory.fecharConexao(this.conexao);
		}
	}

	public boolean remover(int cdPessoa) {
		this.getConexao();

		boolean removidoSucesso = false;
		String sql = "DELETE FROM Pessoa WHERE id = ?";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, cdPessoa);
			int ok = stmt.executeUpdate();

			if (ok == 1) {
				System.out.println("Pessoa removida com sucesso");
				removidoSucesso = true;
			} else {
				System.out.println("Erro ao remover pessoa");
			}

			stmt.close();
			return removidoSucesso;
		} catch (SQLException e) {
			System.out.println("Erro ao remover Pessoa: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}
	
	public Pessoa obterPessoa(String login, String senha) {
		this.getConexao();
		Pessoa pessoa = null;

		String sql = "SELECT * FROM Pessoa WHERE login = ? AND senha = ?";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setMaxRows(1);
			stmt.setString(1, login);
			stmt.setString(2, senha);
			// executa
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				pessoa = new Pessoa();
				pessoa.setId(rs.getInt("id"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setSobrenome(rs.getString("sobrenome"));
				pessoa.setTipoPermissao(rs.getInt("tipo_permissao"));
				
				System.out.println("PESSOA: " + pessoa.getId());
			}
			
			if(pessoa != null){
				EnderecoDAO enderecoDAO = new EnderecoDAO();
				Endereco endereco = enderecoDAO.obter(pessoa.getId());
				pessoa.setEndereco(endereco);
			}

			stmt.close();
			return pessoa;

		} catch (SQLException e) {
			System.out.println("Erro ao obter pessoa: " + e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			//ConnectionFactory.fecharConexao(this.conexao);
		}
	}

	public Pessoa obterPessoa(int cdPessoa) {
		this.getConexao();
		Pessoa pessoa = null;

		String sql = "SELECT * FROM Pessoa WHERE id = ?  LIMIT 1";

		EnderecoDAO enderecoDAO = new EnderecoDAO();
		Endereco endereco = enderecoDAO.obter(cdPessoa);
		System.out.println("ENDERECO: " + endereco.getId() + " :: " + endereco.getRua());

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setMaxRows(1);
			stmt.setInt(1, cdPessoa);
			// executa
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				pessoa = new Pessoa();
				pessoa.setId(rs.getInt("id"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setSobrenome(rs.getString("sobrenome"));
				pessoa.setEndereco(endereco);
				System.out.println("PESSOA: " + pessoa.getId() + " :: " + pessoa.getEndereco().getRua());
			}

			stmt.close();
			return pessoa;

		} catch (SQLException e) {
			System.out.println("Erro ao obter pessoa: " + e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			ConnectionFactory.fecharConexao(this.conexao);
		}
	}

	public List<Pessoa> listar() {
		this.getConexao();
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		String sql = "SELECT * FROM Pessoa";
		try {

			PreparedStatement stmt = conexao.prepareStatement(sql);

			EnderecoDAO enderecoDAO = new EnderecoDAO();
			// executa
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Pessoa pessoa = obterEnderecoResultSet(rs);
				// Consulta o endere�o a partir do id da pessoa
				Endereco e = enderecoDAO.obter(pessoa.getId());
				pessoa.setEndereco(e);
				pessoas.add(pessoa);
			}

			stmt.close();
			return pessoas;
		} catch (SQLException e) {
			System.out.println("Erro ao listar pessoas: " + e.getMessage());
			return pessoas;
		} finally {
			ConnectionFactory.fecharConexao(this.conexao);
		}
	}

	private Pessoa obterEnderecoResultSet(ResultSet rs) {
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		Pessoa pessoa = new Pessoa();
		try {
			pessoa.setId(rs.getInt("id"));
			pessoa.setNome(rs.getString("nome"));
			pessoa.setSobrenome(rs.getString("sobrenome"));
			pessoa.setEndereco(enderecoDAO.obter(pessoa.getId()));

			System.out.println(pessoa.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pessoa;
	}
}
