/**
 * @author Francisco Vasconcelos, Joao Francisco
 * @version 1.0
 */
import java.io.Serializable;
import java.util.Arrays;

/**
 * class que gere os produtos de farmacia com taxa de prescricao normal
 */
public class NormalPrescricao extends Farmacia implements Serializable{

    /**
     * lista das categorias do produto farmaceutico com taxa de prescricao normal
     */
    protected String[] categorias;

    /**
     * contrutor da class NormalPrescricao
     * @param nome nome do produto farmaceutico com taxa de prescricao normal
     * @param valor valor do produto farmaceutico com taxa de prescricao normal
     * @param codigo codigo do produto farmaceutico com taxa de prescricao normal
     * @param descricao descricao do produto farmaceutico com taxa de prescricao normal
     * @param quantidade quantidade do produto farmaceutico com taxa de prescricao normal
     * @param categorias categorias do produto farmaceutico com taxa de prescricao normal
     */
    public NormalPrescricao(String nome, double valor, int codigo,String descricao,int quantidade, String[] categorias){
        super(nome, valor, codigo, descricao, quantidade);
        this.categorias = categorias;
    }

    /**
     * getter das categorias do produto farmaceutico com taxa de prescricao normal
     * @return categorias do produto farmaceutico com taxa de prescricao normal
     */
    public String[] getCategorias() {
        return categorias;
    }

    /**
     * setter das categorias do produto farmaceutico com taxa de prescricao normal
     * @param categorias categorias do produto farmaceutico com taxa de prescricao normal
     */
    public void setCategorias(String[] categorias) {
        this.categorias = categorias;
    }

    /**
     * metodo que calcula o valor do iva com prescricao
     * @param localizacao localizacao do cliente
     * @return return do valor com prescricao do produto farmaceutico com taxa de prescricao normal
     */
    public double calculaIVA(int localizacao){
        double desconto=0;
        for(int i =0; i<categorias.length; i++){
            if(categorias[i].equalsIgnoreCase("animais")){
                desconto = 0.01;
            }
        }
        switch (localizacao){
            case(1):
                valor += valor*(0.23- desconto); //continente
                break;

            case(2):
                valor += valor*(0.23- desconto); //madeira
                break;
            case(3):
                valor+= valor*(0.23- desconto); //acores
        }
        return valor;
    }

    @Override
    public String toString() {
        return "NormalPrescricao{" +
                "categorias=" + Arrays.toString(categorias) +
                '}';
    }
}