package sisFrases.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sisFrases.Connection.SqliteConnection;
import sisFrases.Model.Autor;
import sisFrases.Model.Livro;

public class LivroDAO {
	
	private Connection connection = SqliteConnection.dbConnector();
	String sql;
	
	//Inserindo no DB
	public boolean insere(Livro livro) {
		try {
			sql = "Insert into Livro (livroTitulo, livro_autorId) values (?, ?)";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1,  livro.getLivroTitulo());
			pstm.setInt(2,  livro.getLivroAutorId().getAutorId());
			
			pstm.execute();
			System.out.println("Livro inserido com sucesso");
			return true;
		}
		catch(Exception e) {
			System.out.println("Erro ao inserir livro");
			return false;
		}
	}
	
	//Busca Livro por ID
	public Livro buscaPorId(int id) {
		try {
			Livro livro = new Livro();
			sql = "Select * from livro where livroId = ?";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet resultado = pstm.executeQuery();
			while(resultado.next()) {
				livro.setLivroId(resultado.getInt("livroId"));
				livro.setLivroTitulo(resultado.getString("livroTitulo"));
				Autor autor = new Autor();
				autor.setAutorId(resultado.getInt("livro_autorId"));
				livro.setLivroAutorId(autor);
			}
			return livro;
			
		} catch (Exception e) {
			System.out.println("Erro ao buscar livro");
			return null;
		}
	}
	
	//Busca todos os livros 
	public List<Livro> buscaTodos() {
		try {
			List<Livro> listaLivros= new ArrayList<Livro>(); 
			sql = "Select * from livro";
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet resultado = pstm.executeQuery();
			while(resultado.next()) {
				Livro livro = new Livro();
				livro.setLivroId(resultado.getInt("livroId"));
				livro.setLivroTitulo(resultado.getString("livroTitulo"));
				Autor autor = new Autor();
				autor.setAutorId(resultado.getInt("livro_autorId"));
				livro.setLivroAutorId(autor);
				listaLivros.add(livro);
			}
			return listaLivros;
			
		} catch (Exception e) {
			System.out.println("Erro ao buscar livros");
			return null;
		}
	}
	
	//Altera livro
	public boolean altera(Livro livro) {
		try {
			sql = "Update livro set livroTitulo = ?, livro_AutorId = ? where livroId = ?";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1,  livro.getLivroTitulo());
			pstm.setInt(2, livro.getLivroAutorId().getAutorId());
			pstm.setInt(3, livro.getLivroId());
			pstm.execute();
			System.out.println("Livro alterado com sucesso");
			return true;
			
		} catch (Exception e) {
			System.out.println("Erro ao alterar livro");
			return false;
		}
	}
	
	//Deleta autor
	public boolean deleta(Integer id) {
		try {
			sql = "Delete from livro where livroId = ?";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.execute();
			System.out.println("Livro deletado com sucesso");
			return true;
			
		} catch (Exception e) {
			System.out.println("Erro ao deletar livro");
			return false;
		}
	}

}
