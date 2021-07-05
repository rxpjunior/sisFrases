package sisFrases.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sisFrases.Connection.SqliteConnection;
import sisFrases.Model.Frase;
import sisFrases.Model.Livro;

public class FraseDAO {
	private Connection connection = null;
	String sql;
	
	//Inserindo no DB
	public boolean insere(Frase frase) {
		connection = SqliteConnection.dbConnector();
		try {
			sql = "Insert into frase (fraseTexto, frase_livroId) values (?, ?)";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, frase.getFraseTexto());
			pstm.setInt(2, frase.getFraseLivro().getLivroId());
			
			pstm.execute();
			System.out.println("Inserido com sucesso");
			return true;
		}
		catch(Exception e) {
			System.out.println("Erro ao inserir frase");
			return false;
		}
	}
	
	//Busca Frase por ID
	public Frase buscaPorId(int id) {
		try {
			Frase frase = new Frase();
			sql = "Select * from frase where fraseId = ?";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet resultado = pstm.executeQuery();
			while(resultado.next()) {
				frase.setFraseId(resultado.getInt("fraseId"));
				frase.setFraseTexto(resultado.getString("fraseTexto"));
				Livro livro = new Livro();
				livro.setLivroId(resultado.getInt("frase_LivroId"));
				frase.setFraseLivro(livro);
		}
			return frase;
			
		} catch (Exception e) {
			System.out.println("Erro ao buscar livro");
			return null;
		}
	}
	
	//Busca todos os livros 
	public List<Frase> buscaTodos() {
		try {
			List<Frase> listafrases = new ArrayList<Frase>();
			sql = "Select * from frase";
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet resultado = pstm.executeQuery();
			while(resultado.next()) {
				Frase frase = new Frase();
				frase.setFraseId(resultado.getInt("fraseId"));
				frase.setFraseTexto(resultado.getString("fraseTexto"));
				Livro livro = new Livro();
				livro.setLivroId(resultado.getInt("frase_LivroId"));
				frase.setFraseLivro(livro);
				listafrases.add(frase);
		}
			return listafrases;
			
		} catch (Exception e) {
			System.out.println("Erro ao buscar livro");
			return null;
		}
	}
	
	//Altera livro
	public boolean altera(Frase frase) {
		try {
			sql = "Update frase set fraseTexto = ?, frase_LivroId = ? where fraseId = ?";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1,  frase.getFraseTexto());
			pstm.setInt(2, frase.getFraseLivro().getLivroId());
			pstm.setInt(3, frase.getFraseId());
			pstm.execute();
			System.out.println("Alterado com sucesso");
			return true;
			
		} catch (Exception e) {
			System.out.println("Erro ao alterar frase"+e);
			return false;
		}
	}
	
	//Deleta autor
	public boolean deleta(Integer id) {
		try {
			sql = "Delete from frase where fraseId = ?";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.execute();
			System.out.println("Deletado com sucesso");
			return true;
			
		} catch (Exception e) {
			System.out.println("Erro ao deletar frase");
			return false;
		}
	}
}
