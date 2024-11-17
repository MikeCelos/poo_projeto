/**
 * @author Francisco Vasconelos, Joao Francisco
 * @version 1.0
 */

/**
 * class para gerir os produtos alimentares
 */
public class Alimentares extends Produto{
    /**
     * se o produto é biologico
     */
    private boolean biologico;

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
     * @param biologico
     */
    public void setBiologico(boolean biologico) {
        this.biologico = biologico;
    }
}
