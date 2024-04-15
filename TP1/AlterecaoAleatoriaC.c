#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

void alterar(char frase[], int t, char antes, char depois);

int main(void){
    char frase[500];//declarando a frase
    char antes, depois; //variáveis com a char que será substituída e a que substituirá
    
    scanf(" %[^\n]", frase);//lendo a frase
    
    while ((strcmp(frase, "FIM") != 0)) {
        srand(4);
        antes = (char) abs(rand() % 26)+ 'a'; //valor aleatório para a char substituída(entre 'a' e 'z')
        depois = (char) abs(rand() % 26) + 'a'; //valor aleatótio para a char que substituirá(entre 'a' e 'z')
        
        alterar(frase, strlen(frase) - 1, antes, depois);
        printf("\n");//pula uma linha na entrada

        scanf(" %[^\n]", frase); // lendo a próxima frase
    }

    return 0;
}
void alterar(char frase[], int t, char antes, char depois){
    int tam = strlen(frase);

    if(t < 0) return; // condicao de parada, caso a variavel de controle seja igual ou maior ao tamanho da string para

    else{
        if(frase[t] == antes){
            frase[t] = depois; //caso a char lida é igual a que deve ser substituída, troca 
        }
        alterar(frase, t - 1, antes, depois); //chama a funcão com o valor de t atualizado para a próxima posicao
        printf("%c", frase[t]);//imprime a char na tela
    }


}