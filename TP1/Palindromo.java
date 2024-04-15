class Palindromo {
    public static void main(String args[]) {
        //declarando e lendo a frase
        String frase = MyIO.readLine();
        //enquanto a entrada é diferente de FIM continua o programa
        while (!(frase.length() == 3 && frase.charAt(0) == 'F' && frase.charAt(1) == 'I' && frase.charAt(2) == 'M')) {

            boolean isPalindromo = true; //boolean para controlar se é palíndromo
            
            int tam = frase.length(); //tamanho da frase
            for (int i = 0; i < tam / 2; i++) {
                //se a char da posicao i é diferente da posicao i comecando a contar pelo fim
                if (frase.charAt(i) != frase.charAt(tam - i - 1)) {
                    MyIO.println("NAO"); //printa que não é palíndromo
                    isPalindromo = false; // boolean vira falso
                    break; //termina o loop
                }
            }
            //caso seja isPalindromo seja true, printa sim
            if (isPalindromo) {
                MyIO.println("SIM");
            }
            //lê uma nova frase para recomecar o loop
            frase = MyIO.readLine();
        }
    }
}
