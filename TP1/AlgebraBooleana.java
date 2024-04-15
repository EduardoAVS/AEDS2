class AlgebraBooleana{
    
  public static int encontrarPrimeiroDigito(String s) {
    return encontrarPrimeiroDigito(s, 0);
}

public static int encontrarUltimaOperacao(String s) {

  int not = s.lastIndexOf("not");
  int and = s.lastIndexOf("and");
  int or = s.lastIndexOf("or");

  if (not > and && not > or) {

      return not;
  } else if (and > not && and > or) {

      return and;
  } else if (or > not && or > and) {

      return or;
  }

  return 0;
}

public static int encontrarPrimeiroDigito(String s, int begin) {
    for (int i = begin; i < s.length(); i++) {
        if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
            return i;
        }
    }
    return 0;
}

public static void main(String[] args) {
    
    while(true){
      int N = MyIO.readInt();
      if(N==0){
        break;
      }
      int[] array = new int[N];
      for(int i=0;i<N;i++){
        array[i] = MyIO.readInt();
      }
      String expressao=MyIO.readLine();
      if (expressao.charAt(expressao.length() - 1) == ' ') {
        expressao = expressao.substring(0, expressao.length() - 1);
      }
      for(int i=0;i<N;i++){
        if (i == 0) {
          if (array[0] == 0) {
            expressao = expressao.replace("not(A)", "1");
            expressao = expressao.replace("A", "0");
          } else {
            expressao = expressao.replace("not(A)", "0");
            expressao = expressao.replace("A", "1");
          }
      } else if (i == 1) {
          if (array[1] == 0) {
            expressao = expressao.replace("not(B)", "1");
            expressao = expressao.replace("B", "0");
          } else {
            expressao = expressao.replace("not(B)", "0");
            expressao = expressao.replace("B", "1");
          }
      } else if (i == 2) {
          if (array[2] == 0) {
            expressao = expressao.replace("not(C)", "1");
            expressao = expressao.replace("C", "0");
          } else {
            expressao = expressao.replace("not(C)", "0");
            expressao = expressao.replace("C", "1");
          }
      }
      }
        while(expressao.length()>1){
          int last = encontrarUltimaOperacao(expressao);
          String exp = expressao.substring(last, expressao.indexOf(")", last) + 1);
          if (exp.charAt(0) != 'n') {
            int count = 1;
                  for (int x=0;x<exp.length();x++) {
                    if (exp.charAt(x) == ',') {
                      count++;
                    }
                  }
                  int[] p = new int[count];
                  int pos = 0;
                  for (int x = 0; x < count; x++) {
                    pos = encontrarPrimeiroDigito(exp, pos);
                    String num = exp.substring(pos, ++pos);
                    p[x] = Integer.parseInt(num);
                  }
                  // Avalia a subexpressão e substitui na expressão principal
                  if (exp.charAt(0) == 'a') {
                      String resp = "1";
                        if (count == 1) {
                            resp = String.format("%d", p[0]);
                        } else {
                            for (int i = 0; i < count; i++) {
                              if (p[i] == 0) {
                                resp = "0";
                                break;
                              }
                            }
                        }
                        expressao = expressao.replace(exp, resp);
                    } else if (exp.charAt(0) == 'o') {

                        String resp = "0";

                        if (count == 1) {

                            resp = String.format("%i", p[0]);
                        } else {

                            for (int i = 0; i < count; i++) {

                                if (p[i] == 1) {

                                    resp = "1";
                                    break;
                                }
                            }
                        }

                        expressao = expressao.replace(exp, resp);

                    }
                } else {

                    if (exp.equals("not(0)")) {

                        expressao = expressao.replace("not(0)", "1");
                    } else if (exp.equals("not(1)")) {

                        expressao = expressao.replace("not(1)", "0");
                    }
                }      
            }
        MyIO.println(expressao);
        } 
    } 
}