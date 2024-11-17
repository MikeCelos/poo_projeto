/**
 * @author Francisco Vasconcelos, Joao Francisco
 * @version 1.0
 */

/**
 * class que gere o produto
 */
public class Produto {

    /**
     * nome do produto
     */
    private String nome;

    /**
     * valor do produto
     */
    private double valor;

    /**
     * codigo do produto
     */
    private int codigo;

    /**
     * descricao do produto
     */
    private String descricao;

    /**
     * quantidade do produto
     */
    private int quantidade;

    /**
     * contrutor da class Produto
     * @param nome nome do produto
     * @param valor valor do produto
     * @param codigo codigo do produto
     * @param descricao descricao do produto
     * @param quantidade quantidade do produto
     */
    public Produto(String nome, double valor, int codigo,String descricao,int quantidade){
        this.nome = nome;
        this.valor = valor;
        this.codigo =  codigo;
        this.descricao = descricao;
        this.quantidade=quantidade;

    }

    /**
     * getter da quantidade do produto
     * @return quantidade do produto
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * setter da quantidade do produto
     * @param quantidade quantidade do produto
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * getter do codigo do produto
     * @return codigo do produto
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * setter do codigo do produto
     * @param codigo codigo do produto
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * getter da descricao do produto
     * @return descricao do produto
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * setter da descricao do produto
     * @param descricao descricao do produto
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * gettter do valor do produto
     * @return valor do produto
     */
    public double getValor() {
        return valor;
    }

    /**
     * setter do valor do produto
     * @param valor valor do produto
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * getter do nome do produto
     * @return nome do produto
     */
    public String getNome() {
        return nome;
    }

    /**
     * setter do nome do produto
     * @param nome nome do produto
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
}
