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

public class TelaFrase extends JFrame {

	private JPanel contentPane;
	private JTextField txtFraseAlterada;
	private JTable tblFrases;
	private int linha;
	private String id;
	private Connection connection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaFrase frame = new TelaFrase();
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
	public TelaFrase() {
		setTitle("Frases");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNovaFrase = new JButton("Nova Frase");
		btnNovaFrase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaNovaFrase telaNovaFrase = new TelaNovaFrase();
				telaNovaFrase.setVisible(true);
			}
		});
		btnNovaFrase.setBounds(10, 11, 105, 23);
		contentPane.add(btnNovaFrase);
		
		//Carrega as frases na tabela
		JButton btnFrases = new JButton("Frases");
		btnFrases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connection = SqliteConnection.dbConnector();
					String query = "select * from frase order by fraseId";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					String[] colunasTabela = new String[]{ "ID", "Frase"}; //Nomes que quero visualizar no Table  
					DefaultTableModel modeloTabela = new DefaultTableModel(null,colunasTabela);

					if(rs != null) {
					    while(rs.next()) {
					        modeloTabela.addRow(new String[] {  
					                rs.getString("fraseId"),  //Nomes das colunas
					                rs.getString("fraseTexto")
					            }); 
					    }
					}
					tblFrases.setModel(modeloTabela);
					rs.close();
					pst.close();
					}
					catch(Exception er) {
						JOptionPane.showMessageDialog(null, "Erro ao retornar dados do BD: "+ er);
					}
			}
		});
		btnFrases.setBounds(10, 45, 105, 23);
		contentPane.add(btnFrases);
		
		JLabel lblFraseAlterada = new JLabel("Frase alterada");
		lblFraseAlterada.setBounds(10, 177, 122, 14);
		contentPane.add(lblFraseAlterada);
		
		txtFraseAlterada = new JTextField();
		txtFraseAlterada.setEnabled(false);
		txtFraseAlterada.setEditable(false);
		txtFraseAlterada.setColumns(10);
		txtFraseAlterada.setBounds(10, 202, 155, 20);
		contentPane.add(txtFraseAlterada);
		
		//SALVAR FRASE ALTERADA
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "update frase set fraseTexto = ? where fraseId = ?";
					PreparedStatement pstmt = connection.prepareStatement(sql); 
			        pstmt.setString(1, txtFraseAlterada.getText());
			        pstmt.setString(2, id);
			        pstmt.executeUpdate();
			        JOptionPane.showMessageDialog(null, "frase atualizado com sucesso");
			        dispose();
				}
				catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Erro ao tentar atualizar a frase: "+ e2);
				}
			}
		});
		btnSalvar.setEnabled(false);
		btnSalvar.setBounds(10, 233, 105, 23);
		contentPane.add(btnSalvar);
		
		//Apagar Frase
		JButton btnApagarFrase = new JButton("Apagar Frase");
		btnApagarFrase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "delete from frase where fraseId = ?";
					PreparedStatement pstmt = connection.prepareStatement(sql); 
			        pstmt.setString(1, id);
			        pstmt.execute();
			        JOptionPane.showMessageDialog(null, "Frase exclu?da com sucesso");
			        dispose();
				}
				catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Erro ao tentar deletar a frase: "+ e2);
				}
			}
		});
		btnApagarFrase.setForeground(Color.RED);
		btnApagarFrase.setEnabled(false);
		btnApagarFrase.setBounds(296, 201, 128, 23);
		contentPane.add(btnApagarFrase);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 79, 414, 91);
		contentPane.add(scrollPane);
		
		tblFrases = new JTable();
		scrollPane.setViewportView(tblFrases);
		
		JLabel lblAutoresImg = new JLabel("");
		Image img1 = new ImageIcon(this.getClass().getResource("/frase.png")).getImage();
	    lblAutoresImg.setIcon(new ImageIcon(img1));
		lblAutoresImg.setBounds(369, 11, 55, 52);
		contentPane.add(lblAutoresImg);
		
		//EVENTO DE CLIQUE NA TABELA PARA SELECIONAR O ID DO AUTOR
		tblFrases.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				linha = tblFrases.getSelectedRow();
				txtFraseAlterada.setText(tblFrases.getModel().getValueAt(linha, 1).toString());
				id = tblFrases.getModel().getValueAt(linha, 0).toString();
				btnApagarFrase.setEnabled(true);
				btnSalvar.setEnabled(true);
				txtFraseAlterada.setEnabled(true);
				txtFraseAlterada.setEditable(true);
			}
		});
				
	}

}
