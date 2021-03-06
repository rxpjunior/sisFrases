package sisFrases.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sisFrases.Connection.SqliteConnection;
import sisFrases.Model.Autor;
import sisFrases.Model.Frase;
import sisFrases.Model.Livro;

public class FraseDAO {
	
	private Connection connection = SqliteConnection.dbConnector();
	String sql;
	
	//Inserindo no DB
	public boolean insere(Frase frase) {
		try {
			sql = "Insert into frase (fraseTexto, frase_livroId) values (?, ?)";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, frase.getFraseTexto());
			pstm.setInt(2, frase.getFraseLivro().getLivroId());
			pstm.execute();
			System.out.println("Frase inserida com sucesso");
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
			System.out.println("Erro ao buscar frase");
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
			System.out.println("Erro ao buscar frase");
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
			System.out.println("Frase deletada com sucesso");
			return true;
			
		} catch (Exception e) {
			System.out.println("Erro ao deletar frase");
			return false;
		}
	}
	
	//Busca Frase por ID
	public Frase buscaPost(int id) {
		try {
			Frase frase = new Frase();
			Livro livro = new Livro();
			Autor autor = new Autor();
			sql = "SELECT f.fraseTexto, l.livroTitulo, a.autorNome from frase as f, autor as a, livro as l where a.autorId = l.livro_autorId and l.livroId = f.frase_livroId and f.fraseId = ?";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet resultado = pstm.executeQuery();
			while(resultado.next()) {
				frase.setFraseTexto(resultado.getString("fraseTexto"));
				livro.setLivroTitulo(resultado.getString("livroTitulo"));
				autor.setAutorNome(resultado.getString("autorNome"));
				frase.setFraseLivro(livro);
				livro.setLivroAutorId(autor);
		}
			return frase;
			
		} catch (Exception e) {
			System.out.println("Erro ao buscar frase");
			return null;
		}
	}
	
	//Busca Frase por ID e retorna o post em forma de String
	public String buscaPostString(int id) {
		try {
			Frase frase = new Frase();
			Livro livro = new Livro();
			Autor autor = new Autor();
			sql = "SELECT f.fraseTexto, l.livroTitulo, a.autorNome from frase as f, autor as a, livro as l where a.autorId = l.livro_autorId and l.livroId = f.frase_livroId and f.fraseId = ?";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet resultado = pstm.executeQuery();
			while(resultado.next()) {
				frase.setFraseTexto(resultado.getString("fraseTexto"));
				livro.setLivroTitulo(resultado.getString("livroTitulo"));
				autor.setAutorNome(resultado.getString("autorNome"));
				frase.setFraseLivro(livro);
				livro.setLivroAutorId(autor);
			}
		String post = frase.getFraseTexto()+" - "+frase.getFraseLivro().getLivroTitulo()+" - "+frase.getFraseLivro().getLivroAutorId().getAutorNome(); 
		return post;
		
	} catch (Exception e) {
		System.out.println("Erro ao buscar frase: "+e);
		return null;
	}
	}
}
