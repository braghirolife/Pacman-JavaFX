package Elementos;


import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
/**
 * Interface a qual implementa uns comandos basicos
 * @author Fernando
 */
public interface ComandosBasicos {
    /**
     * 
     * @param mapa - Labirinto do PacMan
     * @param keyEvent - Evento do teclado
     * @param fundoPreto - Imagem do fundo preto
     * @param fantImage - Imagem do fantasma
     * @param gridPane - Grid Pane para ser modificado 
     */
    public void mover(char[][] mapa, KeyEvent keyEvent, Image fundoPreto, Image fantImage, GridPane gridPane);
    //funcionalidade que verificara se o pac ou o fantasma estao vivos
    public boolean vivoOuMorto();
   
}