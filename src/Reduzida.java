/**
 * @author Francisco Vasconcelos, Joao Francisco
 * @version 1.0
 */

import java.util.Arrays;
import java.io.Serializable;
/**
 * class que gere os produtos alimentares de taxa reduzida
 */
public class Reduzida extends Alimentares implements Serializable{
    /**
     * lista de atributos do produto
     */
    String[] atributos;

    /**
     * construtor da class Reduzida
     * @param nome nome do produto alimentar de taxa reduzida
     * @param valor valor do produto alimentar de taxa reduzida
     * @param codigo codigo do produto alimentar de taxa reduzida
     * @param descricao descricao do produto alimentar de taxa reduzida
     * @param quantidade quantidade do produto alimentar de taxa reduzida
     * @param biologico se o produto alimentar de taxa reduzida é biologico ou nao
     * @param atributos atributos do produto alimentar de taxa reduzida
     */
    public Reduzida(String nome, double valor, int codigo,String descricao,int quantidade, boolean biologico, String[] atributos){
        super(nome, valor, codigo, descricao, quantidade, biologico);
        this.atributos = atributos;
    }

    /**
     * getter da lista de atributos do produto alimentar de taxa reduzida
     * @return lista de atributos do produto alimentar de taxa reduzida
     */
    public String[] getAtributos() {
        return atributos;
    }

    /**
     * setter da lista de atributos do produto alimentar de taxa reduzida
     * @param atributos atributos do produto alimentar de taxa reduzida
     */
    public void setAtributos(String[] atributos) {
        this.atributos = atributos;
    }

    /**
     * metodo que calcula o valor do produto alimentar de taxa reduzida com IVA
     * @param localizacao localizacao do cliente
     * @return valor do produto alimentar de taxa reduzida com IVA
     */
    public double calculaIVA(int localizacao){
        double desconto =0;
        if(atributos.length ==4){
            desconto = 0.01;
        }
        if(biologico){
            desconto += 0.10;
        }
        switch (localizacao){
            case(1):
                valor += valor*(0.06 - desconto); //continente
                break;

            case(2):
                valor += valor*(0.05-desconto); //madeira
                break;
            case(3):
                valor+= valor*(0.04-desconto); //acores
        }
        return valor;
    }

    @Override
    public String toString() {
        return "Reduzida{" +
                "atributos=" + Arrays.toString(atributos) +
                '}';
    }
}
