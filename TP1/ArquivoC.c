#include <stdio.h>

int main() {
    int n;
    FILE *arqEntrada;
    FILE *arqSaida;

    // Lê o número de elementos a serem inseridos no arquivo
    scanf("%d", &n);

    // Abre o arquivo para escrita
    arqEntrada = fopen("entrada.txt", "wb");
    if (arqEntrada == NULL) {
        perror("Erro ao abrir o arquivo de entrada");
        return 1;
    }

    // Lê os valores float e os escreve no arquivo binário
    float f;
    for (int i = 0; i < n; i++) {
        scanf("%f", &f);
        fwrite(&f, sizeof(float), 1, arqEntrada);
    }

    // Fecha o arquivo de entrada
    fclose(arqEntrada);

    // Abre o arquivo para leitura
    arqSaida = fopen("entrada.txt", "rb");
    if (arqSaida == NULL) {
        perror("Erro ao abrir o arquivo de saída");
        return 1;
    }

    // Obtém o tamanho do arquivo
    fseek(arqSaida, 0, SEEK_END);
    long tam = ftell(arqSaida);
    fseek(arqSaida, 0, SEEK_SET);

    // Lê os valores do arquivo e formata a saída
    while (tam >= sizeof(float)) {
        fseek(arqSaida, tam - sizeof(float), SEEK_SET);
        fread(&f, sizeof(float), 1, arqSaida);
        tam -= sizeof(float);

        if ((int)f == f) {
            printf("%d\n", (int)f);
        } else {
            
            printf("%g\n", f);
        }
    }

    // Fecha o arquivo de saída
    fclose(arqSaida);

    return 0;
}
