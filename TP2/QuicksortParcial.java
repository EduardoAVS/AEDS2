import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


class QuicksortParcial{

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
            System.out.print("[");
            System.out.print(id);
            System.out.print(" ## ");
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
            System.out.print("]");
            
            } 
        }
        public static void swap(ArrayList<Jogador> jogadores, int x, int y){
            Jogador tmp = jogadores.get(x);
            jogadores.set(x, jogadores.get(y));
            jogadores.set(y, tmp);
        }
        public static void quicksort(ArrayList<Jogador> jogador, int esq, int dir){
            int i = esq, j = dir;
            Jogador pivo = jogador.get((esq + dir) / 2);
            while(i <= j){
                while(jogador.get(i).getEstadoNascimento().compareTo(pivo.getEstadoNascimento()) < 0 || (jogador.get(i).getEstadoNascimento().equals(pivo.getEstadoNascimento()) && jogador.get(i).getNome().compareTo(pivo.getNome()) < 0 )) i++;
                while(jogador.get(j).getEstadoNascimento().compareTo(pivo.getEstadoNascimento()) > 0 || (jogador.get(j).getEstadoNascimento().equals(pivo.getEstadoNascimento()) && jogador.get(j).getNome().compareTo(pivo.getNome()) > 0 )) j--;

                if(i <= j){
                    swap(jogador, i, j);
                    i++;
                    j--;
                }
            }
            if (esq < j){
               quicksort(jogador, esq, j);
            }
            //deixando a ordenacão parcial  
            if (i < dir && i < 10){
               quicksort(jogador, i, dir); 
            }  
        }

    
        public static void main(String args[]) {
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
       
        while(ent.equals("FIM") == false){
            int entrada = Integer.parseInt(ent);
            for (Jogador jogador : jogadores) {
 
                if (jogador.getId() == entrada) {
                    jogadorID.add(jogador);
                    break;
                }
            }
            ent = sc.nextLine();
        }
        //ordenando a array list pelo Estado de nascimento
        int comp = 0;
        int mov = 0;
        quicksort(jogadorID, 0, jogadorID.size() - 1);
        
         for(int i = 0; i < 10; i++){
            Jogador j = jogadorID.get(i);
            j.imprimir();
            System.out.println("");
         }
        
            sc.close();
            long fim = System.currentTimeMillis();
            long tempoDeExecucao = fim - inicio;
             //fazendo o arquivo de log
            String nomeDoArquivo = "matrícula_selecao.txt";

            try (FileOutputStream fos = new FileOutputStream(nomeDoArquivo);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos))) {

                // Escrever dados no arquivo
                writer.write("814939\t" +"Comparacões: " +comp +"\tMovimentacões: " + mov + "\tTempo de execucão: " + tempoDeExecucao);

                writer.close();
            } catch (IOException e) {
                System.err.println("Ocorreu um erro ao criar o arquivo: " + e.getMessage());
            }
        }
}
