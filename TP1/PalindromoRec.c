#include <stdio.h>
#include <string.h>

int isPalindromo(char *frase, int t);

int main(void){
    char frase[500];

    scanf(" %[^\n]", frase);

    while ((strcmp(frase, "FIM") != 0)) {
        
        if (isPalindromo(frase, strlen(frase)) == 1) {
            printf("SIM\n"); // se retornar 1 é palindromo
        }
        else printf("NAO\n"); // se não retornar 1 não é palíndromo 

        scanf(" %[^\n]", frase); // lendo a próxima frase
    }

    return 0;
}
int isPalindromo(char *frase, int t){
    int tam = strlen(frase);
    //condicao de parada, caso a variavel de controle seja igual a metade do tamanho é pal´rndromo
    if(t <= tam / 2){
        return 1;
    }
    //caso a char das posicoes são diferentes, não é um palíndromo
    else if(*(frase + t - 1) != *(frase + tam - t)){
        return 0; 
    }
    //retorna o método com a variável t atualizada para a próxima iteracão
    else{
        return(isPalindromo(frase, --t));
    }
}