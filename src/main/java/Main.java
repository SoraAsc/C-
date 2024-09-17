import java.io.FileInputStream;

public class Main {

    public static void main(String[] args) {
        try{
//            FileInputStream file = new FileInputStream("C:/compilers/example1.txt");
            String local = System.getProperty("user.dir") + "\\example1.txt";
            FileInputStream file = new FileInputStream(local);
            Token t;

            Scanner l =  new Scanner(file);
            while ( (t = l.lex()) != null)
            {
                if (t.getType() == TokenType.IDENT)
                    System.out.println("IDENTIFICADOR" + "  Value= " + t.getValue());
                else if (t.getType() == TokenType.INTCONST)
                    System.out.println("CONSTANTE INTEIRA" + "Value= " + t.getValue());
                else if (t.getType() == TokenType.MENOR)
                    System.out.println("MENOR" + "  Value= " + t.getValue());
                else if (t.getType() == TokenType.MENORIGUAL)
                    System.out.println("MENORIGUAL" + "  Value= " + t.getValue());
                else if (t.getType() == TokenType.MAIOR)
                    System.out.println("MAIOR" + " Value= " + t.getValue());
                else if (t.getType() == TokenType.MAIORIGUAL)
                    System.out.println("MAIORIGUAL" + " Value=" + t.getValue());
                else if (t.getType() == TokenType.PARABERTO)
                    System.out.println("PARABERTO" + "  Value= " + t.getValue());
                else if (t.getType() == TokenType.PARFECHADO)
                    System.out.println("PARFECHADO" + "  Value= " + t.getValue());
                else if (t.getType() == TokenType.COLCHEABERTO)
                    System.out.println("COLCHEABERTO" + "  Value= " + t.getValue());
                else if (t.getType() == TokenType.COLCHEFECHADO)
                    System.out.println("COLCHEFECHADO" + "  Value= " + t.getValue());
                else if (t.getType() == TokenType.IGUALDADE)
                    System.out.println("IGUALDADE" + "  Value= " + t.getValue());
                else if (t.getType() == TokenType.DIFERENTEIGUAL)
                    System.out.println("DIFERENTEIGUALDADE" + "  Value= " + t.getValue());
                else if (t.getType() == TokenType.PONTO)
                {
                    System.out.println("PONTO" + " Value= " + t.getValue());
                    break;
                }
            }
        }
        catch (Exception e) { System.out.println("Erro abrindo o arquivo"); }
    }
}