package sisFrases.Model;

public class Autor {
	private int autorId;
	private String autorNome;
	
	public Autor() {
		
	}

	public Autor(int autorId, String autorNome) {
		super();
		this.autorId = autorId;
		this.autorNome = autorNome;
	}
	
	public int getAutorId() {
		return autorId;
	}

	public void setAutorId(int autorId) {
		this.autorId = autorId;
	}

	public String getAutorNome() {
		return autorNome;
	}

	public void setAutorNome(String autorNome) {
		this.autorNome = autorNome;
	}

	@Override
	public String toString() {
		return "Autor [autorId=" + autorId + ", autorNome=" + autorNome + "]";
	}
	
	
	
}
