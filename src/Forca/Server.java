/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forca;

import java.util.*;
import java.io.*;
import java.net.*;

/**
 *
 * @authors Luan Motta & Ezequiel Mross Ferreira
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Instancia o servidor
            ServerSocket server = new ServerSocket(8765);
            
            // Conecta no servidor
            Socket s = server.accept();
            
            // Busca streams de E/S
            Scanner entrada = new Scanner(new InputStreamReader(s.getInputStream()));
            PrintWriter saida = new PrintWriter(s.getOutputStream());
            
            while (true) {
                String linha = entrada.nextLine();
                System.out.println(linha);
                
                // Envia dados atraves do Stream
                saida.println("pepo\n");
                saida.flush();
            }    

        } catch (UnknownHostException ex) {
            System.out.println("Host desconhecido");
        } catch (IOException ex) {
            System.out.println("Erro na conexao: " + ex.getMessage());
        }
    }
    
}
