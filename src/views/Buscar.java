package views;

import controller.HospedeController;
import controller.ReservaController;
import modelo.Hospede;
import modelo.Reserva;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@SuppressWarnings("serial")
public class Buscar extends JFrame {

    private JPanel contentPane;
    private JTextField txtBuscar;
    private JTable tbHospedes;
    private JTable tbReservas;
    private DefaultTableModel modelo;
    private DefaultTableModel modeloHospedes;
    private JLabel labelAtras;
    private JLabel labelExit;
    int xMouse, yMouse;
    private ReservaController reservaController;
    private HospedeController hospedeController;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Buscar frame = new Buscar();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Buscar() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/imagenes/lOGO-50PX.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 571);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);

        this.reservaController = new ReservaController();
        this.hospedeController = new HospedeController();

        txtBuscar = new JTextField();
        txtBuscar.setBounds(536, 127, 193, 31);
        txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contentPane.add(txtBuscar);
        txtBuscar.setColumns(10);


        JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
        lblTitulo.setForeground(new Color(12, 138, 199));
        lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
        lblTitulo.setBounds(331, 62, 280, 42);
        contentPane.add(lblTitulo);

        JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
        panel.setBackground(new Color(12, 138, 199));
        panel.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel.setBounds(20, 169, 865, 328);
        contentPane.add(panel);

        tbReservas = new JTable();
        tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
        modelo = (DefaultTableModel) tbReservas.getModel();
        modelo.addColumn("Numero de Reserva");
        modelo.addColumn("Data Check In");
        modelo.addColumn("Data Check Out");
        modelo.addColumn("Valor");
        modelo.addColumn("Forma de Pagamento");
        JScrollPane scroll_table = new JScrollPane(tbReservas);
        panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("/imagenes/reservado.png")), scroll_table, null);
        scroll_table.setVisible(true);


        tbHospedes = new JTable();
        tbHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbHospedes.setFont(new Font("Roboto", Font.PLAIN, 16));
        modeloHospedes = (DefaultTableModel) tbHospedes.getModel();
        modeloHospedes.addColumn("Número de Hospede");
        modeloHospedes.addColumn("Nome");
        modeloHospedes.addColumn("Sobrenome");
        modeloHospedes.addColumn("Data de Nascimento");
        modeloHospedes.addColumn("Nacionalidade");
        modeloHospedes.addColumn("Telefone");
        modeloHospedes.addColumn("Número de Reserva");
        JScrollPane scroll_tableHuespedes = new JScrollPane(tbHospedes);
        panel.addTab("Hospedes", new ImageIcon(Buscar.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
        scroll_tableHuespedes.setVisible(true);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(Buscar.class.getResource("/imagenes/Ha-100px.png")));
        lblNewLabel_2.setBounds(56, 51, 104, 107);
        contentPane.add(lblNewLabel_2);

        JPanel header = new JPanel();
        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                headerMouseDragged(e);
            }
        });
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                headerMousePressed(e);
            }
        });
        header.setLayout(null);
        header.setBackground(Color.WHITE);
        header.setBounds(0, 0, 910, 36);
        contentPane.add(header);

        JPanel btnAtras = new JPanel();
        btnAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAtras.setBackground(new Color(12, 138, 199));
                labelAtras.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAtras.setBackground(Color.white);
                labelAtras.setForeground(Color.black);
            }
        });
        btnAtras.setLayout(null);
        btnAtras.setBackground(Color.WHITE);
        btnAtras.setBounds(0, 0, 53, 36);
        header.add(btnAtras);

        labelAtras = new JLabel("<");
        labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
        labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
        labelAtras.setBounds(0, 0, 53, 36);
        btnAtras.add(labelAtras);

        JPanel btnexit = new JPanel();
        btnexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) { // Quando o usuário passa o mouse sobre o botão, ele muda de cor
                btnexit.setBackground(Color.red);
                labelExit.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) { //Quando o usuário remove o mouse do botão, ele retornará ao estado original
                btnexit.setBackground(Color.white);
                labelExit.setForeground(Color.black);
            }
        });
        btnexit.setLayout(null);
        btnexit.setBackground(Color.WHITE);
        btnexit.setBounds(857, 0, 53, 36);
        header.add(btnexit);

        labelExit = new JLabel("X");
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setForeground(Color.BLACK);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setForeground(new Color(12, 138, 199));
        separator_1_2.setBackground(new Color(12, 138, 199));
        separator_1_2.setBounds(539, 159, 193, 2);
        contentPane.add(separator_1_2);

        JPanel btnbuscar = new JPanel();
        btnbuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!txtBuscar.getText().isBlank()) {
                    try {
                        int idReserva = Integer.parseInt(txtBuscar.getText());
                        buscarReservaPorId(idReserva);
                    } catch (NumberFormatException ex) {
                        buscarHospedePorNome(txtBuscar.getText());
                    }
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Campo de busca não pode ser vazio. Digite um Nome ou número de Reserva válido.");
                }
            }
        });
        btnbuscar.setLayout(null);
        btnbuscar.setBackground(new Color(12, 138, 199));
        btnbuscar.setBounds(748, 125, 122, 35);
        btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnbuscar);

        JLabel lblBuscar = new JLabel("BUSCAR");
        lblBuscar.setBounds(0, 0, 122, 35);
        btnbuscar.add(lblBuscar);
        lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
        lblBuscar.setForeground(Color.WHITE);
        lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));


        JPanel btnEditar = new JPanel();
        btnEditar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!modeloHospedes.getDataVector().isEmpty()) {
                    try {
                        editarHospede();
                        JOptionPane.showMessageDialog(contentPane,"Dados do usuário alterados com sucesso.");
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (!modelo.getDataVector().isEmpty()) {
                    try {
                        editarReserva();
                        JOptionPane.showMessageDialog(contentPane,"Dados da reserva alterados com sucesso.");
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Nenhum dado selecionado. Selecione o ID para alterar.");
                }

            }
        });
        btnEditar.setLayout(null);
        btnEditar.setBackground(new Color(12, 138, 199));
        btnEditar.setBounds(635, 508, 122, 35);
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEditar);

        JLabel lblEditar = new JLabel("EDITAR");
        lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEditar.setForeground(Color.WHITE);
        lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEditar.setBounds(0, 0, 122, 35);
        btnEditar.add(lblEditar);


        JPanel btnDeletar = new JPanel();
        btnDeletar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!modeloHospedes.getDataVector().isEmpty()) {
                    deletarHospede();
                } else if (!modelo.getDataVector().isEmpty()) {
                    deletarReserva();
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Nenhum dado selecionado. Selecione o ID para Deletar");
                }
            }
        });
        btnDeletar.setLayout(null);
        btnDeletar.setBackground(new Color(12, 138, 199));
        btnDeletar.setBounds(767, 508, 122, 35);
        btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnDeletar);

        JLabel lblExcluir = new JLabel("DELETAR");
        lblExcluir.setHorizontalAlignment(SwingConstants.CENTER);
        lblExcluir.setForeground(Color.WHITE);
        lblExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblExcluir.setBounds(0, 0, 122, 35);
        btnDeletar.add(lblExcluir);
        setResizable(false);
    }

    //Código que permite movimentar a janela pela tela seguindo a posição de "x" e "y"
    private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }

    private void buscarHospedePorNome(String nome) {
        Hospede h = hospedeController.buscarPorNome(nome);
        if (h != null) {
            modeloHospedes.addRow(new Object[]{h.getId()
                    , h.getNome()
                    , h.getSobrenome()
                    , h.getDataNascimento()
                    , h.getNacionalidade()
                    , h.getTelefone()
                    , h.getReservaId()});
        } else {
            JOptionPane.showMessageDialog(contentPane, "Usuário não existe. Digite um usuário válido");
        }
    }

    public void buscarReservaPorId(int idReserva) {
        Reserva r = reservaController.buscarPorId(idReserva);
        if (r != null) {
            modelo.addRow(new Object[]{r.getId()
                    , r.getDataEntrada()
                    , r.getDataSaida()
                    , r.getValor()
                    , r.getFormaPagamento()});
        } else {
            JOptionPane.showMessageDialog(contentPane, "Reserva não existe. Digite um reserva válida");
        }
    }

    private void editarHospede() throws ParseException {
        Object objetoDaLinha = (Object) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), tbHospedes.getSelectedColumn());
        if (objetoDaLinha instanceof Integer id) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String nome = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 1);
            String sobrenome = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 2);
            java.util.Date dataNascString = sdf.parse(modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 3).toString());
            java.sql.Date dataNasc = new java.sql.Date(dataNascString.getTime());
            String nacionalidade = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 4);
            String tell = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 5);
            this.hospedeController.alterarDadosHospede(nome, sobrenome, dataNasc, nacionalidade, tell, id);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
        }
    }

    private void editarReserva() throws ParseException {
        Object objetoDaLinha = (Object) modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn());
        if (objetoDaLinha instanceof Integer id) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dataES = sdf.parse(modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString());
            java.sql.Date dataE = new java.sql.Date(dataES.getTime());
            java.util.Date dataSS = sdf.parse(modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString());
            java.sql.Date dataS = new java.sql.Date(dataSS.getTime());
            String valorString = modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString();
            BigDecimal valor = new BigDecimal(valorString);
            String formaPag = modelo.getValueAt(tbReservas.getSelectedRow(), 4).toString();
            this.reservaController.alterarReserva(dataE, dataS, valor, formaPag, id);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
        }
    }

    private void deletarHospede() {
        Object objetoDaLinha = (Object) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), tbHospedes.getSelectedColumn());
        if (objetoDaLinha instanceof Integer id) {
            this.hospedeController.deletarHospede(id);
            modeloHospedes.removeRow(tbHospedes.getSelectedRow());
            JOptionPane.showMessageDialog(this, "Item excluido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
        }
    }

    private void deletarReserva() {
        Object objetoDaLinha = (Object) modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn());
        if (objetoDaLinha instanceof Integer id) {
            reservaController.deletarReserva(id);
            modelo.removeRow(tbReservas.getSelectedRow());
            JOptionPane.showMessageDialog(this, "Item excluido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
        }
    }

}
