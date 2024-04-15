class AlgebraBooleanaRec {
    
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

  // Função recursiva para resolver a expressão booleana
  public static int resolverExpressao(String expressao) {
    int last = encontrarUltimaOperacao(expressao);

    if (last == 0) {
      // Se não houver mais operações, a expressão está resolvida
      return Integer.parseInt(expressao);
    } else {
      // Isola a subexpressão a ser avaliada
      String exp = expressao.substring(last, expressao.indexOf(")", last) + 1);

      if (exp.charAt(0) != 'n') {
        int count = 1;
        for (int x = 0; x < exp.length(); x++) {
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

        // Avalia a subexpressão
        if (exp.charAt(0) == 'a') {
          for (int i = 0; i < count; i++) {
            if (p[i] == 0) {
              return 0;
            }
          }
          return 1;
        } else if (exp.charAt(0) == 'o') {
          for (int i = 0; i < count; i++) {
            if (p[i] == 1) {
              return 1;
            }
          }
          return 0;
        }
      } else {
        // Trata a operação "not"
        if (exp.equals("not(0)")) {
          return 1;
        } else if (exp.equals("not(1)")) {
          return 0;
        }
      }

      // Chama recursivamente a função com a expressao modificada
      expressao = expressao.replace(exp, Integer.toString(resolverExpressao(exp)));
      return resolverExpressao(expressao);
    }
  }

  public static void main(String[] args) {
    while (true) {
      int N = MyIO.readInt();
      if (N == 0) {
        break;
      }

      int[] array = new int[N];
      for (int i = 0; i < N; i++) {
        array[i] = MyIO.readInt();
      }

      String expressao = MyIO.readLine();

      if (expressao.charAt(expressao.length() - 1) == ' ') {
        expressao = expressao.substring(0, expressao.length() - 1);
      }

      for (int i = 0; i < N; i++) {
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

      // Chama a função recursiva para resolver a expressão
      int resultado = resolverExpressao(expressao);

      MyIO.println(resultado);
    } 
  } 
}
