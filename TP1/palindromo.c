#include <stdio.h>
#include <string.h>
#include <stdbool.h>

int main(void) {
    char frase[500];

    scanf(" %[^\n]", frase);

    while ((strcmp(frase, "FIM") != 0)) {
        
        bool isPalindromo = true;
        int tam = strlen(frase);

        for (int i = 0; i < tam / 2; i++) {
            if (frase[i] != frase[tam - i - 1]) {
                isPalindromo = false;
                printf("NAO\n");
                break;
            }
        }
        
        if (isPalindromo) {
            printf("SIM\n");
        }
        scanf(" %[^\n]", frase);
    }

    return 0;
}
