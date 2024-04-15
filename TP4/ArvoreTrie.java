import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class ArvoreTrie{

    static class Jogador{

        //atributos da classe jogador
        private Integer id;
        private String nome;
        private Integer altura;
        private Integer peso;
        private String universidade;
        private Integer anoNascimento;
        private String cidadeNascimento;
        private String estadoNascimento;
        
        //constutor padrão(sem entradas)
        public Jogador() {
            this(0, "", 0, 0, "", 0, "", "");
        }
        //construtor com entradas
        public Jogador(int id, String nome, Integer altura, Integer peso, String universidade, Integer anoNascimento, String cidadeNascimento, String estadoNascimento) {
            
            this.id = id;
            this.nome = nome;
            this.altura = altura;
            this.peso = peso;
            this.universidade = universidade;
            this.anoNascimento = anoNascimento;
            this.cidadeNascimento = cidadeNascimento;
            this.estadoNascimento = estadoNascimento;
        }
        // Métodos set para cada atributo

        public void setId(Integer id) {
            this.id = id;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public void setAltura(Integer altura) {
            this.altura = altura;
        }

        public void setPeso(Integer peso) {
            this.peso = peso;
        }

        public void setUniversidade(String universidade) {
            this.universidade = universidade;
        }

        public void setAnoNascimento(Integer anoNascimento) {
            this.anoNascimento = anoNascimento;
        }

        public void setCidadeNascimento(String cidadeNascimento) {
            this.cidadeNascimento = cidadeNascimento;
        }

        public void setEstadoNascimento(String estadoNascimento) {
            this.estadoNascimento = estadoNascimento;
        }

        // Métodos get para cada atributo

        public int getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }

        public Integer getAltura() {
            return altura;
        }

        public Integer getPeso() {
            return peso;
        }

        public String getUniversidade() {
            return universidade;
        }

        public Integer getAnoNascimento() {
            return anoNascimento;
        }

        public String getCidadeNascimento() {
            return cidadeNascimento;
        }

        public String getEstadoNascimento() {
            return estadoNascimento;
        }
        public Jogador clone() {
            Jogador clone = new Jogador(id, nome, altura, peso, universidade, anoNascimento, cidadeNascimento, estadoNascimento);
            return clone;
        }
        public static Jogador ler(String line){

                String[] arr = line.split(",");
                while(arr.length < 8){
                    line += ", ";
                    arr = line.split(",");
                }
                // Certifique-se de que arr tenha pelo menos 8 elementos
                                    
                    Integer id = Integer.parseInt(arr[0]);
                    String player = arr[1];
                    Integer height = Integer.parseInt(arr[2]);
                    Integer weight = Integer.parseInt(arr[3]);
                    String college;
                    Integer born = Integer.parseInt(arr[5]);
                    String birth_city;
                    String birth_state;

                    if (arr[6].isEmpty() || arr[6].equals("")) {
                        birth_city = "nao informado";
                    } else {
                        birth_city = arr[6];
                    }
            
                    if (arr[7].isEmpty() || arr[7].equals("")) {
                        birth_state = "nao informado";
                    } else {
                        birth_state = arr[7];
                    }
            
                    if (arr[4].isEmpty() || arr[4].equals("")) {
                        college = "nao informado";
                    } else {
                        college = arr[4];
                    }
                    
                    return new Jogador(id, player, height, weight, college, born, birth_city, birth_state);
               
        }

        public void imprimir() {
            //imprimindo seguindo a saída
            System.out.print("## ");
            System.out.print(nome);
            System.out.print(" ## ");
            System.out.print(altura);
            System.out.print(" ## ");
            System.out.print(peso);
            System.out.print(" ## ");
            System.out.print(anoNascimento);
            System.out.print(" ## ");
            System.out.print(universidade);
            System.out.print(" ## ");
            System.out.print(cidadeNascimento);
            System.out.print(" ## ");
            System.out.print(estadoNascimento);
            System.out.print(" ##");
            System.out.println("");
        }
        
    }

    static class No{
        public char elemento;
        public final int tam = 255;
        public No prox[];
        public boolean folha;

        public No(){
            this(' ');
        }

        public No(char elemento){
            this.elemento = elemento;
            prox = new No[tam];
            for(int i = 0; i < tam; i++){
                prox[i] = null;
            }
            folha = false;
        }

        public static int hash(char x){
            return (int) x;
        }
    }

    static class Arvore{
        private No raiz;

        //cria árvore com raiz nula
        public Arvore(){
            raiz = new No();
        }

        public boolean pesquisar(String x)throws Exception{
            return pesquisar(x, raiz, 0);
        }

        private boolean pesquisar(String s, No no, int i)throws Exception{
            boolean resp = false;

            if(no.prox[s.charAt(i)] == null){
                resp = false;
                System.out.println(" NAO");
            }
            else if(i == s.length() - 1){
                resp = (no.prox[s.charAt(i)].folha == true);
                System.out.println(" SIM");
            }
            else if(i < s.length() - 1){
                resp = pesquisar(s, no.prox[s.charAt(i)], ++i);
            }
            else{
                throw new Exception("Erro ao pesquisar");
            }
            return resp;
        }

        public void inserir(String s) throws Exception {
            inserir(s, raiz, 0);
        }
    
        private void inserir(String s, No no, int i) throws Exception {
            if(no.prox[s.charAt(i)] == null){
                no.prox[s.charAt(i)] = new No(s.charAt(i));
    
                if(i == s.length() - 1){
                    no.prox[s.charAt(i)].folha = true;
                }else{
                    inserir(s, no.prox[s.charAt(i)], i + 1);
                }
    
            } else if (no.prox[s.charAt(i)].folha == false && i < s.length() - 1){
                inserir(s, no.prox[s.charAt(i)], i + 1);
    
            } else {
                throw new Exception("Erro ao inserir!");
            } 
        }
        
        public static ArrayList<String> mostrar(Arvore x){
            return mostrarRecursivo(x.raiz, "");
        }
    
        private static ArrayList<String> mostrarRecursivo(No no, String palavraAtual) {
            ArrayList<String> palavras = new ArrayList<String>();
        
            if (no.folha) {
                palavras.add(palavraAtual + no.elemento);
            } else {
                for (int i = 0; i < no.prox.length; i++) {
                    if (no.prox[i] != null) {
                        palavras.addAll(mostrarRecursivo(no.prox[i], palavraAtual + (char)(i)));
                    }
                }
            }
            return palavras;
        }

        public static Arvore arvoreResultante(Arvore x, Arvore y)throws Exception{
            Arvore z = new Arvore();
            ArrayList<String> palavras1 = new ArrayList<String>();
            ArrayList<String> palavras2 = new ArrayList<String>();

            palavras1 = mostrar(x);
            palavras2 = mostrar(y);
            //loop para colocar todas as Strings da arvore x na nova arvore
            for(String s : palavras1){
                z.inserir(s);
            }
            //loop para colocar todas as Strings da arvore y na nova arvore 
            for(String s : palavras2){
                z.inserir(s);
            }
            return z;
        }
    

    }

    
    

    
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
        String arq = "/tmp/players.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(arq))) {

        //gravando todos os jogadores em um arraylist
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
        //criando árvore trie para a primeira e segunda insercao
        Arvore arvore1 = new Arvore();
        Arvore arvore2 = new Arvore();

        String ent = sc.nextLine();

        //insere na arvore 1
        while(ent.equals("FIM") == false){
            int entrada = Integer.parseInt(ent);
            for (Jogador jogador : jogadores) {
                if (jogador.getId() == entrada) {
                    arvore1.inserir(jogador.getNome());
                    System.out.println(entrada + "   " + jogador.getNome());
                    break;
                }
            }
            ent = sc.nextLine();
        }
        ent = sc.nextLine();
        //insere na arvore 2
         while(ent.equals("FIM") == false){
            int entrada = Integer.parseInt(ent);
            for (Jogador jogador : jogadores) {
                if (jogador.getId() == entrada) {
                    arvore2.inserir(jogador.getNome());
                    System.out.println(entrada + " " + jogador.getNome());
                    break;
                }
            }
            ent = sc.nextLine();
        }

        //preenchendo a arvore resultante
        Arvore arvore3 = Arvore.arvoreResultante(arvore2, arvore1);

        String ent2 = sc.nextLine();
        while (!ent2.equals("FIM")) {
            System.out.print(ent2);
            arvore3.pesquisar(ent2);
            ent2 = sc.nextLine();
        }


        sc.close();
    }
}