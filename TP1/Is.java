class Is{
    public static void main(String args[]){
        //declarando e lendo a frase
        String s = MyIO.readLine();
        
        //enquanto a entrada é diferente de FIM continua o programa
        while (!(s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M')){
            //chamando os métodos iterativos 
            isVogal(s);
            isConsoante(s);
            isInteiro(s);
            isReal(s);
            MyIO.println("");//pular linha
            s = MyIO.readLine();//recebe a próxima String como entrada
        }
       
    }
    public static void isVogal(String s){
        for(int i = 0; i < s.length(); i++){
            //se for diferente de vogal termina o método
            if((s.charAt(i) >= 'a' && s.charAt(i) <= 'z') || (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') || ((s.charAt(i) >= '0' && s.charAt(i) <= '9'))){
                if(s.charAt(i) != 'a' && s.charAt(i) != 'e' && s.charAt(i) != 'i' && s.charAt(i) != 'o' && s.charAt(i) != 'u' && s.charAt(i) != 'A' && s.charAt(i) !=  'E' && s.charAt(i) != 'I' && s.charAt(i) != 'O' && s.charAt(i) != 'U'){
                    MyIO.print("NAO ");
                    return;
                }
            }
        }
        MyIO.print("SIM ");//caso o método acabe sem retornar, só tem vogal na string
    }

    public static void isConsoante(String s){
        for(int i = 0; i < s.length(); i++){
            //se for diferente de consoante termina o método
            if((s.charAt(i) >= 'a' && s.charAt(i) <= 'z' )|| (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') || ((s.charAt(i) >= '0' && s.charAt(i) <= '9'))){
                if(s.charAt(i) == 'a' || s.charAt(i) == 'e' || s.charAt(i) == 'i' || s.charAt(i) == 'o' || s.charAt(i) == 'u' || s.charAt(i) == 'A' || s.charAt(i) ==  'E' || s.charAt(i) == 'I' || s.charAt(i) == 'O' || s.charAt(i) == 'U' || ((s.charAt(i) >= '0' && s.charAt(i) <= '9'))){
                    MyIO.print("NAO ");
                    return;
                }
            }
        }
        MyIO.print("SIM ");//caso o método acabe sem retornar, só tem vogal na string
    }

    public static void isInteiro(String s){
        for(int i = 0; i < s.length(); i++){
            //caso não seja um número não é inteiro e termina o método
            if(!(s.charAt(i) >= '0' && s.charAt(i) <= '9')){
                MyIO.print("NAO ");
                return;
            }
        }
        MyIO.print("SIM ");
    }

    public static void isReal(String s){
        int virgulaEponto = 0; //caso tenha mais de uma virgula ou ponto, não é inteiro

        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == ',' || s.charAt(i) == '.'){
                virgulaEponto++; //contando o número de pontos e vírgulas
            }
        }
        if(virgulaEponto > 1){
            MyIO.print("NAO ");
            return;
        }
        for(int i = 0; i < s.length(); i++){
            if(!(s.charAt(i) >= '0' && s.charAt(i) <= '9')&& s.charAt(i) != ',' && s.charAt(i) != '.'){
                MyIO.print("NAO ");                
                return;
            }
        }
        MyIO.print("SIM ");
    }

}