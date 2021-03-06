package sisFrases.View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sisFrases.Connection.SqliteConnection;
import javax.swing.JLabel;

public class TelaNovoAutor extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomeAutor;
	private Connection connection;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaNovoAutor frame = new TelaNovoAutor();
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
	public TelaNovoAutor() {
		setTitle("Novo Autor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNomeAutor = new JTextField();
		txtNomeAutor.setBounds(10, 46, 231, 20);
		contentPane.add(txtNomeAutor);
		txtNomeAutor.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		
		//SALVAR AUTOR
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String novoAutor = txtNomeAutor.getText();
				try {
					connection = new SqliteConnection().dbConnector();
					String sql = "INSERT INTO autor(autorNome) VALUES(?)";
					PreparedStatement pstmt = connection.prepareStatement(sql); 
			        pstmt.setString(1, novoAutor);
			        pstmt.executeUpdate();
			        JOptionPane.showMessageDialog(null, "Autor Salvo com Sucesso");
			        dispose();
			    }
				catch(Exception er) {
					JOptionPane.showMessageDialog(null, "Erro ao tentar salvar o autor: "+ er);
					dispose();
				}
				
			}
		});
		btnSalvar.setBounds(276, 45, 89, 23);
		contentPane.add(btnSalvar);
		
		lblNewLabel = new JLabel("Nome do autor");
		lblNewLabel.setBounds(10, 21, 108, 14);
		contentPane.add(lblNewLabel);
	}
}
