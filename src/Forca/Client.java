/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Forca;

import java.net.*;
import java.io.*;
import java.util.*;


/**
 *
 * @authors Luan Motta & Ezequiel Mross Ferreira
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner teclado = new Scanner(System.in);
        
        System.out.println("Digite o endereco IP do servidor");
        String endereco = teclado.nextLine();
        
        System.out.println("Digite a porta do servidor");
        int porta = teclado.nextInt();
        
        System.out.println("Digite o username");
        String username = teclado.nextLine();
        
        System.out.println("Digite a chave");
        String chave = teclado.nextLine();
        
        
        try {
            // Conecta no servidor
            Socket s = new Socket("localhost", 8765);
            
            // Busca streams de E/S
            Scanner entrada = new Scanner(new InputStreamReader(s.getInputStream()));
            PrintWriter saida = new PrintWriter(s.getOutputStream());
            
            while (true) {
                
                // Envia dados atraves do Stream
                saida.println("papo\n");
                saida.flush();
                
                System.out.println(entrada.nextLine());
            }
            
        } catch (UnknownHostException ex) {
            System.out.println("Host desconhecido");
        } catch (IOException ex) {
            System.out.println("Erro na conexao: " + ex.getMessage());
        }
    }
    
}
