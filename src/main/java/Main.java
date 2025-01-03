import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Token> tokens = new ArrayList<>();
        String local = System.getProperty("user.dir") + "\\example2.txt";
        FileInputStream file;

        try {
            System.out.println("Abrindo o arquivo...");
            file = new FileInputStream(local);
        } catch (Exception e) { System.err.println("Erro abrindo o arquivo"); return; }

        try {
            System.out.println("Iniciando a Análise Léxica...");
            Token t;
            Scanner l =  new Scanner(file);

            while ( (t = l.lex()) != null)
            {
                if (t.getType() == TokenType.IDENT)
                    System.out.println("IDENTIFICADOR" + "  Value = " + "\""+ t.getValue() + "\"");
                else if (t.getType() == TokenType.INTCONST)
                    System.out.println("CONSTANTE INTEIRA" + "  Value = " + "\""+ t.getValue() + "\"");
                else if (t.getType() == TokenType.MENOR)
                    System.out.println("MENOR" + "  Value = " + "\""+ t.getValue() + "\"");
                else if (t.getType() == TokenType.MENORIGUAL)
                    System.out.println("MENORIGUAL" + "  Value = " + "\""+ t.getValue() + "\"");
                else if (t.getType() == TokenType.MAIOR)
                    System.out.println("MAIOR" + "  Value = " + "\""+ t.getValue() + "\"");
                else if (t.getType() == TokenType.MAIORIGUAL)
                    System.out.println("MAIORIGUAL" + "  Value = " + "\""+ t.getValue() + "\"");
                else if (t.getType() == TokenType.PARABERTO)
                    System.out.println("PARABERTO" + "  Value = " + "\""+ t.getValue() + "\"");
                else if (t.getType() == TokenType.PARFECHADO)
                    System.out.println("PARFECHADO" + "  Value = " + "\""+ t.getValue() + "\"");
                else if (t.getType() == TokenType.CHAVEABERTO)
                    System.out.println("CHAVEABERTO" + "  Value = " + "\""+ t.getValue() + "\"");
                else if (t.getType() == TokenType.CHAVEFECHADO)
                    System.out.println("CHAVEFECHADO" + "  Value = " + "\""+ t.getValue() + "\"");
                else if (t.getType() == TokenType.COLCHEABERTO)
                    System.out.println("COLCHEABERTO" + "  Value = " + "\""+ t.getValue() + "\"");
                else if (t.getType() == TokenType.COLCHEFECHADO)
                    System.out.println("COLCHEFECHADO" + "  Value = " + "\""+ t.getValue() + "\"");
                else if (t.getType() == TokenType.IGUALDADE)
                    System.out.println("IGUALDADE" + "  Value = " + "\""+ t.getValue() + "\"");
                else if (t.getType() == TokenType.DIFERENTEIGUAL)
                    System.out.println("DIFERENTEIGUALDADE" + "  Value = " + "\""+ t.getValue() + "\"");
                else if (t.getType() == TokenType.BARRA)
                    System.out.println("BARRA" + "  Value = " + "\""+ t.getValue() + "\"");
                else if (t.getType() == TokenType.IGUAL)
                    System.out.println("IGUAL" + "  Value = " + "\""+ t.getValue() + "\"");
                else if (t.getType() == TokenType.ASTERISCO)
                    System.out.println("ASTERISCO" + "  Value = " + "\""+ t.getValue() + "\"");
                else if (t.getType() == TokenType.INTTIPO)
                    System.out.println("INTTIPO" + "  Value = " + "\""+ t.getValue() + "\"");
                else if (t.getType() == TokenType.VOIDTIPO)
                    System.out.println("VOIDTIPO" + "  Value = " + "\""+ t.getValue() + "\"");
                else if (t.getType() == TokenType.WHILE)
                    System.out.println("WHILE" + "  Value = " + "\""+ t.getValue() + "\"");
                else if (t.getType() == TokenType.IF)
                    System.out.println("IF" + "  Value = " + "\""+ t.getValue() + "\"");
                else if (t.getType() == TokenType.ELSE)
                    System.out.println("ELSE" + "  Value = " + "\""+ t.getValue() + "\"");
                else if (t.getType() == TokenType.RETURN)
                    System.out.println("RETURN" + "  Value = " + "\""+ t.getValue() + "\"");
                else if (t.getType() == TokenType.VIRGULA)
                    System.out.println("VIRGULA" + "  Value = " + "\""+ t.getValue() + "\"");
                else if (t.getType() == TokenType.PONTOVIRGULA)
                    System.out.println("PONTOVIRGULA" + "  Value = " + "\""+ t.getValue() + "\"");
                else if (t.getType() == TokenType.SIMBOLOMAIS)
                    System.out.println("SIMBOLOMAIS" + "  Value = " + "\""+ t.getValue() + "\"");
                else if (t.getType() == TokenType.SIMBOLOMENOS)
                    System.out.println("SIMBOLOMENOS" + "  Value = " + "\""+ t.getValue() + "\"");
                else if (t.getType() == TokenType.PONTO)
                {
                    System.out.println("PONTO" + "  Value = " + "\""+ t.getValue() + "\"");
                    break;
                }
                tokens.add(t);
            }
            System.out.println("Análise Léxica Finalizada");
        } catch (Exception e) { System.err.println(e.getMessage()); return;}
        try {
            System.out.println("Iniciando a Análise Sintática...");
            Parser parser = new Parser(tokens);
            parser.parse();
            System.out.println("Análise Sintática Finalizada");
        } catch (Exception e) { System.err.println(e.getMessage()); }
    }
}