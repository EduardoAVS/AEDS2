import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.lang.System;

class PesquisaSequencial{
    static class Jogador{
        private int id;
        private String nome;

        // Construtor padrão (sem parâmetros)
        public Jogador() {
        }

        // Construtor com parâmetros
        public Jogador(int id, String nome) {
            setId(id);
            setNome(nome);
        }

        // Getter para o atributo id
        public int getId() {
            return id;
        }

        // Setter para o atributo id
        public void setId(int id) {
            this.id = id;
        }

        // Getter para o atributo nome
        public String getNome() {
            return nome;
        }

        // Setter para o atributo nome
        public void setNome(String nome) {
            this.nome = nome;
        }
        
        public static Jogador ler(String line){
            //separa os atributos id e nome por vírgula
            String[] atributos = line.split(",");
            int id = Integer.parseInt(atributos[0]);
            String nome = atributos[1];
            
            //retorna om objeto Jogador com os atributos lidos
            return new Jogador(id, nome);
        }
    }
    public static void main(String args[]) {
        // Registrar o tempo antes da execução do algoritmo
        long inicio = System.currentTimeMillis();
        Scanner sc = new Scanner(System.in);
        ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
        String arq = "/tmp/players.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(arq))) {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                Jogador j = Jogador.ler(line);
                jogadores.add(j);
                line = br.readLine();
                }
        }

        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        //arraylist com todos os jogadores que tiveram sua id gravadas na entrada
        ArrayList<Jogador> jogadorID = new ArrayList<Jogador>();
        String ent = sc.nextLine();
        int comp = 0;
        while(ent.equals("FIM") == false){
            int entrada = Integer.parseInt(ent);
            for (Jogador jogador : jogadores) {
                comp++;
                if (jogador.getId() == entrada) {
                    jogadorID.add(jogador);
                    break;
                }
            }
            ent = sc.nextLine();
            }
        //verificando se o nome da entrada corresponde a algum dos jogadores no array ID
        
        String ent2 = sc.nextLine();
        //loop com as entradas ate ser FIM
        while(ent2.equals("FIM") == false){
            boolean existe = false;
            //percorre todos os jogadores em jogadorID
            for (Jogador jogador : jogadorID) {
                if (jogador.getNome().equals(ent2)) {
                    System.out.println("SIM");
                    existe = true;
                    break;
                }
            }
            if(!existe){
                System.out.println("NAO");
            }
            ent2 = sc.nextLine();
        }
        sc.close();
        long fim = System.currentTimeMillis();
        long tempoDeExecucao = fim - inicio;

        //fazendo o arquivo de log
        String nomeDoArquivo = "matrícula_sequencial.txt";

        try (FileOutputStream fos = new FileOutputStream(nomeDoArquivo);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos))) {

            // Escrever dados no arquivo
            writer.write("814939\t" +tempoDeExecucao +"\t" + comp);

            writer.close();
        } catch (IOException e) {
            System.err.println("Ocorreu um erro ao criar o arquivo: " + e.getMessage());
        }
    }
}

