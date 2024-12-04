/**
 * @author Francisco Vasconcelos, Joao Francisco
 * @version 1.0
 */
import java.io.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.Serializable;

/**
 * classe para gerir/apresentar dados das faturas, dos clientes e dos produtos
 */
class GereClientes implements Serializable {
    /**
     * nome do ficheiro a ser manipulado
     */
    private String filePath;

    /**
     * lista dos clientes
     */
    private List<Cliente> clientes;

    /**
     * lista das faturas
     */
    private List<Fatura> faturas;

    /**
     *lista de produtos alimentares de taxa reduzida
     */
    private List<Reduzida> reduzidas;

    /**
     * Lista de produtos alimentares com taxas intermediárias.
     */
    private List<Intermedia> intermedias;

    /**
     * Lista de produtos alimentares de taxa normal
     */
    private List<Normal> normals;

    /**
     * lista de produtos farmaceuticos sem prescricao
     */
    private List<NormalPrescricao> normalPrescricoes;

    /**
     * Lista de produtos farmaceuticos com prescriçao
     */
    private List<Prescricao> prescricaos;

    /**
     * Lista de produtos
     */
    private List<Produto> produtos;


    /**
     * Metodo da class GereClientes que gere o as listas e os ficheiros
     * @param filePath nome do ficheiro a ser modificado
     * @param clientes lista de clientes
     * @param reduzidas lista de produtos alimentares de taxa reduzida
     * @param faturas
     * @param intermedias
     * @param normals
     * @param normalPrescricoes
     * @param prescricaos
     * @param produtos
     */
    public GereClientes(String filePath, List<Cliente> clientes, List<Reduzida> reduzidas, List<Fatura> faturas, List<Intermedia> intermedias, List<Normal> normals, List<NormalPrescricao> normalPrescricoes, List<Prescricao> prescricaos,List<Produto> produtos) {
        this.filePath = filePath;
        this.clientes = clientes;
        this.reduzidas = reduzidas;
        this.faturas = faturas;
        this.intermedias = intermedias;
        this.normals = normals;
        this.normalPrescricoes = normalPrescricoes;
        this.prescricaos = prescricaos;
        this.produtos = produtos;

    }

    /**
     * metodo que edita um cliente
     */
    public void editaCliente(){//metodo que edita o cliente
        System.out.print("Indique o nome do cliente a ser editado:");
        Scanner sc = new Scanner(System.in);
        String nome = sc.nextLine(); //le o nome do cliente
        for(Cliente cliente : clientes){ //itera sobre os clientes
            if(cliente.nome.trim().equalsIgnoreCase(nome)){ //se o nome dado for igual ao do cliente a ser avaliado
                clientes.remove(cliente); //retira o cliente da lista
                addCliente(); //pede novos dados

                return;
            }
        }
        System.out.println("Cliente nao encontrado. tente novamente");

    }

    /**
     * metodo que adiciona um cliente
     */
    public void addCliente(){ //metodo que adiciona um cliente
        System.out.println("Criaçao de um novo cliente:");
        Scanner sc = new Scanner(System.in);
        String frase = "Nome: ";
        String nome = leStr(sc,frase); //le nome do cliente

        frase = "Contribuinte: ";
        int contribuinte = leInt(sc,frase);
        if(!(contribuinte>0 && contribuinte/100000000>=1 && contribuinte/100000000<10)){ //verificaçao se o contribuinte é valido
            System.out.println("contribuinte invalido. tente novamente");
            return;
        }

        System.out.println("localizacao-> 1-Continente, 2-Madeira, 3-Açores");
        frase = "localizacao: ";
        int localizacao = leInt(sc,frase);
        if(!(localizacao ==1 || localizacao ==2 || localizacao ==3)){ //verifica se a localizacao é valida
            System.out.print("localizacao invalida. tente novamente");
            return;
        }


        Cliente cliente = new Cliente(nome, contribuinte, localizacao); //cria um novo cliente
        clientes.add(cliente); //adiciona o cliente á lista de clientes
        escreveFich(clientes, faturas, reduzidas, intermedias, normals, normalPrescricoes, prescricaos); //reescreve o ficheiro com as novas informações



    }

    /**
     * metodo que le string
     * @param sc scanner
     * @param frase frase a ser imprimida
     * @return string lida
     */
    private String leStr(Scanner sc, String frase){

        String texto = "";
        boolean valido = false;
        while(!valido){ //executa enquanto nao for valido
            System.out.print(frase);
            if(sc.hasNextLine()){ //verifica se foi introduzido alguma string
                texto = sc.nextLine();
                if(!texto.trim().equals("")){ //verifica se nao é vazio
                    valido = true;
                }
                else{
                    System.out.println("ERRO: A string nao pode ser vazia. tente novamente ");
                }
            }

        }
        return texto; //devolve string
    }

    /**
     * metodo que le um inteiro
     * @param sc scanner
     * @param frase frase a ser apresentada no terminal
     * @return valor do inteiro lido
     */
    private int leInt(Scanner sc, String frase){
        int numero = 0;
        boolean valido = false;
        while(!valido){ //executa enquanto nao encontra um inteiro valido
            System.out.print(frase);
            if(sc.hasNextInt()){
                numero = sc.nextInt();
                valido = true;
            }
            else{
                System.out.println("ERRO: Entrada invalida. tente novamente ");
                sc.next();
            }
        }
        return numero; //devolve inteiro
    }

    /**
     * metodo que adiciona uma fatura
     */
    public void addFatura(){ //metodo que adiciona uma fatura
        System.out.println("Criar uma nova fatura:");
        Scanner sc = new Scanner(System.in);
        String produto = "";
        List<Produto> prodAdicionar = new ArrayList<>(); //cria uma lista de produtos para a fatura
        System.out.println("Indique o produto a ser adicionado (se nao quiser introduzir mais produtos digite fim):");
        int prod =0;
        while(produto !="fim"){ //enquanto estiver a introduzir produtos vai executar, até ler "fim"
            String frase = "Indique o nome do produto: ";
            produto = leStr(sc,frase); //le string
            if(produto.trim().equalsIgnoreCase("fim")){ //verifica se o valor lido é "fim"
                break;
            }

            for(Produto produto1 : produtos){ //itera sobre todos os produtos
                if(produto1.nome.trim().equalsIgnoreCase(produto)){ //se o nome do produto for igual ao iterado executa
                    prodAdicionar.add(produto1); //adiciona um produto á lista de produtos
                    prod++;
                    break;
                }
            }
        }
        if(prod>0){
            System.out.println("Sem produtos inseridos. tente novamente");
            return;
        }
        String frase= "Indique o numero da fatura: ";
        int numeroF = leInt(sc, frase);
        sc.nextLine();
        frase = "Indique o nome do cliente: ";
        String nomeC = leStr(sc,frase);

        System.out.println("Indique a data da compra:");
        frase = "Dia:";
        int dia = leInt(sc, frase);
        frase = "Mes:";
        int mes = leInt(sc, frase);

        frase = "Ano:";
        int ano = leInt(sc, frase);

        if(!dataValida(dia,mes,ano)){
            System.out.println("Data invalida. tente novamente");
            return;
        }

        Fatura f1 = new Fatura(prodAdicionar,numeroF,nomeC,dia,mes,ano);
        faturas.add(f1);
        escreveFich(clientes, faturas, reduzidas, intermedias, normals, normalPrescricoes, prescricaos);
    }

    /**
     * metodo para verificar se a data é valida
     * @param dia dia da data a ser avaliada
     * @param mes mes da data a ser avaliada
     * @param ano ano da data a ser avaliada
     * @return boolean se a data é valida ou nao
     */
    public boolean dataValida(int dia, int mes, int ano){
        boolean valido = false;
        if(dia>0 && dia<32 && mes>0 && mes<13 && ano>0){
            valido = true;
        }
        return valido;
    }

    /**
     * metodo para escrever no ficheiro de objetos
     * @param clientes Lista de clientes a serem escritos no ficheiro de objetos
     * @param faturas Lista de faturas a serem escritos no ficheiro de objetos
     * @param reduzidas Lista de produtos alimentares de taxa reduzida a serem escritos no ficheiro de objetos
     * @param intermedias Lista de produtos alimentares de taxa intermedia a serem escritos no ficheiro de objetos
     * @param normals Lista de produtos alimentares de taxa normal a serem escritos no ficheiro
     * @param normalPrescricoes Lista de produtos farmaceuticos de prescriçao normal a serem escritos no ficheiro
     * @param prescricaos Lista de produtos farmaceuticos sem prescricao a serem escritos no ficheiro
     */
    public void escreveFich(List<Cliente> clientes, List<Fatura> faturas, List<Reduzida> reduzidas,List<Intermedia> intermedias, List<Normal> normals, List<NormalPrescricao> normalPrescricoes, List<Prescricao> prescricaos){
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(clientes);
            oos.writeObject(faturas);
            oos.writeObject(reduzidas);
            oos.writeObject(intermedias);
            oos.writeObject(normals);
            oos.writeObject(normalPrescricoes);
            oos.writeObject(prescricaos);
            oos.close();
            fos.close();
        } catch(FileNotFoundException ex){
            System.out.println("Erro ao criar o ficheiro.");
        }catch(IOException ex){
            System.out.println("Erro a escrever para o ficheiro.");
        }
    }

    /**
     * Metodo que lista os clientes
     */
    public void listaClientes(){
        if (clientes.isEmpty()) { //verifica se a lista de clientes está vazia
            System.out.println("Nenhum cliente encontrado.");
            return;
        }
        System.out.println("Clientes:");
        System.out.println("------------------------------------");
        for(Cliente cliente : clientes) {
            System.out.println(cliente);
        }
        System.out.println("------------------------------------");
    }

    /**
     * Metodo que lista as faturas
     */
    public void listaFaturas() {
        if (clientes.isEmpty()) { // verifica se a lista de faturas está vazia
            System.out.println("Nenhum cliente encontrado.");
            return;
        }
        System.out.println("Faturas:");
        System.out.println("------------------------------------");
        for (Fatura fatura : faturas) {
            System.out.printf("Numero da fatura: %s\n", fatura.numeroF);
            boolean clienteEncontrado = false;
            double totalIVA=0;
            double total=0;
            for (Cliente cliente : clientes) {
                if (cliente.nome.trim().equalsIgnoreCase(fatura.nomeC.trim())) {
                    System.out.println(cliente.toString());
                    int numProd = fatura.produtoList.size();
                    System.out.printf("Numero de produtos: %s.\n", numProd);
                    for(Produto p1 : fatura.produtoList){
                        total +=p1.valor;
                        totalIVA += p1.calculaIVA(cliente.localizacao);

                    }
                    System.out.printf("Valor total com IVA: %.2f\n", totalIVA);
                    System.out.printf("Valor total sem IVA: %.2f\n", total);
                    clienteEncontrado = true;
                    System.out.println();
                    break;
                }
            }

            if (!clienteEncontrado) {
                System.out.println("Cliente não encontrado. Tente novamente.");
            }
        }
        System.out.println("------------------------------------");
    }

    /**
     * Metodo que edita a fatura
     */
    public void editaFatura(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Indique o numero da fatura a ser editada:" );
        int numeroFatura = sc.nextInt();
        for(Fatura fatura : faturas){
            if(numeroFatura == fatura.numeroF){
                faturas.remove(fatura);
                addFatura();
                return;
            }
        }
        System.out.println("Fatura nao encontrada. tente novamente");
    }

    /**
     * Metodo para vizualizar a fatura
     */
    public void visualizarFatura(){
        System.out.print("Indique o numero da fatura que pretende vizualizar: ");
        Scanner sc = new Scanner(System.in);
        int numero = sc.nextInt();
        int loc =1;
        for(Fatura fatura : faturas){
            if(fatura.numeroF == numero){
                System.out.printf("Fatura numero: %d\n",numero);
                for(Cliente cliente : clientes){
                    if(fatura.nomeC.trim().equalsIgnoreCase(cliente.nome)){
                        System.out.println(cliente);
                        loc = cliente.localizacao;
                        break;
                    }
                }
                double total=0;
                double totalIVA = 0;
                System.out.println("Produtos da fatura:");
                for(Produto produto : produtos){
                    if(fatura.produtoList.contains(produto)){
                        System.out.printf("%s:\n",produto.nome);
                        System.out.printf("Valor: %.2f\n", produto.valor);
                        System.out.printf("Valor com IVA: %.2f\n", produto.calculaIVA(loc));
                        double valorIVA = produto.calculaIVA(loc) - produto.valor;
                        double taxaIVA = 100 -(((produto.valor - valorIVA) *100)/produto.valor);
                        System.out.printf("Taxa do IVA: %.2f\n", taxaIVA);
                        total += produto.valor;
                        totalIVA += produto.calculaIVA(loc);
                        System.out.println();
                    }
                }
                System.out.println("--------------------------");
                System.out.printf("Valor total sem IVA: %.2f\n",total);
                System.out.printf("Valor total com IVA: %.2f\n",totalIVA);
                double valorIVA = totalIVA - total;
                double taxaIVA = 100 -(((total - valorIVA) *100)/total);
                System.out.printf("Valor do IVA: %.2f\n",valorIVA);
                System.out.printf("Taxa IVA: %.2f\n",taxaIVA);
                System.out.println("---------------------------");
                break;
            }
        }
    }

    /**
     * Metodo para importar a fatura
     */
    public void importarFatura(){

        File f = new File("Fatura.txt");
        if(f.exists() && f.isFile()){
            try{
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String line;
                while((line = br.readLine()) != null){
                    String[] prod = line.trim().split(";"); //vai separar dados dos produtos dos dados do cliente
                    List<Produto> listaProd = new ArrayList<>(); //cria uma lista de produtos
                    String[] prod2 = prod[0].trim().split("\\."); //separa os produtos por "."
                    for(int i=0; i<prod2.length; i++){
                        String[] palavras = prod2[i].trim().split(","); //separa os dados dor produtos por ","
                        for(Produto produto: produtos){ //itera dobre cada um dos produtos
                            if(produto.nome.trim().equalsIgnoreCase(palavras[0])){
                                int quantidade = Integer.parseInt(palavras[4].trim());
                                produto.setQuantidade(quantidade); //set da quantidade do produto
                                listaProd.add(produto);//adiciona o produto á lista de produtos
                            }
                        }


                    }
                    String[] prod3 = prod[1].trim().split(","); //separa os dados do cliente por ","
                    int numero = Integer.parseInt(prod3[0].trim());
                    String[] data = prod3[2].trim().split("/"); //separa a data por "/"
                    int dia = Integer.parseInt(data[0].trim());
                    int mes = Integer.parseInt(data[1].trim());
                    int ano = Integer.parseInt(data[2].trim());
                    Fatura fatura = new Fatura(listaProd,numero,prod3[1].trim(),dia, mes, ano); //cria fatura
                    faturas.add(fatura); //adiciona a fatura á lista correspondente
                }
                br.close();
            }catch(FileNotFoundException ex ){
                System.out.println("Erro ao abrir o ficheiro.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            System.out.println("Ficheiro não existe.");
        }
        System.out.println("Ficheiro importado com sucesso.");
        File f2 = new File("fatura2.txt");
        try{
            FileWriter fw = new FileWriter(f2);
            BufferedWriter bw = new BufferedWriter(fw);
            for(Fatura fatura : faturas){
                bw.write(fatura.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();

        }catch(IOException ex){
            System.out.println("Erro ao escrever no ficheiro.");
        }
    }

    /**
     * metodo que apresenta as estatisticas
     */
    public void estatistica(){
        System.out.println("Estatistica:");
        int numero =0;
        double totalIVA =0;
        double total=0;
        for (Fatura fatura : faturas) {
            for (Cliente cliente : clientes) {
                if (cliente.nome.trim().equalsIgnoreCase(fatura.nomeC.trim())) {
                    for(Produto p1 : fatura.produtoList){
                        numero += p1.quantidade;
                        total +=p1.valor;
                        totalIVA += p1.calculaIVA(cliente.localizacao);
                    }
                }
            }
        }
        System.out.println("------------------------------------");
        System.out.printf("Número de faturas: %d\n",faturas.size());
        System.out.printf("Numero de produtos: %d\n",numero);
        System.out.printf("Valor total com IVA: %.2f\n", totalIVA);
        double valorIVA = totalIVA - total;
        System.out.printf("Valor total sem IVA: %.2f\n", total);
        System.out.printf("Valor total do IVA: %.2f\n",valorIVA);
        System.out.println("------------------------------------");
    }
}

