import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class ListaFlexivel{

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
    //classe célula
    static class Celula{
        public Jogador elemento;
        public Celula prox;

        public Celula(){
            this(null);
        }

        public Celula(Jogador elemento){
            this.elemento = elemento;
            this.prox = null;
        }
    }
    //classe lista
    static class Lista{
        //lista com os jogadores como variável global
        private Celula primeiro;
        private Celula ultimo;
        private int n;

        //chamar com o primeiro número depois de FIM
        public Lista(){
            primeiro = new Celula();
            ultimo = primeiro;
        }

        public int getTamanho(){
            return n;
        }


        public void inserirInicio(Jogador x) throws Exception {
            Celula tmp = new Celula(x);
            tmp.prox = primeiro.prox;
            primeiro.prox = tmp;
            if(primeiro == ultimo){
                ultimo = tmp;
            }
            tmp = null;
            n++;
        }
        public void inserirFim(Jogador x) throws Exception {
            ultimo.prox = new Celula(x);
            ultimo = ultimo.prox;
            n++;
        }
        public void inserir(Jogador x, int pos) throws Exception {
            if(pos < 0 || pos > n){
                throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + n + ") invalida!");
            } 
            else if (pos == 0){
                inserirInicio(x);
            } 
            else if (pos == n){
                inserirFim(x);
            } 
            else {
                Celula i = primeiro;
                //caminhar ate a pos da insercao
                for(int j = 0; j < pos; j++, i = i.prox);
                Celula tmp = new Celula(x);
                tmp.prox = i.prox;
                i.prox = tmp;
                tmp = i = null;
                n++;
            }

        }

        public Jogador removerInicio() throws Exception {
            //validar remocao
            if (primeiro == ultimo) {
                throw new Exception("Erro ao remover!");
            }
            Celula tmp = primeiro;
            primeiro = primeiro.prox;
            Jogador resp = primeiro.elemento;
            tmp.prox = null;
            tmp = null;
            n--;
            return resp;
        }

        public Jogador removerFim() throws Exception {
            //validar remocao
            if (primeiro == ultimo) {
                throw new Exception("Erro ao remover!");
            }
            //caminhar ate a penultima celula
            Celula i;
            for(i = primeiro; i.prox != ultimo; i = i.prox);
            Jogador resp = ultimo.elemento;
            ultimo = i;
            i = ultimo.prox = null;
            n--;
            return resp;
        }
        public Jogador remover(int pos) throws Exception {
            Jogador resp;
            //validar remocao
            if (n == 0 || pos < 0 || pos >= n) {
                throw new Exception("Erro ao remover!");
            }
            else if (pos == 0){
                resp = removerInicio();
             } 
             else if (pos == n - 1){
                resp = removerFim();
             } 
             else {
                Celula i = primeiro;
                //encontrando a posicao do anterior a pos
                for(int j = 0; j < pos; j++, i = i.prox);
                Celula tmp = i.prox;
                resp = tmp.elemento;
                i.prox = tmp.prox;
                tmp.prox = null;
                i = tmp = null;
                n--;
             }
            return resp;
            }
                public void mostrar(){
                    Celula i = primeiro.prox;
                    for(int j = 0; j < n; j++, i = i.prox){
                        System.out.print("[" + j + "] ");
                        i.elemento.imprimir();
                    }
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
        //lista com todos os jogadores que tiveram sua id gravadas na entrada
        Lista lista = new Lista();
        String ent = sc.nextLine();

        while(ent.equals("FIM") == false){
            int entrada = Integer.parseInt(ent);
            for (Jogador jogador : jogadores) {
                if (jogador.getId() == entrada) {
                    lista.inserirFim(jogador);
                    break;
                }
            }
            ent = sc.nextLine();
        }
        int num = sc.nextInt();

        //loop que le a segunda parte da entrada, inserindo ou removendo da lista
        
        for(int i = 0; i < num; i++){
            String acao = sc.next();
            if(acao.equals("II")){
                //id
                int n = sc.nextInt();
                lista.inserirInicio(jogadores.get(n));
            }
            else if(acao.equals("IF")){
                //id
                int n = sc.nextInt();
                lista.inserirFim(jogadores.get(n));
            }
            else if(acao.equals("I*")){
                //le a posicao
                int pos = sc.nextInt();
                //le o id
                int n = sc.nextInt();
                lista.inserir(jogadores.get(n), pos);
            }
            else if(acao.equals("RI")){
                
                System.out.println("(R) " + lista.removerInicio().getNome());//nome do primeiro jogador
                
            }
            else if(acao.equals("RF")){
                System.out.println("(R) " + lista.removerFim().getNome());//mome do ultimo jogador
                
            }
            else{
                int pos = sc.nextInt();
                System.out.println("(R) " + lista.remover(pos).getNome());//nome do jogador em pos
                
            }
        }
        lista.mostrar();
        sc.close();
    }
}