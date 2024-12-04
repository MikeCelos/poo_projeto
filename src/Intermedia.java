/**
 * @author Francisco Vasconcelos, Joao Francisco
 * @version 1.0
 */
import java.io.Serializable;
import java.util.Arrays;

/**
 * class que gere os produtos de taxas intermedias
 * dos produtos alimentares
 */
public class Intermedia extends Alimentares implements Serializable{
    /**
     * lista de categorias
     */
    private String[] categorias;

    /**
     * construtor da class intermedia
     * @param nome nome do produto alimentar de class intermedia
     * @param valor valor do produto alimentar de class intermedia
     * @param codigo codigo do produto alimentar de class intermedia
     * @param descricao descricao do produto alimentar de class intermedia
     * @param quantidade qunatidade do produto alimentar de class intermedia
     * @param biologico se o produto alimentar de class intermedia é biologico ou nao
     * @param categorias categorias do produto alimentar de class intermedia
     */
    public Intermedia(String nome, double valor, int codigo,String descricao,int quantidade, boolean biologico, String[] categorias){
        super(nome, valor, codigo, descricao, quantidade, biologico);
        this.categorias = categorias;
    }

    /**
     * getter das categorias do produto alimentar de class intermedia
     * @return categorias do produto alimentar de class intermedia
     */
    public String[] getCategorias() {
        return categorias;
    }

    /**
     * setter das categorias do produto alimentar de class intermedia
     * @param categorias categorias do produto alimentar de class intermedia
     */
    public void setCategorias(String[] categorias) {
        this.categorias = categorias;
    }


    public double calculaIVA(int localizacao) {
        double desconto = 0;
        for (String categoria : categorias) {
            if (categoria.equalsIgnoreCase("vinho")) {
                desconto = 0.01;
                break; // No need to continue checking after finding "vinho"
            }
        }
        if (isBiologico()) {
            desconto += 0.10;
        }

        double taxaBase;
        switch (localizacao) {
            case 1:
                taxaBase = 0.13;
                break;
            case 2:
                taxaBase = 0.12;
                break;
            case 3:
                taxaBase = 0.09;
                break;
            default:
                throw new IllegalArgumentException("Localizacao invalida");
        }
        return getValor() * (1 + taxaBase + desconto); // Return the new value without modifying 'valor'
    }


    @Override
    public String toString() {
        return "Intermedia{" +
                "categorias=" + Arrays.toString(categorias) +
                '}';
    }

}
