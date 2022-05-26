package Elementos;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class Pacman extends Personagem{

    private char pac = 'P';
    private int pontuacao = 0;
    private int nVidas = 3;
    /**
     * Construtor do pacman
     * @param nome - nome do pacman
     * @param nVidas - número de vidas
     * @param posX - posição X do pacman
     * @param posY - posição Y do pacman
     */
    public Pacman(String nome, int nVidas, int posX, int posY) {
        super(nome, nVidas, posX, posY);
    }

    /**
     * Coloca a posicao no mapa
     * @param mapa - Labirinto 2D 
     */
    @Override
    public void setPosMapa(char[][] mapa) {
        mapa[this.getPosX()][this.getPosY()] = pac;
    }

    /**
     * Decrementa as vidas do pacman
     */
    public void decrementaNVidas(){
        this.nVidas -= 1;
    }
    
    /**
     * Método que retorna as vidas
     * @return - Quantidade de vidas do pacman
     */
    public int getnVidas(){
        return this.nVidas;
    }

    /**
     * Método o qual incrementa pontuação
     * @param dot - Caracteres do mapa
     * @param fantasma - Fantasma do pacman
     * @return 
     */
    public int incrementaPontuacao(char dot, Fantasma fantasma){
        switch(dot){
            case 'B':
                this.pontuacao += 100;
                fantasma.setPodeSerComido(true);
                break;
            case 'S':
                this.pontuacao += 10;
                return this.pontuacao;
                //break;
            case 'C':
                this.pontuacao += 100;
                break;
            case 'M':
                this.pontuacao += 300;
                break;
            case 'L':
                this.pontuacao += 500;
                break;
            default:
                break;
        }
        return 0;
    }
    
    /**
     * Seta a pontuação
     * @param pontuacao - Passa a pontuação a ser setada 
     */
    public void setPontuacao(int pontuacao){
        this.pontuacao = pontuacao;
    }
    
    /**
     * 
     * @return - Retorna a pontuação atual
     */
    public int getPontuacao(){
        return this.pontuacao;
    }

    //No futuro, essa funcao vai fazer o PacMan se mover conforme o teclado ou controle.
    @Override
    public void mover(char[][] mapa, KeyEvent keyEvent, Image fundoPreto, Image fantImage, GridPane gridPane) {
    }

    @Override
    public boolean vivoOuMorto() {
        return this.getVivo();
    }

}
