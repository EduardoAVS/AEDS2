import java.util.Random;

class AlteracaoAleatoria{
    public static void main(String args[]){
        //usando a classe Random
        Random gerador = new Random();
        gerador.setSeed(4);

        //declarando e lendo a frase
        String frase = MyIO.readLine();
        
        //enquanto a entrada é diferente de FIM continua o programa
        while (!(frase.length() == 3 && frase.charAt(0) == 'F' && frase.charAt(1) == 'I' && frase.charAt(2) == 'M')){
            char[] novaFrase = new char[frase.length()];
            char antes, depois; //char que será substituída e que substituirá respectivamente

            //gerando um inteiro aleatorio e o transformando em char com ascii
            antes = (char)('a' + (Math.abs(gerador.nextInt()) % 26)); 
            depois =  (char)('a' + (Math.abs(gerador.nextInt()) % 26));
            
            for(int i = 0; i < frase.length(); i++){
                if(frase.charAt(i) == antes){
                    novaFrase[i] = depois; //substitui as char
                }
                else
                novaFrase[i] = frase.charAt(i); //coloca a char da frase na nova frase
            }
            MyIO.println(new String(novaFrase)); //printa a frase na tela e o transforma de array de char para String
            
            frase = MyIO.readLine();//lendo a próxima frase
        }
       
    }
}