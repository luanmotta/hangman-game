package servidor.forca;

public class PalavraForca {
	private String palavra, dica;
	public PalavraForca(String palavra, String dica) {
		this.setPalavra(palavra);
		this.setDica(dica);
	}
	public String getPalavra() {
		return palavra;
	}
	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}
	public String getDica() {
		return dica;
	}
	public void setDica(String dica) {
		this.dica = dica;
	}

}
