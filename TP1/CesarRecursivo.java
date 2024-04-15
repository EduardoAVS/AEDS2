class CesarRecursivo{
    public static void main(String args[]){
        //declarando e lendo a frase
        String frase = MyIO.readLine();
        
        //enquanto a entrada é diferente de FIM continua o programa
        while (!(frase.length() == 3 && frase.charAt(0) == 'F' && frase.charAt(1) == 'I' && frase.charAt(2) == 'M')){
            
            cesar(frase, 0);//chama a funcao com a frase e comecando da primeira char
            MyIO.println(""); //printa nova linha
            frase = MyIO.readLine();//lê a próxima frase
        }
       
    }
    public static void cesar(String frase, int t){
        int tam = frase.length();
        char c;

        if(t >= tam){
            return; // condicao de parada, caso a variavel de controle seja igual ou maior ao tamanho da string para
        }
        else{
            c = frase.charAt(t); //armazenando a variavel da posicao t 
            c += 3; // adicionando 3 posicoes à variável
            MyIO.print(c);//imprimindo a variável
            cesar(frase, ++t);//chamando a funcão com a variável de controle atualizada
        }
    }
}