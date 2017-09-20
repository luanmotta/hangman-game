package servidor.forca;

public class Jogador {
	private String nome, chave;
	private int vitorias, derrotas;

	public Jogador(String nome, String chave,int vitorias, int derrotas) {
		this.nome = nome;
		this.chave = chave;
		this.vitorias = vitorias;
		this.derrotas = derrotas;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getDerrotas() {
		return derrotas;
	}

	public void setDerrotas(int derrotas) {
		this.derrotas += derrotas;
	}

	public int getVitorias() {
		return vitorias;
	}

	public void setVitorias(int vitorias) {
		this.vitorias += vitorias;
	}
}
