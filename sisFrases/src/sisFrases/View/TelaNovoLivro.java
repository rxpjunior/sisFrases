package sisFrases.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sisFrases.Connection.SqliteConnection;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class TelaNovoLivro extends JFrame {

	private JPanel contentPane;
	private JTextField txtTituloLivro;
	private Connection connection;
	private JLabel lblNewLabel;
	private JTable tblAutores;
	private JLabel lblNewLabel_1;
	private JScrollPane scrollPane;
	private int linha;
	private String id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaNovoLivro frame = new TelaNovoLivro();
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
	public TelaNovoLivro() {
		setTitle("Novo Livro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtTituloLivro = new JTextField();
		txtTituloLivro.setColumns(10);
		txtTituloLivro.setBounds(10, 228, 231, 20);
		contentPane.add(txtTituloLivro);
		
		
		
		//SALVAR LIVRO
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setEnabled(false);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String novoAutor = txtTituloLivro.getText();
				try {
					connection = new SqliteConnection().dbConnector();
					String sql = "INSERT INTO Livro(livroTitulo, livro_autorId) VALUES(?, ?)";
					PreparedStatement pstmt = connection.prepareStatement(sql); 
			        pstmt.setString(1, novoAutor);
			        pstmt.setString(2, id);
			        pstmt.executeUpdate();
			        JOptionPane.showMessageDialog(null, "Livro Salvo com Sucesso");
			        dispose();
			    }
				catch(Exception er) {
					JOptionPane.showMessageDialog(null, "Erro ao tentar salvar o livro: "+ er);
					dispose();
				}
			}
		});
		btnSalvar.setBounds(276, 227, 89, 23);
		contentPane.add(btnSalvar);
		
		lblNewLabel = new JLabel("T\u00EDtulo de Livro");
		lblNewLabel.setBounds(10, 203, 97, 14);
		contentPane.add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 38, 414, 140);
		contentPane.add(scrollPane);
		
		tblAutores = new JTable();
		scrollPane.setViewportView(tblAutores);
		
		lblNewLabel_1 = new JLabel("Selecione o autor do Livro");
		lblNewLabel_1.setBounds(10, 11, 231, 14);
		contentPane.add(lblNewLabel_1);
		
		//CARREGAR OS AUTORES NA TABELA
		try {
			connection = SqliteConnection.dbConnector();
			String query = "select * from autor order by autorNome";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			String[] colunasTabela = new String[]{ "ID", "NOME"}; //Nomes que quero visualizar no Table  
			DefaultTableModel modeloTabela = new DefaultTableModel(null,colunasTabela);

			if(rs != null) {
			    while(rs.next()) {
			        modeloTabela.addRow(new String[] {  
			                rs.getString("autorId"),  //Nomes das colunas
			                rs.getString("autorNome")
			            }); 
			    }
			}
			tblAutores.setModel(modeloTabela);
			rs.close();
			pst.close();
			}
			catch(Exception er) {
				JOptionPane.showMessageDialog(null, "Erro ao retornar dados do BD: "+ er);
			}
		
		//EVENTO DE CLIQUE NA TABELA PARA SELECIONAR O ID DO AUTOR
			tblAutores.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					linha = tblAutores.getSelectedRow();
					id = tblAutores.getModel().getValueAt(linha, 0).toString();
					btnSalvar.setEnabled(true);
						
				}
			});		
	}
}
