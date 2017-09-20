package cliente;

import java.util.ArrayList;
import java.util.Scanner;

import com.sun.xml.internal.ws.util.StringUtils;

public class ForcaGame {
    
    public boolean StartGame(String palavraSecreta, String dica, Scanner teclado) {
    	StringBuilder solucao = new StringBuilder(palavraSecreta.replaceAll("\\w", "_"));
        String letra;
        int chances = 4, acertos = 0;
        
        System.out.println("\n\nJogo começou!\n");
        
        while(chances > 0) {
        	// Regex: adiciona espaço entre as letras
        	System.out.print("\nTotal de chances: " + chances + "\nPalavra: " + solucao.toString().replaceAll(".(?=.)", "$0 ") + "\nLetra? ");
        	
        	letra = teclado.nextLine();
        	
        	// Valida se usuario nao digitou mais de uma letra
        	if (letra.length() > 1) {
        		System.out.println("\nApenas uma letra!!!\n");
        	} else {
        		// Pega os indexes que a letra foi encontrada
        		ArrayList<Integer> indexes = tentaLetra(letra, palavraSecreta);
            	
        		// Valida se encontrou uma letra se n perde 1 chance
            	chances = indexes.size() > 0 ? chances : chances - 1;

            	// Atualiza espaços em brancos
            	for (Integer index : indexes) {
            		solucao.setCharAt(index, letra.charAt(0));
				}
            	
            	// Valida acertos
            	acertos = countAcertos(solucao.toString());
            	if (acertos == palavraSecreta.length()) {
            		System.out.println("\nGanhou!\n");
            		return true;
            	} else if (acertos >= palavraSecreta.length() / 2)
            		System.out.println("\nDica: " + dica + "\n");
        	}
        }
        
        System.out.println("\nPerdeu!\nA palavra era: " + palavraSecreta + "\n");
        
        return false;
    }
    
    public ArrayList<Integer> tentaLetra(String letra, String palavra) {
    	ArrayList<Integer> indexes = new ArrayList<>();

    	int index = palavra.toLowerCase().indexOf(letra); 
    	while (index >= 0) {
    		indexes.add(index);
    	    index = palavra.indexOf(letra, index + 1);
    	}
    	
    	return indexes;
    }
    
    public int countAcertos(String solucao) {
    	int acertos = 0;
    	for (int i = 0; i < solucao.length(); i++) {
			if (solucao.charAt(i) != '_')
				acertos++;
		}
    	return acertos;
    }
    
}
