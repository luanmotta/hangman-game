package servidor.forca;

import java.util.ArrayList;

public class Ranking {
	private ArrayList<Jogador> rank;

	public Ranking() {
		rank = new ArrayList<>();
	}
	
	public void add(Jogador jog) {
		rank.add(jog);
	}
	
	public Jogador getJogador(String usuario, String chave) {
		for (Jogador j : rank) {
			if (j.getNome().equals(usuario) && j.getChave().equals(chave))
				return j;
		}
		return null;
	}

	public ArrayList<Jogador> orderByAsc() {
		rank.sort((p1, p2) -> p2.getVitorias() - p1.getVitorias());
		return rank;
	}

}
