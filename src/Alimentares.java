/**
 * @author Francisco Vasconelos, Joao Francisco
 * @version 1.0
 */

import java.io.Serializable;
/**
 * class para gerir os produtos alimentares
 */
public abstract class Alimentares extends Produto implements Serializable {
    /**
     * se o produto é biologico
     */
    protected boolean biologico;

    /**
     * construtor da class do produtos alimentares
     * @param nome nome do produto
     * @param valor valor do produto
     * @param codigo codigo do produto
     * @param descricao descricao do produto
     * @param quantidade quantidade de produto
     * @param biologico se o produto é biologico ou nao
     */
    public Alimentares(String nome, double valor, int codigo,String descricao,int quantidade, boolean biologico){
        super(nome, valor, codigo, descricao, quantidade);
        this.biologico = biologico;
    }

    /**
     * getter do biologico
     * @return
     */
    public boolean isBiologico() {
        return biologico;
    }

    /**
     * setter do biologico
     * @param biologico se o produto é biologico ou nao
     */
    public void setBiologico(boolean biologico) {
        this.biologico = biologico;
    }

    @Override
    public String toString() {
        return "Alimentares{" +
                "biologico=" + biologico +
                '}';
    }

    /**
     * metodo abstrato que calcula o IVA
     * @param localizacao localização do cliente
     * @return valor do produto com o iva
     */
    public abstract double calculaIVA(int localizacao);
}
