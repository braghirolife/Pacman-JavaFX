package Elementos;

public abstract class Personagem implements ComandosBasicos{
    private String nome;
    private int nVidas;
    private boolean vivo = true;
    private int posX;
    private int posY;


    public Personagem(String nome, int nVidas, int posX, int posY){
        this.nome = nome;
        nVidas = this.nVidas;
        if(!nome.equals("Pac")){
            this.nVidas = -1; //fantasmas que sao imortais
        }
        this.posX = posX;
        this.posY = posY;
    }

    public boolean getVivo(){
        return this.vivo;
    }

    public void setVivo(boolean vivo){
        this.vivo = vivo;
    }

    public int getPosX(){
        return this.posX;
    }

    public int getPosY(){
        return this.posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public abstract void setPosMapa(char[][] mapa);
}
