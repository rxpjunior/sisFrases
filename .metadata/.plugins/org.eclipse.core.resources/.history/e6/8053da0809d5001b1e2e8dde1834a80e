package sisFrases.View;

import sisFrases.DAO.AutorDAO;
import sisFrases.Model.Autor;

public class Main {

	public static void main(String[] args) {
		
		//Inserir
		Autor autor1 = new Autor();
		autor1.setAutorNome("Jeremias");
		AutorDAO autorDao = new AutorDAO();
		System.out.println("Validacao de insercao: "+autorDao.insere(autor1));
		
		//Buscar por ID
		Autor autor2 = new Autor();
		autor2 = autorDao.buscaPorId(9);
		System.out.println("Retorno da busca pelo Id 9: "+autor2);
		
		
		//Buscar todos os Autores
		System.out.println("Retorno da busca por todos: "+autorDao.buscaTodos());
		
		//Altera autor
		autor2.setAutorNome("Novo Nome");
		autorDao.altera(autor2);
		System.out.println("Autor do ID 9 aterado: "+autorDao.buscaPorId(9));
		
		//Deleta autor
		autorDao.deleta(9);
		System.out.println("Retorno da busca pelo ID 9 que acabou de ser excluido"+autorDao.buscaPorId(9));
		
		

	}

}
