class IsRecursivo{
    public static void main(String args[]){
        //declarando e lendo a frase
        String s = MyIO.readLine();
        
        //enquanto a entrada é diferente de FIM continua o programa
        while (!(s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M')){
            //chamando os métodos recursivos
            int tam = s.length(); 
            isVogal(s, tam - 1);
            isConsoante(s, tam - 1);
            isInteiro(s, tam - 1);
            isReal(s, tam - 1, 0);
            MyIO.println("");//pular linha
            s = MyIO.readLine();//recebe a próxima String como entrada
        }
    }

    public static void isVogal(String s, int i){       
        if(i < 0){//condicão de parada
            MyIO.print("SIM ");
            return;
        } 
        char c = s.charAt(i); 
        if(c >= 'A' && c <= 'Z') c += 32; //coloca todas as letras como minúsculas
        //caso não seja vogal imprime NAO e termina o método
        else if(c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u'){
            MyIO.print("NAO ");
            return;
        }
        else{
            isVogal(s, --i); //chama o método com a variável de controle atualizada para a próxima posicão
        }
    }
    public static void isConsoante(String s, int i){
        
        if(i < 0){//condicão de parada
            MyIO.print("SIM ");
            return;
        } 
        char c = s.charAt(i);
        if(c >= 'A' && c <= 'Z') c += 32; //coloca todas as letras como minúsculas
        //caso não seja consoante imprime NAO e termina o método
        else if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' && (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')){
            MyIO.print("NAO ");
            return;
        }
        else{
            isConsoante(s, --i);//chama o método com a variável de controle atualizada para a próxima posicão
        }
    }

    public static void isInteiro(String s, int i){
        
        if(i < 0){//condicão de parada
            MyIO.print("SIM ");
            return;
        } 
        char c = s.charAt(i);
        //caso não seja número imprime NAO e termina o método
        if(!(c >= '0' && c <= '9')){
            MyIO.print("NAO ");
            return;
        }
        else{
            isInteiro(s, --i);//chama o método com a variável de controle atualizada para a próxima posicão
        }
    }
    public static void isReal(String s, int i, int virgulaEponto){
        
        if(i < 0){//condicão de parada
            MyIO.print("SIM ");
            return;
        } 
        char c = s.charAt(i);
        if(c == ',' || c == '.'){
            virgulaEponto++;
        }
        if(virgulaEponto > 1){
            MyIO.print("NAO ");
            return;
        }
        //caso não seja número
         ou ponto ou vírgula imprime NAO e termina o método
        if(!(c >= '0' && c <= '9') && (c != ',' && c != '.')){
            MyIO.print("NAO ");
            return;
        }
        else{
            isReal(s, --i, virgulaEponto);//chama o método com a variável de controle atualizada para a próxima posicão
        }

    }

}