package Interface;

import Elementos.Fantasma;
import Elementos.Frutas;
import Elementos.Pacman;
import Engine.Pair;
import Engine.Wave;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class PacManController implements Initializable{

    //declaracoes de variaveis globais
    private ArrayList<Integer> marcadorMovimentos = new ArrayList<>();
    
    private int nFantasmas = 2;

    private char[][] mapa = new char[21][19];

    private int pacPontosTemp = 0;
    
    private Pacman pacman = new Pacman("Pacman",3,0,0);

    private Wave wave = new Wave();

    private Fantasma pinky = new Fantasma("Pinky",-1,0,0);

    private Fantasma blinky = new Fantasma("Blinky",-1,0,0);

    private Image pacDotGrande = new Image("file:src/imagens/pacDotGrande.png");

    private Image pacDot = new Image("file:src/imagens/pacDotPequeno.png");

    private Image parede = new Image("file:src/imagens/parede.png");

    private Image pacDireita = new Image("file:src/imagens/pacmanRight.gif");

    private Image fantRosa = new Image("file:src/imagens/ghost1.gif");

    private Image fundoPreto = new Image("file:src/imagens/black.png");

    private Image pacEsquerda = new Image("file:src/imagens/pacmanLeft.gif");

    private Image pacCima = new Image("file:src/imagens/pacmanUp.gif");

    private Image pacBaixo = new Image("file:src/imagens/pacmanDown.gif");

    private Image blueGhost = new Image("file:src/imagens/blueGhost.gif");

    private Image blinkyImage = new Image("file:src/imagens/blinky.gif");

    private Frutas frutas = new Frutas();
     
    //marcador de niveis (vai até 3)
    private int nivel = 1;

    
    //flag a qual é falsa no jogo normal e true quando pega a pílula de poder
    private boolean flag = false;

    @FXML
    private GridPane gridPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        //cria labels
        Label score = new Label("Score: ");
        Label pontuacao = new Label("0");
        
        //configura gridPane
        gridPane.setMinSize(100, 120);
        gridPane.setStyle("-fx-background-color: #000000;");
        gridPane.setAlignment(Pos.CENTER);
           
        //cria o labirinto do pacman
        redesenharMapa();
        //coloca as vidas do pacman
        setCoracoes();
    }

    public void onKeyTyp(KeyEvent keyEvent) throws InterruptedException{
        onKeyPres(keyEvent);
    }
    
    
    public void onKeyPres(KeyEvent keyEvent) throws InterruptedException {
        //coloca as vidas
        setCoracoes(keyEvent);
        //método que adiciona fruta se a pontuacao for a correspondente
        frutas.adicionarFruta(pacman.getPontuacao(), gridPane,mapa,nivel);
        
        int posX = 0;
        int posY = 0;
        //pega a posicao do pacman na matriz do mapa
        for(int i = 0 ; i < 21; i++){
            for(int j = 0 ; j < 19; j++){
                if(mapa[i][j] == 'P'){
                    posX = i;
                    posY = j;

                    pacman.setPosX(i);
                    pacman.setPosY(j);
                    break;
                }
            }
        }
       

        //Cria algumas imageview para a movimentacao do pacman
        ImageView pacD = new ImageView(pacDireita);
        ImageView fundoP = new ImageView(fundoPreto);
        ImageView pacE = new ImageView(pacEsquerda);
        ImageView pacC = new ImageView(pacCima);
        ImageView pacB = new ImageView(pacBaixo);

        //ajusta as imagens
        fundoP.setFitWidth(40);
        fundoP.setFitHeight(40);
        pacD.setFitHeight(40);
        pacD.setFitWidth(40);
        pacE.setFitHeight(40);
        pacE.setFitWidth(40);
        pacC.setFitHeight(40);
        pacC.setFitWidth(40);
        pacB.setFitHeight(40);
        pacB.setFitWidth(40);

        //variaveis temporarias a serem modificadas n timeline
        int finalPosY = posY;
        int finalPosX = posX;

        //animacao do Pacman (movimentacao)
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1), (e) ->{
            //ajusta o fundo preto, que é utilizado para substituir a posição passada do pacman pelo mesmo background em preto
            ImageView fundoPr = new ImageView(fundoPreto);
            fundoPr.setFitHeight(30);
            fundoPr.setFitWidth(30);
            
            gridPane.add(fundoPr,10,25);
               
            //atualiza a pontuacao com o movimento
            Label pontuacao = new Label(pacman.getPontuacao() + "");
            pontuacao.setStyle("-fx-text-fill: #FFFFFF;-fx-width: 100;");

            //ajuste da pontuacao
            pontuacao.setMinSize(30.0,30.0);
            pontuacao.setFont(new Font(10.0));
            pontuacao.setMinWidth(Region.USE_PREF_SIZE);
            pontuacao.setMaxWidth(Region.USE_PREF_SIZE);

            gridPane.add(pontuacao,10,25);

            //pega o movimento das teclas do teclado
            //dentro do case, modifica o gridPane e a matriz, colocando uma posicao vazia onde o pacman estava e
            //colocando o pacman na posicao futura, isto é, na posicao a qual o usuario apertou a tecla.
            switch(keyEvent.getCode()){
                case RIGHT:
                    if(mapa[finalPosX][finalPosY +1] != 'W') {
                        pacPontosTemp += pacman.incrementaPontuacao(mapa[finalPosX][finalPosY+1],pinky);
                       
                        mapa[finalPosX][finalPosY] = 'E';
                        gridPane.add(fundoP, finalPosY, finalPosX);
                        gridPane.add(pacD, finalPosY +1, finalPosX);
                        mapa[finalPosX][finalPosY + 1] = 'P';
                    }
                    break;

                case LEFT:
                    if(mapa[finalPosX][finalPosY -1] != 'W') {
                        pacPontosTemp += pacman.incrementaPontuacao(mapa[finalPosX][finalPosY-1],pinky);
                   
                        mapa[finalPosX][finalPosY] = 'E';
                        gridPane.add(fundoP, finalPosY, finalPosX);
                        gridPane.add(pacE, finalPosY - 1, finalPosX);
                        mapa[finalPosX][finalPosY - 1] = 'P';
                    }
                    break;

                case UP:
                    if(mapa[finalPosX -1][finalPosY] != 'W'){
                        pacPontosTemp += pacman.incrementaPontuacao(mapa[finalPosX-1][finalPosY],pinky);
                      
                        mapa[finalPosX][finalPosY] = 'E';
                        gridPane.add(fundoP, finalPosY, finalPosX);
                        gridPane.add(pacC, finalPosY, finalPosX -1);
                        mapa[finalPosX -1][finalPosY] = 'P';
                    }
                    break;

                case DOWN:
                    if(mapa[finalPosX +1][finalPosY] != 'W'){
                        pacPontosTemp += pacman.incrementaPontuacao(mapa[finalPosX+1][finalPosY],pinky);
                       
                        mapa[finalPosX][finalPosY] = 'E';
                        gridPane.add(fundoP, finalPosY, finalPosX);
                        gridPane.add(pacB, finalPosY, finalPosX +1);
                        mapa[finalPosX +1][finalPosY] = 'P';
                    }
                    break;

                default:
                    break;
            }


        }));
        //roda a animacao
        timeline.play();

        //se nao estiver no modo azul (pilula de poder)
        if(flag == false) {
            //invoca o algoritmo de WaveFront (BFS)
            int[] array1D = new int[21 * 19];

            wave.waveFront(array1D, pacman.getPosX(), pacman.getPosY(), mapa);
            ArrayList<Pair<Integer, Integer>> caminho = new ArrayList<>();
            caminho = wave.criar_caminho(array1D, pacman.getPosX(), pacman.getPosY(), pinky.getPosX(), pinky.getPosY());

            ArrayList<Pair<Integer, Integer>> finalCaminho = caminho;

               //cria a movimentacao do Pinky (menor caminho)
            Timeline moverPinky = new Timeline(new KeyFrame(Duration.millis(1), (e) -> {
                int i = 0;
                for (Pair<Integer, Integer> coordenadas : finalCaminho) {
                    //configura algumas imagens
                    ImageView pacDd = new ImageView(pacDot);
                    pacDd.setFitWidth(40);
                    pacDd.setFitHeight(40);

                    ImageView fantR = new ImageView(fantRosa);
                    fantR.setFitHeight(40);
                    fantR.setFitWidth(40);

                    ImageView fundoPr = new ImageView(fundoPreto);
                    fundoPr.setFitWidth(40);
                    fundoPr.setFitHeight(40);
                    
                    //marca como vazio o espaço o qual o fantasma estava
                    mapa[pinky.getPosX()][pinky.getPosY()] = 'E';

                    //gridPane.add(fundoPr,pinky.getPosY(),pinky.getPosX());
                      
                    gridPane.add(pacDd, pinky.getPosY(), pinky.getPosX());

                    gridPane.add(fundoPr, pinky.getPosY(), pinky.getPosX());
                    
                    //pega as coordenadas do menor caminho
                    pinky.setPosX(coordenadas.getValue0());
                    pinky.setPosY(coordenadas.getValue1());

                    gridPane.add(fantR, pinky.getPosY(), pinky.getPosX());
                    i++;
                    //quando i = 1, quer dizer que estava na primeira execucao, e as coordenadas
                    //que foram pegas é a da posicao original do fantasma, e nao da que ele vai se mover.
                    //para isso, i precisa ser = 2.
                    if (i == 2)
                        break;
                }
                //move o blinky, aleatoriamente, junto com o pinky
                blinky.mover(mapa,keyEvent,fundoPreto,blinkyImage,gridPane);
            }));
               
            //roda a animacao
            moverPinky.play();
            
            //checa se a posicao esta igual. Se for igual e o fantasma nao puder ser comido, quer dizer que o jogador vai perder uma vida. Se puder ser comido, movimenta
            //normalmente.
            if (pinky.getPosY() == pacman.getPosY() && pinky.getPosX() == pacman.getPosX() || (blinky.getPosY() == pacman.getPosY() && blinky.getPosX()
                    == pacman.getPosX())) {
                if (!pinky.podeSerComido()) {
                    pacman.decrementaNVidas();
                    redesenharMapa();
                    moverPinky.stop();
                    timeline.stop();
                }else{
                    moverPinky.play();
                    timeline.play();
                    blinky.mover(mapa,keyEvent,fundoPreto,blinkyImage,gridPane);
                }
            }
        }
        //se estiver no modo azul
            if(pinky.podeSerComido()){
              
                    
                    marcadorMovimentos.add(nivel);
                    
                    mapa[pacman.getPosX()][pacman.getPosY()] = 'P';
                    
                    
                    ImageView pacDdd = new ImageView(pacDireita);
                    pacDdd.setFitHeight(40);
                    pacDdd.setFitWidth(40);
                    gridPane.add(pacDdd,pacman.getPosY(),pacman.getPosX());

                Timeline pinkyAzul = new Timeline(new KeyFrame(Duration.millis(1),(e) ->{

                    pinky.mover(mapa,keyEvent,fundoPreto,blueGhost,gridPane);

                    blinky.mover(mapa,keyEvent,fundoPreto,blueGhost,gridPane);

                }));
                //roda a animação do modo azul
                pinkyAzul.play();
                
                //se o pinky e o pacman estiverem na mesma posicao, o pinky vai ser comido e o modo azul se encerra.
                if (pinky.getPosY() == pacman.getPosY() && pinky.getPosX() == pacman.getPosX()) {
                    //limpa a lista
                    marcadorMovimentos.clear();
                    //adiciona as imagens
                    pacman.setPontuacao(pacman.getPontuacao()+200);
                    ImageView fundoPr = new ImageView(fundoPreto);
                    fundoPr.setFitHeight(40);
                    fundoPr.setFitWidth(40);
                    pacman.setPosX(pinky.getPosX());
                    pacman.setPosY(pinky.getPosY());
                    gridPane.add(fundoPr,pinky.getPosY(),pinky.getPosX());
                    ImageView pacDd = new ImageView(pacDireita);
                    pacDd.setFitHeight(40);
                    pacDd.setFitWidth(40);
                    gridPane.add(pacDd,pacman.getPosY(),pacman.getPosX());
                    
                    mapa[pacman.getPosX()][pacman.getPosY()] = 'P';
                    
                    pinky.setPosY(9);
                    pinky.setPosX(8);
                    //coloca como pode ser comido e sai do modo azul
                    pinky.setPodeSerComido(false);
                    //flag para executar o modo normal
                    flag = false;
                    //para a animacao do modo azul
                    pinkyAzul.stop();
                    timeline.play();
                    //mesma coisa, mas para o blinky
                }else if(blinky.getPosY() == pacman.getPosY() && blinky.getPosX() == pacman.getPosX()){
                    marcadorMovimentos.clear();
                    pacman.setPontuacao(pacman.getPontuacao()+200);
                    
                    ImageView fundoPr = new ImageView(fundoPreto);
                    fundoPr.setFitHeight(40);
                    fundoPr.setFitWidth(40);
                    
                    pacman.setPosX(blinky.getPosX());
                    pacman.setPosY(blinky.getPosY());
                    
                    mapa[pacman.getPosX()][pacman.getPosY()] = 'P';
                    gridPane.add(fundoPr,blinky.getPosY(),blinky.getPosX());
                    
                    ImageView pacDd = new ImageView(pacDireita);
                    pacDd.setFitHeight(40);
                    pacDd.setFitWidth(40);
                    gridPane.add(pacDd,pacman.getPosY(),pacman.getPosX());
                    
                    blinky.setPosY(9);
                    blinky.setPosX(8);;
                    pinky.setPodeSerComido(false);
                    flag = false;
                    pinkyAzul.stop(); 
                    timeline.play();
                   
                }
                //se o pacman se mover 18 vezes, para o modo azul.
               else if(marcadorMovimentos.size() == 26){
                    marcadorMovimentos.clear();
                    pacPontosTemp = 0;
                    
                    flag = false;
                    pinky.setPodeSerComido(false);
                    pinkyAzul.stop();
                    timeline.play();
                }
                else{       
                    flag = true;
                }
               
            }
            //checa se ja ganhou. Se sim, o metodo aumenta o nivel e redesenha o mapa.
            if(jaGanhou(keyEvent))
                redesenharMapa();
        }

      
        /** Método para verificar se todos os pacdots já foram consumidos, isto é, verificar se já ganhou
         * 
         * @param keyEvent - Evento do teclado
         * @return Boolean - Se já ganhou ou não
         */
        public boolean jaGanhou(KeyEvent keyEvent){
            
            //percorre o mapa
            for(int i = 0 ; i < 21; i++){
                for(int j = 0 ; j < 19; j++){
                    if(mapa[i][j] == 'S'){
                        return false;
                    }
                }
            }
            //reseta os pontos temporarios
            pacPontosTemp = 0;
            //aumenta o nivel
            nivel++;
            //se o nivel chegar no 4 (nao existe, entao para o programa)
            if(nivel == 4){
                //cria novo gridPane, exclui o stage antigo, e exibe uma tela nova.                            
                GridPane gridPaneAlert = new GridPane();
                gridPaneAlert.setMinSize(800,400);
                
                Node  source = (Node)  keyEvent.getSource();
                Stage stage  = (Stage) source.getScene().getWindow();
                stage.close();

                stage.setScene(new Scene(gridPaneAlert));
                stage.show();
            }
            return true;
        }
        
        /**
         * Método o qual redesnha o mapa (labirinto do pacman) toda vez que o usuário perder, passar pro próximo nível, e quando o jogo é iniciado.
         */
        public void redesenharMapa(){
            //sem modo azul
            flag = false;
            pinky.setPodeSerComido(false);
            //limpa o gridPane
            gridPane.getChildren().clear();
            
            
            //preenche tudo com o fundo preto
            for(int k = 0 ; k < 21; k++){
                for(int j = 0 ; j < 19 ; j++) {
                    ImageView fundoP = new ImageView(fundoPreto);
                    fundoP.setFitHeight(40);
                    fundoP.setFitWidth(40);
                    gridPane.add(fundoP,k,j);
                    }
                }
            //variaveis usadas pra ler os arquivos txt, os quais contem os niveis
            String linha;
            int i = 0;
            BufferedReader bufferedReader = null;

            try {
                bufferedReader = new BufferedReader(new FileReader("src/imagens/nivel"+ nivel +".txt"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //ler do arquivo de nível
            try {
                while (((linha  = bufferedReader.readLine()) != null)){
                    System.out.println("lendo");
                    char[] linhaArray = linha.toCharArray();
                    mapa[i] = linhaArray;
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            //cria as labels de novo
            Label score = new Label("Score: ");
            Label pontuacao = new Label("0");
            //configura o gridPane conforme a matriz lida
            for(int k = 0 ; k < 21; k++){
                for(int j = 0 ; j < 19 ; j++){
                    if(mapa[k][j] == 'W'){
                        ImageView paredeIV = new ImageView(parede);
                        paredeIV.setImage(parede);
                        paredeIV.setFitWidth(40);
                        paredeIV.setFitHeight(40);
                        gridPane.add(paredeIV,j,k);
                    }else if(mapa[k][j] == 'S'){
                        ImageView pD = new ImageView(pacDot);
                        pD.setFitWidth(40);
                        pD.setFitHeight(40);
                        gridPane.add(pD,j,k);
                    }else if(mapa[k][j] == 'B'){
                        ImageView pDG = new ImageView(pacDotGrande);
                        pDG.setFitWidth(40);
                        pDG.setFitHeight(40);
                        gridPane.add(pDG,j,k);
                    }else if(mapa[k][j] == 'P'){
                        ImageView pacD = new ImageView(pacDireita);
                        pacman.setPosX(k);
                        pacman.setPosY(j);
                        pacD.setFitWidth(40);
                        pacD.setFitHeight(40);
                        gridPane.add(pacD,j,k);
                    } else if(mapa[k][j] == '1'){
                        pinky.setPosX(k);
                        pinky.setPosY(j);
                        ImageView fantR = new ImageView(fantRosa);
                        fantR.setFitWidth(40);
                        fantR.setFitHeight(40);
                        gridPane.add(fantR,j,k);
                    } else if(mapa[k][j] == '2'){
                        blinky.setPosX(k);
                        blinky.setPosY(j);
                        ImageView blinkyI = new ImageView(blinkyImage);
                        blinkyI.setFitWidth(40);
                        blinkyI.setFitHeight(40);
                        gridPane.add(blinkyI,j,k);
                    } 
                }
            }
            
            //estiliza o score
            score.setStyle("-fx-text-fill: #FFFFFF;-fx-width: 100;");
            score.setMinSize(30.0,30.0);
            score.setFont(new Font(30.0));
            score.setMinWidth(Region.USE_PREF_SIZE);
            score.setMaxWidth(Region.USE_PREF_SIZE);

            pontuacao.setStyle("-fx-text-fill: #FFFFFF;-fx-width: 100;");

            pontuacao.setMinSize(30.0,30.0);
            pontuacao.setFont(new Font(30.0));
            pontuacao.setMinWidth(Region.USE_PREF_SIZE);
            pontuacao.setMaxWidth(Region.USE_PREF_SIZE);

            pacman.setPontuacao(0);

            gridPane.add(score,0,25);

            gridPane.add(pontuacao,10,25);

            //mapa = mapaOriginal.clone();


        }

        /**
         * Método o qual exibe os corações em cima da tela, indicando quantas vidas restam ao usuário
         * @param keyEvent - Evento do teclado 
         */
        public void setCoracoes(KeyEvent keyEvent){
            Image coracaoImage = new Image("file:src/imagens/pixelheart.jpg");
            ImageView pare = new ImageView(parede);
            pare.setFitHeight(40);
            pare.setFitWidth(40);

            if(pacman.getnVidas() == 0){
               Image gameOver = new Image("file:src/imagens/gameOver.gif");
               
                ImageView gameOverView = new ImageView(gameOver);
                gameOverView.setFitHeight(400);
                gameOverView.setFitWidth(800);
                GridPane gridPaneAlert = new GridPane();
                gridPaneAlert.setStyle("-fx-background-color: #000000;");
                
                gridPaneAlert.setMinSize(800,400);
                gridPaneAlert.setAlignment(Pos.CENTER);
                gridPaneAlert.add(gameOverView,0,0);
                
                Node  source = (Node)  keyEvent.getSource();
                Stage stage  = (Stage) source.getScene().getWindow();
                stage.close();

                stage.setScene(new Scene(gridPaneAlert));
                
                stage.show();
            }

            for(int i = 0; i < pacman.getnVidas(); i++){
                ImageView coracao = new ImageView(coracaoImage);
                coracao.setFitWidth(40);
                coracao.setFitHeight(40);
                gridPane.add(coracao,i,0);
            }
            for(int i = 0 ; i < 3 - pacman.getnVidas(); i++){
                ImageView wall = new ImageView(parede);
                wall.setFitWidth(40);
                wall.setFitHeight(40);
                gridPane.add(wall, 3 - i ,0);
            }
        }
        
        /**
         * Método o qual seta os corações, sem o evento do teclado
         */
        public void setCoracoes(){
        Image coracaoImage = new Image("file:src/imagens/pixelheart.jpg");
        ImageView pare = new ImageView(parede);
        pare.setFitHeight(40);
        pare.setFitWidth(40);

        for(int i = 0; i < pacman.getnVidas(); i++){
            ImageView coracao = new ImageView(coracaoImage);
            coracao.setFitWidth(40);
            coracao.setFitHeight(40);
            gridPane.add(coracao,i,0);
        }
        for(int i = 0 ; i < 3 - pacman.getnVidas(); i++){
            ImageView wall = new ImageView(parede);
            wall.setFitWidth(40);
            wall.setFitHeight(40);
            gridPane.add(wall, 3 - i ,0);
        }
    }
        public void setModoAzul(KeyEvent keyEvent){
                Timeline pinkyAzul = new Timeline(new KeyFrame(Duration.millis(1),(e) ->{

                pinky.mover(mapa,keyEvent,fundoPreto,blueGhost,gridPane);

                blinky.mover(mapa,keyEvent,fundoPreto,blueGhost,gridPane);

            }));

            pinkyAzul.play();
        }

}


