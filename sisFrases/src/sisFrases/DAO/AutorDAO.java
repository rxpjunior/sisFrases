package sisFrases.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import sisFrases.Connection.SqliteConnection;
import sisFrases.Model.Autor;

public class AutorDAO {
	
	private Connection connection = null;
	String sql;
	
	//Inserindo no DB
	public boolean insere(Autor autor) {
		connection = SqliteConnection.dbConnector();
		try {
			sql = "Insert into Autor (autorNome) values (?)";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1,  autor.getAutorNome());
			pstm.execute();
			System.out.println("Inserido com sucesso");
			return true;
		}
		catch(Exception e) {
			System.out.println("Erro ao inserir autor");
			return false;
		}
	}
	
	//Busca Autor por ID
	public Autor buscaPorId(int id) {
		try {
			Autor autor = new Autor();
			sql = "Select * from autor where autorId = ?";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet resultado = pstm.executeQuery();
			while(resultado.next()) {
				autor.setAutorId(resultado.getInt("autorId"));
				autor.setAutorNome(resultado.getString("autorNome"));
				System.out.println("entrou");
			}
			return autor;
			
		} catch (Exception e) {
			System.out.println("Erro ao buscar autor");
			return null;
		}
	}
	
	//Busca todos os autores 
	public List<Autor> buscaPorId() {
		try {
			List<Autor> listaAutores= new ArrayList<Autor>(); 
			sql = "Select * from autor";
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet resultado = pstm.executeQuery();
			while(resultado.next()) {
				Autor autor = new Autor();
				autor.setAutorId(resultado.getInt("autorId"));
				autor.setAutorNome(resultado.getString("autorNome"));
				listaAutores.add(autor);
			}
			return listaAutores;
			
		} catch (Exception e) {
			System.out.println("Erro ao buscar autor");
			return null;
		}
	}
}
