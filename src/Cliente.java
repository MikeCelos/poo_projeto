/**
 * @author Francisco Vaasconcelos, Joao Francisco
 * @version 1.0
 */
import java.io.Serializable;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * class que gere os clientes
 */
public class Cliente implements Serializable{
    /**
     * nome do cliente
     */
    protected String nome;

    /**
     * contribuinte do cliente
     */
    private int contribuinte;

    /**
     * localizacao do cliente
     */
    protected int localizacao;

    /**
     * contrutor da class cliente
     * @param nome nome do cliente
     * @param contribuinte contribuinte do cliente
     * @param localizacao localizacao do cliente
     */
    public Cliente(String nome, int contribuinte, int localizacao) {
        this.nome = nome;
        this.contribuinte = contribuinte;
        this.localizacao = localizacao;
    }

    /**
     * getter do nome do cliente
     * @return nome do cliente
     */
    public String getNome() {
        return nome;
    }

    /**
     * setter do nome do cliente
     * @param nome nome do cliente
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * getter do contribuinte do cliente
     * @return contibuinte do cliente
     */
    public int getContribuinte() {
        return contribuinte;
    }

    /**
     * setter do contribuinte do cliente
     * @param contribuinte contribuinte do cliente
     */
    public void setContribuinte(int contribuinte) {
        this.contribuinte = contribuinte;
    }

    /**
     * getter da localizacao do cliente
     * @return localizacao do cliente
     */
    public int getLocalizacao() {
        return localizacao;
    }

    /**
     * setter da localizacao do cliente
     * @param localizacao localizacao do cliente
     */
    public void setLocalizacao(int localizacao) {
        this.localizacao = localizacao;
    }


    @Override
    public String toString() {
        String loc="";
        if(localizacao == 1){
           loc = "Continente";
        }
        else if(localizacao == 3){
            loc = "Madeira";
        }
        else if(localizacao == 2){
            loc = "Açores";

        }

        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", contribuinte=" + contribuinte +
                ", localizacao=" + loc +
                '}';
    }
}

