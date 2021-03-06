package sisFrases.Model;

public class Livro {
	
	private Integer livroId;
	private String livroTitulo;
	private Autor livroAutor;

	public Livro() {
		
	}
	
	public Livro(Integer livroId, String titulo, Autor livroAutor) {
		this.livroAutor = livroAutor;
		this.livroId = livroId;
		this.livroTitulo = titulo;
	}

	public Integer getLivroId() {
		return livroId;
	}

	public void setLivroId(Integer livroId) {
		this.livroId = livroId;
	}

	public String getLivroTitulo() {
		return livroTitulo;
	}

	public void setLivroTitulo(String titulo) {
		this.livroTitulo = titulo;
	}

	public Autor getLivroAutorId() {
		return livroAutor;
	}

	public void setLivroAutorId(Autor livroAutor) {
		this.livroAutor = livroAutor;
	}

	@Override
	public String toString() {
		return "Livro [livroId=" + livroId + ", titulo=" + livroTitulo + ", autor=" + livroAutor + "]";
	}
	
	
}
