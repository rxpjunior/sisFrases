package sisFrases.View;

import sisFrases.DAO.AutorDAO;
import sisFrases.DAO.LivroDAO;
import sisFrases.Model.Autor;
import sisFrases.Model.Livro;

public class Main {

	public static void main(String[] args) {
		
		//AUTORES
		
		//Inserir
		Autor autor = new Autor();
		AutorDAO autorDao = new AutorDAO();
		autor.setAutorNome("Mateus");
		System.out.println("Autor 1 inserido ?: "+autorDao.insere(autor));
	
		autor.setAutorNome("Marcos");
		System.out.println("Autor 2 inserido ?: "+autorDao.insere(autor));
		
		//Buscar por ID
		Autor autor2 = new Autor();
		autor2 = autorDao.buscaPorId(1);
		System.out.println("Retorno da busca pelo Id 1: "+autor2);
		
		//Buscar todos os Autores
		System.out.println("Retorno da busca por todos: "+autorDao.buscaTodos());
		
		//Altera autor
		autor2.setAutorNome("Lucas");
		autorDao.altera(autor2);
		System.out.println("Autor do ID 1 aterado de Mateus para Lucas: "+autorDao.buscaPorId(1));
		
		//Deleta autor
		System.out.println("Autor 1 deletado? :"+autorDao.deleta(1));
		System.out.println("Retorno da busca pelo ID 1 que acabou de ser excluido"+autorDao.buscaPorId(1));
		
		
		//LIVROS
		
		Autor autor3 = new Autor();
		AutorDAO autorDao3 = new AutorDAO();
		autor3.setAutorNome("Jo�o");
		autorDao3.insere(autor3);
		autor3 = autorDao3.buscaPorId(3);
		
		//Inserir
		Livro livro1 = new Livro();
		livro1.setLivroTitulo("Evagelho de Jo�o");
		livro1.setLivroAutorId(autor3);
		LivroDAO livroDao = new LivroDAO();
		System.out.println("Livro foi inserido? : "+livroDao.insere(livro1));
		
		livro1.setLivroTitulo("Apocalipse");
		System.out.println("Livro foi inserido? : "+livroDao.insere(livro1));
		
		//Buscar por ID
		Livro livro2 = new Livro();
		livro2 = livroDao.buscaPorId(2);
		System.out.println("Retorno da busca pelo Id 2: "+livro2);
		
		
		//Buscar todos os livros
		System.out.println("Retorno da busca por todos: "+livroDao.buscaTodos());
		
		//Altera livro
		livro2.setLivroTitulo("Revela��es ou Apocalipse");
		livroDao.altera(livro2);
		System.out.println("Livro do ID 2 aterado: "+livroDao.buscaPorId(2));
		
		//Deleta autor
		System.out.println("Verifica��o dele��o livro 1"+livroDao.deleta(1));
		System.out.println("Retorno da busca pelo ID 1 que acabou de ser excluido"+livroDao.buscaPorId(1));
	}

}
