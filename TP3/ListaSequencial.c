#include <stdio.h>
#include <string.h>
#include <stdlib.h>

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

    Jogador lista[4000];
    int n;

    void iniciaLista(){
       n = 0; 
    }

    void inserirInicio(Jogador x){
        int i;
        if(n >= sizeof(lista)){
            printf("Erro em II\n");
            exit(1);
        }
        //levar os elementos uma pos na frente
        for(i = n; i > 0; i--){
            lista[i] = lista[i - 1];
        }
        lista[0] = x;
        n++;
    }

    void inserirFim(Jogador x){
        if(n >= sizeof(lista)){
            printf("Erro em IF\n");
            exit(1);
        }
        lista[n] = x;
        n++;
    }

    void inserir(int pos, Jogador x){
        int i;
        if(n >= sizeof(lista)){
            printf("Erro em I*\n");
            exit(1);
        }
        //movendo todas as posicoes apos pos uma posicao
        for(i = n; i > pos; i--){
            lista[i] = lista[i - 1];
        }
        lista[pos] = x;
        n++;
    }
    
    Jogador removerInicio(){
        int i;
        Jogador resp;
        if(n == 0){
            printf("Erro RI\n");
            exit(1);
        }
        //salvando o primeiro jogador como resposta
        resp = lista[0];
        n--;
        //movendo todos os jogadores uma posicao para tras;
        for(i = 0; i < n; i++){
            lista[i] = lista[i + 1];
        }
        return resp;
    }

    Jogador removerFim(){
        if(n == 0){
            printf("Erro em RF\n");
            exit(1);
        }
        return lista[--n];
    }

    Jogador remover(int pos){
        int i;
        Jogador resp;
        if(n == 0){
            printf("Erro em R*\n");
            exit(1);
        }
        resp = lista[pos];
        n--;
        //movendo todos os jogadores apartir de pos uma posicao para tras
        for(i = pos; i < n; i++){
            lista[i] = lista[i + 1];
        }
        
        return resp;
    }

    void mostrar(){
        for(int i = 0; i < n; i++){
           printf("[%d] ", i);
           imprimir(lista[i]);
        }
    }


int main(void) {
    Jogador jogador[4000];
    
    FILE *arq;
    arq = fopen("/tmp/players.csv", "r, ccs=UTF-8");
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
    iniciaLista();
    scanf(" %s", entrada);
    while(strcmp(entrada, "FIM") != 0){
        int ent = atoi(entrada);
        //colocando os jogadores com os ids recebidos na lista
        inserirFim(jogador[ent]);
        scanf("%s", entrada);
    }

    int num;
    //lendo a quantidade de entradas da segunda parte
    scanf("%d", &num);
    int id;
    int pos;
    char acao[5];
    for(int i = 0; i < num; i++){
        scanf("%s", acao);
        if(strcmp(acao, "II") == 0){
            scanf("%d", &id);
            inserirInicio(jogador[id]);
        }
        else if(strcmp(acao, "IF") == 0){
            scanf("%d", &id);
            inserirFim(jogador[id]);
        }
        else if(strcmp(acao, "I*") == 0){
            scanf("%d", &pos);
            scanf("%d", &id);
            inserir(pos, jogador[id]);
        }
        else if(strcmp(acao, "RI") == 0){
            printf("(R) %s\n", removerInicio().nome);
        }
        else if(strcmp(acao, "RF") == 0){
            printf("(R) %s\n", removerFim().nome);
        }
        else if(strcmp(acao, "R*") == 0){
            scanf("%d", &pos);
            printf("(R) %s\n", remover(pos).nome);
        }
    }
    mostrar();
    

    return 0;
}