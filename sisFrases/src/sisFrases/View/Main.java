package sisFrases.View;

import sisFrases.DAO.AutorDAO;
import sisFrases.Model.Autor;

public class Main {

	public static void main(String[] args) {
		
		//Inserir
		Autor autor = new Autor();
		autor.setAutorNome("Seritodo11111");
		AutorDAO autorDao = new AutorDAO();
		System.out.println(autorDao.insere(autor));
		
		//Buscar por ID
		System.out.println(autorDao.buscaPorId(2));
		
		//Buscar todos os Autores
		System.out.println(autorDao.buscaPorId());

	}

}
