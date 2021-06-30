package sisFrases.Model;

public class Frase {
	
	private Integer fraseId;
	private String fraseTexto;
	private Livro fraseLivro; 
	
	public Frase() {
		
	}

	public Frase(Integer fraseId, String fraseTexto, Livro fraseLivro) {
		super();
		this.fraseId = fraseId;
		this.fraseTexto = fraseTexto;
		this.fraseLivro = fraseLivro;
	}

	public Integer getFraseId() {
		return fraseId;
	}

	public void setFraseId(Integer fraseId) {
		this.fraseId = fraseId;
	}

	public String getFraseTexto() {
		return fraseTexto;
	}

	public void setFraseTexto(String fraseTexto) {
		this.fraseTexto = fraseTexto;
	}

	public Livro getFraseLivro() {
		return fraseLivro;
	}

	public void setFraseLivro(Livro fraseLivro) {
		this.fraseLivro = fraseLivro;
	}

	@Override
	public String toString() {
		return "Frase [fraseId=" + fraseId + ", fraseTexto=" + fraseTexto + ", fraseLivro=" + fraseLivro + "]";
	}
	
	

}
