package sisFrases.View;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaInicial {

	private JFrame frmSisfrases;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial window = new TelaInicial();
					window.frmSisfrases.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaInicial() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSisfrases = new JFrame();
		frmSisfrases.setTitle("SisFrases1.0");
		frmSisfrases.setBounds(100, 100, 450, 300);
		frmSisfrases.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSisfrases.getContentPane().setLayout(null);
		
		JButton brnAutores = new JButton("Autores");
		brnAutores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaAutor telaAutor = new TelaAutor();
				telaAutor.setVisible(true);
			}
		});
		brnAutores.setBounds(10, 146, 89, 23);
		frmSisfrases.getContentPane().add(brnAutores);
		
		JButton btnFrases = new JButton("Frases");
		btnFrases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaFrase telaFrase = new TelaFrase();
				telaFrase.setVisible(true);
			}
		});
		btnFrases.setBounds(335, 146, 89, 23);
		frmSisfrases.getContentPane().add(btnFrases);
		
		JButton btnLivros = new JButton("Livros");
		btnLivros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaLivro telaLivro = new TelaLivro();
				telaLivro.setVisible(true);
			}
		});
		btnLivros.setBounds(171, 146, 89, 23);
		frmSisfrases.getContentPane().add(btnLivros);
		
		JLabel lblAutores = new JLabel("");
		//Imagem dentro do Label
		Image img1 = new ImageIcon(this.getClass().getResource("/autor.png")).getImage();
	    lblAutores.setIcon(new ImageIcon(img1));
		lblAutores.setBounds(25, 80, 54, 55);
		frmSisfrases.getContentPane().add(lblAutores);
		
		JLabel lblLivros = new JLabel("");
		//Imagem dentro do Label
		Image img2 = new ImageIcon(this.getClass().getResource("/livro.png")).getImage();
		lblLivros.setIcon(new ImageIcon(img2));
		lblLivros.setBounds(197, 73, 54, 62);
		frmSisfrases.getContentPane().add(lblLivros);
		
		JLabel lblFrases = new JLabel("");
		//Imagem dentro do Label
		Image img3 = new ImageIcon(this.getClass().getResource("/frase.png")).getImage();
		lblFrases.setIcon(new ImageIcon(img3));
		lblFrases.setBounds(352, 73, 54, 62);
		frmSisfrases.getContentPane().add(lblFrases);
	}
}
