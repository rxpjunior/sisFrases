package sisFrases.View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
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

public class TelaAutor extends JFrame {

	private JPanel contentPane;
	private JTable tblAutores;
	private JTextField txtNovoNomeAutor;
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
					TelaAutor frame = new TelaAutor();
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
	public TelaAutor() {
		setTitle("Autores");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAutoresImg = new JLabel("");
		Image img1 = new ImageIcon(this.getClass().getResource("/autor.png")).getImage();
	    lblAutoresImg.setIcon(new ImageIcon(img1));
		lblAutoresImg.setBounds(369, 11, 55, 52);
		contentPane.add(lblAutoresImg);
		
		JButton btnNovoAutor = new JButton("Novo Autor");
		btnNovoAutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaNovoAutor telaNovoAutor = new TelaNovoAutor();
				telaNovoAutor.setVisible(true);
			}
		});
		btnNovoAutor.setBounds(10, 11, 105, 23);
		contentPane.add(btnNovoAutor);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 79, 414, 84);
		contentPane.add(scrollPane);
		
		tblAutores = new JTable();
		scrollPane.setViewportView(tblAutores);
		
		JButton btnAutores = new JButton("Autores");
		
		//CARREGA OS AUTORES CADASTRADOS
		btnAutores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
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
				
			}
		});
		btnAutores.setBounds(10, 45, 105, 23);
		contentPane.add(btnAutores);
		
		JLabel lblNewLabel = new JLabel("Novo Nome dos Autor");
		lblNewLabel.setBounds(10, 177, 122, 14);
		contentPane.add(lblNewLabel);
		
		txtNovoNomeAutor = new JTextField();
		txtNovoNomeAutor.setEditable(false);
		txtNovoNomeAutor.setEnabled(false);
		txtNovoNomeAutor.setBounds(10, 202, 155, 20);
		contentPane.add(txtNovoNomeAutor);
		txtNovoNomeAutor.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setEnabled(false);
		
		// SALVAR AS ALTERAÇÕES FEITAS NO NOME DO AUTOR
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "update autor set autorNome = ? where autorId = ?";
					PreparedStatement pstmt = connection.prepareStatement(sql); 
			        pstmt.setString(1, txtNovoNomeAutor.getText());
			        pstmt.setString(2, id);
			        pstmt.executeUpdate();
			        JOptionPane.showMessageDialog(null, "Autor atualizado com sucesso");
			        dispose();
				}
				catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Erro ao tentar atualizar o autor: "+ e2);
				}
			}
		});
		btnSalvar.setBounds(10, 233, 105, 23);
		contentPane.add(btnSalvar);
		
		JButton btnApagarAutor = new JButton("Apagar Autor");
		btnApagarAutor.setEnabled(false);
		
		// DELETAR AUTOR
		btnApagarAutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "delete from autor where autorId = ?";
					PreparedStatement pstmt = connection.prepareStatement(sql); 
			        pstmt.setString(1, id);
			        pstmt.execute();
			        JOptionPane.showMessageDialog(null, "Autor excluído com sucesso");
			        dispose();
				}
				catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Erro ao tentar deletar o autor: "+ e2);
				}
			}
		});
		btnApagarAutor.setForeground(Color.RED);
		btnApagarAutor.setBounds(296, 201, 128, 23);
		contentPane.add(btnApagarAutor);
		
		//EVENTO DE CLIQUE NA TABELA PARA SELECIONAR O ID DO AUTOR
		tblAutores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				linha = tblAutores.getSelectedRow();
				txtNovoNomeAutor.setText(tblAutores.getModel().getValueAt(linha, 1).toString());
				id = tblAutores.getModel().getValueAt(linha, 0).toString();
				btnApagarAutor.setEnabled(true);
				btnSalvar.setEnabled(true);
				txtNovoNomeAutor.setEnabled(true);
				txtNovoNomeAutor.setEditable(true);
			}
		});
	}
}
