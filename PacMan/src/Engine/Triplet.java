package Engine;

/**
 * Definição do tipo Triplet; semelhante ao tipo pair com 3 valores em C++
 * @author Fernando
 * @param <T> tipo genérico
 * @param <E> tipo genérico
 * @param <P> tipo genérico
 */
public class Triplet<T,E,P> {
    
    private T value0;
    private E value1;
    private P value2;

    /**
     * Construtor da Tripla
     * @param value0
     * @param value1
     * @param value2 
     */
    public Triplet(T value0, E value1, P value2) {
        this.value0 = value0;
        this.value1 = value1;
        this.value2 = value2;
    }

    /**
     * Método o qual retorna o primeiro valor da tripla
     * @return T - Primeiro valor da tripla 
     */
    public T getValue0() {
        return value0;
    }
    /**
     * Método o qual seta o primeiro valor da tripla
     * @param value0 - Primeiro valor da tripla
     */
   
    public void setValue0(T value0) {
        this.value0 = value0;
    }

    /**
     * Método o qual retorna o segundo valor da tripla
     * @return E - Segundo valor da tripla
     */
    public E getValue1() {
        return value1;
    }

    /**
     * Método o qual seta o segundo valor da tripla
     * @param value1 - Segundo valor da trilpa
     */
    public void setValue1(E value1) {
        this.value1 = value1;
    }

    /**
     * Método o qual retorna o terceiro valor da tripla
     * @return P - Terceiro valor da tripla
     */
    public P getValue2() {
        return value2;
    }

    /**
     * Método o qual seta o terceiro valor da tripla
     * @param value2 - Terceiro valor da tripla
     */
    public void setValue2(P value2) {
        this.value2 = value2;
    }





}
