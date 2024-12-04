/**
 * @author Francisco Vasconcelos, Joao Francisco
 * @version 1.0
 */
import java.io.Serializable;
/**
 * class que gere os produtos farmaceuticos com prescricao
 */
public class Prescricao extends Farmacia implements Serializable{

    /**
     * construtor para a class Prescricao
     * @param nome nome do produto farmaceutico com prescricao
     * @param valor valor do produto farmaceutico com prescricao
     * @param codigo codigo do produto farmaceutico com prescricao
     * @param descricao descricao do produto farmaceutico com prescricao
     * @param quantidade quantidade do produto farmaceutico com prescricao
     */
    public Prescricao(String nome, double valor, int codigo, String descricao,int quantidade){
        super(nome, valor, codigo, descricao, quantidade);
    }

    /**
     * metodo para calcular o valor com prescricao do produto farmaceutico com prescricao
     * @param localizacao localizacao do cliente
     * @return valor do produto farmaceutico com prescricao
     */
    public double calculaIVA(int localizacao){
        switch (localizacao){
            case(1):
                valor += valor*0.06; //continente
                break;

            case(2):
                valor += valor*0.05; //madeira
                break;
            case(3):
                valor+= valor*0.04; //acores
        }
        return valor;
    }

}
