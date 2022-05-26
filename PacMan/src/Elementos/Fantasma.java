package Elementos;



import Engine.Pair;
import Engine.Wave;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Random;

public class Fantasma extends Personagem{
    Wave wave = new Wave();
    private static char fantasma = 'f';
    private boolean podeSerComido = false;

    public Fantasma(String nome, int nVidas, int posX, int posY) {
        super(nome, nVidas, posX, posY);

    }


    @Override
    public void setPosMapa(char[][] mapa) {
        mapa[this.getPosX()][this.getPosY()] = fantasma;
    }

    @Override
    public void mover(char[][] mapa, KeyEvent keyEvent, Image fundoPreto, Image fantImage, GridPane gridPane) {
        Random random = new Random();

        int chance = random.nextInt(4);

        if(mapa[this.getPosX()][this.getPosY()+1] != 'W' && chance == 0){
            ImageView blueG = new ImageView(fantImage);
            blueG.setFitHeight(40);
            blueG.setFitWidth(40);
            //gridPane.add(blueG, this.getPosY(),this.getPosX());
            ImageView fundoPr = new ImageView(fundoPreto);
            fundoPr.setFitHeight(40);
            fundoPr.setFitWidth(40);
            gridPane.add(fundoPr,this.getPosY(),this.getPosX());
            this.setPosX(this.getPosX());
            this.setPosY(this.getPosY()+1);
            mapa[this.getPosX()][this.getPosY()] = 'E';
            gridPane.add(blueG,this.getPosY(),this.getPosX());
        }if(mapa[this.getPosX()][this.getPosY()-1] != 'W' && chance == 1){
            ImageView blueG = new ImageView(fantImage);
            blueG.setFitHeight(40);
            blueG.setFitWidth(40);
            //gridPane.add(blueG, this.getPosY(),this.getPosX());
            ImageView fundoPr = new ImageView(fundoPreto);
            fundoPr.setFitHeight(40);
            fundoPr.setFitWidth(40);
            gridPane.add(fundoPr,this.getPosY(),this.getPosX());
            this.setPosX(this.getPosX());
            this.setPosY(this.getPosY()-1);
            mapa[this.getPosX()][this.getPosY()] = 'E';
            gridPane.add(blueG,this.getPosY(),this.getPosX());
        }
        if(mapa[this.getPosX()+1][this.getPosY()] != 'W' && chance == 2){
            ImageView blueG = new ImageView(fantImage);
            blueG.setFitHeight(40);
            blueG.setFitWidth(40);
            //gridPane.add(blueG, this.getPosY(),this.getPosX());
            ImageView fundoPr = new ImageView(fundoPreto);
            fundoPr.setFitHeight(40);
            fundoPr.setFitWidth(40);
            gridPane.add(fundoPr,this.getPosY(),this.getPosX());
            this.setPosX(this.getPosX()+1);
            this.setPosY(this.getPosY());
            mapa[this.getPosX()][this.getPosY()] = 'E';
            gridPane.add(blueG,this.getPosY(),this.getPosX());
        }
        if(mapa[this.getPosX()-1][this.getPosY()] != 'W' && chance == 3){
            ImageView blueG = new ImageView(fantImage);
            blueG.setFitHeight(40);
            blueG.setFitWidth(40);
            //gridPane.add(blueG, this.getPosY(),this.getPosX());
            ImageView fundoPr = new ImageView(fundoPreto);
            fundoPr.setFitHeight(40);
            fundoPr.setFitWidth(40);
            gridPane.add(fundoPr,this.getPosY(),this.getPosX());
            this.setPosX(this.getPosX()-1);
            this.setPosY(this.getPosY());
            mapa[this.getPosX()][this.getPosY()] = 'E';
            gridPane.add(blueG,this.getPosY(),this.getPosX());
        }
    }


    public boolean podeSerComido(){
        return this.podeSerComido;
    }

    public void setPodeSerComido(boolean podeSerComido){
        this.podeSerComido = podeSerComido;
    }

    public void moverFant(char[][] mapa, int[] array, Pacman pacman){
        ArrayList<Pair<Integer,Integer>> path = new ArrayList<>();
        wave.waveFront(array,pacman.getPosX(),pacman.getPosY(),mapa);
        path = wave.criar_caminho(array, pacman.getPosX(),pacman.getPosY(),this.getPosX(),this.getPosY());
        if(path.size() > 1) {
            mapa[this.getPosX()][this.getPosY()] = ' ';
            this.setPosX(path.get(1).getValue0());
            this.setPosY(path.get(1).getValue1());
        }
        Pair<Integer,Integer> pair;
        //mostrar o caminho com ".":
        if(path.size() > 1){
            for(int i = 2 ; i < path.size() - 1 ; i++){
                pair = path.get(i);
                mapa[pair.getValue0()][pair.getValue1()] = '-';
            }
        }
        mapa[this.getPosX()][this.getPosY()] = fantasma;
    }

    @Override
    public boolean vivoOuMorto() {
        return this.getVivo();
    }

}
