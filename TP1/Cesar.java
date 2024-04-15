class Cesar{
    public static void main(String args[]){
        //declarando e lendo a frase
        String frase = MyIO.readLine();
        
        //enquanto a entrada é diferente de FIM continua o programa
        while (!(frase.length() == 3 && frase.charAt(0) == 'F' && frase.charAt(1) == 'I' && frase.charAt(2) == 'M')){
            //string que armazena a frase codificada
            char[] cod = new char[frase.length()];
            //loop para mover todos os caracteres 3 posicões
            for(int i = 0; i < frase.length(); i++){
                char c = frase.charAt(i);//armazena a char na posicao i em c
                c += 3; // adiciona 3 ao código na tabela ASCII
                cod[i] = c;
            }
            MyIO.println(new String(cod)); //printa o código na tela e o transforma de array de char para String
            frase = MyIO.readLine();//lê a próxima frase
        }
       
    }
}