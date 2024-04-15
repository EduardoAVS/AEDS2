import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class TabelaHashRehash{

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
        public Jogador elemento;
        public No esq, dir;

        public No(Jogador elemento){
            this(elemento, null, null);
        }

        public No(Jogador elemento, No esq, No dir){
            this.elemento = elemento;
            this.esq = esq;
            this.dir = dir;
        }
    }

    public static class Hash {
        String tabela[];
        int m;
        final String NULO = "";

        public Hash() {
            this(13);
        }

        public Hash(int m) {
            this.m = m;
            this.tabela = new String[this.m];
            for (int i = 0; i < m; i++) {
                tabela[i] = NULO;
            }
        }

        public int h(int elemento) {
            return elemento % m;
        }

        public int reh(int elemento) {
            return ++elemento % m;
        }

        public boolean inserir(Jogador elemento) {
            boolean resp = false;
            if (elemento.getNome() != NULO) {
                int pos = h(elemento.getAltura());
                if (tabela[pos] == NULO) {
                    tabela[pos] = elemento.getNome();
                    resp = true;
                } else {
                    pos = reh(elemento.getAltura());
                    if (tabela[pos] == NULO) {
                    tabela[pos] = elemento.getNome();
                    resp = true;
                    }
                }
            }
            return resp;
        }

        public boolean pesquisar(Jogador elemento) {
            boolean resp = false;
            int pos = h(elemento.getAltura());
            if (tabela[pos] == elemento.getNome()) {
                resp = true;
            } else if (tabela[pos] != NULO) {
                pos = reh(elemento.getAltura());
                if (tabela[pos] == elemento.getNome()) {
                    resp = true;
                }
            }
            return resp;
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
        //criando árvore com raiz nula
        Hash hash = new Hash(25);

        String ent = sc.nextLine();

        //coloca os jogadores com id recebidos na entrada na hash
        while(ent.equals("FIM") == false){
            int entrada = Integer.parseInt(ent);
            for (Jogador jogador : jogadores) {
                if (jogador.getId() == entrada) {
                    hash.inserir(jogador);
                    break;
                }
            }
            ent = sc.nextLine();
        }
        String ent2 = sc.nextLine();

        //loop que le a segunda parte da entrada, pesquisando na hash
        
        while(ent2.equals("FIM") == false){
            System.out.print(ent2);
            Jogador x = new Jogador();
            for(Jogador j : jogadores){
                if(j.getNome().equals(ent2)){
                    x = j;
                    break;
                }
            }
            if(hash.pesquisar(x)){
                System.out.print(" SIM");
            }
            else System.out.print(" NAO");
            System.out.println("");
            ent2 = sc.nextLine();
        }

        sc.close();
    }
}