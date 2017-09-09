/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forca;

/**
 *
 * @author luanmotta
 */
public class ForcaGame {
    
    private String palavra;
    private String[] palavraArray;
    private int erros = 0;
    
    public ForcaGame(String palavra) {
        this.palavra = palavra;
        this.palavraArray = new String[palavra.length()];
    }
    
    public void tentaLetra(String letra) {
        
        String palavraParaExplorar = this.palavra;
        boolean acertou = false;

        // Procura por todas as letras que batem na palavra
        while (true) {
        
            int index = palavraParaExplorar.indexOf(letra);
        
            // Se nao encontrar nenhuma outra letra na palavra
            if (index == -1) {
                if (acertou == false) {
                    this.erros++;
                }
                // Para de procurar por mais letras pois já percorreu toda a palavra
                break;
            } else {
                
                acertou = true;
                
                this.palavraArray[index] = letra;
            
                // Descarta o que já foi percorrido da palavra
                palavraParaExplorar = palavraParaExplorar.substring(0, index);

            }
            
        }
        
        
    }
    
    public String mostraPalavra() {
        String palavraDisplay = "";
        
        for (String letra : this.palavraArray) {
            if (letra == "") {
                palavraDisplay += "_";
            } else {
                palavraDisplay += letra;
            }
            palavraDisplay += " ";
        }
        return palavraDisplay;
    }
    
}
