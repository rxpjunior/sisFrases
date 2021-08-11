package sisFrases.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Color;

public class TelaAutor extends JFrame {

	private JPanel contentPane;
	private JTable tblAutores;
	private JTextField txtNovoNomeAutor;

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
		btnNovoAutor.setBounds(10, 11, 105, 23);
		contentPane.add(btnNovoAutor);
		
		JButton btnAutores = new JButton("Autores");
		btnAutores.setBounds(10, 45, 105, 23);
		contentPane.add(btnAutores);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 79, 414, 84);
		contentPane.add(scrollPane);
		
		tblAutores = new JTable();
		scrollPane.setViewportView(tblAutores);
		
		JLabel lblNewLabel = new JLabel("Novo Nome dos Autor");
		lblNewLabel.setBounds(10, 177, 122, 14);
		contentPane.add(lblNewLabel);
		
		txtNovoNomeAutor = new JTextField();
		txtNovoNomeAutor.setBounds(10, 202, 155, 20);
		contentPane.add(txtNovoNomeAutor);
		txtNovoNomeAutor.setColumns(10);
		
		JButton txtSalvar = new JButton("Salvar");
		txtSalvar.setBounds(10, 233, 105, 23);
		contentPane.add(txtSalvar);
		
		JButton btnApagarAutor = new JButton("Apagar Autor");
		btnApagarAutor.setForeground(Color.RED);
		btnApagarAutor.setBounds(296, 201, 128, 23);
		contentPane.add(btnApagarAutor);
	}
}
