/**
 * @author Francisco Vasconcelos, Joao Francisco
 * @version 1.0
 */
import java.io.Serializable;
/**
 * class que gere os produtos farmaceutico
 */
public abstract class Farmacia extends Produto implements Serializable{

    /**
     * contrutor da class farmacia
     * @param nome nome do produto farmaceutico
     * @param valor valor do produto farmaceutico
     * @param codigo codigo do produto farmaceutico
     * @param descricao descricao do produto farmaceutico
     * @param quantidade quantidade do produto farmaceutico
     */
    public Farmacia(String nome, double valor, int codigo,String descricao,int quantidade){
        super(nome, valor, codigo, descricao, quantidade);
    }

    /**
     * metodo abstrato que calcula o IVA
     * @param localizacao localização do cliente
     * @return valor do produto com o iva
     */
    public abstract double calculaIVA(int localizacao);
}
