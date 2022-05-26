package Engine;

/**
 * Definição do tipo Pair (semelhante a pair em C++)
 * @author Fernando
 * @param <T> tipo genérico
 * @param <E> tipo genérico
 */
public class Pair<T,E> {

    T valor0;
    E valor1;

    /**
     * Construtor
     * @param valor0 - Primeiro valor do par
     * @param valor1 - Segundo valor do par
     */
    public Pair(T valor0, E valor1){
        this.valor0 = valor0;
        this.valor1 = valor1;
    }
    /**
     * Método o qual retorna o primeiro valor do par
     * @return T - Primeiro valor
     */
    public T getValue0(){
        return this.valor0;
    }

    /**
     * Método o qual retorna o segundo valor do par
     * @return E - Segundo valor 
     */
    public E getValue1(){
        return this.valor1;
    }
}
