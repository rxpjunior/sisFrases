package sisFrases.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sisFrases.Connection.SqliteConnection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class TelaLivro extends JFrame {

	private JPanel contentPane;
	private JTable tblLivros;
	private JTextField txtNovoNomeLivro;
	private Connection connection;
	private int linha;
	private String id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLivro frame = new TelaLivro();
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
	public TelaLivro() {
		setTitle("Livros");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNovoLivro = new JButton("Novo Livro");
		btnNovoLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaNovoLivro telaNovoLivro = new TelaNovoLivro();
				telaNovoLivro.setVisible(true);
			}
		});
		btnNovoLivro.setBounds(10, 11, 105, 23);
		contentPane.add(btnNovoLivro);
		
		JButton btnLivros = new JButton("Livros");
		btnLivros.addActionListener(new ActionListener() {
			
			//CARREGA OS LIVROS CADASTRADOS
			public void actionPerformed(ActionEvent arg0) {
				try {
					connection = SqliteConnection.dbConnector();
					String query = "select * from livro order by livroTitulo";
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
			}
		});
		btnLivros.setBounds(10, 45, 105, 23);
		contentPane.add(btnLivros);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 79, 414, 82);
		contentPane.add(scrollPane);
		
		tblLivros = new JTable();
		scrollPane.setViewportView(tblLivros);
		
		JLabel lblNovoNomeDo = new JLabel("Novo Nome do Livro");
		lblNovoNomeDo.setBounds(10, 171, 122, 14);
		contentPane.add(lblNovoNomeDo);
		
		txtNovoNomeLivro = new JTextField();
		txtNovoNomeLivro.setEnabled(false);
		txtNovoNomeLivro.setEditable(false);
		txtNovoNomeLivro.setColumns(10);
		txtNovoNomeLivro.setBounds(10, 196, 155, 20);
		contentPane.add(txtNovoNomeLivro);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			
			// SALVAR AS ALTERA??ES FEITAS NO NOME DO LIVRO
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "update livro set livroTitulo = ? where livroId = ?";
					PreparedStatement pstmt = connection.prepareStatement(sql); 
			        pstmt.setString(1, txtNovoNomeLivro.getText());
			        pstmt.setString(2, id);
			        pstmt.executeUpdate();
			        JOptionPane.showMessageDialog(null, "livro atualizado com sucesso");
			        dispose();
				}
				catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Erro ao tentar atualizar o livro: "+ e2);
				}
			}
		});
		btnSalvar.setEnabled(false);
		btnSalvar.setBounds(10, 227, 105, 23);
		contentPane.add(btnSalvar);
		
		// DELETAR LIVRO
		JButton btnApagarLivro = new JButton("Apagar Livro");
		btnApagarLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "delete from livro where livroId = ?";
					PreparedStatement pstmt = connection.prepareStatement(sql); 
			        pstmt.setString(1, id);
			        pstmt.execute();
			        JOptionPane.showMessageDialog(null, "livro exclu?do com sucesso");
			        dispose();
				}
				catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Erro ao tentar deletar o livro: "+ e2);
				}
			}
		});
		btnApagarLivro.setForeground(Color.RED);
		btnApagarLivro.setEnabled(false);
		btnApagarLivro.setBounds(296, 195, 128, 23);
		contentPane.add(btnApagarLivro);
		
		JLabel lblAutoresImg = new JLabel("");
		Image img1 = new ImageIcon(this.getClass().getResource("/livro.png")).getImage();
	    lblAutoresImg.setIcon(new ImageIcon(img1));
		lblAutoresImg.setBounds(369, 11, 55, 52);
		contentPane.add(lblAutoresImg);
		
		//EVENTO DE CLIQUE NA TABELA PARA SELECIONAR O ID DO LIVRO
				tblLivros.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						linha = tblLivros.getSelectedRow();
						txtNovoNomeLivro.setText(tblLivros.getModel().getValueAt(linha, 1).toString());
						id = tblLivros.getModel().getValueAt(linha, 0).toString();
						btnApagarLivro.setEnabled(true);
						btnSalvar.setEnabled(true);
						txtNovoNomeLivro.setEnabled(true);
						txtNovoNomeLivro.setEditable(true);
					}
				});
	}
}
