package Engine;

import java.util.ArrayList;
import java.util.Comparator;


// Essa classe implementa o algoritmo de WaveFront, o qual cada casa do tabuleiro é visitada e mapeada. Semelhante à busca
// "BFS", isto é, por largura.

/**
 * Definição da classe "Wave", responsável por fazer o algoritmo de WaveFront;
 * @author Fernando
 */
public class Wave {

    private static int vertical = 21;
    private static int horizontal = 19;

    /**
     * Método o qual passa os elementos de um array2D para um array1D.
     * @param x posição X no labirinto
     * @param y posição Y no labirinto
     * @return posição que será utilizada no array 1D
     */
    public static int p(int x,int y){
        return y*vertical + x;
    }

    /**
     * Método o qual faz o algoritmo de waveFront
     * @param array armazena os valores válidos do tabuleiro nesta variável
     * @param localizacaoPacX localização X do pacman
     * @param localizacaoPacY localização Y do pacman
     * @param mapa labirinto do pacman
     */
    public void waveFront(int[] array,int localizacaoPacX, int localizacaoPacY,char[][] mapa){

        for(int i = 0 ; i < vertical*horizontal ; i++){
            array[i] = 0;
        }
        for(int i = 0 ; i < vertical; i++){
            for(int j = 0 ; j < horizontal ; j++){
                //se o valor for válido, salva como 0 no array
                if(/*mapa[i][j] == 'S' || mapa[i][j] == 'P' || mapa[i][j] == 'B' || mapa[i][j] == 'E'*/ mapa[i][j] == 'W'){
                    //array[p(i,j)] = 0;
                    array[p(i,j)] = -1;
                }else { //caso contrario, ele seta como "-1" e não será usado no algoritmo.
                    //array[p(i,j)] = -1;
                    array[p(i,j)] = 0;
                }
            }
        }

        //Tripla contendo posicao x, posicao y, e um valor de distancia "d" do nodo. Nota-se que o nodo o qual o pacman está recebe o valor "1", e os nodos
        //do lado vao ter
        ArrayList<Triplet<Integer,Integer,Integer>> nodes = new ArrayList<>();

        nodes.add(new Triplet<>(localizacaoPacX,localizacaoPacY,1));
        int tempX;
        int tempY;
        boolean flagAchou = false;
        //ArrayList<Triplet<Integer,Integer,Integer>> new_nodes;
        while(!nodes.isEmpty()){

            ArrayList<Triplet<Integer,Integer,Integer>> new_nodes = new ArrayList<>();
            for(Triplet<Integer,Integer,Integer> n: nodes){


                int x = n.getValue0();
                int y = n.getValue1();
                int d = n.getValue2();
                Triplet<Integer,Integer,Integer> triplet;
                array[p(x,y)] = d;

                //checar leste
                if((x+1) < vertical - 1 && /*flowFieldZ.get(p(x+1,y)) == 0)*/ array[p(x+1,y)] == 0){
                    triplet = new Triplet<>(x+1,y,d+1);
                    for(Triplet<Integer,Integer,Integer> n2 : new_nodes){
                        tempX = n2.getValue0();
                        tempY = n2.getValue1();
                        if(tempX == x+1 && tempY == y){
                            flagAchou = true;
                        }
                    }
                    if(flagAchou == false)
                        new_nodes.add(triplet);
                    flagAchou = false;
                }

                //checar oeste
                if((x-1) >= 0 && /*flowFieldZ.get(p(x-1,y)) == 0)*/ array[p(x-1,y)] == 0){
                    triplet = new Triplet<>(x-1,y,d+1);
                    for(Triplet<Integer,Integer,Integer> n2 : new_nodes){
                        tempX = n2.getValue0();
                        tempY = n2.getValue1();
                        if(tempX == x-1 && tempY == y){
                            flagAchou = true;
                        }
                    }
                    if(flagAchou == false)
                        new_nodes.add(triplet);
                    flagAchou = false;
                }

                //checar sul
                if((y+1) < horizontal && /*flowFieldZ.get(p(x,y+1)) == 0)*/ array[p(x,y+1)] == 0){
                    triplet = new Triplet<>(x,y+1,d+1);
                    for(Triplet<Integer,Integer,Integer> n2 : new_nodes){
                        tempX = n2.getValue0();
                        tempY = n2.getValue1();
                        if(tempX == x && tempY == y+1){
                            flagAchou = true;
                        }
                    }
                    if(flagAchou == false)
                        new_nodes.add(triplet);
                    flagAchou = false;
                }

                //checar norte
                if(y-1 >= 0 && /*flowFieldZ.get(p(x-1,y)) == 0)*/ array[p(x,y-1)] == 0){
                    triplet = new Triplet<>(x,y-1,d+1);
                    for(Triplet<Integer,Integer,Integer> n2 : new_nodes){
                        tempX = n2.getValue0();
                        tempY = n2.getValue1();
                        if(tempX == x && tempY == y-1){
                            flagAchou = true;
                        }
                    }
                    if(flagAchou == false)
                        new_nodes.add(triplet);
                    flagAchou = false;
                }
            }
            flagAchou = false;
            nodes.clear();
            nodes.addAll(new_nodes);
        }

        //printMapa(mapa, flowFieldZ,localizacaoPacX,localizacaoPacY,localizacaoFantasmaX,localizacaoFantasmaY);
    }

    //Função que retorna uma Lista com "X,Y" do menor caminho possível do fantasma até o pacman.
    /**
     * Método o qual retorna uma lista de pares contendo o menor caminho possível do fantasma até o pacman.
     * @param array valores armazenados pelo algoritmo de wavefront
     * @param pacLocX localização X do pacman
     * @param pacLocY localização Y do pacman
     * @param fantLocX localização X do fantasma
     * @param fantLocY localização Y do fantasma
     * @return lista com o menor caminho possível
     */
    public ArrayList<Pair<Integer,Integer>> criar_caminho(int [] array, int pacLocX, int pacLocY, int fantLocX, int fantLocY){
        ArrayList<Pair<Integer,Integer>> path = new ArrayList<>();
        path.add(new Pair<Integer,Integer>(fantLocX,fantLocY));
        int nLocX = fantLocX;
        int nLocY = fantLocY;
        boolean bNoPath = false;

        while (!(nLocX == pacLocX && nLocY == pacLocY) && !bNoPath)
        {
            ArrayList<Triplet<Integer,Integer,Integer>> listNeighbours = new ArrayList<>();

            if ((nLocY - 1) >= 0 && array[p(nLocX, nLocY - 1)] > 0)
                listNeighbours.add( new Triplet<Integer,Integer,Integer>(nLocX, nLocY - 1, array[p(nLocX, nLocY - 1)]));

            if ((nLocX + 1) < vertical && array[p(nLocX + 1, nLocY)] > 0)
                listNeighbours.add(new Triplet<Integer,Integer,Integer>(nLocX+1, nLocY, array[p(nLocX+1, nLocY)]));

            if ((nLocY + 1) < horizontal && array[p(nLocX, nLocY + 1)] > 0)
                listNeighbours.add(new Triplet<Integer,Integer,Integer>(nLocX, nLocY + 1, array[p(nLocX, nLocY +1)]));

            if ((nLocX - 1) >= 0 && array[p(nLocX - 1, nLocY)] > 0)
                listNeighbours.add(new Triplet<Integer,Integer,Integer>(nLocX - 1, nLocY, array[p(nLocX - 1, nLocY)]));

            listNeighbours.sort(Comparator.comparing(Triplet::getValue2));

            if (listNeighbours.isEmpty()) { // Neighbour is invalid or no possible path
                bNoPath = true;
            }
            else
            {
                nLocX = listNeighbours.get(0).getValue0();
                nLocY = listNeighbours.get(0).getValue1();
                path.add(new Pair<Integer,Integer>(nLocX, nLocY));
            }
        }
        return path;
    }
}
