import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class ListaDupla{

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
            System.out.print("[" + id);
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
            System.out.println("");
        }
        
    }
    //classe célula
    static class CelulaDupla {
        public Jogador elemento;
        public CelulaDupla ant;
        public CelulaDupla prox;

        /**
         * Construtor da classe.
         */
        public CelulaDupla(){
            this(null);
        }

        public CelulaDupla(Jogador elemento){
            this.elemento = elemento;
            this.ant = this.prox = null;
        }
    }
    //classe lista
    static class Lista{
        //lista com os jogadores como variável global
        private CelulaDupla primeiro;
        private CelulaDupla ultimo;
        private int n;

        //chamar com o primeiro número depois de FIM
        public Lista(){
            primeiro = new CelulaDupla();
            ultimo = primeiro;
        }

        public int getTamanho(){
            return n;
        }


        public void inserirInicio(Jogador x) throws Exception {
            CelulaDupla tmp = new CelulaDupla(x);
            tmp.ant = primeiro;
            tmp.prox = primeiro.prox;
            primeiro.prox = tmp;
            if(primeiro == ultimo){
                ultimo = tmp;
            }
            else{
                tmp.prox.ant = tmp;
            }
            tmp = null;
            n++;
        }
        public void inserirFim(Jogador x) throws Exception {
            ultimo.prox = new CelulaDupla(x);
            ultimo.prox.ant = ultimo;
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
                CelulaDupla i = primeiro;
                //caminhar ate a pos da insercao
                for(int j = 0; j < pos; j++, i = i.prox);
                CelulaDupla tmp = new CelulaDupla(x);
                tmp.ant = i;
                tmp.prox = i.prox;
                tmp.ant.prox = tmp.prox.ant = tmp;
                tmp = i = null;
                n++;
            }

        }

        public Jogador removerInicio() throws Exception {
            //validar remocao
            if (primeiro == ultimo) {
                throw new Exception("Erro ao remover!");
            }
            CelulaDupla tmp = primeiro;
            primeiro = primeiro.prox;
            Jogador resp = primeiro.elemento;
            tmp.prox = tmp.ant= null;
            tmp = null;
            n--;
            return resp;
        }

        public Jogador removerFim() throws Exception {
            //validar remocao
            if (primeiro == ultimo) {
                throw new Exception("Erro ao remover!");
            }
            //caminhar ate a penultima CelulaDupla
            Jogador resp = ultimo.elemento;
            ultimo = ultimo.ant;
            ultimo.prox.ant = null;
            ultimo.prox = null;
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
                CelulaDupla i = primeiro.prox;
                //encontrando a posicao do anterior a pos
                for(int j = 0; j < pos; j++, i = i.prox);
                CelulaDupla tmp = i.prox;
                resp = tmp.elemento;
                i.ant.prox = i.prox;
                i.prox.ant = i.ant;
                i.prox = tmp.prox;
                tmp.prox = null;
                i = tmp = null;
                n--;
             }
            return resp;
            }
        public void mostrar(){
            CelulaDupla i = primeiro.prox;
            for(int j = 0; j < n; j++, i = i.prox){
                i.elemento.imprimir();
            }
        }
        //chamada sem parâmetros que chama com as Celulas primeiro e ultimo
        public void quicksort(){
            quicksort(primeiro.prox, ultimo);
        }
        public void quicksort(CelulaDupla esq, CelulaDupla dir) {
            if (esq != null && dir != null && esq != dir && esq.ant != dir) {
                CelulaDupla pivo = particionar(esq, dir);
                quicksort(esq, pivo.ant);
                quicksort(pivo.prox, dir);
            }
        }


        public CelulaDupla particionar(CelulaDupla esquerda, CelulaDupla direita) {
            Jogador pivo = direita.elemento;
            CelulaDupla i = esquerda.ant;
    
            for (CelulaDupla j = esquerda; j != direita; j = j.prox) {
                
                if (j.elemento.getEstadoNascimento().compareTo(pivo.getEstadoNascimento()) < 0
                        || (j.elemento.getEstadoNascimento().equals(pivo.getEstadoNascimento())
                                && j.elemento.getNome().compareTo(direita.elemento.getNome()) < 0)) {
                    i = (i == null) ? esquerda : i.prox;
                    swap(i, j);
                }
            }
    
            i = (i == null) ? esquerda : i.prox;
            swap(i, direita);
    
            return i;
        }

        void swap(CelulaDupla celula1, CelulaDupla celula2) {
            Jogador tmp = celula1.elemento;
            celula1.elemento = celula2.elemento;
            celula2.elemento = tmp;
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

        //chamando quicksort sem parâmetros
        lista.quicksort();

        lista.mostrar();
        sc.close();
    }
}