package sisFrases.View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sisFrases.Connection.SqliteConnection;

public class TelaNovaFrase extends JFrame {

	private JPanel contentPane;
	private JTextField txtNovaFrase;
	private Connection connection;
	private String novaFrase;
	private JTable tblLivros;
	private int linha;
	private String id;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaNovaFrase frame = new TelaNovaFrase();
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
	public TelaNovaFrase() {
		setTitle("Nova Frase");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFrase = new JLabel("Frase");
		lblFrase.setBounds(10, 203, 108, 14);
		contentPane.add(lblFrase);
		
		txtNovaFrase = new JTextField();
		txtNovaFrase.setColumns(10);
		txtNovaFrase.setBounds(10, 228, 231, 20);
		contentPane.add(txtNovaFrase);
		
		//SALVAR FRASE
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				novaFrase = txtNovaFrase.getText();
				try {
					connection = new SqliteConnection().dbConnector();
					String sql = "INSERT INTO Frase(fraseTexto, frase_livroId) VALUES(?, ?)";
					PreparedStatement pstmt = connection.prepareStatement(sql); 
			        pstmt.setString(1, novaFrase);
			        pstmt.setString(2, id);
			        pstmt.executeUpdate();
			        JOptionPane.showMessageDialog(null, "Frase Salva com Sucesso");
			        dispose();
			    }
				catch(Exception er) {
					JOptionPane.showMessageDialog(null, "Erro ao tentar salvar a frase: "+ er);
					dispose();
				}
			}
		});
		btnSalvar.setBounds(276, 227, 89, 23);
		contentPane.add(btnSalvar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 414, 143);
		contentPane.add(scrollPane);
		
		tblLivros = new JTable();
		scrollPane.setViewportView(tblLivros);
		
		JLabel lblNewLabel_1 = new JLabel("Selecione o livro da Frase");
		lblNewLabel_1.setBounds(10, 11, 231, 14);
		contentPane.add(lblNewLabel_1);
		
		//CARREGAR OS LIVROS NA TABELA
		try {
			connection = SqliteConnection.dbConnector();
			String query = "select * from Livro order by livroTitulo";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			String[] colunasTabela = new String[]{ "ID", "TITULO"}; //Nomes que quero visualizar no Table  
			DefaultTableModel modeloTabela = new DefaultTableModel(null,colunasTabela);

			if(rs != null) {
			    while(rs.next()) {
			        modeloTabela.addRow(new String[] {  
			                rs.getString("livroId"),  //Nomes das colunas
			                rs.getString("livroTitulo")
			            }); 
			    }
			}
			tblLivros.setModel(modeloTabela);
			rs.close();
			pst.close();
			}
			catch(Exception er) {
				JOptionPane.showMessageDialog(null, "Erro ao retornar dados do BD: "+ er);
			}
		//EVENTO DE CLIQUE NA TABELA PARA SELECIONAR O ID DO AUTOR
		tblLivros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				linha = tblLivros.getSelectedRow();
				id = tblLivros.getModel().getValueAt(linha, 0).toString();
				btnSalvar.setEnabled(true);
					
			}
		});		
	}
}
