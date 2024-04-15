/*#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>

#define TAM 6

// Definição da estrutura
typedef struct Jogador{
    int id;
    char nome[40];
    int altura;
    int peso;
    char universidade[100];
    int anoNascimento;
    char cidadeNascimento[100];
    char estadoNascimento[100];

}  Jogador;

    void setID(Jogador *p, int id){
        p->id = id;
    }

    void setNome(Jogador *p, char nome[]){
        strcpy(p->nome, nome);
    }

    void setAltura(Jogador *p, int alt){
        p->altura = alt;
    }

    void setPeso(Jogador *p, int peso){
        p->peso = peso;
    }

    void setUniversidade(Jogador *p, char texto[]){
        strcpy(p->universidade, texto);
    }

    void setAno(Jogador *p, int ano){
        p->anoNascimento = ano;
    }

    void setCidade(Jogador *p, char cidade[]){
        strcpy(p->cidadeNascimento,cidade);
    }

    void setEstado(Jogador *p, char estado[]){
        strcpy(p->estadoNascimento,estado);
    }

    int getID(Jogador *p){
        return p->id;
    }

    char* getNome(Jogador *p){
        return p->nome;
    }

    int getAltura(Jogador *p){
        return p->altura;
    }

    int getPeso(Jogador *p){
        return p->peso;
    }

    char* getUniversidade(Jogador *p){
        return p->universidade;
    }

    int getAno(Jogador *p){
        return p->anoNascimento;
    }

    char* getCidade(Jogador *p){
        return p->cidadeNascimento;
    }

    char* getEstadoNascimento(Jogador *p){
        return p->estadoNascimento;
    }

    void ler(Jogador *jogador, char linha[1000]){
        char id[10];
        char nome[40];
        char altura[40];            
        char peso[40];
        char universidade[100];
        char anoNascimento[40];
        char cidadeNascimento[100];
        char estadoNascimento[100];
        int virgulas[7];
        int contVirgula;

        //guardando em um vetor a pos das vírgulas
        contVirgula = 0;
        for(int i = 0; i < strlen(linha); i++){
            if(linha[i] == ','){
                virgulas[contVirgula++] = i;               
            }
        }
        //for da id
        int j;
        for(j = 0; j < virgulas[0]; j++){
            id[j] = linha[j];
        }
        id[++j] = '\0';
        int ID = atoi(id);
        setID(jogador, ID);

        //for do nome
        int k = 0;
        for(j = virgulas[0] + 1; j < virgulas[1]; j++){
            nome[k++] = linha[j];
        }
        nome[k] = '\0';
        k = 0;
        setNome(jogador, nome);

        // Extraindo a altura
        k = 0;
        for (j = virgulas[1] + 1; j < virgulas[2]; j++) {
            altura[k++] = linha[j];
        }
        altura[k] = '\0';
        int Altura = atoi(altura);
        setAltura(jogador, Altura);

        // Extraindo o peso
        k = 0;
        for (j = virgulas[2] + 1; j < virgulas[3]; j++) {
            peso[k++] = linha[j];
        }
        peso[k] = '\0';
        int Peso = atoi(peso);  
        setPeso(jogador, Peso);     

        // Extraindo a universidade
        k = 0;
        if(virgulas[4] - virgulas[3] == 1){
            strcpy(universidade, "nao informado\0");
        }
        else{
            for (j = virgulas[3] + 1; j < virgulas[4]; j++) {
                universidade[k++] = linha[j];
            }
            universidade[k] = '\0';
        }   
        setUniversidade(jogador, universidade);  

        // Extraindo o ano de nascimento
        k = 0;
        for (j = virgulas[4] + 1; j < virgulas[5]; j++) {
            anoNascimento[k++] = linha[j];
        }
        anoNascimento[k] = '\0';
        int ano = atoi(anoNascimento);
        setAno(jogador, ano);

        // Extraindo a cidade de nascimento
        k = 0;
        if(virgulas[6] - virgulas[5] == 1){
            strcpy(cidadeNascimento, "nao informado\0");
        }
        else{
            for (j = virgulas[5] + 1; j < virgulas[6]; j++) {
                cidadeNascimento[k++] = linha[j];
            }
            cidadeNascimento[k] = '\0';
        }
        setCidade(jogador, cidadeNascimento);

        // Extraindo o estado de nascimento
        k = 0;
        if(strlen(linha) - 1 - virgulas[6] == 1){
            strcpy(estadoNascimento, "nao informado\0");
        }
        else{
            for (j = virgulas[6] + 1; j < strlen(linha) - 1; j++) {
                estadoNascimento[k++] = linha[j];
            }
            estadoNascimento[k] = '\0';
        }
        setEstado(jogador, estadoNascimento);

    }
    void imprimir(Jogador jogador){
        printf("## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", getNome(&jogador), getAltura(&jogador), getPeso(&jogador), getAno(&jogador), getUniversidade(&jogador), getCidade(&jogador), getEstadoNascimento(&jogador));
    }

    typedef struct Celula {
        Jogador elemento;        // Elemento inserido na celula.
        struct Celula* prox; // Aponta a celula prox.
    } Celula;

    Celula* novaCelula(Jogador elemento) {
        Celula* nova = (Celula*) malloc(sizeof(Celula));
        nova->elemento = elemento;
        nova->prox = NULL;
        return nova;
    }

    //FILA PROPRIAMENTE DITA ========================================================
    Celula* primeiro;
    Celula* ultimo;

    void iniciaLista (Jogador x) {
        primeiro = novaCelula(x);
        ultimo = primeiro;
    }

    int tamanho(){
        Celula* i;
        int cont = 0;
        for (i = primeiro->prox; i != NULL; i = i->prox) {
            cont++;
        }

        return cont;
    }
    Jogador remover() {
        if (primeiro == ultimo) {
            printf("Erro na remocão\n");
            exit(1);
        }
        Celula* tmp = primeiro;
        primeiro = primeiro->prox;
        Jogador resp = primeiro->elemento;
        tmp->prox = NULL;
        free(tmp);
        tmp = NULL;
        return resp;
    }

    void inserir(Jogador x) {
        if(tamanho() > 4){
            remover();
        }
        ultimo->prox = novaCelula(x);
        ultimo = ultimo->prox;
    }
    

    int arredonda(float number){
        return (number >= 0) ? (int)(number + 0.5) : (int)(number - 0.5);
    }
    int mediaAlturas(){
        int cont = 0, soma = 0;
        Celula* i;
        for(i = primeiro->prox; i != NULL; i = i->prox){
            soma += i->elemento.altura;
            cont++;
        }
        int resp = arredonda((float)soma / cont);
        return resp;
    }


    void mostrar() {
        Celula* i;
        int cont = 0;
        for (i = primeiro->prox; i != NULL; i = i->prox) {
            printf("[%d] ", cont++);
            imprimir(i->elemento);
        }

    }


int main(void) {
    Jogador jogador[4000];
    
    FILE *arq;
    arq = fopen("/tmp/players.csv", "r, ccs=UTF-8");
    char linha[1000];
    //descarte primeira linha
    fgets(linha, 1000, arq);
    int cont = 0;

    //lendo todas as linhas do arquivo
    while(fgets(linha, 1000, arq)){       
        ler(&jogador[cont++], linha);

    }

    //pegando os id de entrada e colocando na lista

    char entrada[5];
    iniciaLista(jogador[0]);
    scanf(" %s", entrada);
    while(strcmp(entrada, "FIM") != 0){
        int ent = atoi(entrada);
        //colocando os jogadores com os ids recebidos na lista
        inserir(jogador[ent]);
        printf("%d\n", mediaAlturas());
        scanf("%s", entrada);
    }

    int num;
    //lendo a quantidade de entradas da segunda parte
    scanf("%d", &num);
    int id;

    char acao;
    for(int i = 0; i < num * 2; i++){
        scanf("%c", &acao);
        if(acao == 'I'){
            scanf("%d", &id);
            inserir(jogador[id]);
            printf("%d\n", mediaAlturas());
        }
        else if(acao == 'R'){
            printf("(R) %s\n", remover().nome);
        }
    }
    mostrar();
    

    return 0;
}*/