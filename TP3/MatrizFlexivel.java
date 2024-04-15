import java.util.*;

class Celula {
    int elemento;
    Celula dir;
    Celula esq;
    Celula sup;
    Celula inf;

    public Celula() {

    }

    public Celula(int elemento) {
        this.elemento = elemento;
        this.dir = null;
        this.esq = null;
        this.inf = null;
        this.sup = null;
    }

}

class Matriz {
    private Celula inicio;
    private int linhas, colunas;

    public Matriz() {
    }

    public Matriz(int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;

        inicio = new Celula();
        Celula atual = inicio;

        // construindo colunas
        for (int i = 1; i < colunas; i++) {
            Celula nova = new Celula();
            atual.dir = nova;
            nova.esq = atual;
            atual = nova;
        }

        Celula esqCel = inicio;

        // construindo linhas
        for (int i = 1; i < linhas; i++) {
            Celula nova = new Celula();
            esqCel.inf = nova;
            nova.sup = esqCel;
            atual = nova;

            for (int j = 1; j < colunas; j++) {
                Celula celula = new Celula();
                atual.dir = celula;
                celula.esq = atual;
                celula.sup = esqCel.dir;
                esqCel.dir.inf = celula;
                esqCel = esqCel.dir;
                atual = atual.dir;
            }
            esqCel = nova;
        }

    }

    public void insert(int[][] matriz) {
        Celula atual = inicio;

        for (int i = 0; i < linhas; i++) {
            Celula temp = atual;
            for (int j = 0; j < colunas; j++) {
                temp.elemento = matriz[i][j];
                if (temp.dir != null) {
                    temp = temp.dir;
                }
            }
            if (atual.inf != null) {
                atual = atual.inf;
            }
        }

    }

    public void mostarDiagonalPrincipal() {
        ArrayList<Integer> elemento = new ArrayList<>();
        Celula atual = inicio;
        while (atual != null) {
            elemento.add(atual.elemento);
            if (atual.inf != null && atual.inf.dir != null) {
                atual = atual.inf.dir;
            } else {
                break;
            }
        }
        for (int x : elemento) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    public void mostarDiagonalSecundaria() {
        ArrayList<Integer> elemento = new ArrayList<>();
        Celula atual = inicio;
        while (atual.dir != null) {
            atual = atual.dir;
        }

        while (atual != null) {
            elemento.add(atual.elemento);
            if (atual.inf != null && atual.inf.esq != null) {
                atual = atual.inf.esq;
            } else {
                break;
            }
        }
        for (int x : elemento) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    public Matriz soma(Matriz m1, Matriz m2) {
        Matriz matrizSoma = new Matriz(m1.linhas, m1.colunas);
        Celula atual1 = m1.inicio;
        Celula atual2 = m2.inicio;
        Celula atualSoma = matrizSoma.inicio;

        for (int i = 0; i < linhas; i++) {
            Celula tmp1 = atual1;
            Celula tmp2 = atual2;
            Celula tmpSoma = atualSoma;
            for (int j = 0; j < colunas; j++) {
                tmpSoma.elemento = tmp1.elemento + tmp2.elemento;

                if (tmp1.dir != null) {
                    tmp1 = tmp1.dir;
                    tmp2 = tmp2.dir;
                    tmpSoma = tmpSoma.dir;
                }
            }
            if (atual1.inf != null) {
                atual1 = atual1.inf;
                atual2 = atual2.inf;
                atualSoma = atualSoma.inf;
            }
        }

        return matrizSoma;
    }

    public int[][] multiplicacao(Matriz mat) {
    if (colunas != mat.linhas) {
        return null; // Matrices cannot be multiplied
    }

    int[][] result = new int[linhas][mat.colunas];

    Celula aux1 = inicio;

    for (int i = 0; i < linhas; i++) {
        Celula aux2 = mat.inicio;

        for (int j = 0; j < mat.colunas; j++) {
            int sum = 0;
            Celula temp1 = aux1;
            Celula temp2 = aux2;

            for (int k = 0; k < colunas; k++) {
                sum += temp1.elemento * temp2.elemento;
                temp1 = temp1.dir;
                temp2 = temp2.inf;
            }

            result[i][j] = sum;
            aux2 = aux2.dir;
        }

        aux1 = aux1.inf;
    }

    return result;
}

    public void exibirMatriz() {
        Celula atual = inicio;
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                System.out.print(atual.elemento + " ");
                if (atual.dir != null) {
                    atual = atual.dir;
                }
            }
            System.out.println();
            if (inicio.inf != null) {
                inicio = inicio.inf;
                atual = inicio;
            }
        }
    }

}

public class MatrizFlexivel{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int k = 0; k < n; k++) {
            int linhas = sc.nextInt();
            int colunas = sc.nextInt();
            int[][] entrada1 = new int[linhas][colunas];

            for (int i = 0; i < linhas; i++) {
                for (int j = 0; j < colunas; j++) {
                    entrada1[i][j] = sc.nextInt();
                }
            }
            Matriz matriz1 = new Matriz(linhas, colunas);
            matriz1.insert(entrada1);

            int[][] entrada2 = new int[linhas][colunas];

            linhas = sc.nextInt();
            colunas = sc.nextInt();
            for (int i = 0; i < linhas; i++) {
                for (int j = 0; j < colunas; j++) {
                    entrada2[i][j] = sc.nextInt();
                }
            }
            Matriz matriz2 = new Matriz(linhas, colunas);
            matriz2.insert(entrada2);

            matriz1.mostarDiagonalPrincipal();
            matriz1.mostarDiagonalSecundaria();
            Matriz resSoma = matriz1.soma(matriz1, matriz2);
            int[][] resMulti = matriz1.multiplicacao(matriz2);
            resSoma.exibirMatriz();

            for (int i = 0; i < resMulti.length; i++) {
                for (int j = 0; j < resMulti.length; j++) {
                    System.out.print(resMulti[i][j] + " ");
                }
                System.out.println();
            }

        }

        sc.close();
    }

}