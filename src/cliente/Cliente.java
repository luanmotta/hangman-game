package cliente;

import java.io.*;
import java.net.*;
import java.util.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Cliente {

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		
		System.out.print("Informe o nome de usuário: ");
		String usuario = teclado.nextLine();
		System.out.print("Informe a chave: ");
		String chave = teclado.nextLine();

		System.out.print("Informe o endereco IP do servidor: ");
		String ipServer = teclado.nextLine();
		System.out.print("Informe a porta para conexao: ");
		int portServer = teclado.nextInt();
		teclado.nextLine();

		System.out.println("Conectando no servidor...");
		Socket socket;
		ForcaGame game = new ForcaGame();
		int vitorias = 0, derrotas = 0;

		try {
			int opcao = 0;
			do {
				if ((opcao == 0) || (opcao == 1) || (opcao == 2)) {
					socket = new Socket(ipServer, portServer);
					System.out.println("Conectado...");

					Scanner entrada = new Scanner(socket.getInputStream());
					PrintWriter saida = new PrintWriter(socket.getOutputStream());

					if (opcao == 0) {
						request("BUSCARPALAVRA", saida);
						JSONObject json = parseJSON(response(entrada));
						if (json == null)
							opcao = 3;
						else
							if (game.StartGame((String) json.get("palavra"), (String) json.get("dica"), teclado))
								vitorias++;
							else
								derrotas++;
						System.out.print("Deseja continuar?\n1 - Sim\n2 - não\n");
						opcao = teclado.nextInt() == 1 ? 0 : 1;
						teclado.nextLine();
					} else if (opcao == 1) {
						request("ENCERRARJOGO " + usuario + " " + chave + " " + vitorias + " " + derrotas, saida);
						response(entrada);
						opcao = 2;
					} else if (opcao == 2) {
						request("BUSCARRANKING", saida);
						System.out.println(response(entrada).replaceAll("\\{|\\[|\"|\\]", "").replaceAll("\\}|ranking", "\n").replaceAll(":|,", " "));
						opcao = 3;
					}

					socket.close();
					System.out.println("Conexao encerrada");
				}
			} while (opcao != 3);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static JSONObject parseJSON(String jsonString) {
		try {
			return (JSONObject) new JSONParser().parse(jsonString);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static void request(String message, PrintWriter saida) {
		saida.println(message);
		saida.flush();
	}
	
	private static String response(Scanner entrada) {
		String response = "";
		while(entrada.hasNextLine()) {
			response += entrada.nextLine();
		}
		return response;
	}

}
