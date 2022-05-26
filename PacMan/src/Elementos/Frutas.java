/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Elementos;

/**
 *
 * @author Fernando
 */

import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


public class Frutas {
    private Image cereja = new Image("file:src/imagens/cherry.png");
    
    private Image morango = new Image("file:src/imagens/morango.png");
    
    private Image laranja = new Image("file:src/imagens/laranja.png");
    
    private Random coordenadaRandom = new Random();
    
    private boolean adicionadoMorango = false;
    
    private boolean adicionadoCereja = false;
    
    private boolean adicionadoLaranja = false;
    
    public Frutas(){
        
    }
    
    public void adicionarFruta(int pontuacao, GridPane gridPane, char[][] mapa, int nivel){
        
        if(pontuacao == 700 && nivel == 1 && adicionadoCereja == false){
            adicionadoCereja = true;
            ImageView frutaView = new ImageView(cereja);
            frutaView.setFitHeight(40);
            frutaView.setFitWidth(40);
            
            int coordenadaRandomX = coordenadaRandom.nextInt(19);
            int coordenadaRandomY = coordenadaRandom.nextInt(21);
            
            while(mapa[coordenadaRandomX][coordenadaRandomY] == 'W'){
                coordenadaRandomX = coordenadaRandom.nextInt(19);
                coordenadaRandomY = coordenadaRandom.nextInt(21);
            }
            
            mapa[coordenadaRandomX][coordenadaRandomY] = 'C';
            
            gridPane.add(frutaView, coordenadaRandomY, coordenadaRandomX);
            
        } else if(pontuacao == 1000 && nivel == 2 && adicionadoMorango == false){
            adicionadoMorango = true;
            ImageView frutaView = new ImageView(morango);
            frutaView.setFitHeight(40);
            frutaView.setFitWidth(40);
            
            int coordenadaRandomX = coordenadaRandom.nextInt(19);
            int coordenadaRandomY = coordenadaRandom.nextInt(21);
            
            while(mapa[coordenadaRandomX][coordenadaRandomY] == 'W'){
                coordenadaRandomX = coordenadaRandom.nextInt(19);
                coordenadaRandomY = coordenadaRandom.nextInt(21);
            }
            
            mapa[coordenadaRandomX][coordenadaRandomY] = 'M';
            
            gridPane.add(frutaView, coordenadaRandomY, coordenadaRandomX);
            
            
        } if(pontuacao == 1000 && nivel == 3 && adicionadoLaranja == false){
            adicionadoLaranja = true;
            ImageView frutaView = new ImageView(laranja);
            frutaView.setFitHeight(40);
            frutaView.setFitWidth(40);
            
            int coordenadaRandomX = coordenadaRandom.nextInt(19);
            int coordenadaRandomY = coordenadaRandom.nextInt(21);
            
            while(mapa[coordenadaRandomX][coordenadaRandomY] == 'W'){
                coordenadaRandomX = coordenadaRandom.nextInt(19);
                coordenadaRandomY = coordenadaRandom.nextInt(21);
            }
            
            mapa[coordenadaRandomX][coordenadaRandomY] = 'L';
            
            gridPane.add(frutaView, coordenadaRandomY, coordenadaRandomX);
            
        }
    }
}
