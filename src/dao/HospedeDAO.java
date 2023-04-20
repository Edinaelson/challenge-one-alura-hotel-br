package dao;

import modelo.Hospede;

import java.sql.*;

public class HospedeDAO {
    private Connection connection;

    public HospedeDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Hospede hospede) {

        String sql = "INSERT INTO hospedes (nome, sobrenome, data_nascimento, nacionalidade, telefone, reserva_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, hospede.getNome());
            statement.setString(2, hospede.getSobrenome());
            statement.setDate(3, Date.valueOf(hospede.getDataNascimento().toLocalDate()));
            statement.setString(4, hospede.getNacionalidade());
            statement.setString(5, hospede.getTelefone());
            statement.setInt(6, hospede.getReservaId());

            statement.execute();

            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                hospede.setId(keys.getInt(1));
            }
        } catch (
                SQLException e) {
            System.out.println("Não foi possível inserir o hospede: " + e.getMessage());
        }
    }

    public Hospede buscarPorNome(String nome) {
        Hospede hospede = null;
        String sql = "SELECT * FROM hospedes WHERE nome LIKE ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                hospede = new Hospede();
                hospede.setId(rs.getInt("id"));
                hospede.setNome(rs.getString("nome"));
                hospede.setSobrenome(rs.getString("sobrenome"));
                hospede.setDataNascimento(rs.getDate("data_nascimento"));
                hospede.setNacionalidade(rs.getString("nacionalidade"));
                hospede.setTelefone(rs.getString("telefone"));
                hospede.setReservaId(rs.getInt("reserva_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hospede;
    }

    public void deletar(Integer id)  {
        try (PreparedStatement stm = connection.prepareStatement("DELETE FROM hospedes WHERE ID = ?")) {
            stm.setInt(1, id);
            stm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alterar(String nome, String sobrenome,Date nasc,String nacionalidade,String tell, Integer id) {
        try (PreparedStatement stm = connection
                .prepareStatement("UPDATE hospedes h SET h.nome = ?, h.sobrenome = ?, h.data_nascimento = ?, h.nacionalidade = ?, h.telefone = ? WHERE ID = ?")) {
            stm.setString(1, nome);
            stm.setString(2, sobrenome);
            stm.setDate(3,nasc);
            stm.setString(4,nacionalidade);
            stm.setString(5,tell);
            stm.setInt(6, id);
            stm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
