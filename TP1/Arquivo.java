import java.io.RandomAccessFile;

class Arquivo {
    public static void main(String args[])throws Exception {
        int n = MyIO.readInt(); 
        // declarando o arquivo para escrita e leitura
        RandomAccessFile arqEntrada = new RandomAccessFile("entrada.txt", "rw");
        for(int i = 0; i < n; i++){
            float f = MyIO.readFloat(); //lendo float como entrada
            arqEntrada.writeFloat(f); //imprimindo no arquivo
        }
        arqEntrada.close();
        //declarando para leitura
        RandomAccessFile arq = new RandomAccessFile("entrada.txt", "r");

        long tam = arq.length();
        long pos = tam - 4; //tamanho - tamanho de um float
        while(pos >= 0){
            arq.seek(pos);
            float num = arq.readFloat();
            pos -= 4;
       
            //formatando a saída
            if(num % 1 == 0)MyIO.println((int)num);
            else MyIO.println(num);
        }
        arq.close();
    }
}