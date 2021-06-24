package sisFrases.View;

import sisFrases.DAO.AutorDAO;
import sisFrases.Model.Autor;

public class Main {

	public static void main(String[] args) {
		
		//Inserir
		Autor autor1 = new Autor();
		autor1.setAutorNome("Juliana");
		AutorDAO autorDao = new AutorDAO();
		System.out.println("Validacao de insercao: "+autorDao.insere(autor1));
		
		autor1.setAutorNome("Juliano");
		System.out.println("Validacao de insercao: "+autorDao.insere(autor1));
		
		//Buscar por ID
		Autor autor2 = new Autor();
		autor2 = autorDao.buscaPorId(2);
		System.out.println("Retorno da busca pelo Id 2: "+autor2);
		
		
		//Buscar todos os Autores
		System.out.println("Retorno da busca por todos: "+autorDao.buscaTodos());
		
		//Altera autor
		autor2.setAutorNome("Maxwell");
		autorDao.altera(autor2);
		System.out.println("Autor do ID 2 aterado: "+autorDao.buscaPorId(2));
		
		//Deleta autor
		System.out.println("Verifica��o dele��o autor 1"+autorDao.deleta(1));
		System.out.println("Retorno da busca pelo ID 1 que acabou de ser excluido"+autorDao.buscaPorId(1));
		
		

	}

}
