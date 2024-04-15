class PalindromoRecursivo{
    public static void main(String args[]) {
        //declarando e lendo a frase
        String frase = MyIO.readLine();
        //enquanto a entrada é diferente de FIM continua o programa
        while (!(frase.length() == 3 && frase.charAt(0) == 'F' && frase.charAt(1) == 'I' && frase.charAt(2) == 'M')) {

            //caso seja isPalindromo seja true, printa sim
            if (isPalindromo(frase, frase.length())) {
                MyIO.println("SIM");
            }
            else MyIO.println("NAO");

            //lê uma nova frase para recomecar o loop
            frase = MyIO.readLine();
        }
    }
    public static boolean isPalindromo(String frase, int t){
        int tam = frase.length();

        if(frase.charAt(t - 1) != frase.charAt(tam - t)){
            return false; // caso as char das posicoes sejam diferentes retorna que não é palíndromo
        }
        else if(t <= tam / 2){
            return true; //condicao de parada, quando t passa da metade da String nao e necessario verificar se é palindromo
        }
        else{
            return (isPalindromo(frase, --t));//retorna um novo valor para t, comparando as próximas char
        }
    }
}
