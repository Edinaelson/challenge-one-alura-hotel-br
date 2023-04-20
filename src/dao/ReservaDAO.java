package dao;

import modelo.Reserva;

import java.math.BigDecimal;
import java.sql.*;

public class ReservaDAO {

    private Connection connection;

    public ReservaDAO(Connection connection) {
        this.connection = connection;
    }

    public void cadastrar(Reserva reserva) {

        String sql = "INSERT INTO reservas (data_entrada, data_saida, valor, forma_pagamento) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDate(1, Date.valueOf(reserva.getDataEntrada().toLocalDate()));
            stmt.setDate(2, Date.valueOf(reserva.getDataSaida().toLocalDate()));
            stmt.setBigDecimal(3, reserva.getValor());
            stmt.setString(4, reserva.getFormaPagamento());

            stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                reserva.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("Não foi possível inserir a reserva: " + e.getMessage());
        }
    }

    public Reserva buscarReservaPorId(int id) {
        String sql = "SELECT * FROM reservas WHERE id = ?";
        Reserva reserva = null;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    reserva = new Reserva();
                    reserva.setId(rs.getInt("id"));
                    reserva.setDataEntrada(rs.getDate("data_entrada"));
                    reserva.setDataSaida(rs.getDate("data_saida"));
                    reserva.setValor(rs.getBigDecimal("valor"));
                    reserva.setFormaPagamento(rs.getString("forma_pagamento"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar reserva por ID: " + e.getMessage());
        }
        return reserva;
    }

    public void deletar(Integer id)  {
        try (PreparedStatement stm = connection.prepareStatement("DELETE FROM reservas WHERE ID = ?")) {
            stm.setInt(1, id);
            stm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alterar(Date dataE, Date dataS, BigDecimal valor, String formPagamento, Integer id) {
        try (PreparedStatement stm = connection
                .prepareStatement("UPDATE reservas r SET r.data_entrada = ?, r.data_saida = ?, valor = ?, forma_pagamento = ? WHERE ID = ?")) {
            stm.setDate(1, dataE);
            stm.setDate(2, dataS);
            stm.setBigDecimal(3, valor);
            stm.setString(4, formPagamento);
            stm.setInt(5, id);
            stm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
