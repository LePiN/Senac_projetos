package br.sc.senac.perisistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.sc.senac.entidades.Montadora;


public class MontadoraDAO {

	private Connection conexao;

	public MontadoraDAO() {
		conexao = ConnectionFactory.getInstance().obterConexao();
	}

	public int inserir(Montadora m) {
		int idInserido = -1;
		String sql = "INSERT INTO MONTADORA(NOME, PAIS) VALUES (?, ?)";
		try {
			PreparedStatement ps = this.getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			// Preenche a consulta com os atributos do objeto
			ps.setString(1, m.getNome());
			ps.setString(2, m.getPais());
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				idInserido = rs.getInt(1);
			}

			if (idInserido > 0) {
				System.out.println("Montadora inserida com sucesso");
			} else {
				System.out.println("Erro ao inserir montadora");
			}

			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return idInserido;
	}

	public boolean excluir(int id) {
		boolean removidoSucesso = false;
		String sql = "DELETE FROM MONTADORA WHERE idMontadora = ?";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, id);

			int codigoRetorno = ps.executeUpdate();
			if (codigoRetorno == 1) {
				System.out.println("Montadora removida com sucesso");
				removidoSucesso = true;
			} else {
				System.out.println("Erro ao remover montadora.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return removidoSucesso;
	}

	public boolean atualizar(Montadora m) {
		boolean atualizadoSucesso = false;
		String sql = "UPDATE MONTADORA SET NOME=?, PAIS=? WHERE idMontadora=?";

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
			instrucaoSQL.setString(1, m.getNome());
			instrucaoSQL.setString(2, m.getPais());
			instrucaoSQL.setInt(3, m.getId());

			int codigoRetorno = instrucaoSQL.executeUpdate();
			if (codigoRetorno == 1) {
				System.out.println("Montadora atualizada com sucesso");
				atualizadoSucesso = true;
			} else {
				System.out.println("Erro ao atualizar montadora.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return atualizadoSucesso;
	}

	public Montadora obterPorId(int idMontadora) {
		Montadora m = null;

		String sql = " SELECT * FROM MONTADORA WHERE idMontadora=? LIMIT 1";

		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idMontadora);
			//stmt.setMaxRows(1);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				m = this.criarMontadoraResultSet(rs);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return m;
	}

	public ArrayList<Montadora> listar() {
		ArrayList<Montadora> montadoras = new ArrayList<>();

		String sql = "SELECT * FROM MONTADORA";

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
			ResultSet resultadoConsulta = instrucaoSQL.executeQuery();

			while (resultadoConsulta.next()) {
				// Cria uma nova montadora para cada item retornado no resultSet
				// Usa como chave o nome da coluna na tabela
				Montadora m = criarMontadoraResultSet(resultadoConsulta);
				montadoras.add(m);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return montadoras;
	}

	public ArrayList<Montadora> listarPorPais(String paisMontadora) {
		ArrayList<Montadora> montadoras = new ArrayList<>();

		String sql = "SELECT * FROM MONTADORA WHERE PAIS=?";

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
			instrucaoSQL.setString(1, paisMontadora);

			ResultSet resultadoConsulta = instrucaoSQL.executeQuery();

			while (resultadoConsulta.next()) {
				// Cria uma nova montadora para cada item retornado no resultSet
				// Usa como chave o nome da coluna na tabela
				Montadora m = criarMontadoraResultSet(resultadoConsulta);
				montadoras.add(m);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return montadoras;
	}

	private Montadora criarMontadoraResultSet(ResultSet resultadoConsulta) {
		Montadora m = null;

		try {
			m = new Montadora();
			m.setId(resultadoConsulta.getInt("idMontadora"));
			m.setNome(resultadoConsulta.getString("nomeMontadora"));
			m.setPais(resultadoConsulta.getString("pais"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return m;
	}

	public Connection getConexao() {
		return conexao;
	}

	public void setConexao(Connection conexao) {
		this.conexao = conexao;
	}

	public Montadora obterPorNome(String nome) {
		Montadora m = null;

		String sql = " SELECT * FROM MONTADORA WHERE nomeMontadora=? LIMIT 1";

		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, nome);
			//stmt.setMaxRows(1);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				m = this.criarMontadoraResultSet(rs);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return m;
	}
}