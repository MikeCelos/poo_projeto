/**
 * @author Francisco Vasconcelos, Joao Francisco
 * @version 1.0
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * class main
 */
public class Main {
    /**
     * class main
     * @param args
     */
    public static void main(String[] args) {

            String filePath = "Produtos.obj"; //ficheiro de objetos
            String filePath2 = "Produtos.txt"; //ficheiro de texto com os dados da fatura, clientes e produtos
            int num =0; //numero de "fim"s lidos
            File file = new File(filePath);
            List<Cliente> clientes = new ArrayList<>(); //lista de clientes
            List<Fatura> faturas = new ArrayList<>(); //lista de faturas
            List<Reduzida> reduzidas = new ArrayList<>(); //lista de produtos alimentares de taxa reduzida
            List<Intermedia> intermedias = new ArrayList<>(); //lista de produtos alimentares de taxa intermedia
            List<Normal> normais = new ArrayList<>();//lista de produtos alimentares de taxa normal
            List<NormalPrescricao> normalPrescricoes = new ArrayList<>(); //lista de produtos farmaceuticos sem prescricao
            List<Prescricao> prescricoes = new ArrayList<>();//lista de produtos farmaceuticos com prescricao
            List<Produto> produtos = new ArrayList<>(); //lista de produtos
            if(file.exists() && file.isFile()){ //abre o ficheiro de objetos caso exista e le o seu conteudo inserindo os respetivos dados nas suas listas correspondentes
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
                    clientes = (List<Cliente>) ois.readObject();
                    reduzidas = (List<Reduzida>) ois.readObject();
                    intermedias = (List<Intermedia>) ois.readObject();
                    normais = (List<Normal>) ois.readObject();
                    normalPrescricoes = (List<NormalPrescricao>) ois.readObject();
                    prescricoes = (List<Prescricao>) ois.readObject();
                    faturas = (List<Fatura>) ois.readObject();
                } catch (FileNotFoundException ex) { //exceçao ao abrir o ficheiro de objetos
                    System.out.println("Erro ao criar o ficheiro.");

                } catch(IOException | ClassNotFoundException ex){//exceçao ao abrir o ficheiro de objetos
                    System.out.println("Erro a escrever para o ficheiro.");
                }
            }
            else{ //caso o ficheiro ja exista vai ler o ficheiro de texto
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
                    File f = new File(filePath2);
                    if(f.exists() && f.isFile()){
                        try {
                            FileReader fr = new FileReader(f);
                            BufferedReader br = new BufferedReader(fr);
                            String line;


                            while((line = br.readLine()) != null){ //ler ate terminar o ficheiro de texto
                                if(num==3){
                                    //escreve no ficheiro de objetos
                                    oos.writeObject(clientes);
                                    oos.writeObject(reduzidas);
                                    oos.writeObject(intermedias);
                                    oos.writeObject(normais);
                                    oos.writeObject(normalPrescricoes);
                                    oos.writeObject(prescricoes);
                                    oos.writeObject(faturas);
                                    break;
                                }
                                if(line.trim().equalsIgnoreCase("fim")){//se encontrar a palavra fim,
                                    num++; //incrmenta 1 ao valor de fins
                                    continue;//salta essa linha
                                }
                                if(num==0){ //se ainda nao tiverem sido lidos nenhum fins
                                    String[] dadosCliente = line.trim().split(";"); //separa os dados do cliente por ";"
                                    if(dadosCliente.length != 3){
                                        System.out.println("Erro nos dados do cliente no ficheiro.");
                                        return;
                                    }
                                    String nome = dadosCliente[0].trim();
                                    String cont = dadosCliente[1].trim();
                                    if(cont.length() !=9){
                                        System.out.println("contribuinte do ficheiro invalido");
                                        continue;
                                    }

                                    String loca = dadosCliente[2].trim();

                                    int contribuinte = Integer.parseInt(cont);
                                    int localizacao = Integer.parseInt(loca);
                                    if(contribuinte<0 ||(localizacao != 1 & localizacao != 2 & localizacao != 3)){
                                        System.out.println("Erro nos dados do cliente no ficheiro.");
                                        return;
                                    }
                                    Cliente cliente = new Cliente(nome, contribuinte, localizacao); //cria um cliente com os seus dados
                                    clientes.add(cliente); //adiciona o cliente criado á lista de clientes
                                }

                                else if(num==1){ //se ja tiverem sido lido 1 fim vai ler os dados dos produtos
                                    String[] prod = line.trim().split(";");//separa os produtos por ";"
                                    if(prod.length<=1){
                                        System.out.println("Erro nos dados dos produtos no ficheiro.");
                                        return;
                                    }
                                    if(prod[0].trim().equalsIgnoreCase("Atr")){ //caso os produtos sejam do tipo alimentar de taxa reduzida
                                        for(int i=1; i<prod.length; i++){
                                            String[] palavras = prod[i].trim().split(",");//vai separar os dados de cada produto por ","

                                            boolean biologico = true;
                                            if(palavras[5].trim().equalsIgnoreCase("nao") || palavras[5].trim().equalsIgnoreCase("não")){
                                                biologico = false; //caso nao seja biologico
                                            }
                                            else if(palavras[5].trim().equalsIgnoreCase("sim") || palavras[5].trim().equalsIgnoreCase("sim")){
                                                biologico = true;
                                            }
                                            else{
                                                System.out.println("Parametro de verifica se é  biologico inválido. (sim/nao)");
                                                return;
                                            }

                                            String val = palavras[1].trim();
                                            String cod = palavras[2].trim();
                                            String quant = palavras[4].trim();

                                            int valor = Integer.parseInt(val);
                                            int codigo = Integer.parseInt(cod);
                                            int quantidade = Integer.parseInt(quant);
                                            String[] certificacoes = palavras[6].trim().split("/");
                                            Reduzida red = new Reduzida(palavras[0].trim(),valor,codigo,prod[3].trim(),quantidade,biologico,certificacoes); //cria um produto alimentar de taxa reduzida com os dados do produto
                                            produtos.add(red); //adiciona o produto alimentar de taxa reduzida á lista correspondente
                                            if(valor<0 || codigo<0 || quantidade<0){ //verifica se os dados sao validos
                                                System.out.println("Erro nos dados dos produtos no ficheiro. (valor, codigo ou quantidade invalido)");
                                                return;
                                            }

                                        }
                                    }

                                    else if(prod[0].trim().equalsIgnoreCase("Ati")){ //caso os produtos sejam do tipo alimentar de taxa intermedia
                                        for(int i=1; i<prod.length; i++){
                                            String[] palavras = prod[i].trim().split(","); //separa os dados de cada produto por ","

                                            if(palavras.length != 7){
                                                System.out.println("Erro nos dados dos produtos no ficheiro. (numero de dados invalido)");
                                                return;
                                            }
                                            boolean biologico = true;
                                            if(palavras[5].trim().equalsIgnoreCase("nao") || palavras[5].trim().equalsIgnoreCase("não")){
                                                biologico = false; //caso nao seja biologico
                                            }
                                            else if(palavras[5].trim().equalsIgnoreCase("sim") || palavras[5].trim().equalsIgnoreCase("sim")){
                                                biologico = true;
                                            }
                                            else{
                                                System.out.println("Parametro de verifica se é  biologico inválido. (sim/nao)");
                                                return;
                                            }

                                            String val = palavras[1].trim();
                                            String cod = palavras[2].trim();
                                            String quant = palavras[4].trim();

                                            int valor = Integer.parseInt(val);
                                            int codigo = Integer.parseInt(cod);
                                            int quantidade = Integer.parseInt(quant);
                                            String[] categorias = palavras[6].trim().split("/");
                                            Intermedia interm = new Intermedia(palavras[0].trim(),valor,codigo,prod[3].trim(),quantidade,biologico,categorias); //cria um produto alimentar de taxa intermedia com os valores do produto
                                            produtos.add(interm); //adiciona o produto á lista correspondente
                                            if(valor<0 || codigo<0 || quantidade<0){
                                                System.out.println("Erro nos dados dos produtos no ficheiro. (valor, codigo ou quantidade invalido)");
                                                return;
                                            }
                                        }
                                    }
                                    else if(prod[0].trim().equalsIgnoreCase("Atn")){ //caso os produtos lidos sejam do tipo alimentar de taxa normal
                                        for(int i=1; i<prod.length; i++){
                                            String[] palavras = prod[i].split(","); //separa os dados de cada produto por ","
                                            if(palavras.length != 6){
                                                System.out.println("Erro nos dados dos produtos no ficheiro. (numero de dados invalido)");
                                                return;
                                            }

                                            boolean biologico = true;
                                            if(palavras[1].trim().equalsIgnoreCase("nao") || palavras[1].trim().equalsIgnoreCase("não")){
                                                biologico = false; //caso nao seja biologico
                                            }
                                            String val = palavras[1].trim();
                                            String cod = palavras[2].trim();
                                            String quant = palavras[4].trim();

                                            double valor = Double.parseDouble(val);
                                            int codigo = Integer.parseInt(cod);
                                            int quantidade = Integer.parseInt(quant);
                                            Normal normal = new Normal(palavras[0].trim(),valor,codigo,prod[3].trim(),quantidade,biologico); //cria um produto alimentar de taxa normal
                                            produtos.add(normal); //adiciona o produto alimentar de taxa normal á sua lista respetiva
                                            if(valor<0 || codigo<0 || quantidade<0){
                                                System.out.println("Erro nos dados dos produtos no ficheiro. (valor, codigo ou quantidade invalido)");
                                                return;
                                            }
                                        }
                                    }
                                    else if(prod[0].trim().equalsIgnoreCase("Fnp")){ //caso os produtos lidos sejam farmaceuticos sem prescriçao
                                        for(int i=1; i<prod.length; i++){
                                            String[] palavras = prod[i].split(","); //separa os dados de cada produto por ","
                                            if(palavras.length != 6){
                                                System.out.println("Erro nos dados dos produtos no ficheiro. (numero de dados invalido)");
                                                return;
                                            }
                                            String val = palavras[1].trim();
                                            String cod = palavras[2].trim();
                                            String quant = palavras[4].trim();
                                            double valor = Double.parseDouble(val);
                                            int codigo = Integer.parseInt(cod);
                                            int quantidade = Integer.parseInt(quant);
                                            String[] categorias = palavras[5].trim().split("/");
                                            NormalPrescricao normalPrescricao = new NormalPrescricao(palavras[0],valor,codigo,prod[3],quantidade,categorias); //cria o produto farmaceutico sem prescriçao com os dados do produto
                                            produtos.add(normalPrescricao); //adiciona o produto farmaceutico sem prescricao á sua lista correspondente
                                            if(valor<0 || codigo<0 || quantidade<0){
                                                System.out.println("Erro nos dados dos produtos no ficheiro. (valor, codigo ou quantidade invalido)");
                                                return;
                                            }
                                        }
                                    }
                                    else if(prod[0].trim().equalsIgnoreCase("Fp")){ //caso os produtos lidos sejam farmaceuticos com prescriçao

                                        for(int i=1; i<prod.length; i++) {
                                            String[] palavras = prod[i].trim().split(","); //separa os dados do produto por ","
                                            if (palavras.length != 5) {
                                                System.out.println("Erro nos dados dos produtos no ficheiro. (numero de dados invalido)");
                                                return;
                                            }


                                            String val = palavras[1].trim();
                                            String cod = palavras[2].trim();
                                            String quant = palavras[4].trim();
                                            double valor = Double.parseDouble(val);
                                            int codigo = Integer.parseInt(cod);
                                            int quantidade = Integer.parseInt(quant);
                                            Prescricao prescricao = new Prescricao(palavras[0].trim(), valor, codigo, palavras[3].trim(), quantidade); //cria o produto farmaceutico sem prescriçao com os dados do produto
                                            produtos.add(prescricao);//adiciona o produto farmaceutico sem prescricao á sua lista correspondente
                                            if (valor < 0 || codigo < 0 || quantidade < 0) {
                                                System.out.println("Erro nos dados dos produtos no ficheiro. (valor, codigo ou quantidade invalido)");
                                                return;
                                            }
                                        }
                                    }


                                }
                                else{ // caso ja tenham sido lidos 2 fins, entao vai ler faturas
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


                            }

                        } catch (FileNotFoundException ex) {
                            System.out.println("Erro ao criar o ficheiro.");
                        } catch(IOException ex){
                            System.out.println("Erro a escrever para o ficheiro.");
                        }catch (NumberFormatException e) {
                            return;
                        }
                    }
                }
                catch (FileNotFoundException ex) {
                    System.out.println("Error: File not found.");
                } catch (IOException ex) {
                    System.out.println("Error reading the file.");
                }
            }

            int opcao=0;
            GereClientes gereClientes = new GereClientes(filePath, clientes, reduzidas, faturas, intermedias, normais, normalPrescricoes, prescricoes, produtos);
            Scanner sc = new Scanner(System.in);
            while(opcao!=10) {

                System.out.println("\u001B[1;34mMENU\n\u001B[0m------------------------------------");
                System.out.println("Indique que operaçao pretende executar:");
                System.out.println("\u001B[32m1-\u001B[0mCriar um cliente;\n\u001B[32m2-\u001B[0mEditar um cliente;\n\u001B[32m3-\u001B[0mListar clientes;\n\u001B[32m4-\u001B[0mAdicionar uma fatura;\n\u001B[32m5-\u001B[0mEditar uma fatura;\n\u001B[32m6-\u001B[0mListar faturas;\n\u001B[32m7-\u001B[0mListar uma fatura;\n\u001B[32m8-\u001B[0mImportar e exportar fatura;\n\u001B[32m9-\u001B[0mEstatisticas;");
                System.out.println("\u001B[31m10-\u001B[0mEncerrar programa");
                System.out.println("------------------------------------");
                System.out.print("Opcao: ");

                if (sc.hasNextInt()) {
                    opcao = sc.nextInt();
                    sc.nextLine();

                    switch (opcao) {
                        case (1): //opçao para adicionar um cliente
                            gereClientes.addCliente();
                            break;

                        case (2): //opçao para editar um cliente
                            gereClientes.editaCliente();
                            break;
                        case (3): //opçao para listar os clientes
                            gereClientes.listaClientes();
                            break;
                        case(4): //opçao para adicionar uma fatura
                            gereClientes.addFatura();
                            break;
                        case(5): //opçao para editar uma fatura
                            gereClientes.editaFatura();
                            break;
                        case (6): //opçao para listar as faturas
                            gereClientes.listaFaturas();
                            break;
                        case (7): //opçao para vizualizar uma fatura
                            gereClientes.visualizarFatura();
                            break;
                        case (8): //opçao para importar e exportar fatura
                            gereClientes.importarFatura();
                            break;
                        case (9): //opçao para apresentar a estatistica
                            gereClientes.estatistica();
                            break;
                        case (10): //opção para encerrar o programa
                            System.out.println("Encerrando o programa...\nAdeus ┌( ಠ_ಠ)┘");
                            return;
                        default:
                            System.out.println("Opção inválida. Por favor, escolha novamente.");
                    }
                } else {
                    System.out.println("Opção invalida, tente novamente.");
                    sc.nextLine();
                }

            }
    }
}