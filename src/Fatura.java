/**
 * @author Francisco Vasconcelos, Joao Francisco
 * @version 1.0
 */
import java.util.List;
/**
 * class que gere aa faturas dos produtos
 */
public class Fatura {
    /**
     * lista dos produtos
     */
    private List<Produto> produtoList;

    /**
     * numero da fatura
     */
    private int numeroF;

    /**
     * nome do cliente
     */
    private String nomeC;

    /**
     * dia da data
     */
    private int dia;

    /**
     * mes da data
     */
    private int mes;

    /**
     * ano da data
     */
    private int ano;

    /**
     *contrutor da class fatura
     * @param produtoList lista dos produtos na fatura
     * @param numeroF numero da fatura
     * @param nomeC nome do cliente na fatura
     * @param dia dia da data na fatura
     * @param mes mes da data na fatura
     * @param ano ano da data na fatura
     */
    public Fatura(List<Produto> produtoList, int numeroF, String nomeC, int dia, int mes, int ano) {
        this.produtoList = produtoList;
        this.numeroF = numeroF;
        this.nomeC = nomeC;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    /**
     * getter da lista de produtos
     * @return lista de produtos
     */
    public List<Produto> getProdutoList() {
        return produtoList;
    }

    /**
     * setter da lista de produtos
     * @param produtoList lista de produtos
     */
    public void setProdutoList(List<Produto> produtoList) {
        this.produtoList = produtoList;
    }

    /**
     * getter do numero da fatura
     * @return numero da fatura
     */
    public int getNumeroF() {
        return numeroF;
    }

    /**
     * setter do numero da fatura
     * @param numeroF numero da fatura
     */
    public void setNumeroF(int numeroF) {
        this.numeroF = numeroF;
    }

    /**
     * getter do nome do cliente
     * @return nome do cliente
     */
    public String getNomeC() {
        return nomeC;
    }

    /**
     * setter do nome do cliente
     * @param nomeC nome do cliente
     */
    public void setNomeC(String nomeC) {
        this.nomeC = nomeC;
    }

    /**
     * getter do dia da data
     * @return dia da data
     */
    public int getDia() {
        return dia;
    }

    /**
     * setter do dia da data
     * @param dia dia da data
     */
    public void setDia(int dia) {
        this.dia = dia;
    }

    /**
     * getter do mes da data
     * @return mes da data
     */
    public int getMes() {
        return mes;
    }

    /**
     * setter do mes da data
     * @param mes mes da data
     */
    public void setMes(int mes) {
        this.mes = mes;
    }

    /**
     * getter do ano da data
     * @return ano da data
     */
    public int getAno() {
        return ano;
    }

    /**
     *  setter do ano da data
     * @param ano ano da data
     */
    public void setAno(int ano) {
        this.ano = ano;
    }
}
