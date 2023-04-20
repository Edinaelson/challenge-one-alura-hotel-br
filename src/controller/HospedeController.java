package controller;

import dao.HospedeDAO;
import factory.ConnectionFactory;
import modelo.Hospede;
import modelo.Reserva;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class HospedeController {

    private HospedeDAO hospedeDAO;

    public HospedeController() {
        Connection connection = new ConnectionFactory().recuperarConexao();
        this.hospedeDAO = new HospedeDAO(connection);
    }

    public void cadastrar(Hospede hospede) {
        this.hospedeDAO.salvar(hospede);
    }

    public Hospede buscarPorNome(String nome) {
        return hospedeDAO.buscarPorNome(nome);
    }

    public void deletarHospede(int id) {
        this.hospedeDAO.deletar(id);
    }

    public void alterarDadosHospede(String nome, String sobrenome, Date nasc, String nacionalidade, String tell, Integer id) {
        this.hospedeDAO.alterar(nome, sobrenome, nasc, nacionalidade, tell, id);
    }
}
