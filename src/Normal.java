/**
 * @author Francisco Vasconcelos, Joao Francisco
 * @version 1.0
 */

/**
 * class que gere os produtos alimentares de texa normal
 */
public class Normal extends Alimentares{

    /**
     * construtor da class Normal
     * @param nome nome do produto alimentar de taxa normal
     * @param valor valor do produto alimentar de taxa normal
     * @param codigo codigo do produto alimentar de taxa normal
     * @param descricao descricao do produto alimentar de taxa normal
     * @param quantidade qunatidade do produto alimentar de taxa normal
     * @param biologico se o produto alimentar de taxa normal é biologico ou nao
     */
    public Normal(String nome, double valor, int codigo,String descricao,int quantidade, boolean biologico){
        super(nome, valor, codigo, descricao, quantidade, biologico);
    }

    /**
     * metodo que calcula o valor do produto alimentar de taxa normal com IVA
     * @param valor valor do produto alimentar de taxa normal
     * @param localizacao localizacao do cliente
     * @param biologico se o produto alimentar de taxa normal é biologico ou nao
     * @return valor do produto alimentar de taxa normal com IVA
     */
    public double calculaIVA(int valor, int localizacao, boolean biologico){
        int desconto =0;
        if(biologico){
            desconto += 0.10;
        }
        switch (localizacao){
            case(1):
                valor += valor*0.23; //continente
                break;

            case(2):
                valor += valor*0.22; //madeira
                break;
            case(3):
                valor+= valor*0.16; //acores

        }
        return valor;
    }
}
