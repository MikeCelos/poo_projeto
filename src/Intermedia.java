/**
 * @author Francisco Vasconcelos, Joao Francisco
 * @version 1.0
 */

/**
 * class que gere os produtos de taxas intermedias
 * dos produtos alimentares
 */
public class Intermedia extends Alimentares{
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

    /**
     * metodo que calcula o valor do produto alimentar de class intermedia
     * com iva
     * @param valor valor do produto alimentar de class intermedia
     * @param localizacao localizacao do cliente
     * @param categorias categorias do produto alimentar de class intermedia
     * @param biologico se o produto alimentar de class intermedia é biologico ou nao
     * @return valor do produto alimentar de class intermedia com iva
     */
    public double calculaIVA(int valor, int localizacao, String[] categorias, boolean biologico){
        double desconto=0;
        for(int i =0; i<categorias.length; i++){
            if(categorias[i].equalsIgnoreCase("vinho")){
                desconto = 0.01;
            }
        }
        if(biologico){
            desconto += 0.10;
        }
        switch (localizacao){
            case(1):
                valor += valor*(0.13 + desconto);
                break;

            case(2):
                valor += valor*(0.12 + desconto);
                break;
            case(3):
                valor+= valor*(0.09 + desconto);

        }
        return valor;
    }
}
