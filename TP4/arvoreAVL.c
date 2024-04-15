#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>
#include <math.h>


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

    typedef struct No{
        char elemento[1000];
        struct No* esq, *dir;
        int nivel;
    }No;

    //construtor No so com o elemento
    No* novoNo(char x[]){
        No* nova = (No*) malloc(sizeof(No)); //alocacao dinamica
        strcpy(nova->elemento, x); 
        nova->esq = nova->dir = NULL;
        nova->nivel = 1;
        return nova;
    }

    No* novoNoCompleto(char x[], No* esq, No* dir, int nivel){
        No* nova = (No*) malloc(sizeof(No)); //alocacao dinamica
        strcpy(nova->elemento, x); 
        nova->esq = esq;
        nova->dir = dir;
        nova->nivel = nivel;
        return nova;
    }

    int getNivel(No* i){
        return (i == NULL) ? 0 : i->nivel; 
    }
    void setNivel(No* i){
        i->nivel = 1 + (getNivel(i->esq) > getNivel(i->dir)? getNivel(i->esq) : getNivel(i->dir));
    }

    
    //no raiz como variável global
    No* raiz;
    
    //inicia a árvore
    void iniciar(){
        raiz =  NULL;
    }

    //métodos de pesquisa

    bool pesquisar1(char s[], No* i){
        printf("1 ");
        bool resp;

        if(strcmp(i -> elemento, s) == 0){
            resp = true;
            printf("2 ");
        }
        else if(i == NULL){
            resp = false;
            printf("2 ");
        }
        else if(strcmp(s, i->elemento) > 0){
            printf(" dir");
            resp = pesquisar1(s, i->dir);
        }
        else{
            printf(" esq");
            resp = pesquisar1(s, i -> esq);
        }

        return resp;
    }

    bool pesquisar(char s[]){
        printf("0 ");
        return pesquisar1(s, raiz);

    }

    No* rotacionarDir(No* no){
        No* noEsq = no->esq;
        No* noEsqDir = noEsq->dir;

        noEsq->dir = no;
        no->esq = noEsqDir;;
        setNivel(no);
        setNivel(noEsq);

        return noEsq;
    }
    
    No* rotacionarEsq(No* no){
        No* noDir = no->dir;
        No* noDirEsq = noDir->esq;

        noDir->esq = no;
        no->dir = noDirEsq;
        
        setNivel(no);
        setNivel(noDir);

        return noDir;
    }

    No* balancear(No* no){
        if(no != NULL){
            int fator = getNivel(no->dir) - getNivel(no->esq);

            //arvore balanceada
            if(abs(fator) <= 1){
                setNivel(no);
            }

            //desbalanceada para a direita
            else if(fator == 2){
                int fatorFilhoDir = getNivel(no->dir->dir) - getNivel(no->dir->esq);
                //se o filho da direita também estiver desbalanceado
                if(fatorFilhoDir == -1){
                    no->dir = rotacionarDir(no->dir);
                }
                no = rotacionarEsq(no);
            }
            else if(fator == -2){
                int fatorFilhoEsq = getNivel(no->esq->dir) - getNivel(no->esq->esq);
                //se o filho da esquerda também estiver desbalanceado
                if(fatorFilhoEsq == 1){
                    no->esq = rotacionarEsq(no->esq);
                }
                no = rotacionarDir(no);
            }
        }
        return no;
    }
    //inserir
    No* inserir1(char s[], No* i) {
        if(i == NULL) {
            printf("I %s ", s);
            i = novoNo(s);
        }
        else if(strcmp(s, i->elemento) > 0) {
            printf("dir ");
            i->dir = inserir1(s, i->dir);
        }
        else if(strcmp(s, i->elemento) < 0) {
            printf("esq ");
            i->esq = inserir1(s, i -> esq);
        }
        else {
            printf("Elemento inserido já existe!\n");
        }

        return balancear(i);
    }

    void inserir(char s[]){
        raiz = inserir1(s, raiz);
    } 

    void percorreCentral(No* i){
        percorreCentral(i->esq);
        printf("%s\n", i->elemento);
        percorreCentral(i->dir);
    }
    void percorrer(){
        percorreCentral(raiz);
    }   


int main(void) {
    Jogador jogador[4000];
    
    FILE *arq;
    arq = fopen("players.csv", "r, ccs=UTF-8");
    char linha[1000];
    int virgulas[7];
    int contVirgula;
    //descarte primeira linha
    fgets(linha, 1000, arq);
    int cont = 0;

    //lendo todas as linhas do arquivo
    while(fgets(linha, 1000, arq)){       
        ler(&jogador[cont++], linha);

    }

    //pegando os id de entrada e colocando na lista
    Jogador jogadorID[4000];

    char entrada[5];
    iniciar();
    scanf(" %s", entrada);
    while(strcmp(entrada, "FIM") != 0){
        int ent = atoi(entrada);
        //colocando os jogadores com os ids recebidos na lista
        inserir(getNome(&jogador[ent]));
        scanf(" %s", entrada);
    }

    char entrada2[100];
    scanf(" %[^\n]", entrada2);
    printf("b ");
    while(strcmp(entrada2, "FIM") != 0){
        printf("%s raiz ", entrada2);
        if(pesquisar(entrada2)){
            printf(" SIM\n");
        }
        else{
            printf(" NAO\n");
        }

        scanf(" %[^\n]", entrada2);
    }
    
    return 0;
}