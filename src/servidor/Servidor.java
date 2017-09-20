package servidor;

import java.io.*;
import java.net.*;

import servidor.forca.ForcaPalavras;
import servidor.forca.Ranking;

public class Servidor {

	public static void main(String args[]){
		
		ServerSocket server = null;
		int porta = 8765;
		ForcaPalavras palavrasForca = new ForcaPalavras();
		Ranking rank = new Ranking();
		try {
			System.out.println("Iniciando servidor na porta " + porta);
			server = new ServerSocket(porta);

			while(true){
				System.out.println("Aguardando conexao...");
				Socket cliente = server.accept();
				
				System.out.println("Conexao estabelecida...");
				System.out.println("Iniciando thread para tratar requisicao...");
				TrataRequest request = new TrataRequest(cliente, palavrasForca, rank);
				request.start();
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
