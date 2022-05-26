package Interface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.Key;
import static javafx.application.Application.launch;
/**
 * Classe "Main" do JavaFX
 * @author Fernando Cardoso Braghiroli
 */
public class PacMan extends Application {
/**
 * Método o qual inicia a aplicação
 * @param stage - Estagio inicial da aplicação
 */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PacMan.class.getResource("FXMLDocument.fxml"));

        Image pacDotGrande = new Image("file:src/main/java/com/example/demo/pacDotGrande.png");

        Image pacDot = new Image("file:src/main/java/com/example/demo/pacDotPequeno.png");

        Image parede = new Image("file:src/main/java/com/example/demo/parede.png");

        Scene scene = new Scene(fxmlLoader.load(),400,400 /*gridPane*/);
        scene.getRoot().requestFocus();
        stage.setTitle("Pacman");
        stage.setScene(scene);
        stage.setMinHeight(1000);
        stage.setMinWidth(1000);
        stage.setFullScreen(true);

        stage.show();

    }

    public static void main(String[] args) {

        launch();
    }
}