package servidor;

import java.net.*;
import java.io.*;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import servidor.forca.ForcaPalavras;
import servidor.forca.Jogador;
import servidor.forca.PalavraForca;
import servidor.forca.Ranking;

public class TrataRequest extends Thread {

	private Socket socket;
	private Scanner entrada;
	private PrintWriter saida;
	private ForcaPalavras palavras;
	private Ranking rank;
	
	TrataRequest(Socket socket, ForcaPalavras palavras, Ranking rank) throws IOException{
		this.socket = socket;
		this.entrada = new Scanner(this.socket.getInputStream());
		this.saida = new PrintWriter(this.socket.getOutputStream());
		this.palavras = palavras;
		this.rank = rank;
	}
	
	public void run() {
		
		String linha = entrada.nextLine();
		
		System.out.println("Requisicao Recebida: " + linha);
		
		if (linha.contains("BUSCARPALAVRA")) {
			BUSCAPALAVRA();
		} else if (linha.contains("ENCERRARJOGO")) {
			ENCERRARJOGO(linha);
		} else if (linha.contains("BUSCARRANKING")) {
			BUSCARRANKING();
		} else {
			saida.println("Comando Invalido");
			saida.flush();
		}
		
		System.out.println("Encerrando conexao: " + linha);
		try {
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
	
	@SuppressWarnings("unchecked")
	private void BUSCAPALAVRA() {
		JSONObject json = new JSONObject();
		PalavraForca palavra = palavras.getPalavra();
		
		json.put("palavra", palavra.getPalavra());
		json.put("dica", palavra.getDica());
		
		reply(json.toJSONString());
	}
	
	@SuppressWarnings("unchecked")
	private void ENCERRARJOGO(String request) {
		JSONObject json = new JSONObject();
		String[] requestData = request.split(" ");
		
		if (requestData.length == 5) {
			Jogador jog = rank.getJogador(requestData[1], requestData[2]);
			if (jog != null) {
				jog.setVitorias(Integer.parseInt(requestData[3]));
				jog.setDerrotas(Integer.parseInt(requestData[4]));
			} else {
				jog = new Jogador(requestData[1], requestData[2], Integer.parseInt(requestData[3]), Integer.parseInt(requestData[4]));
				rank.add(jog);
			}
			
			json.put("usuario", jog.getNome());
			json.put("vitorias", jog.getVitorias());
			json.put("derrotas", jog.getDerrotas());
			
			reply(json.toJSONString());
		} else
			reply("Request inválido quantidade de parametros passados foram: " + requestData.length);
	}
	
	@SuppressWarnings("unchecked")
	private void BUSCARRANKING() {
		JSONObject main = new JSONObject();
		JSONArray list = new JSONArray();
		
		for (Jogador jog : rank.orderByAsc()) {
			JSONObject json = new JSONObject();
			json.put("usuario", jog.getNome());
			json.put("vitorias", jog.getVitorias());
			json.put("derrotas", jog.getDerrotas());
			
			list.add(json);
		}

		main.put("ranking", list);
		
		reply(main.toJSONString());
	}

	private void reply(String message) {
		saida.println(message);
		saida.flush();
	}
	
}
