package views;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

@SuppressWarnings("serial")
public class MenuPrincipal extends JFrame {

	private JPanel contentPane;
	private JLabel labelExit;
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
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
	public MenuPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/imagens/aH-40px.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 910, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);

		
		Panel painel = new Panel();
		painel.setBackground(SystemColor.window);
		painel.setBounds(0, 0, 910, 537);
		contentPane.add(painel);
		painel.setLayout(null);
		
		JLabel imagemFundo = new JLabel("");
		imagemFundo.setBounds(-50, 0, 732, 501);
		imagemFundo.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagens/menu-img.png")));
		painel.add(imagemFundo);
		
		JLabel logo = new JLabel("");
		logo.setBounds(722, 80, 150, 156);
		logo.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagens/aH-150px.png")));
		painel.add(logo);
		
		JPanel painel_1 = new JPanel();
		painel_1.setBounds(0, 500, 910, 37);
		painel_1.setBackground(new Color(12, 138, 199));
		painel.add(painel_1);
		painel_1.setLayout(null);
		
		JLabel lblCopyR = new JLabel("Desenvolvido por Amilton José © 2023");
		lblCopyR.setBounds(315, 11, 301, 19);
		lblCopyR.setForeground(new Color(240, 248, 255));
		lblCopyR.setFont(new Font("Roboto", Font.PLAIN, 16));
		painel_1.add(lblCopyR);
		
		//Barra para controlar la ventana 
		JPanel header = new JPanel();
		header.setBounds(0, 0, 910, 36);
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
		painel.add(header);
		
		//Botão para sair
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		//Botão de Login
		JPanel btnLogin = new JPanel(); 
		btnLogin.setBounds(754, 300, 83, 70);
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
		btnLogin.setLayout(null);
		btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnLogin.setBackground(SystemColor.window);
		painel.add(btnLogin);
		
		JLabel imageLogin = new JLabel("");
		imageLogin.setBounds(0, 0, 80, 70);
		btnLogin.add(imageLogin);
		imageLogin.setHorizontalAlignment(SwingConstants.CENTER);
		imageLogin.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagens/login.png")));
		
		JLabel lblTitulo = new JLabel("LOGIN");
		lblTitulo.setBounds(754, 265, 83, 24);
		lblTitulo.setBackground(SystemColor.window);
		painel.add(lblTitulo);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(SystemColor.controlDkShadow);
		lblTitulo.setFont(new Font("Roboto Light", Font.PLAIN, 20));


		JPanel btnSair = new JPanel();
		btnSair.setBounds(764, 410, 83, 70);
		btnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int opcao = JOptionPane.showConfirmDialog(contentPane, "Deseja continuar?");
				if (opcao == 1) {
					System.exit(0);
				}
			}
		});
		btnSair.setLayout(null);
		btnSair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnSair.setBackground(SystemColor.window);
		painel.add(btnSair);

		JLabel imageSair = new JLabel("");
		imageSair.setBounds(0, 0, 80, 70);
		btnSair.add(imageSair);
		imageSair.setHorizontalAlignment(SwingConstants.CENTER);
		//imageSair.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagens/Sair.png")));
		
	}
	
	//Código que permite movimentar a janela pela tela seguindo a posição de "x" y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }
    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
}
}
